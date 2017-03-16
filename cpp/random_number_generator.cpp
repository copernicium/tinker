/*
 • Project: A draft for a random word selecting program. 
 • Author(s): Logan Traffas.
 • Description: A program that will randomly select a word and output it.
*/
#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include "util/simple_time.h"

using namespace std;

int main(){
	for(int stop=0;stop<10000000;stop++){
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
		srand(time(NULL));
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
		int random=(rand() % 100000)+1;
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
		cout<<random<<endl<<endl;
		Simple_time::sleep(250,Simple_time::Unit::MILLISECONDS);
	}
	return 0;
}
