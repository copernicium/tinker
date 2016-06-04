#include <iostream>
#include <array>
#include <cassert>
#include <string>
#include <stdlib.h>

using namespace std;

enum class Mark{BLANK,X,O};

template<class Type, long unsigned int Len>
std::ostream& operator<<(std::ostream& o,std::array<Type,Len> a){
	o<<"(";
	for(unsigned int i=0; i<a.size(); i++){
		o<<a[i];
		if(i<a.size()-1)o<<",";
	}
	return o<<")";
}

ostream& operator<<(ostream& o,Mark a){
	switch(a){
		case Mark::X: return o<<"X";
		case Mark::O: return o<<"O";
		case Mark::BLANK: return o<<" ";
		default: assert(0);
	}
}

struct Box{
	unsigned int row;
	unsigned int column;
	Box();
	Box(unsigned int,unsigned int);
};

Box::Box():row(0),column(0){}
Box::Box(unsigned int r,unsigned int c):row(r),column(c){}
ostream& operator<<(ostream& o,Box a){
	return o<<"("<<a.row<<","<<a.column<<")";
}

class Board{
	public:
	enum class State{UNFINISHED,DRAW,X,O};
	private:
	array<array<Mark,3>,3> boxes;
	public:
	Mark get(Box);
	void place(Box,Mark);
	bool done();
	State state();
	Board();
	Board(array<array<Mark,3>,3>);
};

void rotate_90(array<array<Mark,3>,3>& boxes){
	array<array<Mark,3>,3> new_boxes;
	for(unsigned int x=0; x<3; x++){
		array<Mark,3> row;
		for(unsigned int y=0; y<3; y++){
			row[y]=boxes[y][x];
		}
		new_boxes[2-x]=row;
	}
	boxes=new_boxes;
}

Board::State Board::state(){
	{
		array<array<Mark,3>,3> test=boxes;
		for(unsigned int angle=0; angle<360; angle+=90){
			{//horizontal and vertical
				for(unsigned int row=0; row<3; row++){
					#define ACROSS(MARK) if(test[row]==array<Mark,3>{Mark::MARK,Mark::MARK,Mark::MARK})return Board::State::MARK;
					ACROSS(X) ACROSS(O)
					#undef ACROSS
				}
			}
			{//diagonal
				#define DIAGONAL(MARK) if(test[0][0]==Mark::MARK && test[2][2]==Mark::MARK && test[3][3]==Mark::MARK)return Board::State::MARK;
				DIAGONAL(X) DIAGONAL(O)
				#undef DIAGONAL
			}
			rotate_90(test);
		}
	}
	for(unsigned int i=0; i<3;i++){
		for(unsigned int j=0; j<3; j++){
			if(boxes[i][j]==Mark::BLANK) return Board::State::UNFINISHED;
		}
	}
	return Board::State::DRAW;
}


Board::Board():boxes(){
	for(unsigned int i=0; i<3;i++){
		for(unsigned int j=0; j<3; j++){
			boxes[i][j]={Mark::BLANK};
		}
	}
}

Board::Board(array<array<Mark,3>,3> b):boxes(b){}

Mark Board::get(Box b){
	return boxes[b.row][b.column];
}

void Board::place(Box b,Mark m){
	assert(get(b)==Mark::BLANK);
	boxes[b.row][b.column]=m;
}

ostream& operator<<(ostream& o,Board a){
	for(unsigned int row=0; row<3; row++){
		o<<"   |   |   \n";
		#define X(ROW,COLUMN) " "<<(a.get({ROW,COLUMN}))<<" "
		o<<X(row,0)<<"|"<<X(row,1)<<"|"<<X(row,2)<<"\n";
		#undef X
		o<<"   |   |   \n";
		if(row<2)o<<"===========\n";
	}
	return o;
}

void player_turn(Board& board, const Mark mark){
	string s;
	cout<<"Where will you place your "<<mark<<"? ";
	cin>>s;
	Box box;
	{//set box to numbers on either side of comma
		unsigned int comma;
		for(; comma<s.size() && s[comma]!=','; comma++);
		box={(unsigned int)atoi((s.substr(0,comma)).c_str())-1,(unsigned int)atoi((s.substr(comma+1,s.size())).c_str())-1};
		assert(box.row<=3 && box.column<=3);
	}
	board.place(box,mark);
}

void game(){
	Board board;
	while(board.state()==Board::State::UNFINISHED){
		system("clear");
		cout<<"Tic Tac Toe\n"<<board;
		player_turn(board,Mark::X);
		system("clear");
		cout<<"Tic Tac Toe\n"<<board;
		if(board.state()==Board::State::UNFINISHED)player_turn(board,Mark::O);
	}
	switch(board.state()){
		case Board::State::DRAW:
			cout<<"It was a draw!";
			break;
		case Board::State::UNFINISHED:
			cout<<"ERROR GAME NOT OVER";
		case Board::State::X:
			cout<<"Player X wins!";
			break;
		case Board::State::O:
			cout<<"Player O wins!";
			break;
		default: assert(0);
	}
}

int main(){
	game();
	return 0;
}