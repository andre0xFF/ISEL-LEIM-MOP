package tps.tp1.pack3Arrays;

import java.util.Scanner;

/**
 * A classe P02ArrayIntsAddMirror corresponde ao 2o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe gera 10 numeros aleatorios dentro de um intervalo de 10 a 99,
 * dos quais e' determinado o maior numero impar para ser somado a todos os numeros pares.
 * <p>
 * 
 * @author Andre Fonseca
 */
public class P02ArrayIntsAddMirror {

	/**
	 * E' pedido ao utilizador 10 numeros de 10 a 99, cujo pedido e' pedido caso algum dos numeros
	 * introduzidos nao esteja dentro do intervalo. De seguida e' relizada a soma entre numeros de
	 * posicoes opostas atraves de um ciclo que percorre metade do array de numeros e somando os
	 * respectivos valores.
	 */
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

	/**
	 * Constroi uma string com os valores de um determinado array.
	 * 
	 * @param numbers
	 * 				Array de numeros
	 * @return
	 * 				String com a representacao dos numeros no array
	 */
	public static String arrayToString(int[] numbers) {
		String msg = "[";
		
		for(int i = 0; i < numbers.length; i++) {
			msg += " " + numbers[i] + " ";
		}

		return msg + "]\n";
	}

}
