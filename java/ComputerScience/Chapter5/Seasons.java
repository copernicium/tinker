package ComputerScience.Chapter5;

import Util.Util;

import java.util.Scanner;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/5/2016
 * assignment: Chapter 05--Decisions--E5.14--Seasons
 */
public class Seasons {
	public static class Date {
		private int month;
		private int day;

		public int getDay() {
			return this.day;
		}

		public int getMonth() {
			return this.month;
		}

		public Date() {
			this.month = 0;
			this.day = 0;
		}

		public Date(int month, int day) {
			Util.myAssert(1 <= month && month <= 12, Util.getFileName(), Util.getLineNumber());
			Util.myAssert(1 <= month && month <= 31, Util.getFileName(), Util.getLineNumber());

			this.month = month;
			this.day = day;
		}
	}
	public enum Season{WINTER,SPRING,SUMMER,FALL}

	Date date;

	public void setDate(){
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the month and day in number format (ex: May 9th is \"5 9\"): ");
		String date = input.nextLine().trim();

		String monthStr = "", dayStr = "";

		{
			boolean addToMonth = true;
			for(char a : date.toCharArray()) {
				if(a == ' ') {
					addToMonth  = false;
					continue;
				}
				if(addToMonth ) monthStr += a;
				else dayStr += a;
			}

			monthStr = monthStr.trim();
			dayStr = dayStr.trim();
		}
		int month = Integer.parseInt(monthStr);
		int day = Integer.parseInt(dayStr);
		this.date = new Date(month,day);
	}

	public Season getSeason() {
		Season season;
		if(1 <= this.date.getMonth() && this.date.getMonth() <= 3) {
			season = Season.WINTER;
		} else if(4 <= this.date.getMonth() && this.date.getMonth() <= 6) {
			season = Season.SPRING;
		} else if(7 <= this.date.getMonth() && this.date.getMonth() <= 9) {
			season = Season.SUMMER;
		} else season = Season.FALL;

		boolean advance = (this.date.getMonth() % 3 == 0 && this.date.getDay() >= 21);
		if(season == Season.WINTER && advance) {
			season = Season.SPRING;
		} else if(season == Season.SPRING && advance) {
			season = Season.SUMMER;
		} else if(season == Season.SUMMER && advance) {
			season = Season.FALL;
		} else if(season == Season.FALL && advance){
			season = Season.WINTER;
		}
		return season;
	}

	public Seasons() {
		this.date = new Date();
	}

	public static void main(String[] args){
		Seasons seasons = new Seasons();
		seasons.setDate();
		System.out.println("The season is " + seasons.getSeason());
	}

}
