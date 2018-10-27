#pragma once

#include <iostream>
#include <memory>
#include <type_traits>

struct Base{
	virtual void run()const = 0;
};

struct Input{
private:
	static std::shared_ptr<std::string> in;

public:
	static std::shared_ptr<Base> create(const std::string&);

	static std::shared_ptr<Base> create();

	Input() = delete;
};

struct A: public Base{
private:
	std::shared_ptr<Base> inner;

public:
	void run()const{
		std::cout << "Ran A -> ";
		inner->run();
	}

	static A setup(){
		A a;
		a.inner = Input::create();
		return a;
	}

	A():inner(nullptr){}
};

struct B: public Base{
	void run()const{
		std::cout << "Ran B";
	}

	static B setup(){
		return B();
	}
};

struct C: public Base{
private:
	std::shared_ptr<Base> inner;

public:
	void run()const{
		std::cout << "Ran C -> ";
		inner->run();
	}

	static C setup(){
		C a;
		a.inner = Input::create();
		return a;
	}

	C():inner(nullptr){}
};
