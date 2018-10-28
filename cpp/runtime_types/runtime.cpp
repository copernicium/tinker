#include "runtime.h"
#include <iostream>

// Int a = 5
// Int b = 5
// Int c = a + b

int main(){
	std::string type_str;
	std::cout<<"Type: ";
	std::cin>>type_str;
	
	ObjectBase::Type type = (type_str == "Real") ? ObjectBase::Type::REAL : ObjectBase::Type::INT;
	
	std::shared_ptr<ObjectBase> a = create(type);
	set(a, 5);
	
	std::shared_ptr<ObjectBase> b = create(type);
	set(b, 5);

	std::shared_ptr<ObjectBase> c = visit(a, b, add);
	
	std::cout<<c->toString()<<"\n";
}