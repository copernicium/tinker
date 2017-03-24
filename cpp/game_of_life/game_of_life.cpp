#include "game_of_life.h"

#include <iostream>
#include <cassert>
#include "../util/simple_time.h"
#include "../util/util.h"
#include "../util/nyi.h"

#define UPDATE_EVERY 1

/*
RULES:
Any live cell with fewer than two live neighbours dies
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes a live cell
*/

using namespace std;

Point::Point(unsigned a,unsigned b,bool is_alive):x(),y(),alive_(is_alive){
	assert(a<X_LEN && b<Y_LEN);//is at least zero by nature of being unsigned
	x=a;
	y=b;
}
Point::Point(unsigned a,unsigned b):Point(a,b,false){}
Point::Point():Point(0,0,false){}

bool Point::alive()const{
	return alive_;
}

ostream& operator<<(ostream& o,Point a){
	return o<<"("<<a.x<<","<<a.y<<",alive:"<<a.alive_<<")";
}

string Point::print()const{
	return alive_ ? "X" : " ";
}

vector<Point> Point::get_neighbors()const{//returns a vector of neighbors of a point
	vector<Point> v;
	if(x > 0){
		v.push_back({x - 1,y});//left
		if(y > 0) v.push_back({x - 1,y - 1});//up left
		if(y < Y_LEN - 1) v.push_back({x - 1,y + 1});//down left
	}
	if(x < X_LEN - 1){
		v.push_back({x + 1,y});//right
		if(y > 0) v.push_back({x + 1,y - 1});//up right
		if( y < Y_LEN - 1) v.push_back({x + 1,y + 1});//down right
	}
	if(y > 0) v.push_back({x,y - 1});//up
	if(y < Y_LEN - 1) v.push_back({x,y + 1});//down	
	return v;
}

Grid::Grid():grid({{}}){
	for(unsigned y: range(Y_LEN)){
		for(unsigned x: range(X_LEN)){
			grid[y][x] = make_unique<Point>(x,y);
		}
	}
}

void Point::update(Point_array const& grid){
	alive_ = [&]{
		//what the rules dictate happens in certain cases
		unsigned neighbors = filter( 
			[&](Point const& p){ 
				return grid[p.y][p.x].alive();
			},
			get_neighbors()
		).size();
		if(alive_){//if current point is alive
			if(neighbors == 2 || neighbors == 3){
				return true;
			}
			return false; 
		}
		return neighbors==3;
	}();
}

void Grid::update(){
	Point_array old_grid = [&]{
		Point_array p;
		for(unsigned y: range(Y_LEN)){
			for(unsigned x: range(X_LEN)){
				p[y][x] = *grid[y][x];
			}
		}
		return p;
	}();
	for(unsigned y: range(Y_LEN)){
		for(unsigned x: range(X_LEN)){
			grid[y][x]->update(old_grid);
		}
	}
}

void Grid::set(Point_vector v){
	for(unsigned i: range(v.size())){
		grid[v[i]->y][v[i]->x]->alive_ = true;
	}
}

ostream& operator<<(ostream& o,Grid a){
	return o<<a.to_string();
}

string Grid::to_string(){
	string s;
	for(unsigned y: range(Y_LEN)){
		for(unsigned x: range(X_LEN)){
			s.append(grid[y][x]->print());
		}
		s.append("\n");
	}
	return s;
}

void run(){
	srand(time(NULL));
	Grid grid;
	while(true){
		{
			Point_vector random_points;
			for(unsigned y: range(Y_LEN)){
				for(unsigned x: range(X_LEN)){
					if((bool)(rand() % 2)) random_points.push_back(make_unique<Point>(x,y));
				}
			}
			grid.set(move(random_points));
		}
		string grid_str = grid.to_string();
		string last_grid = grid_str;
		cout<<grid_str<<"\n";
		unsigned i = 0;
		while(true){
			i++;
			if(Simple_time::get_time(Simple_time::Unit::MILLISECONDS) % UPDATE_EVERY > 0) continue;
			if(i % 2 == 0)last_grid = grid_str;
			grid.update();
			grid_str = grid.to_string();
			if(last_grid == grid_str) break;
			system("clear");
			cout<<grid_str<<"\n";
		}
	}
}

int main(){
	run();
	return 0;
}
