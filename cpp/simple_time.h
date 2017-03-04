#include <chrono>
#include <cassert>

namespace Simple_time{

	enum class Unit{SECONDS,MILLISECONDS,MICROSECONDS,NANOSECONDS};
	
	void sleep(int,Unit);
	
	unsigned long int get_time(Unit);
}
