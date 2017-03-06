/*
 • Project: A draft for a random word selecting program. 
 • Author(s): Logan Traffas.
 • Description: A program that will randomly select a word and output it.
*/
#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include "simple_time.h"

using namespace std;

int main(){
	int i=0;
	string word="";
	ifstream chooseword;
	while(true){
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
		i=0;
		srand(time(NULL));
		int random=(rand() % 99171)+1;
		chooseword.open("hangman/words.txt");
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
		while(!chooseword.eof()){//Choosing the word.
			if(i==random){
				Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
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
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
	}
	return 0;
}
