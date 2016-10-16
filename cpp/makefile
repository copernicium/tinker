all: vigenere tic_tac_toe monte_carlo 

square_root: square_root.cpp 
	g++ -std=c++14 -g -Wall -Werror -Wextra square_root.cpp -o "square_root"

vigenere: vigenere.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

tic_tac_toe: tic_tac_toe.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra tic_tac_toe.cpp -o "tic_tac_toe"

monte_carlo: monte_carlo.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra monte_carlo.cpp -o "monte_carlo"

.PHONY: clean
clean:
	rm -rf vigenere; rm -rf tic_tac_toe; rm -rf monte_carlo; rm -rf square_root;
