//Author: Logan Traffas
#include <iostream>
#include <sstream>

using namespace std;

int main(){
	while(1){
		int input_number,total=1;
		cout<<"Please input an integer: ";
		string input;
		getline(cin,input);
		stringstream test_if_number (input);
		if(!(test_if_number>>input_number))cout<<"Error: Non-integers are not compatible with this program. Please only input integers.\n(Integers are defined as a whole number, that is it can be represented as a rational fraction over a denomintor of one.)";
		else{
			if(input_number>1){
				for(int i=1; i<input_number; i++){
					total*=i;
					if(i==0)break;
				}
				total++;
				int test_number=total%input_number;
				if(test_number==0)cout<<input_number<<" is a prime.";
				else cout<<input_number<<" is not a prime.";
			}
			else cout<<input_number<<" is not a prime.";
		}
		cout<<"\n";
	}
	return 0;
}
