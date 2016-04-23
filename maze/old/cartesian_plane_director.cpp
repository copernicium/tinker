//Author: Logan. Description: A program that gives directions from coordinates of a Cartesian plane.
//Finished.
#include <stdlib.h> 
#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>
#include <cmath>
using namespace std;
int main(){

	cout<<"Hello.\n";
        
	double currentX=0;
	double currentY=0;
	double destinationX=0;
	double destinationY=0;
	double horizDist=0;
	double vertDist=0;
	char cont='y';
	bool c=false;
	bool p=false;
	string f="";
	stringstream input;
	input.str("");
	std::cout << std::fixed << std::setprecision(1); 
		
	cout<<"Would you like to receive directions?(Type 'y' for yes and 'n' for no.)\n";
	cin>>cont;
	cout<<endl;
	
	while(cont=='y'){
			
		cout<<"What is your current x coordinate?\n";
		do{
			getline(cin,f);
			stringstream input(f); 
			if(!(input >> currentX)){
				currentX = 0;
				if(p==true){
					cout<<"Please insert a number for your current x coordinate.\n";
				}
				p=true;
			}
			else{
				c=true;
			}
		}
		while(c==false);
		
		c=false;
		input.str("");
		f="";	
		p=false;
			
		cout<<"What is your current y coordinate?\n";
		do{
			getline(cin,f);
			stringstream input (f);
			if(!(input >> currentY)){
				currentY = 0;
				cout<<"Please insert a number for your current y coordinate.\n";
			}
			else{
				c=true;
			}
		}
		while(c==false);
		
		c=false;
		input.str("");
		f="";     
		
		cout<<"What is your destination's x coordinate?\n";
		do{
			getline(cin,f);
			stringstream input (f);
			if(!(input >> destinationX)){
				destinationX = 0;
				cout<<"Please insert a number for your destination's x coordinate.\n";
			}
			else{
				c=true;
			}
		}
		while(c==false);
		
		c=false;
		input.str("");
		f="";
	
		cout<<"What is your destination's y coordinate?\n";
		do{
			getline(cin,f);
			stringstream input (f);
			if(!(input >> destinationY)){
				destinationY = 0;
				cout<<"Please insert a number for your destination's y coordinate.\n";
			}
			else{
				c=true;
			}
		}
		while(c==false);

		c=false;
		input.str("");
		f="";     
        
		horizDist=((destinationX)-(currentX));
		vertDist=((destinationY)-(currentY));
	
		cout<<"\n";
	
		if(horizDist==0 && vertDist==0){
			cout<<"You are already there.\n";
		}
		if(horizDist<0){
			cout<<"Move " <<abs(horizDist)<< " spaces left.\n";
			}
		if(horizDist>0){
			cout<<"Move " <<horizDist<< " spaces right.\n";
		}
		if(horizDist==0 && vertDist!=0){
			cout<<"Don't move left or right.\n";
		}
		
		if(vertDist==0 && horizDist!=0){
			cout<<"Don't move up or down.\n";
		}
		if(vertDist>0){
			cout<<"Move " <<vertDist<< " spaces up.\n";
		}
		if(vertDist<0){
			cout<<"Move " <<abs(vertDist)<< " spaces down.\n";
		}
			
		cout<<"\nWould you like to receive directions again?(Type 'y' for yes and 'n' for no.)\n"; 
			cin>>cont;
		if(cont=='y'){
			cout<<"\n";
		   }
		else{
			cout<<"\n";
			break;
		}
	}
        
    cout<<"Okay. Bye.\n";
}