// • Project: A draft for an RFID input based attendance program. 
// • Author(s): Adrian Hardt, Matthew Macovsky, and Logan Traffas. FIRST Team #1425 "ERROR CODE XERO".
// • Description: A program that will take an input identification from an RFID tag and input it into a log.

#include <iostream>
#include <string>
#include <fstream>
#include <stdlib.h>

using namespace std;

void memberRead(string name){//Reads out (from the log) the times the person was present.
	cout<<endl;
	bool findTime=0;
	bool foundName=0;
	bool setDay=1;
	bool outputTimes;
	unsigned int loggedHours=0;
	unsigned int loggedMinutes=0;
	unsigned int loggedSeconds=0;
	string hourS="hours";
	string minuteS="minutes";
	string secondS="seconds";
	string signedInTime;
	string signedOutTime;
	string tempSignedInTime;
	string Day;
	string testDay;
	string tempLine;
	string tempName;
	ifstream log;
	log.open("logOfTimes.txt");
	while(!(log.eof())){
		string line;
		if(outputTimes==1){
			line=tempLine;
			tempName=line;
		}
		else{
			getline(log,line);
			tempName=line;
		}
		string testLine=line;
		if(testLine!=""){
			testLine.erase(testLine.length()-1,1);
		}
		if(testLine!=""){
			string tempTime=line;
			string equalSign;
			unsigned int findEqualSign=0;
			unsigned int thirdEqualSign=0;
			for(findEqualSign; findEqualSign<=line.length(); findEqualSign++){
				equalSign=tempTime[findEqualSign];
				if(equalSign=="="){
					thirdEqualSign++;
					if(thirdEqualSign==1){
						tempName.erase(findEqualSign,tempName.length());
						if(tempName==name){
							foundName=1;
							findTime=1;
						}
						else{
							findTime=0;
						}
					}
					if(thirdEqualSign==2){
						break;
					}
				}
			}
			if(findTime==1){
				if(thirdEqualSign==2){
					tempTime.erase(0,findEqualSign+1);
					testDay=tempTime.substr(4,6);
					if(setDay==1){
						Day=tempTime.substr(4,6);
						setDay=0;
						if(tempSignedInTime==""){
							signedInTime=tempTime;
							signedInTime.erase(0,16);
							signedInTime.erase(signedInTime.length()-1,1);
						}
						else{
							signedInTime=tempSignedInTime;
						}	
					}
					if(testDay==Day){
						signedOutTime=tempTime;
						signedOutTime.erase(0,16);
						signedOutTime.erase(signedOutTime.length()-1,1);
						outputTimes=0;
					}
					else if(testDay!=""){
						tempSignedInTime=tempTime;
						tempSignedInTime.erase(0,16);
						tempSignedInTime.erase(tempSignedInTime.length()-1,1);
						outputTimes=1;
						setDay=1;
					}
				}
				if(outputTimes==1){
					cout<<name<<" was here from "<<signedInTime<<" to "<<signedOutTime<<" on "<<Day<<"."<<endl;
					tempLine=line;
					string signedInHour=signedInTime.substr(0,2);
					string signedInMinute=signedInTime.substr(3,2);
					string signedInSecond=signedInTime.substr(6,2);
					string signedOutHour=signedOutTime.substr(0,2);
					string signedOutMinute=signedOutTime.substr(3,2);
					string signedOutSecond=signedOutTime.substr(6,2);
					unsigned int loginHour=atoi(signedInHour.c_str());
					unsigned int loginMinute=atoi(signedInMinute.c_str());		
					unsigned int loginSecond=atoi(signedInSecond.c_str());				
					unsigned int logoutHour=atoi(signedOutHour.c_str());
					unsigned int logoutMinute=atoi(signedOutMinute.c_str());
					unsigned int logoutSecond=atoi(signedOutSecond.c_str());
					loggedSeconds+=(logoutSecond-loginSecond);
					while(loggedSeconds>=60){
						loggedMinutes++;
						loggedSeconds-=60;
					}
					loggedMinutes+=(logoutMinute-loginMinute);
					while(loggedMinutes>=60){
						loggedHours++;
						loggedMinutes-=60;
					}
					loggedHours+=(logoutHour-loginHour);
					if(loggedHours==1){
						hourS="hour";
					}
					else{
						hourS="hours";
					}
					if(loggedMinutes==1){
						minuteS="minute";
					}
					else{
						minuteS="minutes";
					}
					if(loggedSeconds==1){
						secondS="second";
					}
					else{
						secondS="seconds";
					}
				}
			}
		}
	}
	if(outputTimes==0 && foundName==1){
		cout<<name<<" was here from "<<signedInTime<<" to "<<signedOutTime<<" on "<<Day<<"."<<endl;
		string signedInHour=signedInTime.substr(0,2);
		string signedInMinute=signedInTime.substr(3,2);
		string signedInSecond=signedInTime.substr(6,2);
		string signedOutHour=signedOutTime.substr(0,2);
		string signedOutMinute=signedOutTime.substr(3,2);
		string signedOutSecond=signedOutTime.substr(6,2);
		unsigned int loginHour=atoi(signedInHour.c_str());
		unsigned int loginMinute=atoi(signedInMinute.c_str());
		unsigned int loginSecond=atoi(signedInSecond.c_str());		
		unsigned int logoutHour=atoi(signedOutHour.c_str());		
		unsigned int logoutMinute=atoi(signedOutMinute.c_str());		
		unsigned int logoutSecond=atoi(signedOutSecond.c_str());		
		loggedSeconds+=(logoutSecond-loginSecond);
		while(loggedSeconds>=60){
			loggedMinutes++;
			loggedSeconds-=60;
		}	
		loggedMinutes+=(logoutMinute-loginMinute);
		while(loggedMinutes>=60){
			loggedHours++;
			loggedMinutes-=60;
		}
		loggedHours+=(logoutHour-loginHour);
		if(loggedHours==1){
			hourS="hour";
		}
		else{
			hourS="hours";
		}
		if(loggedMinutes==1){
			minuteS="minute";
		}
		else{
			minuteS="minutes";
		}
		if(loggedSeconds==1){
			secondS="second";
		}
		else{
			secondS="seconds";
		}
	}
	log.close();
	if(foundName==0){
		cout<<"No history has been found for \""<<name<<"\". Check if you have misspelled their name."<<endl;
	}
	else{
		cout<<endl<<"The total time this person has logged is "<<loggedHours<<" "<<hourS<<", "<<loggedMinutes<<" "<<minuteS<<", and "<<loggedSeconds<<" "<<secondS<<"."<<endl;
	}
}

int main(){//Input the RFID.
	while(1){
		cout<<"Please input your a name to view their times logged or \"exit\" to exit: ";
		string name;
		getline(cin,name);
		if(name=="exit"){
			break;
		}
		memberRead(name);
		cout<<endl;
	}
	return 0;
}