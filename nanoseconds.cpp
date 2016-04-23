#include <iostream>
#include <chrono>
unsigned long int get_nano(){ return (unsigned long int)(std::chrono::duration_cast<std::chrono::nanoseconds>((std::chrono::time_point_cast<std::chrono::nanoseconds>(std::chrono::system_clock::now())).time_since_epoch())).count(); }//returns system time in nanoseconds
int main(){ while(1) std::cout<<get_nano()<<"\n"; }
