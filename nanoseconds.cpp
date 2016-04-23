#include <iostream>
#include <chrono>

using namespace std;

unsigned long int get_nano(){
	auto seedauto=chrono::time_point_cast<chrono::nanoseconds>(chrono::system_clock::now());
        auto seedvalue=chrono::duration_cast<chrono::nanoseconds>(seedauto.time_since_epoch());
        unsigned long int seed=seedvalue.count();
	return seed;
}

int main(){
	while(1) cout<<get_nano()<<endl;
}
