#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

string toMorseImpl(char in){
	switch(in){
	case ' ':
		return " ";
	case 'a':
		return ".-";
	case 'b':
		return "-...";
	case 'c':
		return "-.-.";
	case 'd':
		return "-..";
	case 'e':
		return ".";
	case 'f':
		return "..-.";
	case 'g':
		return "--.";
	case 'h':
		return "....";
	case 'i':
		return "..";
	case 'j':
		return ".---";
	case 'k':
		return "-.-";
	case 'l':
		return ".-..";
	case 'm':
		return "--";
	case 'n':
		return "-.";
	case 'o':
		return "---";
	case 'p':
		return ".--.";
	case 'q':
		return "--.-";
	case 'r':
		return ".-.";
	case 's':
		return "...";
	case 't':
		return "-";
	case 'u':
		return "..-";
	case 'v':
		return "...-";
	case 'w':
		return ".--";
	case 'x':
		return "-..-";
	case 'y':
		return "-.--";
	case 'z':
		return "--..";
	default:
		return "error";
	}
	return "";
}

string toMorse(const string& in){
	string out;
	for(char c: in){
		out += toMorseImpl(c) + " ";
	}
	return out;
}

char fromMorseImpl(const string& in){
	if(in == ".-"){
		return 'a';
	} else if(in == "-..."){
		return 'b';
	} else if(in == "-.-."){
		return 'c';
	} else if(in == "-.."){
		return 'd';
	} else if(in == "."){
		return 'e';
	} else if(in == "..-."){
		return 'f';
	} else if(in == "--."){
		return 'g';
	} else if(in == "...."){
		return 'h';
	} else if(in == ".."){
		return 'i';
	} else if(in == ".---"){
		return 'j';
	} else if(in == "-.-"){
		return 'k';
	} else if(in == ".-.."){
		return 'l';
	} else if(in == "--"){
		return 'm';
	} else if(in == "-."){
		return 'n';
	} else if(in == "---"){
		return 'o';
	} else if(in == ".--."){
		return 'p';
	} else if(in == "--.-"){
		return 'q';
	} else if(in == ".-."){
		return 'r';
	} else if(in == "..."){
		return 's';
	} else if(in == "-"){
		return 't';
	} else if(in == "..-"){
		return 'u';
	} else if(in == "...-"){
		return 'v';
	} else if(in == ".--"){
		return 'w';
	} else if(in == "-..-"){
		return 'x';
	} else if(in == "-.--"){
		return 'y';
	} else if(in == "--.."){
		return 'z';
	}
	return '?';
}

string fromMorse(const string& in){
	string out = "";
	string temp = "";
	for(char c: in){
		if(c != ' '){
			temp += c;
		} else {
			out += fromMorseImpl(temp);
			temp = "";
		}
	}
	return out;
}

char flipMorse(char in){
	switch(in){
	case '.':
		return '-';
	case '-':
		return '.';
	case ' ':
		return ' ';
	default:
		return 'e';
	}
}

string flip(const string& in){
	string out = "";
	for(char c: in){
		out += flipMorse(c);
	}
	return out;
}

vector<string> read(){
	vector<string> v;
	std::ifstream f("hangman/words.txt");
	while(!f.eof()){
		string temp;
		getline(f, temp);
		temp.erase(temp.size() -1 , 1);
		transform(temp.begin(), temp.end(), temp.begin(), ::tolower); 
		v.push_back(temp);
	}
	f.close();
	return v;
}

bool isWord(const string& I, const vector<string>& v){
	return find(v.begin(), v.end(), I) != v.end();
}

int main(){
	vector<string> v = read();
	for(string a: v){
		string morse = toMorse(a);
		string flipped = flip(morse);
		string reverted = fromMorse(flipped);
		//cout << a << " " << reverted << "\n";
		if(isWord(reverted, v)){
			cout << a << " becomes " << reverted << "\n";
		}
	}
	
	return 0;
}