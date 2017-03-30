#ifndef CONVERTER_H
#define CONVERTER_H

#include <vector>
#include <iostream>

struct Build_rule{
	private:
	std::string name;
	std::string out;
	std::vector<std::string> srcs;
	std::vector<std::string> copts;
	std::vector<std::string> deps;
	std::string timeout;
	
	public:
	std::string get_name()const;
	
	static Build_rule parse(std::string const&);
	static Build_rule parse(std::vector<std::string> const&);
	void make_test()const;
	void make_test(std::string const&)const;
	
	Build_rule();
	
	friend std::ostream& operator<<(std::ostream&,Build_rule const&);
};

struct Project{
	static const std::string SOURCE;
	
	private:
	std::vector<Build_rule> build_rules;
	static std::vector<std::vector<std::string>> read_project(std::string const&); 
	
	public:
	void import(std::string const&);
	void import();
	
	void make_tests()const;
	void make_tests(std::string const&)const;
	
	Project();
	friend std::ostream& operator<<(std::ostream&,Project const&);
};

#endif
