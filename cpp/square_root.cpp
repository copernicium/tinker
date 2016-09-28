#include <iostream>
#include <cmath>

using namespace std;

double next_number(double square_root,double radicand){//takes a potential square root and the test and averages them. Eventually they must converge on the actual root.
	return (square_root + (radicand/square_root)) / 2;
}

int main(){
	srand(time(NULL));
	double radicand = rand(), square_root = rand();
	while(square_root != next_number(square_root,radicand)){//run until square root is found
		square_root = next_number(square_root,radicand);
		cout<<square_root<<"\n";
	}
}