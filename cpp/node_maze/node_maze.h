#ifndef NODE_MAZE_H
#define NODE_MAZE_H

#include <iostream>
#include <vector>

static const unsigned DEFAULT_BOUND = 5;

#define DIRECTIONS \
	X(UP) \
	X(DOWN) \
	X(LEFT) \
	X(RIGHT)

enum class Direction{
	#define X(DIRECTION) DIRECTION, 
	DIRECTIONS
	#undef X
};

std::ostream& operator<<(std::ostream&,Direction const&);

struct Position{
	unsigned x;
	unsigned y;
	Position();
	Position(unsigned,unsigned);
};

bool operator==(Position const&,Position const&);

class Tile{
	public:
	#define TYPES \
		X(WALL) \
		X(START) \
		X(END) \
		X(EMPTY)
	enum class Type{
		#define X(TYPE) TYPE,
		TYPES
		#undef X	
	};

	private:
	Position position_;
	Type type_;

	public:
	Type type()const;
	Position position()const;
	
	std::string type_to_string()const;
	static Type parse_type(std::string const&);

	Tile();
	Tile(Position);
	Tile(Type);
	Tile(Position,Type);
};

std::ostream& operator<<(std::ostream&,Tile::Type const&);
std::ostream& operator<<(std::ostream&,Tile const&);

class Maze{
	std::vector<Tile> tiles;
	unsigned x_bound;
	unsigned y_bound;

	public:
	bool in_bounds(Position const&)const;

	Tile get(Position const&)const;
	std::vector<Tile> get_neighbors(Position const&)const;

	static Maze parse(std::string const&);
	std::string to_string()const;

	Maze();
	Maze(unsigned,unsigned);
};

class Node{ //TODO
	Position position;

	public:
	Node();	
};

class Node_maze{ //TODO

	public:
	Node_maze();
	Node_maze(Maze);
};

#endif
