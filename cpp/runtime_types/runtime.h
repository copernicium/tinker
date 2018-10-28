#include <cassert>
#include <vector>
#include <memory>

struct ObjectBase{
	enum class Type{
		INT,
		REAL,
		LIST,
		COLLECTION
	};

protected:
	Type type;

public:
	Type getType()const noexcept{
		return type;
	}

	ObjectBase() = delete;

	ObjectBase(const Type& t)noexcept: type(t){}

	virtual std::shared_ptr<ObjectBase> clone()const noexcept = 0;

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

	void setValue(const T& a)noexcept{
		value = a;
	}

	std::string toString()const noexcept{
		return std::to_string(value);
	}

	std::shared_ptr<ObjectBase> clone()const noexcept{
		return std::make_shared<Object<T, ObjectType>>(*this);
	}

	Object(T v)noexcept: ObjectBase(ObjectType), value(v){}

	Object()noexcept: ObjectBase(ObjectType), value(T()){}
};

using Int = Object<int,ObjectBase::Type::INT>;
using Real = Object<double, ObjectBase::Type::REAL>;

struct Add{
	template<typename T>
	std::shared_ptr<ObjectBase> operator()(const T& a, const T& b)const noexcept{
		return std::make_shared<T>(T(a.getValue() + b.getValue()));
	}
};

constexpr Add add;

template<typename F, typename... Args>
std::shared_ptr<ObjectBase> visit(const std::shared_ptr<ObjectBase>& a, const std::shared_ptr<ObjectBase>& b, const F& f, const Args&... args){
	assert(a->getType() == b->getType());

	switch(a->getType()){
	case ObjectBase::Type::REAL:
		return f(*std::dynamic_pointer_cast<Real>(a), *std::dynamic_pointer_cast<Real>(b), args...);
	case ObjectBase::Type::INT:
		return f(*std::dynamic_pointer_cast<Int>(a), *std::dynamic_pointer_cast<Int>(b), args...);
	default:
		assert(0);
	}
}

struct Assign{
	template<typename T, typename R>
	void operator()(const T& a, const R& b)const noexcept{
		a->setValue(b);
	}
};

constexpr Assign assign;

template<typename F, typename... Args>
void visit(const std::shared_ptr<ObjectBase>& a, const F& f, const Args&... args){
	switch(a->getType()){
	case ObjectBase::Type::REAL:
		f(std::dynamic_pointer_cast<Real>(a), args...);
		break;
	case ObjectBase::Type::INT:
		f(std::dynamic_pointer_cast<Int>(a), args...);
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

struct Container: public ObjectBase{
protected:
	std::vector<std::shared_ptr<ObjectBase>> inner;

	Container(const ObjectBase::Type& TYPE): ObjectBase(TYPE){}

public:
	std::shared_ptr<ObjectBase>& at(const std::size_t& I)noexcept{
		return inner.at(I);
	}

	const std::shared_ptr<ObjectBase>& at(const std::size_t& I)const noexcept{
		return inner.at(I);
	}

	std::size_t size()const noexcept{
		return inner.size();
	}

	std::string toString()const noexcept{
		std::string s = "[";
		for(const auto& a: inner){
			s += a->toString() + " ";
		}
		s += "]";
		return s;
	}
};

struct List: public Container{
	std::shared_ptr<ObjectBase> clone()const noexcept{
		return std::make_shared<List>(*this);
	}

	List(const std::shared_ptr<ObjectBase>& TYPE, const std::size_t& SIZE)noexcept: Container(ObjectBase::Type::LIST){
		this->inner.reserve(SIZE);
		for(unsigned i = 0; i < SIZE; i++){
			inner.push_back(TYPE->clone());
		}
	}
};

std::shared_ptr<ObjectBase> create(const std::shared_ptr<ObjectBase>& TYPE, const std::size_t& SIZE){
	return std::make_shared<List>(List(TYPE, SIZE));
}

struct Collection: public Container{
	std::shared_ptr<ObjectBase> clone()const noexcept{
		return std::make_shared<Collection>(*this);
	}

	Collection(const std::vector<std::shared_ptr<ObjectBase>>& TYPES)noexcept: Container(ObjectBase::Type::COLLECTION){
		this->inner.reserve(TYPES.size());
		for(unsigned i = 0; i < TYPES.size(); i++){
			inner.push_back(TYPES[i]->clone());
		}
	}
};

std::shared_ptr<ObjectBase> create(const std::vector<std::shared_ptr<ObjectBase>>& TYPES){
	return std::make_shared<Collection>(Collection(TYPES));
}

std::shared_ptr<ObjectBase> at(const std::shared_ptr<ObjectBase>& CONTAINER, const std::size_t& I){
	switch(CONTAINER->getType()){
	case ObjectBase::Type::LIST:
		return std::dynamic_pointer_cast<List>(CONTAINER)->at(I);
	case ObjectBase::Type::COLLECTION:
		return std::dynamic_pointer_cast<Collection>(CONTAINER)->at(I);
	default:
		assert(0);
	}
}
