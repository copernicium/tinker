//Author: Logan Traffas
#include <iostream>
#include <string>

using namespace std;

int main(){
	cout<<"Please start typing the digits of pi and press enter when you want to compare. Type \"exit\" any time to leave."<<endl<<endl;
	bool correct=1;
	bool allCorrect=0;
	while(1){	
		int i=0;
		string myGuess;
		string pi="3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
		cout<<"Your guess:  ";
		cin>>myGuess;
		if(myGuess=="exit"){
			break;
		}
		cout<<"The real pi: ";
		for(i; i<myGuess.length(); i++){
			cout<<pi[i];
		}
		cout<<endl;
		for(i=0; i<myGuess.length(); i++){
			if(myGuess[i]!=pi[i]){
				int h=i+1;
				cout<<"Place "<<h<<" is incorrect. Your guess was: "<<myGuess[i]<<". The correct digit is: "<<pi[i]<<endl;
				correct=0;
			}
			if(myGuess.length()>=pi.length()){
				break;
			}
		}
		if(correct==1){
			cout<<"No errors we found. Good job."<<endl;
		}
		if(correct==1 && i==102){
			allCorrect=1;
			break;
		}
		cout<<endl<<"Try to go farther (goal 100 digits). The next eight are: "<<pi[i]<<" "<<pi[i+1]<<" "<<pi[i+2]<<" "<<pi[i+3]<<" "<<pi[i+4]<<" "<<pi[i+5]<<" "<<pi[i+6]<<" "<<pi[i+7]<<"."<<endl<<endl;
	}
	if(allCorrect==0){
		cout<<"Thanks for trying."<<endl;
	}
	else{
		cout<<"CONGRADULATIONS!"<<endl<<"You've memorized them all!"<<endl;
	}
	return 0;
}