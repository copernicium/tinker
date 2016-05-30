#include <iostream>
#include <array>
#include <cassert>
#include <string>

using namespace std;

enum class Mark{BLANK,X,O};

string mark_to_string(Mark a){
	switch(a){
		case Mark::X: return "X";
		case Mark::O: return "O";
		case Mark::BLANK: return " ";
		default: assert(0);
	}
}

ostream& operator<<(ostream& o,Mark a){
	return o<<mark_to_string(a);
}

struct Box{
	unsigned int row;
	unsigned int column;
	Box();
	Box(unsigned int,unsigned int);
};

Box::Box():row(0),column(0){}
Box::Box(unsigned int r,unsigned int c):row(r),column(c){}

class Board{
	array<array<Mark,3>,3> boxes;
	public:
	Mark get(Box);
	void place(Box,Mark);
	Board();
};

Board::Board():boxes(){}

Mark Board::get(Box b){
	return boxes[b.row][b.column];
}

void Board::place(Box b,Mark m){
	boxes[b.row][b.column]=m;
}

ostream& operator<<(ostream& o,Board a){
	for(unsigned int row=0; row<3; row++){
		o<<"   |   |   \n";
		#define X(ROW,COLUMN) mark_to_string(a.get({ROW,COLUMN}))
		o<<" "<<X(row,0)<<" | "<<X(row,1)<<" | "<<X(row,2)<<" \n";
		#undef X
		o<<"   |   |   \n";
		if(row<2)o<<"===========\n";
	}
	return o;
}

int main(){
	Board b;
	cout<<b;
	return 0;
}