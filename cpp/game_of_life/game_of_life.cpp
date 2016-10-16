#include "game_of_life.h"

#include <iostream>
#include <cassert>
#include "../simple_time.h"

#define UPDATE_EVERY 5

/*
RULES:
Any live cell with fewer than two live neighbours dies
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes a live cell
*/

using namespace std;

template<typename T>
std::ostream& operator<<(std::ostream& o,std::vector<T> v){
	o<<"(";
	for(unsigned int i=0; i<v.size(); i++){
		o<<v[i];
		if(i<v.size()-1) o<<",";
	}
	return o<<")";
}

Point::Point():x(0),y(0),alive_(false){}
Point::Point(unsigned int a,unsigned int b):x(),y(),alive_(false){
	assert(a<X_LEN && b<Y_LEN);//is at least zero by nature of being unsigned
	x=a;
	y=b;
}
Point::Point(unsigned int a,unsigned int b,bool is_alive):x(),y(),alive_(is_alive){
	assert(a<X_LEN && b<Y_LEN);//is at least zero by nature of being unsigned
	x=a;
	y=b;
}

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
	if(x>0){
		v.push_back({x-1,y});//left
		if(y>0)v.push_back({x-1,y-1});//up left
		if(y<Y_LEN-1)v.push_back({x-1,y+1});//down left
	}
	if(x<X_LEN-1){
		v.push_back({x+1,y});//right
		if(y>0)v.push_back({x+1,y-1});//up right
		if(y<Y_LEN-1)v.push_back({x+1,y+1});//down right
	}
	if(y>0) v.push_back({x,y-1});//up
	if(y<Y_LEN-1) v.push_back({x,y+1});//down	
	return v;
}

Grid::Grid():grid({{}}){
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			grid[y][x] = std::make_unique<Point>(x,y,false);
		}
	}
}

void Point::update(const array<array<Point,X_LEN>,Y_LEN> grid){
	unsigned int neighbors=0;
	for(Point neighbor: this->get_neighbors()){//count the number of living neighbors
		if((grid[neighbor.y][neighbor.x]).alive()) neighbors++;
	}
	if(grid[y][x].alive()){//if current point is alive
		switch(neighbors){//what the rules dictate happens in certain cases
			case 0:
			case 1:
				alive_ = false;
				return;
			case 2:
			case 3:
				alive_ = true;
				return;
			default:
				alive_ = false;
				return;
		}
	}
	else alive_ = (neighbors==3);
}

void Grid::update(){
	array<array<Point,X_LEN>,Y_LEN> old_grid;
	for(unsigned int y = 0; y<Y_LEN; y++){
		for(unsigned int x = 0; x < X_LEN; x++){
			old_grid[y][x] = *(grid[y][x]);
		}
	}
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			grid[y][x]->update(old_grid);
		}
	}
}

void Grid::set(vector<std::unique_ptr<Point>> v){
	for(unsigned int i = 0; i < v.size(); i++){
		Point p = *v[i];
		(*grid[p.y][p.x]).alive_ = true;
	}
}

std::ostream& operator<<(std::ostream& o,Grid a){
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			Point p = *a.grid[y][x];
			o<<p.print();
		}
		o<<"\n";
	}
	return o;
}

std::string Grid::to_string(){
	string s;
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			s += grid[y][x]->print();
		}
		s+="\n";
	}
	return s;
}

void run(){
	srand(time(NULL));
	Grid grid;
	while(true){
		vector<unique_ptr<Point>> random_points;
		for(unsigned int x=0; x<X_LEN; x++){
			for(unsigned int y=0; y<Y_LEN; y++){
				bool random = (rand() % 2);
				if(random) random_points.push_back(make_unique<Point>(x,y));
			}
		}
		grid.set(move(random_points));
		string grid_str = grid.to_string();
		cout<<grid_str<<"\n";
		string last_grid = grid_str;
		int i =0;
		while(true){
			i++;
			if(get_time(Time_type::MILLISECONDS)%UPDATE_EVERY!=0) continue;
			if(i%2==0)last_grid = grid_str;
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