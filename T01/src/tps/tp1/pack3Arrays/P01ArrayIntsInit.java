package tps.tp1.pack3Arrays;

import java.util.Random;

/**
 * A classe P01ArrayIntsInit corresponde ao 1o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe gera 10 numeros aleatorios dentro de um intervalo de 10 a 50,
 * dos quais e' determinado o maior numero impar para ser somado a todos os numeros pares.
 * <p>
 * 
 * @author Andre Fonseca
 */
public class P01ArrayIntsInit {
	
	/**
	 * O programa comeca por gerar 10 numeros aleatorios, todos num intervalo de 10 a 50.
	 * Desses numeros gerados e' determinado o numero impar mais alto, o qual e' apresentado
	 * ao utilizador. De seguida esse mesmo numero e' somado a todos os numero pares e,
	 * novamente, apresentado ao utilizador.
	 */
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
	
	/**
	 * Adiciona a todos os numeros pares um outro numero.
	 * 
	 * @param numbers
	 * 					Array de numeros
	 * @param addNumber
	 * 					Numero a ser adicionado
	 * @return
	 * 					Respectivos array de numero cujos
	 * 					numeros pares lhes foi somado um numero.
	 */
	public static int[] addToEvenNumbers(int[] numbers, int addNumber) {
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] % 2 == 0) {
				numbers[i] += addNumber;
			}
		}	
		
		return numbers;
	}
	
	/**
	 * Determina o numero impar mais alto presente num array.
	 * 
	 * @param numbers
	 * 					Array de numeros
	 * @return
	 * 					Numero mais alto presente num array
	 */
	public static int higherOddNumber(int[] numbers) {
		int max = 0;
		
		for(int i = 0; i < numbers.length; i++) {
			if((numbers[i] % 2 != 0) && (numbers[i]) > max) {
				max = numbers[i];
			}
		}
		
		return max;
	}
	
	/**
	 * Gera um numero aleatorio com base num intervalo de numero
	 * minimo e maximo.
	 * 
	 * @param min
	 * 				Numero minimo que pode ser gerado.
	 * @param max
	 * 				Numero maximo que pode ser gerado.
	 * @return
	 * 				Numero aleatorio.
	 */
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
