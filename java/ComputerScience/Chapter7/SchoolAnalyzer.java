package ComputerScience.Chapter7;

/**
 * Looks through a data set and prints information about a school
 *
 * @author Logan Traffas
 * @version 1/19/2017
 * assignment: Chapter 07--Arrays 7.6--Two Dimensional Arrays--Courses and Students
 */
public class SchoolAnalyzer {
	public enum Class{ENGLISH,ALGEBRA,GEOMETRY,CALCULUS,HISTORY,BIOLOGY,CHEMISTRY,PHYSICS,COMPUTER}
	public enum Grade{NINTH,TENTH,ELEVENTH,TWELFTH}

	public static void main(String[] args){
		final Class[] classes = {Class.ENGLISH,Class.ALGEBRA,Class.GEOMETRY,Class.CALCULUS,Class.HISTORY,Class.BIOLOGY,Class.CHEMISTRY,Class.PHYSICS,Class.COMPUTER};
		final Grade[] grades = {Grade.NINTH,Grade.TENTH,Grade.ELEVENTH,Grade.TWELFTH};

		final int[][] school = {
				{100, 200, 100, 100},
				{150, 175, 50, 10},
				{50, 100, 20, 10},
				//TODO
				{}
		};
	}
}
