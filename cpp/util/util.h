#ifndef UTIL_H
#define UTIL_H

#include <set>
#include <vector>
#include <iostream>
#include <sstream>

template<typename FUNC,typename T>
std::vector<T> filter(FUNC f,std::vector<T> v){
	std::vector<T> out;
	for(T a: v){
		if(f(a)) out.push_back(a);
	}
	return out;
}

template<typename FUNC,typename T>
auto map(FUNC f, std::vector<T> const& v) -> std::vector<decltype(f(*((T*)nullptr)))> {
	std::vector<decltype(f(*((T*)nullptr)))> out;
	for(T a:v){
		out.push_back(f(a));
	}
	return out;
}

std::vector<unsigned> range(unsigned lim){
	std::vector<unsigned> v;
	for(unsigned i=0;i<lim;i++){
		v.push_back(i);
	}
	return v;
}

template<typename T>
std::ostream& operator<<(std::ostream& o, std::vector<T> const& v){
	o<<"[";
	for(T a: v){
		o<<a<<" ";
	}
	o<<"]";
	return o;
}

template<typename T>
std::ostream& operator<<(std::ostream& o, std::set<T> const& v){
	o<<"[";
	for(T a: v){
		o<<a<<" ";
	}
	o<<"]";
	return o;
}

template<typename T>
std::string as_string(T const& t){
	std::stringstream ss;
	ss<<t;
	return ss.str();
}

#endif