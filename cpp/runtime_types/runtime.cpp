#include "runtime.h"
#include <iostream>

// Int a = TEST_VAL
// Int b = TEST_VAL
// Int c = a + b

ObjectBase::Type toType(const std::string& IN){
	if(IN == "Real"){
		return ObjectBase::Type::REAL;
	} else if(IN == "Int"){
		return ObjectBase::Type::INT;
	}
	assert(0);
}

int main(){
	constexpr unsigned TEST_VAL = 5;

	std::string type_str;
	std::cout<<"Type: ";
	std::cin>>type_str;

	ObjectBase::Type type = toType(type_str);

	{
		std::shared_ptr<ObjectBase> a = create(type);
		visit(a, assign, TEST_VAL);

		std::shared_ptr<ObjectBase> b = create(type);
		visit(b, assign, TEST_VAL);

		std::shared_ptr<ObjectBase> c = visit(a, b, add);

		std::cout<<c->toString()<<"\n";
	}
	{
		int size = 0, index = 0;

		std::cout<<"Type: ";
		std::cin>>type_str;
		type = toType(type_str);

		std::cout<<"Size: ";
		std::cin>>size;

		std::cout<<"Test index: ";
		std::cin>>index;

		std::shared_ptr<ObjectBase> list = create(create(type), size);
		visit(at(list, index), assign, TEST_VAL);

		std::cout<<list->toString()<<"\n";
		std::cout<<at(list, index)->toString()<<"\n";
	}
	{
		std::vector<std::shared_ptr<ObjectBase>> types;
		int index = 0;

		while(true){
			std::cout<<"Type: ";
			std::cin>>type_str;
			if(type_str == "q"){
				break;
			}
			types.push_back(create(toType(type_str)));
		}

		std::cout<<"Test index: ";
		std::cin>>index;

		std::shared_ptr<ObjectBase> collection = create(types);
		visit(at(collection, index), assign, TEST_VAL);

		std::cout<<collection->toString()<<"\n";
		std::cout<<at(collection, index)->toString()<<"\n";
	}
}
