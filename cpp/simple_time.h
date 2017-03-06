#include <chrono>
#include <iostream>
#include <cassert>

namespace Simple_time{

	#define UNITS \
		X(SECONDS,seconds) \
		X(MILLISECONDS,milliseconds) \
		X(MICROSECONDS,microseconds) \
		X(NANOSECONDS,nanoseconds)
			
	enum class Unit{
		#define X(UNIT,B) UNIT,
		UNITS
		#undef X
	};
	
	void sleep(long int,Unit);
	
	unsigned long int get_time(Unit);
}

std::ostream& operator<<(std::ostream&,Simple_time::Unit const&);