all: vigenere game_of_life tic_tac_toe monte_carlo 

vigenere: vigenere.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

game_of_life: game_of_life.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra game_of_life.cpp -o "game_of_life"

tic_tac_toe: tic_tac_toe.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra tic_tac_toe.cpp -o "tic_tac_toe"

monte_carlo: monte_carlo.cpp
	g++ -std=c++11 -g -Wall -Werror -Wextra monte_carlo.cpp -o "monte_carlo"

.PHONY: clean
clean:
	rm -rf vigenere; rm -rf game_of_life; rm -rf tic_tac_toe; rm -rf monte_carlo;
