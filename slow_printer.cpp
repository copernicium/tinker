//Author: Logan Traffas
#include <iostream>
#include <string>
#include <windows.h>

using namespace std;

void print_slowly(string to_print){
	for(unsigned int i; i<to_print.size(); i++){
		cout<<to_print[i]<<flush;
		Sleep(125);
	}
}

int main(){
	string to_print;
	getline(cin, to_print);
	print_slowly(to_print);
	return 0;
}