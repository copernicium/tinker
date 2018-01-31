#ifndef MULTIPLEXER_H
#define MULTIPLEXER_H

#include <map>
#include <set>
#include <iostream>

class Multiplexer{//TODO rename everything (this isn't a multiplexer, and encoder may be confusing)
	private:
	class Encoder_base{//class used to store encodings of any type
		private:
		virtual int operator()(const void*)const = 0;
		
		public:
		virtual ~Encoder_base(){}
	};
	
	public:
	template<typename T>
	class Encoder: public Encoder_base{
		private:
		std::map<T,int> encodings;
		
		int operator()(const void* key_ptr)const{
			T* typed_key_ptr = (T*)key_ptr;
			
			T key = *typed_key_ptr;
			
			if(encodings.find(key) == encodings.end()){
				std::cout<<__FILE__<<":"<<__LINE__<<": Warning: no encoder found with name \""<<key<<"\""<<std::endl;
			}
			
			return encodings.at(key);
		}
		
		public:
		int operator()(const T& key)const{
			const T* key_ptr = &key;
		
			return (*this)(key_ptr);
		}
		
		Encoder(unsigned& highest_unassigned_value, std::set<T> keys){
			for(T k: keys){
				encodings.insert(std::pair<T,int>(k,highest_unassigned_value));
				highest_unassigned_value++;
			}
		}
	};
	
	private:
	unsigned highest_unassigned_value;
	std::map<std::string, Encoder_base*> all_encodings;

	public: 
	template<typename T> 
	int encode(const std::string NAME, const T& KEY)const{
		if(all_encodings.find(NAME) == all_encodings.end()){
			std::cout<<__FILE__<<":"<<__LINE__<<": Warning: no encoder found with name \""<<NAME<<"\""<<std::endl;
		}
		const Encoder<T>* encoder_ptr = (Encoder<T>*)all_encodings.at(NAME);
		Encoder<T> encoder = *encoder_ptr;
			
		return encoder(KEY);	
	}
	
	template<typename T>
	void add(const std::string NAME,const std::set<T>& KEYS){
		if(all_encodings.find(NAME) != all_encodings.end()){
			std::cout<<__FILE__<<":"<<__LINE__<<":"<<" Error: encoder already set with name \""<<NAME<<"\""<<std::endl;
			exit(0);
		}
		
		all_encodings.insert(std::pair<std::string,Encoder_base*>(NAME, (Encoder_base*)(new Encoder<T>(highest_unassigned_value,KEYS))));
	}
		
	Multiplexer():highest_unassigned_value(0){}
};

#endif