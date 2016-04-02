package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

public class P04BaralhadorDeNomes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] names = new String[0];	
		String input = new String();

		while(names.length < 10) {
			input = scanner.nextLine();
			
			if(input.compareTo("fim") == 0) {
				if(names.length >= 5) {
					break;
				}
				else {
					System.out.printf("Not enough names!\n");
					continue;
				}
			}
					
			if(checkNameLength(input) && splitName(input, ' ').length > 3 && checkMinCharInName(input)) {
				names = pushToArray(names, input);
			} 
			else {
				System.out.printf("Invalid name!\n");
			}
		}
		
		System.out.printf("[x] Entered names:\n");
		
		for(int i = 0; i < names.length; i++) {
			System.out.printf("[%d] %s\n", i, names[i]);
			String[] nameArray = splitName(names[i], ' ');
		}
		
		System.out.printf("\n[x] Generated names:\n");
	
		String[] shuffledNames = shuffleNames(names);
		
		for(int i = 0; i < shuffledNames.length; i++) {
			System.out.printf("[%d] %s\n", i, shuffledNames[i]);
		}
	}
	
	public static String[] splitName(String name, char chr) {
		String[] nameArray = new String[0];
		
		int first = 0;
		int last = name.indexOf(chr);
		
		while(last != -1) {
			nameArray = pushToArray(nameArray, name.substring(first, last));
			first = name.indexOf(chr, last);
			last = name.indexOf(chr, ++first);
		}
		
		nameArray = pushToArray(nameArray, name.substring(first, name.length()));
		
		return nameArray;
	}
	
	public static String arrayToString(String[] str) {
		String newStr = new String();
		
		for(int i = 0; i < str.length - 1; i++) {
			newStr += str[i] + " ";
		}
		
		newStr += str[str.length - 1];
		
		return newStr;
	}
	
	public static String[] pushToArray(String[] names, String name) {
		String[] newNames = new String[names.length + 1];
		
		for(int i = 0; i < names.length; i++) {
			newNames[i] = names[i];
		}
		
		newNames[newNames.length - 1] = name;
		
		return newNames;		
	}

	public static String[] getAllFirstNames(String[] names) {
		String[] newNames = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			newNames[i] = splitName(names[i], ' ')[0];
		}
		
		return newNames;
	}
	
	public static String[] getAllLastNames(String[] names) {
		String[] newNames = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String[] name = splitName(names[i], ' ');
			newNames[i] = name[name.length - 1];
		}
		
		return newNames;
	}
	
	public static String[] getAllMiddleNames(String[] names) {
		String[] newNames = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String[] name = splitName(names[i], ' ');
					
			newNames[i] = names[i].substring(
					names[i].indexOf(name[0]) + name[0].length() + 1,
					names[i].indexOf(name[name.length - 1]) - 1
					);
		}
		
		return newNames;
	}
	
	public static String[] shuffleNames(String[] names) {
		String[] newNames = new String[0];
		
		for(int i = 0; i < names.length; i++) {
			String[] newName = new String[3];
			String[] randomName;
			
			do {
				randomName = splitName(names[randomNumber(0, names.length - 1)], ' ');
				
			} while(nameAlreadyExistsInArray(getAllFirstNames(newNames), getAllFirstNames(names), randomName[0]));
			
			newName[0] = randomName[0];
			
			int middleIdx;
			
			do {
				randomName = splitName(names[randomNumber(0, names.length - 1)], ' ');
				middleIdx = randomNumber(1, randomName.length - 2);

			} while(nameAlreadyExistsInArray(getAllMiddleNames(newNames), getAllMiddleNames(names), randomName[middleIdx]));
			
			newName[1] = randomName[middleIdx];
			
			do {
				randomName = splitName(names[randomNumber(0, names.length - 1)], ' ');

			} while(nameAlreadyExistsInArray(getAllLastNames(newNames), getAllLastNames(names), randomName[randomName.length - 1]));
			
			newName[2] = randomName[randomName.length - 1];
			
			newNames = pushToArray(newNames, arrayToString(newName));
		}
		
		return newNames;
	}

	private static boolean nameAlreadyExistsInArray(String[] newAllNames, String[] allNames, String name) {		
		int allNamesCounter = 0;
		int newAllNamesCounter = 0;
		
		
		for (int i = 0; i < allNames.length; i++) {
			String[] nameSplit = splitName(allNames[i], ' ');
			
			for (int j = 0; j < nameSplit.length; j++) {
				if(nameSplit[j].compareTo(name) == 0) {
					++allNamesCounter;
				}
			}
		}
		
		for (int i = 0; i < newAllNames.length; i++) {
			String[] nameSplit = splitName(newAllNames[i], ' ');
			
			for (int j = 0; j < nameSplit.length; j++) {
				if(nameSplit[j].compareTo(name) == 0) {
					++newAllNamesCounter;
				}
			}
		}
		
		return allNamesCounter == newAllNamesCounter;
	}

	public static boolean checkMinCharInName(String name) {
		String[] names = splitName(name, ' ');
		
		for(int i = 0; i < names.length; i++) {
			if(names[i].length() < 4) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean checkThreeNames(String name) {		
		return name.split(" ").length >= 3;
	}
	
	public static boolean checkNameLength(String name) {
		return name.length() < 120;
	}
	
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}

}
