#include <iostream>

double sqrt(double& square_root,const double radicand){//take any square_root seed and radicand and find the square root
	double last = square_root;
	square_root = (square_root + (radicand / square_root)) / 2;
	if(last != square_root) sqrt(square_root,radicand);
	return square_root;
}

double sqrt(const double radicand){//use one as the square_root seed and a radicand to find the square root
	double one = 1;
	return sqrt(one,radicand); 
}

int main(){
	srand(time(NULL));
	
	const double radicand = rand();
	std::cout<<"radicand:"<<radicand<<" square root:"<<sqrt(radicand)<<"\n";
}