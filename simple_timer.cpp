//Author: Logan. 
//Description: A program that will output the number of seconds since program began into terminal.
//Finished.
#include <iostream>
#include <windows.h>

using namespace std;
int main(){
	int time=0;
	while(true){
		cout<<time<<endl;
		Sleep(1000);
		time++;
	}
	return 0;
}