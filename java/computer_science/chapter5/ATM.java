package computer_science.chapter5;

import java.util.Scanner;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/5/2016
 * assignment: Chapter 05--Decisions--P5.10 ATM PIN simulation
 */
public class ATM {
	private static final int PIN = 1234;
	private static final int ENTRY_ATTEMPTS = 3;
	private int failedPINEntries;
	private boolean signedIn;

	public void logIn(int pin){
		if(pin != ATM.PIN) failedPINEntries++;
		this.signedIn = pin == ATM.PIN;
	}

	public void enterPIN(){
		Scanner input = new Scanner(System.in);
		while (!this.signedIn && this.failedPINEntries < ATM.ENTRY_ATTEMPTS){
			System.out.print("Please enter your pin: ");
			String pinStr = input.nextLine().trim();
			int pin = Integer.parseInt(pinStr);
			this.logIn(pin);
		}
		if(this.signedIn) System.out.println("Signed in.");
		else System.out.println("Failed to sign in. Attempts exceeded " + ATM.ENTRY_ATTEMPTS + ".");
	}

	public ATM(){
		this.failedPINEntries = 0;
		this.signedIn = false;
	}

	public static void main(String[] args){
		ATM atm = new ATM();
		atm.enterPIN();
	}
}
