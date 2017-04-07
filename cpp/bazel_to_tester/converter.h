#ifndef CONVERTER_H
#define CONVERTER_H

#include <vector>
#include <iostream>
#include "../util/maybe.h"

struct Library{	
	private:
	#define LIBRARY_ITEMS(X) \
		X(std::string,name,NAME) \
		X(std::vector<std::string>,srcs,SRCS) \
		X(std::vector<std::string>,hdrs,HDRS) \
		X(std::vector<std::string>,deps,DEPS) \
		X(std::vector<std::string>,linkopts,LINKOPTS) 
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) TYPE INSTANCE_VARIABLE;
	LIBRARY_ITEMS(X)
	#undef X
	
	public:
	static const std::string HEADING;
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) static const std::string ATTRIBUTE_NAME;
	LIBRARY_ITEMS(X)
	#undef X
	
	std::string get_name()const;
	std::vector<std::string> get_srcs()const;
	std::vector<std::string> get_linkopts()const;
	static Maybe<Library> integrate_deps(Library const&,std::vector<Library> const&);
	
	static Maybe<Library> parse(std::string const&);
	static Maybe<Library> parse(std::vector<std::string> const&);
	
	static Maybe<Library> find(std::string const&,std::vector<Library> const&);
		
	Library();
	
	friend std::ostream& operator<<(std::ostream&,Library const&);
};

struct Rule{
	private:
	#define RULE_ITEMS(X) \
		X(std::string,name,NAME) \
		X(std::vector<std::string>,srcs,SRCS) \
		X(std::vector<std::string>,copts,COPTS) \
		X(std::vector<std::string>,deps,DEPS) \
		X(std::string,timeout,TIMEOUT)
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) TYPE INSTANCE_VARIABLE;
	RULE_ITEMS(X)
	#undef X
	
	public:
	static const std::string COMPILER;
	static const std::vector<std::string> CARGS;
	static const std::string HEADING;
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) static const std::string ATTRIBUTE_NAME;
	RULE_ITEMS(X)
	#undef X

	std::string get_name()const;
	std::string make_inherited_output_path()const;
	
	static Maybe<Rule> parse(std::string const&);
	static Maybe<Rule> parse(std::vector<std::string> const&);
	static Maybe<Rule> integrate_deps(Rule const&,std::vector<Library> const&);
	
	static Maybe<Rule> find(std::string const&,std::vector<Rule> const&);
	
	void make_test(std::string const&)const;
	void make_test(std::string const&,std::vector<Library> const&)const;
	
	Rule();
	
	friend std::ostream& operator<<(std::ostream&,Rule const&);
};

struct Project{
	public:
	#define OUTPUT_MODES \
		X(OUTPUT_PATH) \
		X(INHERIT_OUTPUT_PATH) \
		X(ERROR) 
	#define X(MODE) MODE,
	enum class Output_mode{OUTPUT_MODES};	
	#undef X
	
	private:
	Output_mode output_mode;
	
	std::string source;
	std::string output_path;
	
	std::vector<Rule> rules;
	std::vector<Library> libraries;
	
	std::vector<std::vector<std::string>> read(std::string const&); 
	
	public:
	std::string all_to_string()const;
	
	void import();
	void make_tests()const;
	
	Project();
	static Project use_path(std::string const&,std::string const&);
	static Project inherit(std::string const&);
	static Project parse(int, char*[]);
	
	friend std::ostream& operator<<(std::ostream&,Project const&);
};

std::ostream& operator<<(std::ostream&,Project::Output_mode const&);

#endif
