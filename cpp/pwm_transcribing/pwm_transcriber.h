#ifndef PWM_TRANSCRIBER_H
#define PWM_TRANSCRIBER_H

#include <map>
#include <set>
#include <iostream>
#include <cassert>
#include <cmath>

class PWM_transcriber{
	private:
	class Transcriber_base{//class used to store transcriptions of any type
		private:
		virtual int operator()(const void*)const = 0;
		
		public:
		virtual ~Transcriber_base(){}
	};
	
	public:
	template<typename T>
	class Transcriber: public Transcriber_base{
		private:
		std::map<T,int> transcriptions;
		
		int operator()(const void* key_ptr)const{
			T* typed_key_ptr = (T*)key_ptr;
			
			T key = *typed_key_ptr;
			
			if(transcriptions.find(key) == transcriptions.end()){
				std::cout<<__FILE__<<":"<<__LINE__<<": Warning: no transcriber found with name \""<<key<<"\""<<std::endl;
			}
			
			return transcriptions.at(key);
		}
		
		public:
		int operator()(const T& key)const{
			const T* key_ptr = &key;
		
			return (*this)(key_ptr);
		}
		
		Transcriber(unsigned& highest_unassigned_value, std::set<T> keys){
			for(T k: keys){
				transcriptions.insert(std::pair<T,int>(k,highest_unassigned_value));
				highest_unassigned_value++;
			}
		}
	};
	
	private:
	unsigned highest_unassigned_value;
	double transmit_time;
	std::map<std::string, Transcriber_base*> all_transcriptions;

	public: 
	template<typename T> 
	int map(const std::string NAME, const T& KEY)const{
		if(all_transcriptions.find(NAME) == all_transcriptions.end()){
			std::cout<<__FILE__<<":"<<__LINE__<<": Warning: no transcriber found with name \""<<NAME<<"\""<<std::endl;
		}
		const Transcriber<T>* transcriber_ptr = (Transcriber<T>*)all_transcriptions.at(NAME);
		Transcriber<T> transcriber = *transcriber_ptr;
			
		return transcriber(KEY);	
	}
	
	template<typename T> 
	double transcribe(const std::string NAME, const T& KEY)const{
		const double TIME_INTERVAL = transmit_time / all_transcriptions.size();//seconds to transmit each part of data
	
		int value = map(NAME, KEY);
		
		const double PWM_RANGE = 2.0;
		const double PWM_INCREMENT = PWM_RANGE / (double)highest_unassigned_value;
		const double STARTING_PWM_VALUE = -1.0;

	        return STARTING_PWM_VALUE + PWM_INCREMENT * value; //convert the integer transmission value to a pwm valu
	};

	template<typename T>
	void add(const std::string NAME,const std::set<T>& KEYS){
		if(all_transcriptions.find(NAME) != all_transcriptions.end()){
			std::cout<<__FILE__<<":"<<__LINE__<<":"<<" Error: transcriber already set with name \""<<NAME<<"\""<<std::endl;
			exit(0);
		}
		
		all_transcriptions.insert(std::pair<std::string,Transcriber_base*>(NAME, (Transcriber_base*)(new Transcriber<T>(highest_unassigned_value,KEYS))));
	}
		
	PWM_transcriber(double t):highest_unassigned_value(0),transmit_time(t){}
	PWM_transcriber():PWM_transcriber(0.1){}
};

#endif
