package tps.tp1.pack3Arrays;

import java.util.Random;

public class P03ArraysExtractUniqsAndReps {
	
	public static void main(String[] args) {
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		
		for(int i = 0; i < arr1.length; i++) {
			int n;
			
			do {
				n = randomNumber(0, 20);
			} while(checkNumberInArray(arr1, n));
			
			arr1[i] = n;
			
			do {
				n = randomNumber(0, 20);
			} while(checkNumberInArray(arr2, n));
			
			arr2[i] = n;
		}
		
		System.out.printf(
				"Initial arrays:" 
				+ "\n" + arrayToString(arr1) 
				+ "\n" + arrayToString(arr2)
				+ "\n"
				);
		
		int[] uniqueNumbers = new int[0];
		int[] nonUniqueNumbers = new int[0];
		
		for(int i = 0; i < arr1.length; i++) {
			if(!checkNumberInArray(arr1, i, true) && !checkNumberInArray(arr2, arr1[i])) {
				uniqueNumbers = push(uniqueNumbers, arr1[i]);	
			} 
			else if(!checkNumberInArray(nonUniqueNumbers, arr1[i])) {
				nonUniqueNumbers = push(nonUniqueNumbers, arr1[i]);
			}
			
			if(!checkNumberInArray(arr1, arr2[i]) && !checkNumberInArray(arr2, i, true)) {
				uniqueNumbers = push(uniqueNumbers, arr2[i]);
			} 
			else if(!checkNumberInArray(nonUniqueNumbers, arr2[i])) {
				nonUniqueNumbers = push(nonUniqueNumbers, arr2[i]);
			}
		}
		
		System.out.printf(
				"Unique numbers in both arrays:" 
				+ "\n" + arrayToString(uniqueNumbers)
				+ "\n" + "Non unique numbers in both arrays:"
				+ "\n" + arrayToString(nonUniqueNumbers)
				);
	}
	
	private static int[] push(int[] uniqueNumbers, int number) {
		int[] newArr = new int[uniqueNumbers.length + 1];
		
		for(int i = 0; i < uniqueNumbers.length; i++) {
			newArr[i] = uniqueNumbers[i];
		}
		
		newArr[newArr.length - 1] = number;
		
		return newArr;
	}

	public static String arrayToString(int[] numbers) {
		String msg = "[";
		
		for(int i = 0; i < numbers.length; i++) {
			msg += " " + numbers[i] + " ";
		}
		
		return msg + "]\n";
	}
	
	public static boolean checkNumberInArray(int[] arr, int number) {
		for(int j = 0; j < arr.length; j++) {
			if(arr[j] == number) {
				return true;
			}
		}		
		
		return false;
	}
	
	public static boolean checkNumberInArray(int[] arr, int idx, boolean numberBelongsToArray) {
		if(!numberBelongsToArray) {
			return checkNumberInArray(arr, arr[idx]);
		}
		
		for(int j = 0; j < arr.length; j++) {
			if(j != idx && arr[j] == arr[idx]) {
				return true;
			}
		}		
		
		return false;
	}
	
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
