all: vigenere monte_carlo square_root animals random_number_generator simple_time_test temperature_control num_converter random_word_generator text_adventure_alpha vigenere_old pi_helper reader prime_finder #skip_printer high_order_functions

#high_order_functions: high_order_functions.cpp \
	g++ -std=c++14 -g -Wall -Werror -Wextra high_order_functions.cpp -o "high_order_functions"

square_root: square_root.cpp 
	g++ -std=c++14 -g -Wall -Werror -Wextra square_root.cpp -o "square_root"

vigenere: vigenere.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra vigenere.cpp -o "vigenere"

monte_carlo: monte_carlo.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra monte_carlo.cpp util/simple_time.cpp -o "monte_carlo"

animals: animals.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra animals.cpp -o "animals"


random_number_generator: random_number_generator.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra random_number_generator.cpp util/simple_time.cpp -o "random_number_generator"

simple_time_test: simple_time_test.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra simple_time_test.cpp util/simple_time.cpp -o "simple_time_test"

temperature_control: temperature_control.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra temperature_control.cpp -o "temperature_control"

num_converter: num_converter.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra num_converter.cpp -o "num_converter"

random_word_generator: random_word_generator.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra random_word_generator.cpp util/simple_time.cpp -o "random_word_generator"

skip_printer: skip_printer.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra skip_printer.cpp -o "skip_printer"

text_adventure_alpha: text_adventure_alpha.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra text_adventure_alpha.cpp -o "text_adventure_alpha"

vigenere_old: vigenere_old.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra vigenere_old.cpp -o "vigenere_old"

pi_helper: pi_helper.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra pi_helper.cpp -o "pi_helper"

reader: reader.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra reader.cpp -o "reader"

prime_finder: prime_finder.cpp
	g++ -std=c++14 -g -Wall -Werror -Wextra prime_finder.cpp -o "prime_finder"

.PHONY: clean
clean:
	rm -rf vigenere; rm -rf high_order_functions; rm -rf monte_carlo; rm -rf square_root; rm -rf animals; rm -rf random_number_generator; rm -rf simple_time_test; rm -rf temperature_control; rm -rf num_converter; rm -rf random_word_generator; rm -rf text_adventure_alpha; rm -rf vigenere_old; rm -rf pi_helper; rm -rf reader; rm -rf prime_finder; rm -rf skip_printer;
