#ifndef CONVERTER_H
#define CONVERTER_H

#include <vector>
#include <iostream>

struct Build_rule{
	std::string name;
	std::string out;
	std::vector<std::string> srcs;
	std::vector<std::string> copts;
	std::vector<std::string> deps;
	std::string timeout;
	
	static Build_rule parse(std::string const&);
	void make_test()const;
	void make_test(std::string const&)const;
	
	Build_rule();
};

std::ostream& operator<<(std::ostream&,Build_rule const&);


#endif
