import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {

	HashMap<String, String> dictMap;
	HashMap<String, String> syllMap;
	
	public Dictionary(File rawDict) {
		dictMap = new HashMap<String, String>();
		syllMap = new HashMap<String, String>();
		fillMap(rawDict);
	}
	
	private void fillMap(File rawDict) {
		try {
			Scanner sc = new Scanner(rawDict, "ISO-8859-1");
			String workingString = sc.nextLine();
			while(workingString.charAt(0) == ';'){
				workingString = sc.nextLine();
			}
			while(sc.hasNextLine()) {
				dictMap.put(workingString.substring(0, workingString.indexOf(' ')), workingString.substring(workingString.indexOf(' ')+2));
				syllMap.put(workingString.substring(workingString.indexOf(' ')+2), workingString.substring(0, workingString.indexOf(' ')));
				workingString = sc.nextLine();
			}
//			TODO remove test printing
//			for(String d : dictMap.keySet()) {
//				System.out.println(d + "\t" + dictMap.get(d));
//			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.\n");
			e.printStackTrace();
		}
	}
	
	public String getSyllable(String word) {
		return dictMap.get(word);
	}
	
	public String getWord(String syll) {
		return syllMap.get(syll);
	}
 }
