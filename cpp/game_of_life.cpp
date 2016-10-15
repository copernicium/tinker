#include <iostream>
#include <array>
#include <vector>
#include <cassert>
#include "simple_time.h"

#define X_LEN 100
#define Y_LEN 40
#define RUN_TIME 1000
#define UPDATE_EVERY 500

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

struct Point{
	unsigned int x,y;
	Point();
	Point(unsigned int,unsigned int);
};

ostream& operator<<(ostream& o,Point a){
	return o<<"("<<a.x<<","<<a.y<<")";
}

vector<Point> get_neighbors(Point a){
	vector<Point> v;
	if(a.x>0){
		v.push_back({a.x-1,a.y});//left
		if(a.y>0)v.push_back({a.x-1,a.y-1});//up left
		if(a.y<Y_LEN-1)v.push_back({a.x-1,a.y+1});//down left
	}
	if(a.x<X_LEN-1){
		v.push_back({a.x+1,a.y});//right
		if(a.y>0)v.push_back({a.x+1,a.y-1});//up right
		if(a.y<Y_LEN-1)v.push_back({a.x+1,a.y+1});//down right
	}
	if(a.y>0) v.push_back({a.x,a.y-1});//up
	if(a.y<Y_LEN-1) v.push_back({a.x,a.y+1});//down	
	return v;
}

Point::Point():x(0),y(0){}
Point::Point(unsigned int a,unsigned int b):x(a),y(b){}

struct Grid{
	private:
	array<array<bool,X_LEN>,Y_LEN> grid;
	
	public:
	void init(vector<Point>);
	void update();
	array<array<bool,X_LEN>,Y_LEN> get();
	Grid();
};

Grid::Grid():grid({{}}){
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			grid[y][x]=false;
		}
	}
}

array<array<bool,X_LEN>,Y_LEN> Grid::get(){
	return grid;
}

/*
RULES:
Any live cell with fewer than two live neighbours dies
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies
Any dead cell with exactly three live neighbours becomes a live cell
*/

bool update_val(const Point p,const array<array<bool,X_LEN>,Y_LEN> grid){
	bool alive=grid[p.y][p.x];
	vector<Point> v=get_neighbors(p);
	unsigned int neighbors=0;
	for(unsigned int i=0;i<v.size();i++){
		Point t=v[i];
		if(grid[t.y][t.x]) neighbors++;
	}
	if(alive){
		switch(neighbors){
			case 0:
			case 1:
				return false;
			case 2:
			case 3:
				return true;
			default:
				return false;
		}
	}
	else return neighbors==3;
}

void Grid::update(){
	array<array<bool,X_LEN>,Y_LEN> new_grid=grid;
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			new_grid[y][x]=update_val(Point{x,y},grid);
		}
	}
	grid=new_grid;
}

void Grid::init(vector<Point> v){
	for(unsigned int i=0; i<v.size(); i++){
		Point p=v[i];
		assert(p.x<X_LEN && p.y<Y_LEN);
		grid[p.y][p.x]=true;//!grid[p.y][p.x];
	}
}

std::ostream& operator<<(std::ostream& o,Grid a){
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			o<<a.get()[y][x];
		}
		o<<"\n";
	}
	return o;
}

std::string to_string(Grid a){
	string s;
	for(unsigned int y=0; y<Y_LEN; y++){
		for(unsigned int x=0; x<X_LEN; x++){
			bool b = a.get()[y][x];
			s += b ? "X" : " ";
		}
		s+="\n";
	}
	return s;
}

void run(){
	srand(time(NULL));
	Grid grid;
	vector<Point> random_points;
	for(unsigned int x=0; x<X_LEN; x++){
		for(unsigned int y=0; y<Y_LEN; y++){
			bool random=(rand() % 2);
			if(random)random_points.push_back(Point{x,y});
		}
	}
	//vector<Point> init_points{{4,5},{4,4},{4,3}};
	//grid.init(init_points);
	grid.init(random_points);
	//cout<<grid<<"\n";
	for(unsigned int i=0;i<RUN_TIME; ){
		if(get_time(Time_type::MILLISECONDS)%UPDATE_EVERY!=0) continue;
		i++;
		grid.update();
		string grid_str = to_string(grid);
		system("clear");
		cout<<grid_str<<"\n";
	}
}

int main(){
	run();
	return 0;
}