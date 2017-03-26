//Author: Logan Traffas
#include <iostream>

using namespace std;

int main(){
	int time=0;
	int AC_time=0;
	double current_inside_temperature=68;
	double current_outside_temperature=83;
	for(int i=0;i<20000000;i++){
		time+=300;
		current_inside_temperature=(.95*current_inside_temperature)+(.05*current_outside_temperature);
		if(current_inside_temperature>72){
			cout<<"AC ran."<<endl;
			current_inside_temperature-=2.5;
			AC_time+=150;
		}
		cout<<"Temperature currently at "<<current_inside_temperature<<" degrees Fahrenheit inside."<<endl;
		cout<<"Temperature currently at "<<current_outside_temperature<<" degrees Fahrenheit outside."<<endl;
	}
	cout<<"Simulation ran for "<<(time/60)<<" minutes. AC ran for "<<(AC_time/60)<<" minutes."<<endl;
	return 0;
}