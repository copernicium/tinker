#ifndef UTIL_H
#define UTIL_H

#include <vector>

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

#endif