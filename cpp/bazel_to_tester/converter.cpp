#include "converter.h"
#include "../util/util.h"
#include <fstream>
#include "../util/nyi.h"

using namespace std;

static const string ENDING = ")";

void run_command(string const& EXECUTABLE){ //move to util?
	const char* SYSTEM_ARG = EXECUTABLE.c_str();
	system(SYSTEM_ARG);
}

vector<string> read(string const& FILENAME){
	vector<string> lines;
	ifstream f(FILENAME);
	if(!f.good()){
		cerr<<"Error: could not open file \""<<FILENAME<<"\"\n";
		return lines;
	}
	while(!f.eof()){
		string original = "";
		getline(f,original);
		string line = "";
		for(char c: original){
			if(c == '\n' || c == '\r' || c == '\t' || c == ',' || c == ' ' || c == ':') continue;//ignore certain characters
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

ostream& operator<<(ostream& o,Rule const& a){
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

string Rule::get_name()const{
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

Maybe<Rule> Rule::integrate_deps(Rule const& RULE,vector<Library> const& LIBRARIES){
	if(RULE.deps.empty()) return Maybe<Rule>(RULE);
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
	

	vector<string> dependent_copts = [&]{
		vector<string> v;
		for(Library library: dependencies){
			for(string copt: library.get_linkopts()){
				v.push_back(copt);
			}
		}
		return v;
	}();
	
	vector<string> new_srcs = merge(RULE.srcs, dependencies_srcs);
	vector<string> new_copts = merge(RULE.copts, dependent_copts);
	
	Maybe<Rule> rule = Maybe<Rule>(RULE);
	(*rule).srcs = new_srcs;
	(*rule).copts = new_copts;
	
	return rule;
}

void Rule::make_test(string const& PATH)const{
	make_test(PATH,"",{});
}

void Rule::make_test(string const& PATH,string const& ABS_PATH, vector<Library> const& LIBRARIES)const{
	string file_out;
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
	file_out += Rule::COMPILER + " ";
	for(string copt: rule.copts){
		file_out += copt + " ";
	}
	file_out += " \\\n";
	for(unsigned i =0; i < rule.srcs.size(); i++){
		string src = rule.srcs[i];
		file_out += "\t" + ABS_PATH + src + " \\";
		if(i < rule.srcs.size() - 1) file_out += "\n";
	}
	string out = [&]{
	//make the out file name
		static const string TEST_EXTENSION = "_test";
		if(rule.name.size() >= TEST_EXTENSION.size()){
			return rule.name.substr(0, rule.name.size() - TEST_EXTENSION.size());
		}
		NYI
	}();
	file_out += "\n\t-o "+ out + " 2>&1 && ./" + out;
	ofstream o(PATH + rule.name);
	o<<file_out;
	{ 
		static const string COMMAND = "chmod +x";
		const string EXECUTABLE = COMMAND + " " + PATH + rule.name;
		run_command(EXECUTABLE);
	}
}

Maybe<Rule> Rule::parse(string const& FILENAME){
	return Rule::parse(read(FILENAME));
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
				if(line.find(ENDING) != string::npos) break;
			}
		}
		if(!found) return {};
	}
	return trimmed_lines;
}

bool exists(string const& S,vector<string> const& LINES){
	bool exists = false;
	for(string a: LINES){
		if(a.find(S) != string::npos){
			exists = true;
			break;
		}
	}
	return exists;
}

string get_from(string const& START, string const& END, vector<string> const& LINES){
	string s;
	for(string line: LINES){
		if(line.find(START) != string::npos){
			string end = line.substr(line.find(START) + START.size());
			if(end.find(END) != string::npos){
				return end.substr(0,end.find(END));
			} else {
				
				return end;
			}
		}
	}
	return s;
}


string get_from(string const& START, char const& START_SECTION, char const& END_SECTION, vector<string> const& LINES){
	string s;
	bool collect = false, start_search = false;
	for(string line: LINES){
		if(line.find(START) != string::npos){
			start_search = true;
		}
		if(start_search){
			for(char c: line){
				if(collect && c == END_SECTION){
					return s;
				}
				if(collect) s += c;
				if(c == START_SECTION){
					collect = true;
				} 
			}
		}
	}	
	return s;
}

void parse(string const& ATTRIBUTE_NAME, vector<string> const& LINES, vector<string>& instance_variable){
	if(!exists(ATTRIBUTE_NAME,LINES)) return;
	static const char START_SECTION = '[', END_SECTION = ']';
	string attribute_specific = get_from(ATTRIBUTE_NAME,START_SECTION,END_SECTION,LINES);
	
	for(string part: split(attribute_specific,'\"')){  
		if(part == "") continue; 
		instance_variable.push_back(part); 
	} 
}

void parse(string const& ATTRIBUTE_NAME, vector<string> const& LINES, string& instance_variable){
	if(!exists(ATTRIBUTE_NAME,LINES)) return;
	static const char START_SECTION = '\"', END_SECTION = '\"';
	instance_variable = get_from(ATTRIBUTE_NAME,START_SECTION,END_SECTION,LINES);
}

#define PARSE_ITEM(A,INSTANCE_VARIABLE,ATTRIBUTE_NAME) ::parse(ATTRIBUTE_NAME,lines,parseable.INSTANCE_VARIABLE); 	

Maybe<Rule> Rule::parse(vector<string> const& LINES){
	vector<string> lines = trim_lines(LINES,Rule::HEADING);
	if(lines.empty()) return Maybe<Rule>();
	Maybe<Rule> rule;
	{//parse the chunk of lines 
		Rule parseable;		
		RULE_ITEMS(PARSE_ITEM) 	
		rule = parseable;
	}
	return rule;
}

Maybe<Library> Library::parse(vector<string> const& LINES){
	vector<string> lines = trim_lines(LINES,Library::HEADING);
	if(lines.empty()) return Maybe<Library>();
	Maybe<Library> library;	
	{
		Library parseable;
		LIBRARY_ITEMS(PARSE_ITEM) 
		library = parseable;
	}
	return library;
}

#undef PARSE_ITEM

Library::Library():name(""),srcs({}),hdrs({}),deps({}),linkopts({}){}

const string Library::NAME = Rule::NAME;
const string Library::SRCS = Rule::SRCS;
const string Library::DEPS = Rule::DEPS;
const string Library::HDRS = "hdrs=";
const string Library::LINKOPTS = "linkopts=";
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

Maybe<Library> Library::integrate_deps(Library const& LIBRARY,vector<Library> const& LIBRARIES){
	if(LIBRARY.deps.empty()) return Maybe<Library>(LIBRARY);
	vector<Library> dependencies;
	for(string dep: LIBRARY.deps){
		Maybe<Library> library = Library::find(dep,LIBRARIES);
		if(!library){
			cerr<<"Cannot handle dep \""<<dep<<"\" - Library not found\n";
		} else{
			library = Library::integrate_deps(*library,LIBRARIES);	
			dependencies.push_back(*library);
			
		}
	}

	vector<string> dependencies_srcs = [&]{
		vector<string> v;
		for(Library l: dependencies){
			for(string src: l.srcs){
				v.push_back(src);
			}
		}
		return v;
	}();

	vector<string> dependencies_linkopts = [&]{
		vector<string> v;
		for(Library l: dependencies){
			for(string linkopt: l.linkopts){
				v.push_back(linkopt);
			}
		}
		return v;
	}();
	
	vector<string> new_srcs = merge(LIBRARY.srcs,dependencies_srcs);
	vector<string> new_linkopts = merge(LIBRARY.linkopts,dependencies_linkopts);
	Maybe<Library> library = Maybe<Library>(LIBRARY);
	(*library).srcs = new_srcs;
	(*library).linkopts = new_linkopts;
	
	return library;
}

vector<string> Library::get_linkopts()const{
	return linkopts;
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

Project::Project(string const& s,string const& op):source(s),output_path(op),rules({}),libraries({}){}
Project::Project():source("BUILD"),output_path(""),rules({}),libraries({}){}

ostream& operator<<(ostream& o,Project const& a){
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

string Project::all_to_string()const{
	string s;
	s += "Project(";
	s += "Rules(";
	for(Rule rule: rules){
		s += "[" + as_string(rule) + "] ";
	}
	s += ") ";
	s += " Libraries(";
	for(Library library: libraries){
		s += "[" + as_string(library) + "] ";
	}
	s += ")";
	s += ")";
	return s;
}

string pop_filename(string const& PATH){
	string p;
	static const char DIR_MARKER = '/';
	if(PATH.rfind(DIR_MARKER) != string::npos){
		return PATH.substr(0,min(PATH.rfind(DIR_MARKER) + 1, PATH.size()));
	}
	return PATH;
}

vector<vector<string>> Project::read(string const& HEADING){
	vector<string> lines = ::read(source);
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
	for(vector<string> rule_str: read(Rule::HEADING)){
		Maybe<Rule> rule = Rule::parse(rule_str);
		if(!rule){
			cerr<<"Error: failed to parse rule\n";
		} else{
			rules.push_back(*rule);
		}
	}
	for(vector<string> library_str: read(Library::HEADING)){
		Maybe<Library> library = Library::parse(library_str);
		if(!library){
			cerr<<"Error: failed to parse library\n";
		} else{
			libraries.push_back(*library);
		}
	}
}

void Project::make_tests()const{
	if(output_path != ""){
		static const string COMMAND = "mkdir -p"; 
		const string EXECUTABLE = COMMAND + " " + output_path;
		run_command(EXECUTABLE);
	}
	for(Rule a: rules){
		a.make_test(output_path,pop_filename(source),libraries);
	}
}

Project Project::parse(unsigned argc, char* argv[]){ 
	// --source=[PATH/FILENAME]  // "BUILD"
	// --out=[PATH] // "tests/"
	// --add-copt //TODO
	Project a;
	string args = [&]{
		string s = "";
		for(unsigned i = 1; i < argc; i++){
			s += (string)argv[i];
		} 
		return s;
	}();
	
	static const string SOURCE_ARG = "--source=", OUT_ARG = "--out=", HELP_ARG = "--help";
	static const string END = "-";

	if(args.find(HELP_ARG) != string::npos){
		string help_msg;
		help_msg += "Usage: convereter [options] \n";
		help_msg += "\n";
		help_msg += "Options: \n";
		help_msg += "        --help                              Show this help message and exit \n";
		help_msg += "        --source=[PATH/FILENAME]            Run converter with a given path to and the name of the BUILD file. If none is given, searches for the BUILD file within the current directory \n";
		help_msg += "        --out=[PATH]                        Specify a directory to store the generated test files. If none is given, stores them with the BUILD file \n";
		cout<<help_msg;
		exit(0);
	}
	
	if(args.find(SOURCE_ARG) != string::npos){
		a.source = make_path_absolute(get_from(SOURCE_ARG,END,{args}));
	} else {
		a.source = "BUILD";
	}
	if(args.find(OUT_ARG) != string::npos){
		a.output_path = make_path_absolute(get_from(OUT_ARG,END,{args}));
	} else {
		a.output_path = "";
	}
	
	a.rules = {};
	a.libraries= {};
	return a;
}

void test(){
	{
		cout<<"Test 1 - Parsing a Rule out of a file containging one Rule and one Rule only\n";
		Rule a = *Rule::parse("test1/test");
		cout<<a<<"\n";
		a.make_test("test1/");
		cout<<"\n";
	}
	{
		cout<<"Test 2 - Parsing a Project out of a BUILD file containging only rules with no deps\n";
		Project a = {"test2/BUILD","test2/"};
		a.import();
		cout<<a<<"\n";
		a.make_tests();
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
		Project a = {"test3/BUILD","test3/"};
		a.import();
		cout<<a<<"\n";
		a.make_tests();
		cout<<"\n";
	}
	{ 
		cout<<"Test 4 - Parsing chainsaw's BUILD file\n";
		Project a = {"test4/BUILD","test4/"};
		a.import();
		cout<<a<<"\n";
		a.make_tests();
		cout<<"\n";
	}
	{
		cout<<"Test 5 - Parsing a Rule that has arrays which occupy multiple lines\n";
		Project a = {"test5/BUILD","test5/"};
		a.import();
		cout<<a.all_to_string()<<"\n";
		a.make_tests();
		cout<<"\n";
	}
	{
		cout<<"Test 6 - Parsing chainsaw\n";
		Project a = {"chainsaw/BUILD","chainsaw/"};
		a.import();
		cout<<a.all_to_string()<<"\n";
		a.make_tests();
		cout<<"\n";
	}
}

int main(int argc, char* argv[]){
	{
		Project project = Project::parse(argc,argv);
		project.import();
		project.make_tests();
	}
	
	//test();
}
