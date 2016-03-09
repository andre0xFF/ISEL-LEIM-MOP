package tps.tp1.pack2Ciclos;

import java.util.Scanner;

public class P02Triangulo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n;
		
		do {
			System.out.println("Number (1-21)?");
			n = Integer.parseInt(scanner.nextLine());
		} while(n < 1 || n > 21);
		
		
		for(int i = 1; i <= n; i++) {
			printTriangleLine(i, n);
		}
	}
	
	public static void printTriangleLine(int current, int max) {
		int i = 0;
		
		for(; i < (max - current); i++) {
			System.out.printf(" ");
		}
		
		for(; i < max; i++) {
			System.out.printf("#");
		}
		
		System.out.printf("\n");
	}

}
