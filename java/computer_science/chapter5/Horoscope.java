package computer_science.chapter5;

import MySystem.MySystem;

import java.util.Scanner;

/**
 * Makes up a horoscope given a birthday
 *
 * @author Logan Traffas
 * @version 11/30/2016
 * assignment: Chapter 05--Decisions-P5.1--Horoscope
 */
public class Horoscope{
	private int day;
	private int month;

	private enum AstrologicalSign{ARIES, TAURUS, GEMINI, CANCER, LEO, VIRGO, LIBRA, SCORPIO, SAGITTARIUS, CAPRICORN, AQUARIUS, PISCES}

	public void getBirthday(){
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your birth month and day in number format (ex: May 9th is \"5 9\"): ");
		String birthday = input.nextLine().trim();

		String monthStr = "", dayStr = "";

		{
			boolean addtoMonth = true;
			for (char a : birthday.toCharArray()) {
				if(a == ' ') {
					addtoMonth = false;
					continue;
				}
				if(addtoMonth) monthStr += a;
				else dayStr += a;
			}

			monthStr = monthStr.trim();
			dayStr = dayStr.trim();
		}

		this.month = Integer.parseInt(monthStr);
		this.day = Integer.parseInt(dayStr);
	}

	private AstrologicalSign getAstrologicalSign(){
		switch(this.month){
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return AstrologicalSign.AQUARIUS;//should never reach here
	}

	public String getHoroscope(){
		switch(getAstrologicalSign()){
			case ARIES:
				break;
			case TAURUS:
				break;
			case GEMINI:
				break;
			case CANCER:
				break;
			case LEO:
				break;
			case VIRGO:
				break;
			case LIBRA:
				break;
			case SCORPIO:
				break;
			case SAGITTARIUS:
				break;
			case CAPRICORN:
				break;
			case AQUARIUS:
				break;
			case PISCES:
				break;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		return "";//TODO
	}

	public Horoscope(){
		this.day = 0;
		this.month = 0;
	}

	public static void main(String[] args){
		Horoscope horoscope = new Horoscope();
		horoscope.getBirthday();
		System.out.println("Horoscope: " + horoscope.getHoroscope());
	}
}
