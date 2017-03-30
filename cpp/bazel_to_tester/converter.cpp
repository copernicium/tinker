#include "converter.h"
#include "../util/util.h"
#include <fstream>
#include "../util/nyi.h"

using namespace std;

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
			if(c == '\n' || c == '\r' || c == '\t' || c == ',' || c == ' ' || c == '[' || c == ']' || c == ':') continue;//ignore certain characters
			line += c;
		}
		if(line != ""){
			if(line.size() >= 1 && line[0] == '#'){
				continue;
			}
			lines.push_back(line);
		}
	}
	f.close();
	return lines;
}

vector<string> split(string const& STRING,char BREAK){//TODO move to util?
	vector<string> r;
	string s;
	for(char c: STRING){
		if(c==BREAK){
			r.push_back(s);
			s = "";
		} else{
			s += c;
		}
	}
	if(s.size() > 0) r.push_back(s);
	return r;
}

Rule::Rule():name(""),srcs({}),copts({}),deps({}),timeout(""){}

const string Rule::NAME = "name=";
const string Rule::TIMEOUT = "timeout=";
const string Rule::SRCS = "srcs=";
const string Rule::COPTS = "copts=";
const string Rule::DEPS = "deps=";
const string Rule::HEADING = "cc_test(";

std::ostream& operator<<(std::ostream& o,Rule const& a){
	o<<"Rule(";
	#define X(A,NAME,C) o<<" "#NAME":"<<a.NAME;
	RULE_ITEMS(X)
	#undef X
	o<<")";
	return o;
}

Rule Rule::parse(string const& FILENAME){
	return Rule::parse(read(FILENAME));
}


void parse(string const& ATTRIBUTE_NAME, string const& LINE, vector<string>& instance_variable){
	if(LINE.find(ATTRIBUTE_NAME) != std::string::npos){
		vector<string> parts = split(LINE,'\"'); 
		for(string part: parts){  
			if(part == ATTRIBUTE_NAME || part == "") continue; 
			instance_variable.push_back(part); 
		} 
	}
}

void parse(string const& ATTRIBUTE_NAME, string const& LINE, string& instance_variable){
	if(LINE.find(ATTRIBUTE_NAME) != std::string::npos){ 
		vector<string> parts = split(LINE,'\"'); 
		for(string part: parts){ 
			if(part == ATTRIBUTE_NAME || part == "") continue; 
			instance_variable = part;
		}
	} 
}

#define PARSE_ITEM(A,INSTANCE_VARIABLE,ATTRIBUTE_NAME) ::parse(ATTRIBUTE_NAME,line,parseable.INSTANCE_VARIABLE); 	

Rule Rule::parse(vector<string> const& LINES){//TODO take advantage of Maybe?
	Rule rule;
	{
		Rule parseable;		
		for(string line: LINES){ 
			if(line == "") continue; 		
			RULE_ITEMS(PARSE_ITEM) 
		}
		
		rule = parseable;
	}
	{//remove header files from sources
		vector<string> new_srcs;
		static const string HEADER_EXTENSION = ".h";
		for(string src: rule.srcs){
			if(src.size() >= HEADER_EXTENSION.size()){
				if(src.substr(src.size() - HEADER_EXTENSION.size()) != HEADER_EXTENSION){
					new_srcs.push_back(src);
				}
			}
		}
		rule.srcs = new_srcs;
	}
	return rule;
}

Library Library::parse(vector<string> const& LINES){//TODO take advantage of Maybe?
	Library parseable;		
	for(string line: LINES){ 
		if(line == "") continue; 		
		LIBRARY_ITEMS(PARSE_ITEM) 
	}
	
	return parseable;
}

#undef PARSE_ITEM

std::string Rule::get_name()const{
	return name;
}

void Rule::make_test()const{
	make_test("");
}

void Rule::make_test(std::string const& PATH)const{
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
	string out = [&]{
	//make the out file name
		static const string TEST_EXTENSION = "_test";
		if(name.size() >= TEST_EXTENSION.size()){
			return name.substr(0, name.size() - TEST_EXTENSION.size());
		}
		NYI
	}();
	o<<"\n\t-o "<<out<<" 2>&1 && ./"<<out;
	{ 
		const string MAKE_EXECUTABLE = "chmod +x " + PATH + name;
		cout<<"\nPAHT+name=\""<<PATH<<name<<"\"\n";
		const char* SYSTEM_ARG = MAKE_EXECUTABLE.c_str();
		system(SYSTEM_ARG);
	}
}

Library::Library():name(""),srcs({}),hdrs({}),deps({}){}

const string Library::NAME = Rule::NAME;
const string Library::SRCS = Rule::SRCS;
const string Library::DEPS = Rule::DEPS;
const string Library::HDRS = "hdrs=";
const string Library::HEADING = "cc_library(";

ostream& operator<<(ostream& o,Library const& a){
	o<<"Library(";
	#define X(A,NAME,C) o<<" "#NAME":"<<a.NAME;
	LIBRARY_ITEMS(X)
	#undef X
	o<<")";
	return o;
}

string Library::get_name()const{
	return name;
}

Library Library::parse(string const& FILENAME){
	return Library::parse(read(FILENAME));
}

Project::Project():rules({}){}

const string Project::SOURCE = "BUILD";

std::ostream& operator<<(std::ostream& o,Project const& a){
	o<<"Project(";
	vector<string> rule_names;
	for(Rule rule: a.rules){
		rule_names.push_back(rule.get_name());
	}
	o<<"rule_names:"<<rule_names;
	vector<string> library_names;
	for(Library library: a.libraries){
		library_names.push_back(library.get_name());
	}
	o<<" library_names:"<<library_names;
	o<<")";
	return o;
}

vector<vector<string>> Project::read(string const& PATH, string const& HEADING){
	static const string ENDING = ")";
	vector<string> lines = ::read(PATH + SOURCE);
	vector<vector<string>> rules;
	
	vector<string> rule;
	bool collect = false;
	for(unsigned i = 0; i < lines.size(); i++){
		string line = lines[i];
		if(line == HEADING || i == lines.size() - 1){
			if(rule.size() > 0){//store the rule and reset
				rules.push_back(rule);
				rule = {};
			}
			collect = true;
		}
		if(collect){
			rule.push_back(line);
		}
		if(line == ENDING){
			collect = false;
		}
	}
	cout<<"rules:"<<rules<<"\n";
	return rules;
}

void Project::import(){
	import("");
}

void Project::import(string const& PATH){
	for(vector<string> rule: Project::read(PATH,Rule::HEADING)){
		rules.push_back(Rule::parse(rule));
	}
	for(vector<string> library: Project::read(PATH,Library::HEADING)){
		libraries.push_back(Library::parse(library));
	}
}

void Project::make_tests()const{
	make_tests("");
}

void Project::make_tests(std::string const& PATH)const{
	for(Rule a: rules){
		a.make_test(PATH);
	}
}

int main(){
	{
		Rule a = Rule::parse("test1/test");
		cout<<"Test1\n"<<a<<"\n";
		a.make_test("test1/");
		cout<<"\n";
	}
	{
		cout<<"Test2\n";
		Project a;
		a.import("test2/");
		cout<<a<<"\n";
		a.make_tests("test2/");
		cout<<"\n";
	}
	{
		cout<<"Test3\n";
		Project a;
		a.import("test3/");
		cout<<a<<"\n";
		a.make_tests("test3/");
		cout<<"\n";
	}
}
