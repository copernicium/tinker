/*
 • Project: A draft for a hangman game program. 
 • Author(s): Logan Traffas.
 • Description: A program that will randomly select a word that you will try to guess be fore a man is hanged.
 • To Do (Unfinished):
	 • Guessing the correct character multiple times causes victory.
	 • Reappearing character in the word leading to outputting "correct guess" multiple times.
	 • Show apostrophes in blanks.
	 • Already guessed letters listed before asked to guess a letter.
	 • The hangman animation.
*/
#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include <cstdlib>
using namespace std;

string get_word(){//Chooses the word
	ifstream chooseword;
	string word;
	srand(time(NULL));
	int random=(rand() % 99171)+1;
	int f=0;
	chooseword.open("words.txt");
	while(!chooseword.eof()){//Choosing the word.
		if(f==random){
			break;
		}	
		else{
			while(f!=random){
				chooseword >> word;
				f++;
			}
		}
	}
	chooseword.close();
	return word;
}

void play_game(string word, string blanks, int blanks_number){//Accepts user guesses and compares them with the word
	string guess="";
	int s=0;
	bool stopGuessCheck=0;
	unsigned int guessFinished=0;
	unsigned int previously_guessed_rightAmount=0;
	unsigned int previously_guessed_wrong_amount=0;
	string previouslyGuessedWrong="";
	string previously_guessed_right="";
	string previously_guessed_right_check="";
	string previouslyGuessedWrongCheck="";
	string previously_guessed_rightForOutput="";
	string previouslyGuessedWrongForOutput="";
	while(s<blanks_number+6){
		if(previously_guessed_wrong_amount==6){
			break;
		}
		bool correct=0;
		string guess_comparison="";
		string blanks_comparison="";
		if(stopGuessCheck==0){
			cout<<endl<<"You have "<<(6-previously_guessed_wrong_amount)<<" attempts remaining. Please guess a character: ";
			cin>>guess;
			cout<<endl;
		}
		for(unsigned int x; x<=previously_guessed_rightAmount; x++){//Checking if guess has already been guessed correctly.
			previously_guessed_right_check=previously_guessed_right[x];
			if(guess!=previously_guessed_right_check){
				stopGuessCheck=0;
				break;
			}
			else{
				stopGuessCheck=1;
				x--;
				cout<<"That character has already been guessed correctly.\n\n"<<blanks<<endl<<endl;
				cout<<"You have "<<(6-previously_guessed_wrong_amount)<<" attempts remaining. Please guess a character: ";
				cin>>guess;
				cout<<endl;
				break;
			}
		}
		for(unsigned int c; c<=previously_guessed_wrong_amount; c++){//Checking if guess has already been guessed incorrectly.
			previouslyGuessedWrongCheck=previouslyGuessedWrong[c];
			if(guess!=previouslyGuessedWrongCheck){
				stopGuessCheck=0;
				break;
			}
			else{
				stopGuessCheck=1;
				cout<<"That character has already been guessed incorrectly.\n\n"<<blanks<<endl<<endl;
				cout<<"You have "<<(6-previously_guessed_wrong_amount)<<" attempts remaining. Please guess a character: ";
				cin>>guess;
				cout<<endl;
				c=0;
				break;
			}
		}
		if(stopGuessCheck==0){
			s++;
			for(int y; y<blanks_number;y++){//Checks word for guess.
				guess_comparison=word[y];
				blanks_comparison=blanks[y];		
				if(guess==guess_comparison){//The guess is found to be correct.
					cout<<"\nCorrect guess! That character is in the word.\n\n";
					previously_guessed_rightAmount++;
					previously_guessed_right+=guess;
					if(guess!=blanks_comparison){
						correct=1;
						guessFinished++;
						blanks.replace(y*2, 2, guess+" ");
						cout<<blanks<<endl;
					}
				}
				if(y==blanks_number-1 && guess!=guess_comparison && correct!=1){//The guess is found to be incorrect.
					cout<<"Incorrect guess! That character is NOT in the word.\n\n";
					previously_guessed_wrong_amount++;
					previouslyGuessedWrong+=guess;
					cout<<blanks<<endl;
				}				
				}
			if(guessFinished==word.length()){
				cout<<endl<<"=========================================================================="<<endl<<endl<<"Congratulations! You win! the word";
				cout<<" was \""<<word<<"!\""<<endl<<endl<<"=========================================================================="<<endl;
				break;
			}
		}
		if(previously_guessed_wrong_amount==6){
			cout<<endl<<"=========================================================================="<<endl<<endl<<"Game over. You lose. The correct word ";
			cout<<"was: \""<<word<<"\"!\n"<<endl<<"=========================================================================="<<endl;
		}
	}//Ends current hangman game
}

int main(){
	char play='n';
	cout<<"Would you like to play a game of hangman?(y/n): ";
	cin>>play;
	if(play=='y'){
		char instructions;
		cout<<endl<<"Would you like to read the instructions?(y/n): ";
		cin>>instructions;
		cout<<endl;
		if(instructions=='y'){
			cout<<"On the screen there will appear a word written with blanks in the place of each letter. You will then guess a letter. If that letter is in "; 
			cout<<"the word then I will write\n""the letter in every place it appears in the word. If the letter isn't in the word then I will add a head," ;
			cout<<"torso, left arm, right arm, left leg, or right leg to the\ngallows. The word may contain accented letters as well as apostrophes.";
			cout<<endl<<endl<<"The game will continue until either the full body is hanging from the gallows (I win) or if you guess the correct word before ";
			cout<<"the person is hung (you win)."<<endl<<endl;
		}
		cout<<"Would you still like to play a game of hangman?(y/n): ";
		cin>>play;
		cout<<endl;
	}
	while(play=='y'){
		cout<<"Let's play!"<<endl<<endl;
		string word=get_word();
		int blanks_number=word.length();
		string blanks;
		for(int i=0;i<blanks_number;i++){
			blanks+="_ ";
		}
		cout<<blanks<<endl;
		play_game(word, blanks, blanks_number);
		cout<<"Would you like to play another game of hangman?(y/n): ";
		cin>>play;
	}
	cout<<endl<<"Bye now."<<endl;
	return 0;
}