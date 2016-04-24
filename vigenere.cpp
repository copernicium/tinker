#include <iostream>
#include <vector>
#include <cassert>
#include <string>

using namespace std;

const vector<char> alphabet={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

class Square{
	private:
	vector<vector<char>> square_;
	
	public:
	vector<vector<char>> get();
	friend ostream& operator<<(ostream&,Square);
	Square();
};

Square::Square():square_({{}}){
	square_={alphabet};
	vector<char> temp=alphabet;
	for(unsigned int i=0; i<alphabet.size()-1; i++){
		temp.push_back(temp.front());
		temp.erase(temp.begin());
		square_.push_back(temp);
	}
}

ostream& operator<<(ostream& o,Square a){
	vector<vector<char>> b=a.get();
	for(unsigned int i=0;i<b.size();i++){
		for(unsigned int j=0;j<b[i].size();j++){
			o<<b[i][j];
		}
		o<<"\n";
	}
	return o;
}

vector<vector<char>> Square::get(){
	return square_;
}

int find_letter(char a){
	for(unsigned int i=0; i<alphabet.size(); i++){
		if(tolower(a)==tolower(alphabet[i])) return i;
	}
	return -1;
}

string encode(string const message, string const filled){
	string encoded;
	for(unsigned int i=0; i<message.size(); i++){
		int column=find_letter(message[i]);
		if(column==-1){//if it's not a letter
			encoded+=message[i];
			continue;
		}
		int row=find_letter(filled[i]);
		assert(row!=-1);
		Square square;
		encoded+=square.get()[row][column];
	}
	return encoded;	
}

string fill(const string message,const string key){
	assert(message.size()>0 && key.size()>0);
	string filled;
	unsigned int j=0;
	for(unsigned int i=0; i<message.size(); i++){
		if(find_letter(message[i])==-1){
			filled+=message[i];
			continue;//leave punct
		}
		filled+=key[j%key.size()];
		j++;
	}
	return filled;
}

string decode(const string encoded, const string filled){
	(void)encoded;
	(void)filled;
	return " ";
} 

int main(){
	Square square;
	cout<<square<<"\n";
	string key,message;
	cout<<"Key:     ";
	cin>>key;
	cout<<"Message: ";
	cin>>message;
	string keyed=fill(message,key);
	cout<<"Keyed:   "<<keyed<<"\n";
	string encoded=encode(message,keyed);
	cout<<"Encoded: "<<encoded<<"\n";
	string decoded=decode(message,keyed);
	cout<<"Decoded: "<<decoded;
	cout<<"\n\n";
	return 0;
}

