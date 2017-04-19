#include "node_maze.h"
#include "../util/util.h"
#include "../util/nyi.h"

using namespace std;

ostream& operator<<(ostream& o,Direction const& A){
	switch(A){
		#define X(DIRECTION) case Direction::DIRECTION: return o<<""#DIRECTION;
		DIRECTIONS
		#undef X
		default:
			NYI
	};
}

Position Position::up()const{
	Position p = *this;
	p.up();
	return p;
}

Position Position::down()const{
	Position p = *this;
	p.down();
	return p;
}

Position Position::left()const{
	Position p = *this;
	p.left();
	return p;
}

Position Position::right()const{
	Position p = *this;
	p.right();
	return p;
}

void Position::up(){
	y++;
}

void Position::down(){
	y--;
}

void Position::left(){
	x--;
}

void Position::right(){
	x++;
}

void Position::move(Direction const& dir){
	switch(dir){
		case Direction::UP:
			up();
			break;
		case Direction::DOWN:
			down();
			break;
		case Direction::LEFT:
			left();
			break;
		case Direction::RIGHT:
			right();
		default:
			NYI
	}
}

bool operator==(Position const& A,Position const& B){
	if(A.x != B.x) return false;
	if(A.y != B.y) return false;
	return true;
}

Position::Position(unsigned a,unsigned b):x(a),y(b){}
Position::Position():Position(0,0){}

Tile::Tile(Position p, Type t):position_(p),type_(t){}
Tile::Tile(Type t):Tile({},t){}
Tile::Tile(Position p):Tile(p,Type::EMPTY){}

Position Tile::position()const{
	return position_;
}

string Tile::type_to_string()const{
	switch(type_){
		case Type::WALL: return "X";
		case Type::START: return "S";
		case Type::END: return "E";
		case Type::EMPTY: return " ";
		default:
			NYI
	}
}

Tile::Type Tile::parse_type(string const& S){
	if(S == " ") return Type::EMPTY;
	if(S == "E") return Type::END;
	if(S == "S") return Type::START;
	if(S == "X") return Type::WALL;
	NYI
}

Tile::Type Tile::type()const{
	return type_;
}

ostream& operator<<(ostream& o,Tile::Type const& T){
	return o<<(new Tile(T))->type_to_string();
}

ostream& operator<<(ostream& o,Tile const& T){
	o<<"Tile(";
	o<<"pos:"<<T.position();
	o<<" type:"<<T.type();
	return o<<")";
}

bool Maze::in_bounds(Position const& POS)const{
	return POS.x < x_bound && POS.y < y_bound;
}

Tile Maze::get(Position const& POS)const{
	for(Tile a: tiles){
		if(a.position() == POS) return a;
	}
	cout<<"Error: Tile not found at "<<POS<<"\n";
	exit(44);
}

vector<Tile> Maze::get_neighbors(Position const& POS)const{
	vector<Tile> v;
	if((int)POS.x - 1 >= 0) v.push_back(get({POS.x - 1, POS.y}));	
	if(POS.x + 1 < x_bound) v.push_back(get({POS.x + 1, POS.y}));	
	if((int)POS.y - 1 >= 0) v.push_back(get({POS.x, POS.y - 1}));	
	if(POS.y + 1 < y_bound) v.push_back(get({POS.x, POS.y + 1}));	
	return v;
}

vector<vector<string>> make_empty_str_maze(unsigned const& X_BOUND,unsigned const& Y_BOUND){
	return [&]{
		vector<string> empty_row;
		for(unsigned i = 0; i < X_BOUND; i++){
			empty_row.push_back(" ");
		}
		vector<vector<string>> empty_maze;
		for(unsigned i = 0; i < Y_BOUND; i++){
			empty_maze.push_back(empty_row);
		}
		return empty_maze;
	}();
}

Maze Maze::parse(string const& STR_MAZE){
	Maze maze;
	{
		unsigned x = 0, y = 0;
		for(char a: STR_MAZE){
			if(a == '\n'){
				y++;
				x = 0;
				continue;
			}
			{
				string type;
				type += a;
				maze.tiles.push_back({{x,y},Tile::parse_type(type)});
			}

			x++;
		}
	}
	return maze;
}

string Maze::to_string()const{
	vector<vector<string>> str_maze = make_empty_str_maze(x_bound,y_bound);
	
	for(Tile tile: tiles){
		str_maze[tile.position().y][tile.position().x] = tile.type_to_string();
	}

	for(unsigned i: range(y_bound)){
		str_maze[i].push_back("\n");
	}

	string s;
	
	for(vector<string> row: str_maze){
		for(string a: row){
			s += a;
		}
	}

	return s;
}

unsigned gen_rand(const unsigned LIM){
	return rand() % LIM;
}

vector<Direction> get_possible_dirs(Position const& pos, vector<Position> const& walls){
	(void)walls;
	static const vector<Direction> ALL_DIRS = {
		#define X(DIR) Direction::DIR,
		DIRECTIONS
		#undef X
	};

	vector<Direction> possible_dirs;

	for(Direction dir: ALL_DIRS){
		Position p = pos;
		p.move(dir);

		bool occupied = false;
		for(Position wall: walls){
			if(wall == p){
				occupied = true;
				break;
			}
		}
		if(!occupied) possible_dirs.push_back(dir);
		
	}
	return possible_dirs;
}

void generate(unsigned const& X_BOUND,unsigned const& Y_BOUND,vector<Position>& visited,vector<Tile>& walls,bool& done){
	(void)X_BOUND;
	(void)Y_BOUND;
	(void)visited;
	(void)walls;
	if(done){
		return;
	}
	NYI	
}

Maze::Maze():x_bound(DEFAULT_BOUND),y_bound(DEFAULT_BOUND){ //TODO: call other constructor 
	tiles = [&]{
		vector<Tile> v;
		for(unsigned x: range(x_bound)){
			for(unsigned y: range(y_bound)){
				v.push_back({{x,y},Tile::Type::WALL});
			}
		}
		return v;
	}();
}

Maze::Maze(unsigned a,unsigned b):x_bound(a),y_bound(b){
	{
		vector<Position> start = {{gen_rand(x_bound),gen_rand(y_bound)}};
		bool done = false;
		generate(x_bound,y_bound,start,tiles,done);
	}
}

int main(){
	{
		srand(time(NULL));
		Maze a = {10,10};
		cout<<"a:\n"<<a.to_string();
		Maze b = Maze::parse(a.to_string());
		cout<<"b:\n"<<b.to_string();
	}	
	NYI
	return 0;	
}
