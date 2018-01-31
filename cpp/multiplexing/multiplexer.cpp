#include "multiplexer.h"

using namespace std;

#ifdef MULTIPLEXER_TEST

int main(){
	Multiplexer m;
	m.add("climbing",set<bool>{false,true});
	m.add("enabled",set<bool>{false,true});
	m.add("height",set<int>{0,1,2,3,4,5,6,7,8,9,10});
	
	cout<<m.encode("climbing",false)<<endl;
	cout<<m.encode("climbing",true)<<endl;
	cout<<m.encode("height",5)<<endl;
	cout<<m.encode("enabled",true)<<endl;
}

#endif