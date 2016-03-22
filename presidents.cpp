#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <windows.h>

using namespace std;

static const int NUMBER_OF_PRESIDENTS=44;

struct President{
	string name="", served="";
	int number=0;
};

ostream& operator<<(ostream& o, President p){
	return o<<p.number<<". "<<p.name<<" "<<p.served;
}

ostream& operator<<(ostream& o, vector<President> v){
	for(unsigned int i=0; i<v.size(); i++) o<<v[i]<<" ";
	return o;
}

vector<President> get_list(){
	vector<President> presidents;
	vector<string> president_names={"George Washington","John Adams","Thomas Jefferson","James Madison","James Monroe","John Quincy Adams","Andrew Jackson","Martin Van Buren","William Henry Harrison","John Tyler","James K. Polk","Zachary Taylor","Millard Fillmore","Franklin Pierce","James Buchanan","Abraham Lincoln", "Andrew Johnson","Ulysses S. Grant","Rutherford B. Hayes","James A. Garfield","Chester Arthur","Grover Cleveland","Benjamin Harrison","Grover Cleveland","William McKinley","Theodore Roosevelt","William Howard Taft","Woodrow Wilson","Warren G. Harding","Calvin Coolidge","Herbert Hoover","Franklin D. Roosevelt","Harry S Truman","Dwight D. Eisenhower","John F. Kennedy","Lyndon B. Johnson","Richard Nixon","Gerald Ford","Jimmy Carter","Ronald Reagan","George Bush","Bill Clinton","George W. Bush","Barack Obama"};
	vector<string> president_serveds={"1789-1797","1797-1801","1801-1809","1809-1817","1817-1825","1825-1829","1829-1837","1837-1841","1841","1841-1845","1845-1849","1849-1850","1850-1853","1853-1857","1857-1861","1861-1865","1865-1869","1869-1877","1877-1881","1881","1881-1885","1885-1889","1889-1893","1893-1897","1897-1901","1901-1909","1909-1913","1913-1921","1921-1923","1923-1929","1929-1933","1933-1945","1945-1953","1953-1961","1961-1963","1963-1969","1969-1974","1974-1977","1977-1981","1981-1989","1989-1993","1993-2001","2001-2009", "2009-Incumbent"};
	for(unsigned int i=0; i<president_names.size(); i++){
		President p;
		p.name=president_names[i];
		p.served=president_serveds[i];
		p.number=i+1;
		presidents.push_back(p);
	}
	return presidents;
}

void print_list(){
	vector<President> presidents=get_list();
	for(unsigned int i=0; i<presidents.size(); i++){
		cout<<presidents[i]<<"\n";
	}
}

int get_rand(){
	srand(time(NULL));
	return (rand() % NUMBER_OF_PRESIDENTS)+1;
}

void number_to_president(){
	string guess;
	cout<<"President number "<<get_rand()<<": ";
	cin>>guess;
}

int main(){
	int operation=0;
	cout<<"Choose an operation: \n";
	cin>>operation;
	if(operation==1);
	else if(operation==2);
	else if(operation==3);
	else if(operation==4);
	cout<<"\nGoodbye";
	return 0;
}

