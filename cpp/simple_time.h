#include <chrono>
#include <cassert>

enum class Time_type{SECONDS,MILLISECONDS,MICROSECONDS,NANOSECONDS};

unsigned long int get_time(Time_type a){//returns system time in given units
	switch(a){
		case Time_type::SECONDS:
			return (unsigned long int)(std::chrono::duration_cast<std::chrono::seconds>((std::chrono::time_point_cast<std::chrono::seconds>(std::chrono::system_clock::now())).time_since_epoch())).count();
		case Time_type::MILLISECONDS:
			return (unsigned long int)(std::chrono::duration_cast<std::chrono::milliseconds>((std::chrono::time_point_cast<std::chrono::milliseconds>(std::chrono::system_clock::now())).time_since_epoch())).count();
		case Time_type::MICROSECONDS:
			return (unsigned long int)(std::chrono::duration_cast<std::chrono::microseconds>((std::chrono::time_point_cast<std::chrono::microseconds>(std::chrono::system_clock::now())).time_since_epoch())).count();
		case Time_type::NANOSECONDS:
			return (unsigned long int)(std::chrono::duration_cast<std::chrono::nanoseconds>((std::chrono::time_point_cast<std::chrono::nanoseconds>(std::chrono::system_clock::now())).time_since_epoch())).count();
		default: assert(0);
	}
}
