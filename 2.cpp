#include<iostream>
#include<random>
#include<cassert>

using namespace std;

#define PRINT(x) { std::cout<<""#x<<":"<<(x)<<"\n"; }

template<typename T,typename T2>
std::vector<T>& operator|=(std::vector<T> &v,T2 t){
	v.emplace_back(std::move(t));
	return v;
}

template<typename Func,typename Collection>
auto mapf(Func f,Collection const& c)->std::vector<decltype(f(*begin(c)))>{
	std::vector<decltype(f(*begin(c)))> r;
	for(auto &e:c) r|=f(e);
	return r;
}

template<typename Func,typename T>
T filter(Func func,T const& in){
	T r;
	for(auto const& a:in){
		if(func(a)){
			r|=a;
		}
	}
	return r;
}

template<typename Collection>
auto firsts(Collection const& c)->std::vector<decltype(begin(c)->first)>
{
	typedef decltype(begin(c)->first) T;
	std::vector<T> r;
	for(auto a:c) r|=a.first;
	return r;
}

template<typename A,typename B>
std::vector<B> seconds(std::vector<std::pair<A,B>> const& v){
	return mapf([](std::pair<A,B> p){ return p.second; },v);
}

std::vector<unsigned> range(unsigned u){
	std::vector<unsigned> r;
	for(unsigned i=0;i<u;i++){
		r|=i;
	}
	return r;
}

template<typename T>
T sum(std::vector<T> const& v){
	T t=0;
	for(auto &a:v) t+=a;
	return t;
}

template<typename T>
T mean(std::vector<T> const& v){
	assert(v.size());
	return sum(v)/v.size();
}

double square(double a){ return a*a; }

double stddev(vector<double> const& v){
	return sqrt(mean(mapf(
		[&](double x){ return square(x-mean(v)); },
		v
	)));
}

int main(){
	default_random_engine generator;
	normal_distribution<double> t1_dist(40,30);
	normal_distribution<double> t2_dist(50,5);
	using Result=pair<double,double>;
	vector<Result> results;
	for(auto i:range(10000)) results|=make_pair(t1_dist(generator),t2_dist(generator));

	/*for(auto r:results){
		auto a=r.first;
		auto b=r.second;
		cout<<a<<"\t"<<b<<"\n";
	}
	cout<<"\n";*/

	auto t1_sample=firsts(results);
	cout<<"Team 1 mean:"<<mean(t1_sample)<<"\n";
	cout<<"stddev:"<<stddev(t1_sample)<<"\n";
	cout<<"\n";

	auto t2_sample=seconds(results);
	cout<<"Team 2 mean:"<<mean(t2_sample)<<"\n";
	cout<<"stddev:"<<stddev(t2_sample)<<"\n";
	cout<<"\n";

	auto t1_wins=(filter([](auto p){ return p.first>p.second; },results).size()+0.0)/results.size();
	PRINT(t1_wins);
}
