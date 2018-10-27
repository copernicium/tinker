#include "runtime.h"

#include <cassert>

std::shared_ptr<std::string> Input::in = nullptr;

std::shared_ptr<Base> Input::create(const std::string& a){
	if(in == nullptr){
		in = std::make_shared<std::string>();
	}

	*in = a;
	return create();
}

std::shared_ptr<Base> Input::create(){
	if(in->size() > 0){
		std::string type = in->substr(0,1);

		in->erase(0, 1);

		if(type == "A"){
			return std::make_shared<A>(A());
		} else if(type == "B"){
			return std::make_shared<B>(B());
		} else if(type == "C"){
			return std::make_shared<C>(C());
		} else {
			std::cout << "Parsing failed - type input \"" << type << "\"\n";
			exit(1);
		}
	}
	std::cout << "Parsing failed - end of input\n";
	exit(1);
}

int main(){
	std::string types;
	std::cout << "Types: ";
	std::cin >> types;

	std::shared_ptr<Base> result = Input::create(types);

	result->run();
	std::cout<<"\n";
}
