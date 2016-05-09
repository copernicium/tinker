//complie with -DTIME_UNITS=[unit of time to use]
#include <chrono>
#ifndef TIME_UNITS
#define TIME_UNITS seconds
#endif

unsigned long int get_time(){ return (unsigned long int)(std::chrono::duration_cast<std::chrono::TIME_UNITS>((std::chrono::time_point_cast<std::chrono::TIME_UNITS>(std::chrono::system_clock::now())).time_since_epoch())).count(); }//returns system time in given units
