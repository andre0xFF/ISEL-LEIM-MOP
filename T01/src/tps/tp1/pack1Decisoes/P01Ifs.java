package tps.tp1.pack1Decisoes;

import java.io.Console;
import java.util.Scanner;

public class P01Ifs {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
			
		System.out.printf("Just give me a number (1-20): ");
		String read = scanner.nextLine();
		
		int input = Integer.parseInt(read);
		
		if(input > 0 && input < 21) {
			System.out.println(input + " / 3 = " + (input / 3));
			System.out.println(input + " % 3 = " + (input % 3));
			
			System.out.println("This is" + (input % 3 != 0 ? " not" : "") + " a multiple number");
		}
		else
			System.out.println("Number is bigger then 20");
	}

}
