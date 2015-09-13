import java.io.*;
import java.util.*;


public class Sadgab {

	static String[] inSyllables;
	static String[] inStringSplit;
	static String inString;
	static Dictionary dict;
	public static void main(String[] args) {
		File rawDict = new File("dictionary.txt");
		dict = new Dictionary(rawDict);
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to SadGab");
		System.out.println("Please input words: ");
		inString = input.nextLine().toUpperCase();
		
		inStringSplit = inString.split(" ");
		
		StringBuilder inputSyll = new StringBuilder();
		
		for(String s : inStringSplit)
		{
			inputSyll.append(dict.getSyllable(s)).append(" ");
		}
		
		inSyllables = inputSyll.toString().split(" ");
		
		System.out.println("Convert: "+convert(0));
		
//		String outString = dict.getSyllable(inString);
//		System.out.println(outString);
//		System.out.println(dict.getWord(outString));
		

	}
	
	public static String convert(Integer startIndex)
	{
		int endIndex = startIndex;
		int lastIndex = inSyllables.length - 1;
		
		while(endIndex <= lastIndex) {
			String found = dict.getWord(createString(startIndex, endIndex));
			if(found != null && !duplicateWord(found)) {
				if(endIndex == lastIndex) {
					//System.out.println("StartIndex: "+startIndex+" EndIndex: "+endIndex+"/n")
					return found;
				}
				else {
					String subsequent = convert(endIndex + 1);
					if(subsequent != null) {
						return found +" "+ subsequent;
					}
				}
			}
			endIndex++;
		}
		return null;
	}
	
	public static String createString(int startIndex, int lastIndex) {
		StringBuilder sb = new StringBuilder();
		for(int i = startIndex; i <= lastIndex; i++) {
			sb.append(inSyllables[i]).append(" ");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
	
	public static boolean duplicateWord(String word)
	{
		return (inString.indexOf(word.toUpperCase()) != -1);
	}
}
