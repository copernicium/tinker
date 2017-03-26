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

struct Test{
	int a;
	explicit Test(int x):a(x){}
};

ostream& operator<<(ostream& o,Test a){
	return o<<"Test("<<a.a<<")";
}

Test addt(Test a){
	Test b = a;
	b.a++;
	return b;
}

template<typename FUNC,typename T>
auto map(FUNC f, vector<T> const& v) -> vector<decltype(f(*((T*)nullptr)))> {
	vector<decltype(f(*((T*)nullptr)))> out;
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
	cout<<"(";
	for(unsigned i = 0; i < v.size(); i++){
		cout<<v[i];
		if(i < v.size() - 1) cout<<", ";
	}
	cout<<")\n";
}

int main(){
	{
		vector<int> all = {1,2,3,4,5};
		printv(all);
		vector<int> evens = filter(is_even,all);
		printv(evens);
	}
	{
		vector<int> all = {1,2,3,4,5,6,7,8,9};
		printv(all);
		vector<int> added = map(add,all);
		printv(added);
	}
	{
		vector<Test> all = {Test{1},Test{2},Test{3},Test{4},Test{5}};
		printv(all);
		vector<Test> added = map(addt,all);
		printv(added);
	}
}
