#include "tic_tac_toe.h"

#include <iostream>
#include <array>
#include <cassert>
#include <string>
#include <fstream>
#include <stdlib.h>

using namespace std;

ostream& operator<<(ostream& o,Mark a){
	switch(a){
		case Mark::X: return o<<"X";
		case Mark::O: return o<<"O";
		case Mark::BLANK: return o<<" ";
		default: assert(0);
	}
}

Box::Box():row(0),column(0){}
Box::Box(unsigned int r,unsigned int c):row(r),column(c){}
ostream& operator<<(ostream& o,Box a){
	return o<<"("<<a.row<<","<<a.column<<")";
}

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

array<Mark,3> get_diagonal(const array<array<Mark,3>,3> boxes){
	return {boxes[0][0],boxes[1][1],boxes[2][2]};
}

Move::Move():mark(),box(){}
Move::Move(Mark m,Box b):mark(m),box(b){}

Board::State Board::state()const{
	{
		array<array<Mark,3>,3> test=boxes;
		for(unsigned int angle=0; angle<360; angle+=90){
			{//horizontal and vertical
				for(unsigned int row=0; row<3; row++){
					#define ACROSS(MARK) if(test[row]==array<Mark,3>{Mark::MARK,Mark::MARK,Mark::MARK})	return Board::State::MARK;
					ACROSS(X) ACROSS(O)
					#undef ACROSS
				}
			}
			{//diagonal
				#define DIAGONAL(MARK) if(get_diagonal(test)==array<Mark,3>{Mark::MARK,Mark::MARK,Mark::MARK})return Board::State::MARK; \
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

ostream& operator<<(ostream& o,Board::State a){
	#define Y(NAME) if(a==Board::State::NAME)return o<<""#NAME;
	Y(UNFINISHED) Y(X) Y(O) Y(DRAW)
	#undef Y
	assert(0);
}

Board::Board():boxes(){
	for(unsigned int i=0; i<3;i++){
		for(unsigned int j=0; j<3; j++){
			boxes[i][j]={Mark::BLANK};
		}
	}
}

Board::Board(array<array<Mark,3>,3> b):boxes(b){}

Mark Board::get(Box b)const{
	return boxes[b.row][b.column];
}

Board_log::Board_log():board(),moves(){}

bool file_exists(string file_name) {
    ifstream file;
    file.open(file_name);
    bool file_exists = file.is_open();
    file.close();
    return file_exists;
}

ostream& operator<<(ostream& o,const Board a){
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

ostream& operator<<(ostream& o,const Move a){
	return o<<a.mark<<" "<<a.box;
}

ostream& operator<<(ostream& o,const Board_log a){
	o<<"\n"<<a.board;
	o<<"moves:"<<a.moves;
	return o;
}

void make_file_name(){
	unsigned int file_number=0;
	string file_name=LOG_TEMPLATE;
	while(true){
		file_name.append(to_string(file_number));
		if(!file_exists(LOG_PATH+file_name))break;
		file_name.erase(file_name.size()-1,file_name.size());
		file_number++;
	}
	log_name=file_name;
}

void log(const Box box,const Mark player){
	if(log_name=="")make_file_name();
	ofstream out(LOG_PATH+log_name,ios::app);
	out<<player<<" "<<box<<"\n";
	out.close();
}

void check_for_duplicates(){
	unsigned int numer_of_logs=stoi(log_name.substr(log_name.size()-1,log_name.size()))+1;
	vector<Board_log> logged_boards;
	for(unsigned int i=0; i<numer_of_logs; i++){
		string file_name=LOG_TEMPLATE;
		file_name.append(to_string(i));
		logged_boards.push_back(parse(file_name));
	}
}

void log(const Board board){
	ofstream out(LOG_PATH+log_name,ios::app);
	out<<"state\n"<<board.state()<<"\nboard\n"<<board;
	out.close();
	check_for_duplicates();
}

Mark to_mark(const char c){
	if(c==' ')return Mark::BLANK;
	if(c=='X')return Mark::X;
	if(c=='O')return Mark::O;
	assert(0);
}

Board parse(array<string,11> board_str){
	Board board;
	array<unsigned int,3> in_str{1,5,9};
	for(unsigned int row=0; row<3; row++){
		for(unsigned int column=0; column<3; column++){
			board.place({row,column},to_mark(board_str[in_str[row]][in_str[column]]));
		}
	}
	return board;
}

Board_log parse(string log_name){
	vector<string> file;
	ifstream in(LOG_PATH+log_name);
	while(!in.eof()){
		string line;
		getline(in,line);
		file.push_back(line);
	}
	in.close();
	Board_log board_log;
	{//get moves
		for(unsigned int i=0; i<file.size(); i++){
			if(file[i]=="state")break;
			board_log.moves.push_back({to_mark(file[i][0]),{(unsigned int)(file[i][3]-'0'),(unsigned int)(file[i][5]-'0')}});
		}
	}
	{//get board
		array<string,11> board_str;
		unsigned int start_parse=0;
		for(; start_parse<file.size(); start_parse++){
			if(file[start_parse]=="board"){
				start_parse++;
				break;
			}
		}
		for(unsigned int i=start_parse; i<start_parse+board_str.size(); i++){
			board_str[i-start_parse]=file[i];
		}
		board_log.board=parse(board_str);
	}
	cout<<board_log<<"\n";
	return board_log;
}

void Board::place(Box box,Mark mark){
	log(box,mark);
	assert(get(box)==Mark::BLANK);
	boxes[box.row][box.column]=mark;
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
	Mark player=Mark::X;
	Board::State state=Board::State::UNFINISHED;
	while(state==Board::State::UNFINISHED){
		system("clear");
		cout<<"Tic Tac Toe\n"<<board;
		player_turn(board,player);
		player=(player==Mark::X)? Mark::O : Mark::X;
		state=board.state();
	}
	system("clear");
	cout<<"Tic Tac Toe\n"<<board;
	switch(state){
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
	log(board);
}

int main(){
	game();
	return 0;
}