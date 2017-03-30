#ifndef CONVERTER_H
#define CONVERTER_H

#include <vector>
#include <iostream>

struct Rule{	
	private:
	#define RULE_ITEMS(X) \
		X(std::string,name,NAME,SINGLE) \
		X(std::string,out,OUT,SINGLE) \
		X(std::vector<std::string>,srcs,SRCS,MULTIPLE) \
		X(std::vector<std::string>,copts,COPTS,MULTIPLE) \
		X(std::vector<std::string>,deps,DEPS,MULTIPLE) \
		X(std::string,timeout,TIMEOUT,SINGLE)
	#define X(TYPE,NAME,C,D) TYPE NAME;
	RULE_ITEMS(X)
	#undef X
	
	public:
	#define X(A,B,ATTRIBUTE_NAME,D) static const std::string ATTRIBUTE_NAME;
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
		X(std::string,name,NAME,SINGLE) \
		X(std::vector<std::string>,srcs,SRCS,MULTIPLE) \
		X(std::vector<std::string>,hdrs,HDRS,MULTIPLE) \
		X(std::vector<std::string>,deps,DEPS,MULTIPLE)
	#define X(TYPE,NAME,C,D) TYPE NAME;
	LIBRARY_ITEMS(X)
	#undef X
	
	public:
	#define X(A,B,ATTRIBUTE_NAME,D) static const std::string ATTRIBUTE_NAME;
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
	static std::vector<std::vector<std::string>> read_rules(std::string const&); 
	static std::vector<std::vector<std::string>> read_libraries(std::string const&); 
	
	public:
	void import();
	void import(std::string const&);
	
	void make_tests()const;
	void make_tests(std::string const&)const;
	
	Project();
	friend std::ostream& operator<<(std::ostream&,Project const&);
};

#endif
