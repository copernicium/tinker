#include <cassert>
#include <memory>

// Object types

struct ObjectBase{
	enum class Type{INT, REAL};

protected:
	Type type;

public:
	Type getType()const noexcept{
		return type;
	}
	
	ObjectBase(const Type& t)noexcept: type(t){}
	
	virtual std::string toString()const noexcept = 0;
};

template<typename T, ObjectBase::Type ObjectType>
struct Object: public ObjectBase{
private:
	T value;

public:
	T getValue()const noexcept{
		return value;
	}
	
	void setValue(T a)noexcept{
		value = a;
	}
	
	std::string toString()const noexcept{
		return "Object(" + std::to_string(value) + ")";
	}
	
	Object(T v)noexcept: ObjectBase(ObjectType), value(v){}
	Object()noexcept: ObjectBase(ObjectType), value(T()){}
};

using Int = Object<int,ObjectBase::Type::INT>;
using Real = Object<double, ObjectBase::Type::REAL>;

// Visitors

struct Add{
	template<typename T>
	std::shared_ptr<ObjectBase> operator()(T a, T b)const noexcept{
		return std::make_shared<T>(T(a.getValue() + b.getValue()));
	}
};
static constexpr Add add;

template<typename F>
std::shared_ptr<ObjectBase> visit(std::shared_ptr<ObjectBase> a, std::shared_ptr<ObjectBase> b, F op){
	assert(a->getType() == b->getType());
	switch(a->getType()){
	case ObjectBase::Type::REAL:
		return op(*std::dynamic_pointer_cast<Real>(a), *std::dynamic_pointer_cast<Real>(b));
	case ObjectBase::Type::INT:
		return op(*std::dynamic_pointer_cast<Int>(a), *std::dynamic_pointer_cast<Int>(b));
	default:
		assert(0);
	}
}

template<typename T>
void set(std::shared_ptr<ObjectBase> a, T value){
	switch(a->getType()){
	case ObjectBase::Type::REAL:
		std::dynamic_pointer_cast<Real>(a)->setValue(value);
		break;
	case ObjectBase::Type::INT:
		std::dynamic_pointer_cast<Int>(a)->setValue(value);
		break;
	default:
		assert(0);
	}
}
	
std::shared_ptr<ObjectBase> create(const ObjectBase::Type& TYPE){
	switch(TYPE){
	case ObjectBase::Type::REAL:
		return std::make_shared<Real>(Real());
	case ObjectBase::Type::INT:
		return std::make_shared<Int>(Int());
	default:
		assert(0);
	}
}