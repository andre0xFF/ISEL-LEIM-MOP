package tps.tp1.pack2Ciclos;

import java.util.Scanner;

/**
 * A classe P02Triangulo corresponde ao 2o exercicio do capitulo de Ciclos da serie de
 * exercicios. A classe desenha um triangulo composto por asteriscos consoante o numero
 * de linhas introduzido pelo utilizador.<p>
 * 
 * @author Andre Fonseca
 */
public class P02Triangulo {

	/**
	 * Introduzindo um certo numero de linhas no intervalo de 1 a 21 com a respectiva 
	 * verificacao inicia-se um ciclo para o mesmo numero de linhas o qual ira
	 * desenhar o respectivo triangulo linha a linha.
	 */
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
	
	/**
	 * Desenha uma linha do triangulo consoante o numero da linha actual e o maximo
	 * de linhas desejadas
	 * 
	 * @param current
	 * 				Numero de linha actual
	 * @param max
	 * 				Maximo de linhas desejadas
	 */
	protected static void printTriangleLine(int current, int max) {
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
