all: vigenere monte_carlo high_order_functions

high_order_functions: high_order_functions.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra high_order_functions.cpp -o "high_order_functions"

square_root: square_root.cpp 
	g++ -std=c++14 -g -Wall -Werror -Wextra square_root.cpp -o "square_root"

vigenere: vigenere.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

monte_carlo: monte_carlo.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra monte_carlo.cpp -o "monte_carlo"

.PHONY: clean
clean:
	rm -rf vigenere; rm -rf high_order_functions; rm -rf monte_carlo; rm -rf square_root;
