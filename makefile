all: vigenere game_of_life

vigenere: vigenere.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

game_of_life: game_of_life.cpp
	g++ -std=c++11 -DTIME_UNITS=milliseconds -g -Wall -Werror -Wextra game_of_life.cpp -o "game_of_life"

.PHONY: clean
clean:
	rm -rf vigenere; rm -rf game_of_life;
