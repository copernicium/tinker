#include <iostream>
#include <vector>
using namespace std;

template<typename FUNC,typename T>
vector<T> filter(FUNC f,vector<T> v){
	vector<T> out;
	for(T a: v){
		if(f(a)) out.push_back(a);
	}
	return out;
}

template<typename FUNC,typename T>
auto map(FUNC f, vector<T> const& v) -> vector< typename remove_reference< decltype(f)>::type > {
	/*vector<typename remove_const<typename remove_reference<decltype(f)>::type>::type>*/ 
	vector< typename remove_reference< decltype(f)>::type > out;
	for(T a:v){
		out.push_back(f(a));
	}
	return out;
}

bool is_even(int a){
	return a%2 ==0;
}

int add(int a){
	int b = a+1;
	return b;
}

template<typename T>
void printv(const vector<T> v){
	for(T a: v){
		cout<<a;
	}
	cout<<"\n";
}

int main(){
	{
		vector<int> all = {1,2,3,4,5,};
		printv(all);
		vector<int> evens = filter(is_even,all);
		printv(evens);
	}
	{
		vector<int> all = {1,2,3,4,5,6,7,8,9};
		printv(all);
		/*vector<int>*/ auto added = map(add,all);
		printv(added);
	}
}