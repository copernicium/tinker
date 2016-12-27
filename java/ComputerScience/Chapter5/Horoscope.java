package ComputerScience.Chapter5;

import MySystem.MySystem;

import java.util.Scanner;

/**
 * Makes up a horoscope given a birthday
 *
 * @author Logan Traffas
 * @version 12/1/2016
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
		final int JAN = 1, FEB =2, MAR = 3, APR = 4, MAY =5, JUN = 6, JUL = 7, AUG = 8, SEP = 9, OCT = 10, NOV = 11, DEC = 12;
		final int JAN_LIM = 20, FEB_LIM = 19, MAR_LIM = 21, APR_LIM = 20, MAY_LIM = 21, JUN_LIM = 21, JUL_LIM = 23, AUG_LIM = 23, SEP_LIM = 23, OCT_LIM = 22, NOV_LIM = 22, DEC_LIM = 22;
		switch(this.month){
			case JAN:
				if(this.day < JAN_LIM) return AstrologicalSign.CAPRICORN;
				return AstrologicalSign.AQUARIUS;
			case FEB:
				if(this.day < FEB_LIM) return AstrologicalSign.AQUARIUS;
				return AstrologicalSign.PISCES;
			case MAR:
				if(this.day < MAR_LIM) return AstrologicalSign.PISCES;
				return AstrologicalSign.ARIES;
			case APR:
				if(this.day < APR_LIM) return AstrologicalSign.ARIES;
				return AstrologicalSign.TAURUS;
			case MAY:
				if(this.day < MAY_LIM) return AstrologicalSign.TAURUS;
				return AstrologicalSign.GEMINI;
			case JUN:
				if(this.day < JUN_LIM) return AstrologicalSign.GEMINI;
				return AstrologicalSign.CANCER;
			case JUL:
				if(this.day < JUL_LIM) return AstrologicalSign.CANCER;
				return AstrologicalSign.LEO;
			case AUG:
				if(this.day < AUG_LIM) return AstrologicalSign.LEO;
				return AstrologicalSign.VIRGO;
			case SEP:
				if(this.day < SEP_LIM) return AstrologicalSign.VIRGO;
				return AstrologicalSign.LIBRA;
			case OCT:
				if(this.day < OCT_LIM) return AstrologicalSign.LIBRA;
				return AstrologicalSign.SCORPIO;
			case NOV:
				if(this.day < NOV_LIM) return AstrologicalSign.SCORPIO;
				return AstrologicalSign.SAGITTARIUS;
			case DEC:
				if(this.day < DEC_LIM) return AstrologicalSign.SAGITTARIUS;
				return AstrologicalSign.CAPRICORN;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return AstrologicalSign.AQUARIUS;//should never reach here
	}

	public String getHoroscope() {
		AstrologicalSign sign = getAstrologicalSign();
		String horoscope = "" + sign + " - ";
		switch (sign) {
			case ARIES:
				horoscope += "When it comes to dealing with change, you're tops. Truth be told, you just love it -- so your mission now is to provide a blissfully happy example of how terrific the\noutcome of change, no matter how sudden and drastic, can be. It's all in your attitude, as you well know. Encourage your loved ones to stay positive. No, insist on it.";
				break;
			case TAURUS:
				horoscope += "It's time for a change. That doesn't mean those changes will be unpleasant, but because it's really just the concept of change you object to, this could be a tough day.\nIf you wake up feeling that things are already different, don't panic. You never know: This time out, it may all turn out wonderfully. In fact, if you can adapt, wonderful is a given.";
				break;
			case GEMINI:
				horoscope += "If anyone loves the new, different and unusual in life -- and that applies to people, places and things (as long as they're interesting, and, more importantly, fun)\n-- it's you. You're famous for it. Well, the heavens have decided to give you all that and more now, so get ready. Some wonderful diversions are on the way. One of those detours may even arrive in human form -- the kind that's not too hard on the eyes.";
				break;
			case CANCER:
				horoscope += "The heavens have seen fit to get you intimately connected with someone who'll be willing to let their hair down, unafraid to express their innermost feelings and\nabsolutely eager to partner up. If you're already attached to them, it's time to get even closer. If not, don't you dare cancel that date, even if it's a blind date. This could actually be the real thing.";
				break;
			case LEO:
				horoscope += "You're passionate and always game for a new experience -- and that means that keeping secrets can be a bit of a challenge for you. Not the secrets of others,\nmind you (those are safe forever). It's your own that give you problems, possibly due to the fact that you do so love drama -- okay, and even melodrama. So when you say something that isn't in the Miss Manners book of etiquette, ignore their eyebrows. Or, better yet, wink and move on.";
				break;
			case VIRGO:
				horoscope += "You're still worried about work -- but you really don't need to be, especially if it's that issue you know you really should have let go of several days\n(and maybe even weeks) ago. When you're feeling obsessed, however, there's really no talking to you, and no way to distract you. Remember, though, that getting too involved in anything never works out favorably. Go to a movie, call a friend or take up a new hobby. Just keep your mind busy.";
				break;
			case LIBRA:
				horoscope += "It's time for some lovely surprises of a completely, totally romantic nature. Putting up with all this attention, of course, will be just awful for you,\nbut do try to force yourself to bear the pressure. You can handle it. It's your job and you've learned it well. Make sure you don't leave the house unkempt -- you never know who's watching and waiting.";
				break;
			case SCORPIO:
				horoscope += "It's time for some lovely surprises of a completely, totally romantic nature. Putting up with all this attention, of course, will be just awful for you,\nbut do try to force yourself to bear the pressure. You can handle it. It's your job and you've learned it well. Make sure you don't leave the house unkempt -- you never know who's watching and waiting.";
				break;
			case SAGITTARIUS:
				horoscope += "It's time for some lovely surprises of a completely, totally romantic nature. Putting up with all this attention, of course, will be just awful for you,\nbut do try to force yourself to bear the pressure. You can handle it. It's your job and you've learned it well. Make sure you don't leave the house unkempt -- you never know who's watching and waiting.";
				break;
			case CAPRICORN:
				horoscope += "When it comes to spending, you've been totally in control. At the moment, though, there's something you want, and you just can't stand to not having it.\nYou want to go to the mall, and you want to bring along every single piece of plastic you have. Well, why not? If you can afford it, buy someone special something special while you're at it.";
				break;
			case AQUARIUS:
				horoscope += "You're due for romance. A whole lot of it. It might even be love. Of course, it's up to you whether this encounter will last forever and a day or just for a day.\nIf you find yourself thinking about forever, remember your former motto, for which you're famous: 'freedom at all costs.' Convincing the admirer in question that you're thinking about the long haul may be tricky, but anyone as intelligent, unusual and interesting as you are is worth the effort. Share Share Share Share";
				break;
			case PISCES:
				horoscope += "The tricky part of the juicy secret that's about to be whispered into your ear is that you may need to keep it that way for at least a few more weeks --\nand as wonderful as you'll be feeling once you've heard it, that may not be easy. Still, though -- think of how wonderful you'll feel about letting the cat out of the bag when you finally do (and of how smug you'll feel). You're grinning already, aren't you?";
				break;
			default:
				MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		}
		return horoscope;
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
