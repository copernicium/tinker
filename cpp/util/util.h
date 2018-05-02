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



#if defined(WIN32) || defined(_WIN32) || defined(__CYGWIN__)
	#include <windows.h>//for use in retrieving absolute path in Windows
#endif

std::string make_path_absolute(std::string const& REL_PATH){ 
	std::string path;
	#ifdef __linux__
		static const std::string COMMAND = "realpath";
		const std::string EXECUTABLE = COMMAND + " " + REL_PATH; 
		const char* SYSTEM_ARG = EXECUTABLE.c_str();

		FILE *in;
		char buffer[512];
		
		in = popen(SYSTEM_ARG, "r");
		if(!in){
			return "";
		}
		fgets(buffer, sizeof(buffer), in);
		if(buffer != NULL){
			std::string s = buffer;
			for(char c: s){
				if(c != '\n' && c != '\r') path += c;
			}	
		}
		pclose(in);
	#elif defined(WIN32) || defined(_WIN32) || defined(__CYGWIN__)
		char full_path[MAX_PATH];
		GetFullPathName(REL_PATH.c_str(), MAX_PATH, full_path, NULL);
		path = full_path;
		for(unsigned i = 0; i < path.size(); i++){
			if(path[i] == '\\'){
				path[i] = '/';
			}
		}
	#endif
	std::cout<<path<<"\n";
	const char DIR_MARKER = '/';
	if(REL_PATH.size() > 0 && REL_PATH[REL_PATH.size() - 1] == DIR_MARKER){
		path += DIR_MARKER; //readpath does not include the last "/" on directories, so add that back in
	}

	return path;
}

#endif
