//Author: Logan Traffas
#include <iostream>
#include <string>
#include <vector>

using namespace std;


ostream & operator<<(ostream & o, vector<string> in){//Outputs from the vector if type Input the states of the a and b channels as this code interprets it
	for(unsigned int i=0; i<in.size(); i++){
		if(i!=in.size()-1)o<<"a "+in[i]+" ";
		else o<<"and a "+in[i]+" ";
	}
	return o;
}

struct Room_1{
	vector<string> visible{"door", "window", "table"};
	vector<string> object_info{"A locked door.", "A barred window.", "A table with a lock-picking kit with three uses."};
	vector<string> items{"lock-picking kit"};
	bool lock_picking_kit_1, lock_picking_kit_2, lock_picking_kit_3;
};

void explore(int room){
	Room_1 current_room;
	cout<<"There is "<<current_room.visible;
}

void use(int room){
	
}

string take(int room){
	string item;
	cout<<endl<<"What do you want to take? ";
	getline(cin, item);
	Room_1 current_room;
	for(unsigned int i=0; i<current_room.items.size(); i++){
		if(item==current_room.items[i])cout<<"Took "<<current_room.items[i];
	}
	return item;
}

void investigate(int room){
	string object;
	cout<<endl<<"What do you want to investigate? ";
	getline(cin, object);
	Room_1 current_room;
	for(unsigned int i=0; i<current_room.visible.size(); i++){
		if(object==current_room.visible[i]){
			cout<<current_room.object_info[i];
			break;
		}
	}
}

void go_to(int room){

}

string determine_command(string command, int room){
	string item;
	if(command=="explore")explore(room);
	else if(command=="use")use(room);
	else if(command=="investigate")investigate(room);
	else if(command=="go to")go_to(room);
	else if(command=="take")item=take(room);
	return item;
}

int main(){
	char play;
	int room=1;
	string command;
	vector<string> items;
	cout<<"Welcome to the Text Adventure Game!"<<endl;
	cout<<"Would you like to play?(y/n) ";
	cin>>play;
	cout<<endl;
	if(play=='y'){
		cout<<"Commands: \"explore\", \"use\", \"investigate\", \"go to\""<<endl;
		cout<<"You begin in a room with a wooden floor. "<<endl;
		while(1){
			cin>>command;
			items.push_back(determine_command(command, room));
			cout<<endl;
		}
	}
	return 0;
}