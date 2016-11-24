#ifndef TIC_TAC_TOE_H
#define TIC_TAC_TOE_H

#include <iostream>
#include <array>

#define NYI \
	std::cout<<__FILE__<<":"<<__LINE__<<":NYI\n"; \
	exit(0);

enum class Mark{X,O,BLANK};
enum Column{LEFT,CENTER,RIGHT,COLUMN};
enum Row{TOP,MIDDLE,BOTTOM,ROW};
enum class Status{X_WIN,O_WIN,DRAW,IN_PROGRESS};

std::string operator+(const std::string, const Mark);
std::ostream& operator<<(std::ostream&, Mark);

struct Position{
	Column column;
	Row row;
	Position();
	Position(Column,Row);
	Position(unsigned int const&,unsigned int const&);
};

bool operator==(Position const&, Position const&);

class Box{
	Mark mark;
	Position position;
	
	public:
	void set(Mark);
	Mark get_mark()const;
	Position get_position()const;
	
	Box();
	Box(Position);
};

using Move = Box;

class Board{
	Mark player_turn;
	std::array<Box, Column::COLUMN*Row::ROW> boxes;
	Status status;
	
	void set(Position,Mark);
	Mark get(Position)const;
	void print()const;
	void update();
	void log(Move)const;
	
	public:
	void play();
	
	Board();
};

#endif