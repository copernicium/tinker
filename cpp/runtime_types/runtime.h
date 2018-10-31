#include <cassert>
#include <memory>
#include <tuple>
#include <variant>
#include <vector>

template<typename T> 
struct remove_cvref{ // Feature in c++20
    using type = std::remove_cv_t<std::remove_reference_t<T>>;
};

template<typename T>
using remove_cvref_t = typename remove_cvref<T>::type; // Feature in c++20

// Meta type helpers

enum class Type{
	INT,
	REAL,
	CONTAINER
};

namespace detail{
	template<typename... Types>
	struct TypeInterfaceImpl{
		template<Type I>
		using element = std::tuple_element_t<static_cast<std::size_t>(I), std::tuple<Types...>>;

		using variant = std::variant<Types...>;

		static void reset(const Type& TYPE, variant& value){
			switch(TYPE){
			case Type::REAL:
				value = (element<Type::REAL>)0;
				return;
			case Type::INT:
				value = (element<Type::INT>)0;
				return;
			case Type::CONTAINER:
				value = element<Type::CONTAINER>();
				return;
			default:
				assert(0);
			}
		}
	};
}

struct Object;
using TypeInterface = detail::TypeInterfaceImpl<long, double, std::vector<std::shared_ptr<Object>>>;

// Visitor (used in Object class)

struct Assign{
	template<typename T, typename R>
	void operator()(const Type&, T& a, const R& b)const noexcept{
		a = b;
	}
};

Assign assign;

struct Object{
private:
	Type type;

	TypeInterface::variant value;

	// Constructors

	Object() = delete;

	Object(const Type& TYPE)noexcept: type(TYPE){
		TypeInterface::reset(type, value);
	}

	template<typename T>
	Object(const Type& TYPE, const T& VALUE)noexcept: Object(TYPE){
		visit(assign, *this, VALUE);
	}

	Object(const std::shared_ptr<Object>& TYPE, const std::size_t& SIZE)noexcept: Object(Type::CONTAINER){
		std::get<TypeInterface::element<Type::CONTAINER>>(value).reserve(SIZE);
		for(unsigned i = 0; i < SIZE; i++){
			std::get<TypeInterface::element<Type::CONTAINER>>(value).push_back(TYPE->clone());
		}
	}

	Object(const std::vector<std::shared_ptr<Object>>& TYPES)noexcept: Object(Type::CONTAINER){
		std::get<TypeInterface::element<Type::CONTAINER>>(value).reserve(TYPES.size());
		for(unsigned i = 0; i < TYPES.size(); i++){
			std::get<TypeInterface::element<Type::CONTAINER>>(value).push_back(TYPES[i]->clone());
		}
	}

public:
	//Explicit constructors

	static Object primitive(const Type& TYPE)noexcept{
		assert(TYPE != Type::CONTAINER);
		return Object(TYPE);
	}

	template<typename T>
	static Object primitive(const Type& TYPE, const T& VALUE)noexcept{
		assert(TYPE != Type::CONTAINER);
		return Object(TYPE, VALUE);
	}

	static Object list(const std::shared_ptr<Object>& TYPE, const std::size_t& SIZE)noexcept{
		return Object(TYPE, SIZE);
	}

	static Object collection(const std::vector<std::shared_ptr<Object>>& TYPES)noexcept{
		return Object(TYPES);
	}

	std::shared_ptr<Object> clone()const noexcept{
		Object a = *this;
		if(type == Type::CONTAINER){
			for(unsigned i = 0; i < size(); i++){
				std::get<TypeInterface::element<Type::CONTAINER>>(a.value).at(i) = std::get<TypeInterface::element<Type::CONTAINER>>(value).at(i)->clone();
			}
		}
		return std::make_shared<Object>(a);
	}

	template<typename... Args>
	static std::shared_ptr<Object> create(const Args&... ARGS)noexcept{
		return std::make_shared<Object>(Object(ARGS...));
	}

	// Accessors

	const Type& getType()const noexcept{
		return type;
	}

	Object& at(const std::size_t& I){
		assert(type == Type::CONTAINER);
		return *std::get<TypeInterface::element<Type::CONTAINER>>(value).at(I);
	}

	const Object& at(const std::size_t& I)const{
		assert(type == Type::CONTAINER);
		return *std::get<TypeInterface::element<Type::CONTAINER>>(value).at(I);
	}

	std::size_t size()const noexcept{
		assert(type == Type::CONTAINER);
		return std::get<TypeInterface::element<Type::CONTAINER>>(value).size();
	}

	// Visit functions

	template<typename F, typename TObject1, typename TObject2, typename... Args, typename = std::enable_if_t<std::is_same_v<remove_cvref_t<TObject1>, Object> && std::is_same_v<remove_cvref_t<TObject2>, Object>>>
	static auto visit(const F& f, TObject1& a, TObject2& b, Args&... args){
		assert(a.getType() == b.getType());

		switch(a.getType()){
		case Type::REAL:
			return f(a.getType(), std::get<TypeInterface::element<Type::REAL>>(a.value), std::get<TypeInterface::element<Type::REAL>>(b.value), args...);
		case Type::INT:
			return f(a.getType(), std::get<TypeInterface::element<Type::INT>>(a.value), std::get<TypeInterface::element<Type::INT>>(b.value), args...);
		case Type::CONTAINER:
			// Undefined, fall through
		default:
			assert(0);
		}
	}

	template<typename F, typename TObject, typename... Args, typename = std::enable_if_t<std::is_same_v<remove_cvref_t<TObject>, Object>>>
	static auto visit(const F& f, TObject& a, Args&... args){
		switch(a.getType()){
		case Type::REAL:
			return f(a.getType(), std::get<TypeInterface::element<Type::REAL>>(a.value), args...);
		case Type::INT:
			return f(a.getType(), std::get<TypeInterface::element<Type::INT>>(a.value), args...);
		case Type::CONTAINER:
			// Undefined, fall through
		default:
			assert(0);
		}
	}
};

// Visitors

struct ToString{
	template<typename T>
	std::string operator()(const Type&, const T& a)const noexcept{
		return std::to_string(a);
	}
};

ToString toString;

struct Add{
	template<typename T>
	Object operator()(const Type& TYPE, const T& a, const T& b)const noexcept{
		assert(TYPE != Type::CONTAINER);
		return Object::primitive(TYPE, a + b);
	}
};

Add add;