#include "simple_time.h"

using namespace std;

unsigned long int Simple_time::get_time(Simple_time::Unit a){//returns system time in given units
	switch(a){
		#define X(UNIT,CHRONO_UNIT) \
			case Simple_time::Unit::UNIT: \
				return (unsigned long int)(std::chrono::duration_cast<std::chrono::CHRONO_UNIT>((std::chrono::time_point_cast<std::chrono::CHRONO_UNIT>(std::chrono::system_clock::now())).time_since_epoch())).count();
		UNITS
		#undef X
		default: assert(0);
	}
}

void Simple_time::sleep(long int length,Simple_time::Unit unit){
	long int start = Simple_time::get_time(unit);
	long int now = start;
	while(now < start + length){
		now = Simple_time::get_time(unit);
	}
}

ostream& operator<<(ostream& o,Simple_time::Unit const& a){
		switch(a){
				#define X(UNIT,B) case Simple_time::Unit::UNIT: return o<<""#UNIT;
				UNITS
				#undef X
				default:
					assert(0);
		}
}