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
	
	static Maybe<Library> parse(std::string const&);
	static Maybe<Library> parse(std::vector<std::string> const&);
	static Maybe<Library> integrate_deps(Library const&,std::vector<Library> const&);
	
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
	
	static Maybe<Rule> parse(std::string const&);
	static Maybe<Rule> parse(std::vector<std::string> const&);
	static Maybe<Rule> integrate_deps(Rule const&,std::vector<Library> const&);
	
	static Maybe<Rule> find(std::string const&,std::vector<Rule> const&);
	
	void make_test()const;//TODO: make static
	void make_test(std::string const&)const;
	void make_test(std::string const&,std::vector<Library> const&)const;
	
	Rule();
	
	friend std::ostream& operator<<(std::ostream&,Rule const&);
};

struct Project{
	static const std::string SOURCE;
	
	private:
	std::vector<Rule> rules;
	std::vector<Library> libraries;
	static std::vector<std::vector<std::string>> read(std::string const&,std::string const&); 
	
	public:
	std::string all_to_string()const;
	
	void import();
	void import(std::string const&);
	
	void make_tests()const;
	void make_tests(std::string const&)const;
	
	Project();
	friend std::ostream& operator<<(std::ostream&,Project const&);
};

#endif
