#include "converter.h"
#include "../util/util.h"
#include <sstream>
#include <fstream>

using namespace std;

Build_rule::Build_rule():name(""),srcs({}),copts({}),deps({}),timeout(""){}

std::ostream& operator<<(std::ostream& o,Build_rule const& a){
	o<<"Build_rule(";
	o<<"name:"<<a.name;
	o<<" out:"<<a.out;
	o<<" srcs:"<<a.srcs;
	o<<" copts:"<<a.copts;
	o<<" deps:"<<a.deps;
	o<<" timeout:"<<a.timeout;
	o<<")";
	return o;
}

vector<string> read(string const& FILENAME){
	vector<string> lines;
	ifstream f(FILENAME);
	if(!f.good()){
		cerr<<"Error: could not open file\n";
		return lines;
	}
	while(!f.eof()){
		string original = "";
		getline(f,original);
		string line = "";
		for(char c: original){
			if(c == '\n' || c == '\r' || c == '\t' || c == ',' || c == ' ' || c == '[' || c == ']') continue;//ignore certain characters
			line += c;
		}
		if(line != "") lines.push_back(line);
	}
	f.close();
	return lines;
}

vector<string> split(string const& STRING,char BREAK){//TODO move to util
	vector<string> r;
	stringstream ss;//TODO find out is stringstream is needed
	for(char c: STRING){
		if(c==BREAK){
			r.push_back(ss.str());
			ss.str("");
		} else{
			ss<<c;
		}
	}
	if(ss.str().size()) r.push_back(ss.str());
	return r;
}

Build_rule Build_rule::parse(string const& FILENAME){//TODO take advantage of Maybe?
	Build_rule build_rule;
	vector<string> lines = read(FILENAME);
	const string NAME = "name=", TIMEOUT = "timeout=", SRCS = "srcs=", COPTS = "copts=", DEPS = "deps=";
	#define SINGLE(ATTRIBUTE_NAME, INSTANCE_VARIABLE) \
		if(line.find(ATTRIBUTE_NAME) != std::string::npos){ \
			vector<string> parts = split(line,'\"'); \
			for(string part: parts){ \
				if(part == ATTRIBUTE_NAME || part == "") continue; \
				build_rule.INSTANCE_VARIABLE = part; \
			} \
		} 
	#define MULTIPLE(ATTRIBUTE_NAME, INSTANCE_VARIABLE) \
		if(line.find(ATTRIBUTE_NAME) != std::string::npos){ \
			vector<string> parts = split(line,'\"'); \
			for(string part: parts){  \
				if(part == ATTRIBUTE_NAME || part == "") continue; \
				build_rule.INSTANCE_VARIABLE.push_back(part); \
			} \
		} 
	
	for(string line: lines){
		if(line == "") continue;
		SINGLE(NAME,name)
		SINGLE(TIMEOUT,timeout)
		MULTIPLE(SRCS,srcs)
		MULTIPLE(COPTS,copts)
		MULTIPLE(DEPS,deps)
		
		cout<<"\""<<line<<"\"\n";
	}
	#undef SINGLE
	#undef MULTIPLE
	{
		static const string TEST_EXTENSION = "_test";
		if(build_rule.name.size() >= TEST_EXTENSION.size()){
			build_rule.out = build_rule.name.substr(0, build_rule.name.size() - TEST_EXTENSION.size());
		}
	}
	{//remove header files from sources
		vector<string> new_srcs;
		static const string HEADER_EXTENSION = ".h";
		for(string src: build_rule.srcs){
			if(src.size() >= HEADER_EXTENSION.size()){
				if(src.substr(src.size() - HEADER_EXTENSION.size()) != HEADER_EXTENSION){
					new_srcs.push_back(src);
				}
			}
		}
		build_rule.srcs = new_srcs;
	}
	return build_rule;
}

void Build_rule::make_test()const{
	make_test("");
}

void Build_rule::make_test(std::string const& PATH)const{
	// g++ -DPOSEDGE_TRIGGER_TEST posedge_trigger.cpp -o posedge_trigger
	//./posedge_trigger
	ofstream o(PATH + name);
	o<<"g++ -std=c++14 ";
	//TODO: manage deps
	for(string copt: copts){
		o<<copt<<" ";
	}
	o<<" \\\n";
	for(string src: srcs){
		o<<"\t"<<src<<" \\";
	}
	o<<"\n\t-o "<<out<<" 2>&1 && ./"<<out;
	{ 
		static const string MAKE_EXECUTABLE = "chmod +x " + PATH + name;
		const char* SYSTEM_ARG = MAKE_EXECUTABLE.c_str();
		system(SYSTEM_ARG);
	}
}

int main(){
	Build_rule a = Build_rule::parse("test/test");
	cout<<a<<"\n";
	a.make_test("test/");
}
