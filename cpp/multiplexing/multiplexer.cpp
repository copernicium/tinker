#include "multiplexer.h"

using namespace std;


#ifdef MULTIPLEXER_TEST

int main(){
	Multiplexer<string> m;
	
	m.update(vector<string>{"cat","dog","parrot","lion"});
	
	cout<<m<<endl<<endl;
	
	for(double time = 0; time < 0.5; time += 0.001){
		cout<<time<<" "<<m.gen_output(time)<<endl;
	}
}

#endif