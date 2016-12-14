package computer_science.Chapter5;

import MySystem.MySystem;

import java.util.Scanner;

/**
 * Adds the appropriate article to a given country name in French
 *
 * @author Logan Traffas
 * @version 11/30/2016
 * assignment: Chapter 05--Decisions P5.7--French Names
 */
public class FrenchNames{
	enum Gender{MASCULINE,FEMININE}

	private static final String[] EXCEPTIONS = {"belize", "cambodge", "mexique", "mozambique", "zaïre", "zimbabwe"};

	private static final char[] VOWELS = {'a','e','i','o','u'};

	private static String toLower(final String ORIGINAL){
		String lowerCase = "";
		for(char a: ORIGINAL.toCharArray()){
			lowerCase += Character.toLowerCase(a);
		}
		return lowerCase;
	}

	private static Gender getGender(final String NAME){
		String lowerCase = toLower(NAME);
		for(String exception: EXCEPTIONS){
			if(lowerCase.equals(exception)) return Gender.MASCULINE;
		}
		if(lowerCase.charAt(lowerCase.length() - 1) == 'e') return Gender.FEMININE;
		return Gender.MASCULINE;
	}

	private static boolean isAVowel(final char TEST){
		for(char a: VOWELS){
			if(Character.toLowerCase(TEST) == a) return true;
		}
		return false;
	}

	private static boolean isPlural(final String NAME){
		return NAME.charAt(NAME.length() - 1) == 's';
	}

	public static String constructName(final String NAME){
		final String VOWEL_ARTICLE = "l'", PLURAL_ARTICLE = "les", MASCULINE_ARTICLE = "le", FEMININE_ARTICLE = "la";
		if(FrenchNames.isPlural(NAME)){
			return PLURAL_ARTICLE + " " + NAME;
		}
		if(FrenchNames.isAVowel(NAME.charAt(0))){
			return VOWEL_ARTICLE + NAME;
		}
		switch(getGender(NAME)){
			case FEMININE:
				return FEMININE_ARTICLE + " " + NAME;
			case MASCULINE:
				return MASCULINE_ARTICLE + " " + NAME;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return "";//should never reach here
	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a country name in French: ");
		String name = input.next().trim();
		System.out.println("Name is: " + FrenchNames.constructName(name));
	}
}
