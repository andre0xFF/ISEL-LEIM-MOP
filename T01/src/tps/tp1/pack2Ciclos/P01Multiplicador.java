package tps.tp1.pack2Ciclos;

import java.util.Scanner;

/**
 * A classe P01Multiplicador corresponde ao 1o exercicio do capitulo de Ciclos da serie de
 * exercicios. A classe trata de elevar um certo numero entre 0 e 20, demonstrando a
 * multiplicacao do numero por um certo numero de repeticoes.<p>
 * @author Andre Fonseca
 */
public class P01Multiplicador {

	/**
	 * O programa inicia-se por pedir ao utilizador um numero de 0 a 20 e outro de 0 a 10 correspondendo
	 * a numero de repeticoes e realizando as respectivas verificacoes, caso um dos numeros nao esteja
	 * dentro dos intervalos o utilizador e' informado e e'-lhe pedido, novamente, para introduzir
	 * o numero em causa.<p>
	 * Para demonstrar o numero de multiplicacoes (repeticoes) do respectivo numero e' feito um ciclo
	 * para esse numero de repeticoes em que vai sendo escrito na consola a demonstracao aritmetica. 
	 */
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
