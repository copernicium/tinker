#include <array>
#include <vector>
#include <memory>

#define X_LEN 190
#define Y_LEN 65

struct Point;

struct Grid{
	private:
	std::array<std::array<std::unique_ptr<Point>, X_LEN > , Y_LEN> grid;
	
	public:
	friend std::ostream& operator<<(std::ostream&,Grid);
	std::string to_string();
	void set(std::vector<std::unique_ptr<Point>>);
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
	friend void Grid::set(std::vector<std::unique_ptr<Point>>);
	std::vector<Point> get_neighbors()const;
	void update(const std::array<std::array<Point,X_LEN>,Y_LEN>);
	Point();
	Point(unsigned int,unsigned int);
	Point(unsigned int,unsigned int,bool);
};