#include <iostream>
#include <memory>

using namespace std;

struct Animal{
	virtual void make_noise()=0;
	Animal(){}
};

struct Cow:public Animal{
	void make_noise(){
		cout<<"Moo";
	}
	Cow():Animal(){}
};

struct Dog:public Animal{
	bool big;
	void make_noise(){
		if(big) cout<<"Woof";
		else cout<<"Arf";
	}
	Dog():Animal(){
		big=true;
	}
};

int main(){
	unique_ptr<Animal> a=make_unique<Dog>();
	a->make_noise();
	cout<<"\n";
	return 0;
}
