all: converter

converter: converter.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra converter.cpp ../util/util.cpp -o "converter"

.PHONY: clean
clean:
	rm -rf converter;
