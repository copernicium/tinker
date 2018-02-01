#ifndef MULTIPLEXER_H
#define MULTIPLEXER_H

#include <vector>
#include <cassert>
#include <cmath>
#include <iostream>

//time division multiplexer
template<typename T>
class Multiplexer{
	double transmit_time;
	std::vector<T> signals;
	
	public:
	void update(const std::vector<T>& SIGNALS){
		signals = SIGNALS;
	}
	
	T gen_output(const double TIME)const{
		const double TIME_INTERVAL = transmit_time / (double)signals.size();//seconds to transmit each part of data

		double time_remainder = [&]{//time since the last transmit cycle began
			double remainder, integer;
			remainder = modf(TIME / transmit_time, &integer);
			return remainder * transmit_time;
		}();
		
		
		for(unsigned i = 1; i <= signals.size(); i++){//pick and send the appropriate data
			//std::cout<<"\ntime_remainder:"<<time_remainder<<" vs "<<(TIME_INTERVAL * (double)i + 1E-5)<<"\n";
			if(time_remainder < TIME_INTERVAL * (double)i + 1E-5){
				return signals[i - 1];
			}
		}
		assert(0);		
	}
	
	friend std::ostream& operator<<(std::ostream& o, const Multiplexer<T>& m){
		o<<"(";
		o<<"transmit_time:"<<m.transmit_time;
		o<<" signals:[";
		for(T a: m.signals){
			o<<a<<" ";
		}
		o<<"]";
		o<<")";
		return o;
	}
	
	Multiplexer(double i):transmit_time(i){}
	Multiplexer():Multiplexer(0.1){}
};

#endif