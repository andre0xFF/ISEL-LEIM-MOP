package tps.tp1.pack2Ciclos;

import java.util.Scanner;

public class P01Multiplicador {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int number;
		int nReps;
		
		do {
			System.out.printf("Number (0-20)? \n");
			number = Integer.parseInt(scanner.nextLine());
			
			if(number < 0 || number > 20) {
				System.out.println("Invalid number");
			}
		} while(number < 0 || number > 20);
		
		do {
			System.out.printf("nReps (0-10)? \n");
			nReps = Integer.parseInt(scanner.nextLine());
			
			if(nReps < 0 || nReps > 10) {
				System.out.println("Invalid number");
			}
		} while(nReps < 0 || nReps > 10);
		
		
		int i = 1;
		
		do {
			System.out.printf(number + " * ");
		} while(++i < nReps);
		
		System.out.printf(number + " = ");	
		System.out.printf("%f", Math.pow(number, nReps));
		

	}

}
