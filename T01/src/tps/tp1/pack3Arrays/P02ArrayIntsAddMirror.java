package tps.tp1.pack3Arrays;

import java.util.Scanner;

public class P02ArrayIntsAddMirror {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int[] numbers = new int[10];

		for(int i = 0; i < numbers.length; i++) {
			while(numbers[i] == 0) {
				String input = scanner.nextLine();
				
				int aux = Integer.parseInt(input);
				
				if(aux >= 10 && aux <= 99) {
					numbers[i] = aux;
				}
				
			}
		}
		
		System.out.println(arrayToString(numbers));
		
		for(int i = 0; i < numbers.length / 2; i++) {
			int backNumber = numbers.length - i - 1;
			
			int n1 = numbers[i];
			int n2 = numbers[backNumber];
			
			numbers[i] = n1 + n2;
			numbers[backNumber] = n1 + n2;
		}
		
		System.out.println(arrayToString(numbers));
	}

	public static String arrayToString(int[] numbers) {
		String msg = "[";
		
		for(int i = 0; i < numbers.length; i++) {
			msg += " " + numbers[i] + " ";
		}

		return msg + "]\n";
	}

}
