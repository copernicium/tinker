#include <iostream>
#include <random>
#include "simple_time.h"
#include <iomanip>

using namespace std;

int main(){
	const int N=10000;
	
	const double MEAN_1=40;
	const double STANDARD_DEVIATION_1=30;
	normal_distribution<double> alliance_1(MEAN_1,STANDARD_DEVIATION_1);
	
	const double MEAN_2=50;
	const double STANDARD_DEVIATION_2=5;
	normal_distribution<double> alliance_2(MEAN_2,STANDARD_DEVIATION_2);

	default_random_engine generator(get_time(Time_type::NANOSECONDS));
	
	unsigned int alliance_1_wins=0;
	unsigned int alliance_2_wins=0;
	unsigned int ties=0;
	
	for(unsigned int i=0; i<N; i++){
		double alliance_1_score=alliance_1(generator);
		double alliance_2_score=alliance_2(generator);
		cout<<fixed<<setprecision(5)<<alliance_1_score<<"   "<<alliance_2_score<<"\n";
		if(alliance_1_score > alliance_2_score) alliance_1_wins++;
		else if (alliance_1_score <alliance_2_score) alliance_2_wins++;
		else ties++;
	}
	cout<<"\n";
	cout<<"alliance_1_wins:"<<alliance_1_wins<<"\n";
	cout<<"alliance_2_wins:"<<alliance_2_wins<<"\n";
	cout<<"ties:"<<ties<<"\n";
	cout<<"alliance_1 win percentage:"<<((float)alliance_1_wins/(float)N)<<"\n";
	cout<<"alliance_2 win percentage:"<<((float)alliance_2_wins/(float)N);
	return 0;
}