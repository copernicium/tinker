#include <iostream>
#include <array>
#include <string>
#include <vector>

using namespace std;

static const string LOG_PATH="tic_tac_toe_logs/";
static const string LOG_TEMPLATE="tic_tac_toe_log_";
static string log_name="";

enum class Mark{BLANK,X,O};

struct Box{
	unsigned int row;
	unsigned int column;
	Box();
	Box(unsigned int,unsigned int);
};

class Board{
	public:
	enum class State{UNFINISHED,DRAW,X,O};
	private:
	array<array<Mark,3>,3> boxes;
	public:
	Mark get(Box)const;
	void place(Box,Mark);
	State state()const;
	Board();
	Board(array<array<Mark,3>,3>);
};

struct Move{
	Mark mark;
	Box box;
	Move();
	Move(Mark,Box);
};

struct Board_log{
	//string file_name;
	Board board;
	vector<Move> moves;
	Board_log();
};

template<class Type, long unsigned int Len>
ostream& operator<<(ostream& o,array<Type,Len> a){
	o<<"(";
	for(unsigned int i=0; i<a.size(); i++){
		o<<a[i];
		if(i<a.size()-1)o<<",";
	}
	return o<<")";
}

template<typename T>
std::ostream& operator<<(std::ostream& o,std::vector<T> v){
	o<<"(";
	for(unsigned int i=0; i<v.size(); i++){
		o<<v[i];
		if(i<v.size()-1) o<<",";
	}
	return o<<")";
}

ostream& operator<<(ostream&,Mark);
ostream& operator<<(ostream& o,Box a);
ostream& operator<<(ostream&,Board::State);
ostream& operator<<(ostream&,const Board);
ostream& operator<<(ostream&,const Move);
ostream& operator<<(ostream&,const Board_log);

Board_log parse(string);