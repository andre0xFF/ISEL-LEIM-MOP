package tps.tp1.pack1Decisoes;

import java.util.Scanner;

public class P04OpTernario {

	private static String[] ELEMENTS = { "Fire", "Earth", "Metal", "Water", "Wood" };
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Element? \n");
		String input = scanner.nextLine();
		
		int j = getElementIndex(input, ELEMENTS) + 1 % ELEMENTS.length;	
		
		if(j != -1) {
			System.out.println(input + " generates " + ELEMENTS[(j == ELEMENTS.length) ? 0 : j]);
		}
		else {
			System.out.println("Element not found");
		}
	}
	
	private static int getElementIndex(String element, String[] elements) {
		for(int i = 0; i < elements.length; i++) {
			if(element.compareToIgnoreCase(elements[i]) == 0) {
				return i;
			}
		}
		
		return -1;
	}

}
