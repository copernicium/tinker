#include "runtime.h"
#include <iostream>

// Int a = TEST_VAL
// Int b = TEST_VAL
// Int c = a + b

Type toType(const std::string& IN){
	if(IN == "Real"){
		return Type::REAL;
	} else if(IN == "Int"){
		return Type::INT;
	}
	assert(0);
}

int main(){
	constexpr unsigned TEST_VAL = 5;

	std::string type_str;
	Type type;

	{
		std::cout<<"Primitive example -\n";

		std::cout<<"Type: ";
		std::cin>>type_str;
		type = toType(type_str);

		Object a = Object::primitive(type);
		Object::visit(assign, a, TEST_VAL);

		Object b = Object::primitive(type);
		Object::visit(assign, b, TEST_VAL);

		Object c = Object::visit(add, a, b);

		std::cout<<Object::visit(toString, c)<<"\n";
	}
	{
		std::cout<<"\nList example -\n";
		int size = 0, index = 0;

		std::cout<<"Type: ";
		std::cin>>type_str;
		type = toType(type_str);

		std::cout<<"Size: ";
		std::cin>>size;

		std::cout<<"Test index: ";
		std::cin>>index;

		Object list = Object::list(Object::create(type), size);
		Object::visit(assign, list.at(index), TEST_VAL);

		std::cout<<"[";
		for(unsigned i = 0; i < list.size(); i++){
			std::cout<<Object::visit(toString, list.at(i))<<" ";
		}
		std::cout<<"]\n";
	}
	{
		std::cout<<"\nCollection example -\n";
		std::vector<std::shared_ptr<Object>> types;
		int index = 0;

		while(true){
			std::cout<<"Type: ";
			std::cin>>type_str;
			if(type_str == "q"){
				break;
			}
			types.push_back(Object::create(toType(type_str)));
		}

		std::cout<<"Test index: ";
		std::cin>>index;

		Object collection = Object::collection(types);
		Object::visit(assign, collection.at(index), TEST_VAL);

		std::cout<<"[";
		for(unsigned i = 0; i < collection.size(); i++){
			std::cout<<Object::visit(toString, collection.at(i))<<" ";
		}
		std::cout<<"]\n";
	}
}