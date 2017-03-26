package ComputerScience.Chapter7;

import java.util.Arrays;

/**
 * Looks through a data set and prints information about a school
 *
 * @author Logan Traffas
 * @version 1/19/2017
 * assignment: Chapter 07--Arrays 7.6--Two Dimensional Arrays--Courses and Students
 */
public class SchoolAnalyzer {
	public class Classes{
		public static final int ENGLISH = 0;
		public static final int ALGEBRA = 1;
		public static final int GEOMETRY = 2;
		public static final int CALCULUS = 3;
		public static final int HISTORY = 4;
		public static final int BIOLOGY = 5;
		public static final int CHEMISTRY = 6;
		public static final int PHYSICS = 7;
		public static final int COMPUTER = 8;
		public static final int CLASSES = 9;
	}

	public class Grades{
		public static final int NINTH = 0;
		public static final int TENTH = 1;
		public static final int ELEVENTH = 2;
		public static final int TWELFTH = 3;
		public static final int GRADES = 4;
	}

	public static void main(String[] args){
		final String[] CLASS_NAMES = {"ENGLISH", "ALGEBRA", "GEOMETRY", "CALCULUS", "HISTORY", "BIOLOGY", "CHEMISTRY", "PHYSICS", "COMPUTER", "CLASSES"};
		final String[] GRADE_NAMES = {"NINTH", "TENTH", "ELEVENTH", "TWELFTH", "GRADES"};

		final int[] classes = {Classes.ENGLISH,Classes.ALGEBRA,Classes.GEOMETRY,Classes.CALCULUS,Classes.HISTORY,Classes.BIOLOGY,Classes.CHEMISTRY,Classes.PHYSICS,Classes.COMPUTER};
		final int[] grades = {Grades.NINTH,Grades.TENTH,Grades.ELEVENTH,Grades.TWELFTH,Grades.GRADES};

		final int[][] school = {
				{100, 200, 100, 100},
				{150, 175, 50, 10},
				{50, 100, 20, 10},
				{0, 10, 50, 200},
				{0, 50, 150, 200},
				{250, 100, 50, 10},
				{10, 10, 150, 200},
				{0, 30, 200, 100},
				{5, 50, 80, 200}
		};//first an array of classes, then an array of the number of students per grade in those classes

		System.out.println(Arrays.deepToString(school));

		System.out.print("\n");

		int[] gradeSizes = new int[Grades.GRADES];
		{
			for (int[] schoolClass : school) {
				for (int i = 0; i < schoolClass.length; i++) {
					gradeSizes[i] += schoolClass[i];
				}
			}

			for (int i = 0; i < Grades.GRADES; i++) {
				System.out.println(GRADE_NAMES[i] + " grade has " + gradeSizes[i] + " total students.");
			}
		}

		System.out.print("\n");

		int[] subjectSizes = new int[Classes.CLASSES];
		{
			for (int i = 0; i < subjectSizes.length; i++) {
				for (int classSize : school[i]) {
					subjectSizes[i] += classSize;
				}
			}

			for (int i = 0; i < Classes.CLASSES; i++) {
				System.out.println(CLASS_NAMES[i] + " class has " + subjectSizes[i] + " total students.");
			}
		}

		System.out.print("\n");

		{
			int[] ninthGraderClasses = new int[Classes.CLASSES];

			for(int i = 0; i < school.length; i++){
				for(int j = 0; j < school[i].length; j++){
					if(j == Grades.NINTH){
						ninthGraderClasses[i] += school[i][j];
					}
				}
			}

			int largestClass = 0, max = ninthGraderClasses[0];

			for(int i = 0; i < ninthGraderClasses.length; i++){
				if(ninthGraderClasses[i] > max){
					max = ninthGraderClasses[i];
					largestClass = i;
				}
			}

			System.out.println(CLASS_NAMES[largestClass] + " has the most ninth graders in it at " + max + " students.");
		}

		System.out.print("\n");

		{
			double[][] percentOfGradeInClass = new double[Classes.CLASSES][Grades.GRADES];
			for(int i = 0; i < percentOfGradeInClass.length; i++){//classes
				for(int j = 0; j < percentOfGradeInClass[i].length; j++){//grades
					percentOfGradeInClass[i][j] = (double)school[i][j] / (double)subjectSizes[j] * 100;
				}
			}

			for(int i = 0; i < percentOfGradeInClass.length; i++){//classes
				System.out.print(CLASS_NAMES[i] + " class is made up of ");
				for(int j = 0; j < percentOfGradeInClass[i].length; j++){//grades
					System.out.print(percentOfGradeInClass[i][j] + "% of all " + GRADE_NAMES[j] + " graders");
					if(j < percentOfGradeInClass[i].length - 1) System.out.print(", ");
				}
				System.out.print("\n");
			}
		}

		{
			int totalSchoolSize = 0;
			for(int a: gradeSizes){
				totalSchoolSize += a;
			}
			System.out.println("Total school size: " + totalSchoolSize);
		}
	}
}
