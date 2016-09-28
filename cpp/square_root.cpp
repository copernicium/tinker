#include <iostream>

void next_number(double& square_root,const double radicand){//takes a potential square root and the test and averages them. Eventually they must converge on the actual root.
	square_root = (square_root + (radicand / square_root)) / 2;
}

int main(){
	srand(time(NULL));
	const double radicand = rand();
	double square_root = rand();
	while(true){//run until square root is found
		double last = square_root;
		next_number(square_root,radicand);
		std::cout<<square_root<<"\n";
		if(last == square_root) break;
	}
}