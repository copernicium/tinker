#include "pwm_transcriber.h"

using namespace std;

#ifdef PWM_TRANSCRIBER_TEST

int main(){
	PWM_transcriber m;
	m.add("climbing",set<bool>{false,true});
	m.add("enabled",set<bool>{false,true});
	m.add("height",set<int>{0,1,2,3,4,5,6,7,8,9,10});
	
	cout<<m.map("climbing",false)<<endl;
	cout<<m.map("climbing",true)<<endl;
	cout<<m.map("height",5)<<endl;
	cout<<m.map("enabled",true)<<endl;
	
	cout<<m.transcribe("height",10)<<endl;
}

#endif
