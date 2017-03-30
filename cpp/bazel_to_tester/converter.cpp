#include "converter.h"
#include "../util/util.h"
#include <fstream>
#include "../util/nyi.h"

using namespace std;

static const string ENDING = ")";

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
const string Rule::COMPILER = "g++";
const vector<string> Rule::CARGS = {"-std=c++14","-Wall","-Werror"};

std::ostream& operator<<(std::ostream& o,Rule const& a){
	o<<"Rule(";
	#define X(A,NAME,C) o<<" "#NAME":"<<a.NAME;
	RULE_ITEMS(X)
	#undef X
	o<<")";
	return o;
}

Maybe<Rule> Rule::find(string const& NAME,vector<Rule> const& RULES){
	for(Rule rule: RULES){
		if(rule.get_name() == NAME) return Maybe<Rule>(rule);
	}
	cerr<<"Failed to find rule of matching name\n";
	return Maybe<Rule>();
}

std::string Rule::get_name()const{
	return name;
}

vector<string> merge(vector<string> const& A, vector<string> const& B){
	auto unique = [&](string const& NEW,vector<string> const& EXISTING){ 
			for(string a: EXISTING){
				if(a == NEW) return false;
			}
			return true;
		};
	vector<string> sum;
	for(string a: A){
		if(unique(a,sum)) sum.push_back(a);
	}
	for(string a: B){
		if(unique(a,sum)) sum.push_back(a);			
	}
	return sum;
}

Maybe<Rule> Rule::integrate_deps(Rule const& RULE,vector<Library> const& LIBRARIES){//TODO: manage deps
	if(RULE.deps.empty()) return Maybe<Rule>(RULE);
	Maybe<Rule> rule;
	vector<Library> dependencies;
	for(string dep: RULE.deps){
		Maybe<Library> library = Library::find(dep,LIBRARIES);
		if(!library){
			cerr<<"Cannot handle dep \""<<dep<<"\" - Library not found\n";
		} else{
			Maybe<Library> l = Library::integrate_deps(*library,LIBRARIES);
			if(!l){
				cerr<<"Cannot handle dep \""<<dep<<"\" - Library not found\n";
			} else{
				dependencies.push_back(*l);
			}
		}
	}
	
	vector<string> dependencies_srcs = [&]{
		vector<string> v;
		for(Library library: dependencies){
			for(string src: library.get_srcs()){
				v.push_back(src);
			}
		}
		return v;
	}();
	
	vector<string> new_srcs = merge(RULE.srcs, dependencies_srcs);
	
	rule = RULE;
	(*rule).srcs = new_srcs;
	
	return rule;
}

void Rule::make_test()const{
	make_test("");
}

void Rule::make_test(std::string const& PATH)const{
	make_test(PATH,{});
}

void Rule::make_test(string const& PATH,vector<Library> const& LIBRARIES)const{
	Rule rule = *this;
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
	cout<<"\nRule:"<<rule<<"\n";
	{//manage deps
		Maybe<Rule> rule_with_deps = Rule::integrate_deps(rule,LIBRARIES);
		if(!rule_with_deps){
			cerr<<"Unable to handle dependencies\n";
		} else{
			rule = *rule_with_deps;
		}
	}
	{
		rule.copts = merge(rule.copts,Rule::CARGS);
	}
	ofstream o(PATH + rule.name);
	o<<Rule::COMPILER<<" ";
	for(string copt: rule.copts){
		o<<copt<<" ";
	}
	o<<" \\\n";
	for(unsigned i =0; i < rule.srcs.size(); i++){
		string src = rule.srcs[i];
		o<<"\t"<<src<<" \\";
		if(i < rule.srcs.size() - 1) o<<"\n";
	}
	string out = [&]{
	//make the out file name
		static const string TEST_EXTENSION = "_test";
		if(rule.name.size() >= TEST_EXTENSION.size()){
			return rule.name.substr(0, rule.name.size() - TEST_EXTENSION.size());
		}
		NYI
	}();
	o<<"\n\t-o "<<out<<" 2>&1 && ./"<<out;
	{ 
		const string MAKE_EXECUTABLE = "chmod +x " + PATH + rule.name;
		const char* SYSTEM_ARG = MAKE_EXECUTABLE.c_str();
		system(SYSTEM_ARG);
	}
}

Maybe<Rule> Rule::parse(string const& FILENAME){
	return Rule::parse(read(FILENAME));
}

void parse(string const& ATTRIBUTE_NAME, string const& LINE, vector<string>& instance_variable){
	if(LINE.find(ATTRIBUTE_NAME) != string::npos){
		vector<string> parts = split(LINE,'\"'); 
		for(string part: parts){  
			if(part == ATTRIBUTE_NAME || part == "") continue; 
			instance_variable.push_back(part); 
		} 
	}
}

void parse(string const& ATTRIBUTE_NAME, string const& LINE, string& instance_variable){
	if(LINE.find(ATTRIBUTE_NAME) != string::npos){ 
		vector<string> parts = split(LINE,'\"'); 
		for(string part: parts){ 
			if(part == ATTRIBUTE_NAME || part == "") continue; 
			instance_variable = part;
		}
	} 
}

