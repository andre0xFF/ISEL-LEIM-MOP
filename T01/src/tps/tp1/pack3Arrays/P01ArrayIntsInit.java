package tps.tp1.pack3Arrays;

import java.util.Random;

public class P01ArrayIntsInit {
	public static void main(String[] args) {
		int[] numbers = new int[10];

		
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = randomNumber(10, 50);
		}
		
		System.out.println(arrayToString(numbers));	
		
		int higherOddNumber = higherOddNumber(numbers);
		
		System.out.printf("Higher odd number: %d\n\n", higherOddNumber);
		
		int[] addEvenNumbers = addToEvenNumbers(numbers, higherOddNumber);
		
		System.out.println(arrayToString(numbers));
		
		
	}
	
	public static String arrayToString(int[] numbers) {
		String msg = "[";
		
		for(int i = 0; i < numbers.length; i++) {
			msg += " " + numbers[i] + " ";
		}
		
		msg += "]\n";
		
		
		return msg;
	}
	
	public static int[] addToEvenNumbers(int[] numbers, int addNumber) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] % 2 == 0) {
				numbers[i] += addNumber;
			}
		}	
		
		return numbers;
	}
	
	public static int higherOddNumber(int[] numbers) {
		int max = 0;
		
		for(int i = 0; i < numbers.length; i++) {
			if((numbers[i] % 2 != 0) && (numbers[i]) > 0) {
				max = numbers[i];
			}
		}
		
		return max;
	}
	
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
}
