#include "tic_tac_toe.h"

#include <iostream>
#include <cassert>

using namespace std;

Position::Position():column(Column::LEFT),row(Row::BOTTOM){}
Position::Position(Column c,Row r):column(c),row(r){}
Position::Position(unsigned int const& c,unsigned int const& r){
	assert(c < Column::COLUMN);
	assert(r < Row::ROW);
	column = (Column)c;
	row = (Row)r;
}

ostream& operator<<(ostream& o, Mark a){
	string s = "" + a;
	return o<<s;
}

string operator+(const string STR, const Mark mark){
	string str = STR;
	switch(mark){
		case Mark::X:
			str += "X";
			break;
		case Mark::O:
			str += "O";
			break;
		case Mark::BLANK:
			str += " ";
			break;
		default:
			assert(0);
	}
	return str;
}

bool operator==(Position const& a, Position const& b){
	if(a.column != b.column) return false;
	return a.row == b.row;
}

void Box::set(Mark mk){
	mark = mk;
}
	
Mark Box::get_mark()const{
	return mark;
}

Position Box::get_position()const{
	return position;
}

Box::Box():mark(Mark::BLANK),position({}){}
Box::Box(Position pos):mark(Mark::BLANK),position(pos){}

void Board::set(Position position,Mark mark){
	for(unsigned int i = 0; i < boxes.size(); i++){
		if(boxes[i].get_position() == position){
			boxes[i].set(mark);
			return;
		}
	}
	NYI
}

Mark Board::get(Position position)const{
	for(Box box: boxes){
		if(box.get_position() == position) return box.get_mark();
	}
	NYI
}

void Board::print()const{
	string board;
	for(unsigned int row=0; row<3; row++){
		board +="   |   |   \n";
		#define X(ROW,COLUMN) " " + (get({ROW,COLUMN})) + " "
		board += X(row,0) + "|" + X(row,1) + "|" + X(row,2) + "\n";
		#undef X
		board += "   |   |   \n";
		if(row<2) board += "===========\n";
	}
	cout<<board;
}

void Board::play(){
	print();
}

Board::Board():player_turn(Mark::X){
	unsigned int column = 0, row = 0;
	for(unsigned int i = 0; i < boxes.size(); i++){
		boxes[i] = Box(Position(column, row));
		column++;
		if(column >= Column::COLUMN){
			row++;
			column = 0;
		} 
	}
}

void Board::log(Move)const{
	NYI;
}

int main(){
	Board board;
	board.play();
	return 0;
}