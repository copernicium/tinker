#include <iostream>
#include <array>
#include <cassert>
#include <string>
#include <fstream>
#include <stdlib.h>

using namespace std;

static const string LOG_PATH="tic_tac_toe_logs/";
static const string LOG_TEMPLATE="tic_tac_toe_log_";
static string log_name="";

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
	Mark get(Box)const;
	void place(Box,Mark);
	bool done();
	State state()const;
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

Board::State Board::state()const{
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

void log(const Board board){
	ofstream out(LOG_PATH+log_name,ios::app);
	out<<"winner\n"<<board.state()<<"\nboard\n"<<board;
	out.close();
}

Mark to_mark(char str){
	if(str==' ')return Mark::BLANK;
	if(str=='X')return Mark::X;
	if(str=='O')return Mark::O;
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
	while(board.state()==Board::State::UNFINISHED){
		system("clear");
		cout<<"Tic Tac Toe\n"<<board;
		player_turn(board,player);
		player=(player==Mark::X)? Mark::O : Mark::X;
	}
	system("clear");
	cout<<"Tic Tac Toe\n"<<board;
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
	log(board);
}

int main(){
	/*array<string,11> test;
	ifstream in("test");
	unsigned int i=0;
	while(!in.eof()){
		string line;
		getline(in,line);
		test[i]=line;
		i++;
	}
	in.close();
	cout<<parse(test);
	assert(0);*/
	game();
	return 0;
}