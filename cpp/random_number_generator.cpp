/*
 • Project: A draft for a random word selecting program. 
 • Author(s): Logan Traffas.
 • Description: A program that will randomly select a word and output it.
*/
#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include <windows.h>
using namespace std;

int main(){
	for(int stop=0;stop<10000000;stop++){
		Sleep(250);
		srand(time(NULL));
		Sleep(250);
		int random=(rand() % 100000)+1;
		Sleep(250);
		cout<<random<<endl<<endl;
		Sleep(250);
	}
	return 0;
}
