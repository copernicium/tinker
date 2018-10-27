all: runtime.out

runtime.out: runtime.h runtime.cpp
	g++ -std=c++17 -g -Wall -Werror -Wextra runtime.cpp -o "runtime.out"

.PHONY: clean
clean:
	rm -rf \*.out;