vector<string> trim_lines(vector<string> const& LINES,string const& HEADING){
	vector<string> trimmed_lines;
	{
		bool found = false;
		for(string line: LINES){ 
			if(line == "") continue; 
			if(line.find(HEADING) != string::npos){
				found = true;
			}
			if(found){
				trimmed_lines.push_back(line);
			}
			if(found && line.find(ENDING) != string::npos){
				break;
			}
		}
		if(!found) return {};
	}
	return trimmed_lines;
}

#define PARSE_ITEM(A,INSTANCE_VARIABLE,ATTRIBUTE_NAME) ::parse(ATTRIBUTE_NAME,line,parseable.INSTANCE_VARIABLE); 	

Maybe<Rule> Rule::parse(vector<string> const& LINES){
	vector<string> rule_lines = trim_lines(LINES,Rule::HEADING);
	if(rule_lines.empty()) return Maybe<Rule>();
	Maybe<Rule> rule;
	{//parse the chunk of lines 
		Rule parseable;		
		for(string line: rule_lines){ 
			if(line == "") continue; 
			RULE_ITEMS(PARSE_ITEM) 
		}
		
		rule = parseable;
	}
	return rule;
}

Maybe<Library> Library::parse(vector<string> const& LINES){
	vector<string> library_lines = trim_lines(LINES,Library::HEADING);
	if(library_lines.empty()) return Maybe<Library>();
	Maybe<Library> library;	
	{
		Library parseable;
		for(string line: library_lines){ //parse the lines
			if(line == "") continue; 
			LIBRARY_ITEMS(PARSE_ITEM) 
		}
		library = parseable;
	}
	return library;
}

#undef PARSE_ITEM

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

Maybe<Library> Library::find(string const& NAME,vector<Library> const& LIBRARIES){
	for(Library library: LIBRARIES){
		if(library.get_name() == NAME) return Maybe<Library>(library);
	}
	cerr<<"Failed to find library of matching name\n";
	return Maybe<Library>();
}

Maybe<Library> Library::integrate_deps(Library const& LIBRARY,vector<Library> const& LIBRARIES){//TODO: manage deps
	if(LIBRARY.deps.empty()) return Maybe<Library>(LIBRARY);
	Maybe<Library> library;
	vector<Library> dependencies;
	for(string dep: LIBRARY.deps){
		Maybe<Library> l = Library::find(dep,LIBRARIES);
		if(!l){
			cerr<<"Cannot handle dep \""<<dep<<"\" - Library not found\n";
		} else{
			dependencies.push_back(*l);
		}
	}
	
	vector<string> dependencies_srcs = [&]{
		vector<string> v;
		for(Library l: dependencies){
			for(string src: l.get_srcs()){
				v.push_back(src);
			}
		}
		return v;
	}();
	
	vector<string> new_srcs = merge(LIBRARY.srcs,dependencies_srcs);
	
	library = LIBRARY;
	(*library).srcs = new_srcs;
	
	return library;
}


vector<string> Library::get_srcs()const{
	return srcs;
}

string Library::get_name()const{
	return name;
}

Maybe<Library> Library::parse(string const& FILENAME){
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
	return rules;
}

void Project::import(){
	import("");
}

void Project::import(string const& PATH){
	for(vector<string> rule_str: Project::read(PATH,Rule::HEADING)){
		Maybe<Rule> rule = Rule::parse(rule_str);
		if(!rule){
			cerr<<"Error: failed to parse rule\n";
		} else{
			rules.push_back(*rule);
		}
	}
	for(vector<string> library_str: Project::read(PATH,Library::HEADING)){
		Maybe<Library> library = Library::parse(library_str);
		if(!library){
			cerr<<"Error: failed to parse library\n";
		} else{
			libraries.push_back(*library);
		}
	}
}

void Project::make_tests()const{
	make_tests("");
}

void Project::make_tests(std::string const& PATH)const{
	for(Rule a: rules){
		a.make_test(PATH,libraries);
	}
}

int main(){
	{
		cout<<"Test 1 - Parsing a Rule out of a file containging one Rule and one Rule only\n";
		Rule a = *Rule::parse("test1/test");
		cout<<a<<"\n";
		a.make_test("test1/");
		cout<<"\n";
	}
	{
		cout<<"Test 2 - Parsing a Project out of a BUILD file containging only rules with no deps\n";
		Project a;
		a.import("test2/");
		cout<<a<<"\n";
		a.make_tests("test2/");
		cout<<"\n";
	}
	{
		cout<<"Test 3.1 - Parsing a single Library out of a BUILD file with both rules and libraries\n";
		Library a = *Library::parse("test3/BUILD");
		cout<<a<<"\n";
		cout<<"\n";
	}
	{
		cout<<"Test 3.2 - Parsing a Project out of a BUILD file with Rules, Libraries, and deps\n";
		Project a;
		a.import("test3/");
		cout<<a<<"\n";
		a.make_tests("test3/");
		cout<<"\n";
	}
	{
		cout<<"Test 4 - Parsing chainsaw's BUILD file\n";
		Project a;
		a.import("test4/");
		cout<<a<<"\n";
		a.make_tests("test4/");
		cout<<"\n";
	}
}
