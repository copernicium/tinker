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
	int i=0;
	string word="";
	ifstream chooseword;
	while(true){
		Sleep(250);
		i=0;
		srand(time(NULL));
		int random=(rand() % 99171)+1;
		chooseword.open("words.txt");
		Sleep(250);
		while(!chooseword.eof()){//Choosing the word.
			if(i==random){
				Sleep(250);
				break;
			}	
			else{
				while(i!=random){
					chooseword >> word;
					i++;
				}
			}
		}
		chooseword.close();
		cout<<word<<endl<<endl;
		Sleep(250);
	}
	return 0;
}