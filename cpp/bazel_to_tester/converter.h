#ifndef CONVERTER_H
#define CONVERTER_H

#include <vector>
#include <iostream>

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
	static const std::string HEADING;
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) static const std::string ATTRIBUTE_NAME;
	RULE_ITEMS(X)
	#undef X

	std::string get_name()const;
	
	static Rule parse(std::string const&);
	static Rule parse(std::vector<std::string> const&);
	
	void make_test()const;
	void make_test(std::string const&)const;
	
	Rule();
	
	friend std::ostream& operator<<(std::ostream&,Rule const&);
};

struct Library{	
	private:
	#define LIBRARY_ITEMS(X) \
		X(std::string,name,NAME) \
		X(std::vector<std::string>,srcs,SRCS) \
		X(std::vector<std::string>,hdrs,HDRS) \
		X(std::vector<std::string>,deps,DEPS)
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) TYPE INSTANCE_VARIABLE;
	LIBRARY_ITEMS(X)
	#undef X
	
	public:
	static const std::string HEADING;
	#define X(TYPE,INSTANCE_VARIABLE,ATTRIBUTE_NAME) static const std::string ATTRIBUTE_NAME;
	LIBRARY_ITEMS(X)
	#undef X
	
	std::string get_name()const;
	
	static Library parse(std::string const&);
	static Library parse(std::vector<std::string> const&);
		
	Library();
	
	friend std::ostream& operator<<(std::ostream&,Library const&);
};

struct Project{
	static const std::string SOURCE;
	
	private:
	std::vector<Rule> rules;
	std::vector<Library> libraries;
	static std::vector<std::vector<std::string>> read(std::string const&,std::string const&); 
	
	public:
	void import();
	void import(std::string const&);
	
	void make_tests()const;
	void make_tests(std::string const&)const;
	
	Project();
	friend std::ostream& operator<<(std::ostream&,Project const&);
};

#endif
