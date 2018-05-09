#include "util.h"

using namespace std;

std::vector<unsigned> range(unsigned lim){
	std::vector<unsigned> v;
	for(unsigned i=0;i<lim;i++){
		v.push_back(i);
	}
	return v;
}

#if defined(WIN32) || defined(_WIN32) || defined(__CYGWIN__)
	#include <windows.h>//for use in retrieving absolute path in Windows
#endif

string make_path_absolute(string const& REL_PATH){ 
	string path;
	#ifdef __linux__
		static const string COMMAND = "realpath";
		const string EXECUTABLE = COMMAND + " " + REL_PATH; 
		const char* SYSTEM_ARG = EXECUTABLE.c_str();

		FILE *in;
		char buffer[512];
		
		in = popen(SYSTEM_ARG, "r");
		if(!in){
			return "";
		}
		fgets(buffer, sizeof(buffer), in);
		if(buffer != NULL){
			string s = buffer;
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
	const char DIR_MARKER = '/';
	if(REL_PATH.size() > 0 && REL_PATH[REL_PATH.size() - 1] == DIR_MARKER){
		path += DIR_MARKER; //readpath does not include the last "/" on directories, so add that back in
	}
	
	cout<<"\""<<path<<"\"\n";

	return path;
}