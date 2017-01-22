// • Project: A draft for an RFID input based attendance program. 
// • Author(s): Adrian Hardt, Matthew Macovsky, and Logan Traffas. FIRST Team #1425 "ERROR CODE XERO".
// • Description: A program that will take an input identification from an RFID tag and input it into a log.

#include <iostream>
#include <string>
#include <fstream>
#include <ctime>

using namespace std;

string inTime(){//Sets the time the people signed in.
	string signInTime;
	time_t beginningTime;
	struct tm* startTime;
	time(&beginningTime);
	startTime=localtime(&beginningTime);
	signInTime=asctime(startTime);
	signInTime.erase(24,2);
	string moveYear=signInTime.substr(19,5);
	signInTime.erase(19,5);
	signInTime.insert(10,moveYear);
	return signInTime;
}

string getName(string RFIDin){//Sets the name of the person signing in.
	string name;
	string tempId;
	ifstream inputName;
	inputName.open("IdAndNames.txt");
	while(!(inputName.eof())){
		string line;
		getline(inputName,line);
		if(line!=""){
			tempId=line;
			string equalSign;
			unsigned int findEqualSign=0;
			for(findEqualSign; findEqualSign<=tempId.length(); findEqualSign++){
				equalSign=tempId[findEqualSign];
				if(equalSign=="="){
					tempId.erase(findEqualSign,1);
					break;
				}
			}
			unsigned int tempIdLength=tempId.length();
			tempId.erase(findEqualSign,tempIdLength);
			unsigned int locationOfLetter=findEqualSign+1;
			if(RFIDin==tempId){
				string tempName;
				tempName=line;	
				tempName.erase(0,locationOfLetter);
				string addLetter;
				for(locationOfLetter; locationOfLetter<=tempName.length(); locationOfLetter++){
					addLetter=tempName[locationOfLetter];
					name.append(addLetter);
				}
				name=tempName;
				name.erase(name.length()-1,2);
				break;
			}
		}
	}
	inputName.close();
	if(RFIDin!=tempId){
		cout<<endl<<"Error. There is no name associate with the id \""<<RFIDin<<"\". Please try again."<<endl;
	}
	return name;
}

void outputAttendance(string RFIDin, string name, string signInTime){//Outputs the attendance to the corresponding file.
	if(name!=""){
		ofstream logOfTimes;
		logOfTimes.open("logOfTimes.txt",ios::app);
		logOfTimes<<name<<"="<<RFIDin<<"="<<signInTime<<"\r\n";
		logOfTimes.close();
	}
}

int main(){//Input the RFID.
	while(1){
		string name;
		cout<<"Please input your id to sign in or out or \"exit\" to exit: ";
		string RFIDin;
		getline(cin,RFIDin);
		if(RFIDin=="exit"){
			break;
		}
		string signInTime;
		name=getName(RFIDin);
		signInTime=inTime();
		outputAttendance(RFIDin, name, signInTime);
		cout<<endl;
	}
	return 0;
}