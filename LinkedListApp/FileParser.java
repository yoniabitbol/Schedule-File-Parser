import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//-------------------------------------------------------
//Assignment 4
//Written by: Jonathan Abitbol 40190550 & Adir Ben-David 40190551
//For COMP 249 Section D-DB Fall 2021
//Due Date = Sunday December 5, 2021
//-------------------------------------------------------

//Welcome to my Driver.
//This program is written on 12/01/2021 by Jonathan Abitbol & Adir Ben-David.

/**
* Adir Ben-David 40190551 & Jonathan Abitbol 40190550
* COMP 249
* Assignment #4
* 12/01/2021
*/

/**
	 * This FileParser class is a program that accept any text file, as input, and creates a file parser that processes all the words found in that input file
	 * @author Adir Ben-David and Jonathan Abitbol
	 * 
	 */
public class FileParser {
	
/**
 * 
 * This FileParser class is a program that accept any text file, as input, and creates a file parser that processes all the words found in that input file
 * @author Adir Ben-David and Jonathan Abitbol
 * @param args paramater args
 */
	public static void main(String[] args) {

		
		Scanner Scanner = null;
		PrintWriter p1 = null;
		PrintWriter p2 = null;
		PrintWriter p3 = null;
		Scanner keyIn = new Scanner(System.in);
		System.out.println("Enter the file's name for reading: ");
		String fileName = keyIn.nextLine();

		ArrayList<String> dataSet = new ArrayList<String>();
		ArrayList<String> x = new ArrayList<String>();
		ArrayList<String> y = new ArrayList<String>();
		ArrayList<String> z = new ArrayList<String>();

		try {
			p1 = new PrintWriter(new FileOutputStream("src/Files/obsessive_non_ona.txt"));
			p2 = new PrintWriter(new FileOutputStream("src/Files/popular_peeps.txt"));
			p3 = new PrintWriter(new FileOutputStream("src/Files/non_alphanumeric.txt"));
			Scanner = new Scanner(new FileInputStream("src/A4 Files/"+fileName));
			System.out.println("\n" + fileName + " is being parsed!");
			

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Program Terminating!");
			System.exit(0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		while (Scanner.hasNextLine()) {
			dataSet.add(Scanner.next().toLowerCase());
		}

		for (String indData : dataSet) {
			// check if has o or n or a
			if (!(indData.contains("a") || indData.contains("n") || indData.contains("o"))) {
				x.add(indData);
			}
		}
		x = deleteDuplicates(x);
		Collections.sort(x);

		p1.println("word counter: " + x.size());
		for (String string : x) {
			p1.println(string);

		}

		y = duplicate(dataSet);
		Collections.sort(y);
		p2.println("word counter: " + y.size());
		for (String d : y) {
			p2.println(d);
		}

		z = checkAlpha(dataSet);
		z = deleteDuplicates(z);
		Collections.sort(z);
		p3.println("word counter: " + z.size());
		for (String string : z) {
			p3.println(string);
		}

		// CLOSING
		keyIn.close();
		Scanner.close();
		p1.close();
		p2.close();
		p3.close();

	}
	
	/**
	 * 
	 * This method is to make sure that any duplicated data is deleted from the list.
	 * @param list This method is to make sure that any duplicated data is deleted from the list.
	 * @return nList
	 */

	public static ArrayList<String> deleteDuplicates(ArrayList<String> list) {

		ArrayList<String> nList = new ArrayList<String>();
		for (String s : list) {
			if (!nList.contains(s)) {
				nList.add(s);
			}

		}
		return nList;
	}

	/**
	 * This methods checks for alpha numerical data.
	 * @param list This methods checks for alpha numerical data.
	 * @return nList 
	 */
	public static ArrayList<String> checkAlpha(ArrayList<String> list) {
		ArrayList<String> nList = new ArrayList<String>();
		for (String s : list) {
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (!Character.isLetterOrDigit(c[i])) {
					nList.add(String.valueOf(c[i]));
				}
			}
		}
		return nList;
	}
	
	/**
	 * This ArrayList will store and return words used more than three times in a the text file.
	 * @param list This ArrayList will store and return words used more than three times in a the text file.
	 * @return nList
	 */

	public static ArrayList<String> duplicate(ArrayList<String> list) {
		int wordC;
		ArrayList<String> nList = new ArrayList<String>();
		ArrayList<String> UsedWord = new ArrayList<String>();
		for (String s : list) {
			if (!UsedWord.contains(s)) {
				wordC = 0;
				for (String s2 : list) {
					if (s.equals(s2) || s == s2) {
						wordC++;
					}
				}
				if (wordC > 3) {
					nList.add(s);
				}
			}

			UsedWord.add(s);

		}
		return nList;
	}

}
