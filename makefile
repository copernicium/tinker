all: vigenere

vigenere: vigenere.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

.PHONY: clean
clean:
	rm -rf vigenere;
