#include <array>
#include <vector>
#include <memory>

#define X_LEN 180
#define Y_LEN 75 //TODO: find some way of calculating these

struct Point;

using Ptr_point_array = std::array< std::array< std::unique_ptr<Point>, X_LEN>, Y_LEN>;
using Point_array = std::array< std::array<Point, X_LEN>, Y_LEN>;
using Point_vector = std::vector<std::unique_ptr<Point>>;
struct Grid{
	private:
	Ptr_point_array grid;
	
	public:
	friend std::ostream& operator<<(std::ostream&,Grid);
	std::string to_string();
	void set(Point_vector);
	void update();
	Grid();
};

struct Point{
	private:
	unsigned int x,y;
	bool alive_;
	
	public:
	bool alive()const;
		
	std::string print()const;
	
	friend std::ostream& operator<<(std::ostream&,Point);
	friend void Grid::set(Point_vector);
	
	std::vector<Point> get_neighbors()const;//TODO: rename as get_neighboring()
	void update(Point_array const&);
	
	Point();
	Point(unsigned,unsigned);
	Point(unsigned,unsigned,bool);
};
