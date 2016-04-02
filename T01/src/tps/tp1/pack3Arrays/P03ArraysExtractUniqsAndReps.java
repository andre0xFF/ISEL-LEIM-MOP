package tps.tp1.pack3Arrays;

import java.util.Random;

/**
 * A classe P03ArraysExtractUniqsAndReps corresponde ao 3o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe gera 10 numeros aleatorios dentro de um intervalo de 0 a 20 para dois arrays,
 * dos quais e' verificado os numeros que teem e que nao teem repeticoes entre ambos os arrays.
 * <p>
 * 
 * @author Andre Fonseca
 */
public class P03ArraysExtractUniqsAndReps {
	
	/**
	 * Inicialmente sao gerados dois conjuntos de 10 numeros que variam entre 0 e 20.
	 * De seguida, atraves de um ciclo que percorrer todos os 10 numeros, estes sao
	 * verificados por repeticoes nos dois array. Caso nao exista repeticao entao
	 * o numero e' guardado num array correspondente aos numeros que nao se repetem,
	 * o mesmo acontece para os numeros que se repetem.
	 */
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
	
	/**
	 * Adiciona um certo numero na ultima posicao de um array de numeros.
	 * O novo array e' construido com uma dimensao superior ao recebido
	 * de forma a alocar o novo numero na ultima posicao.
	 * 
	 * @param uniqueNumbers
	 * 				Array de numeros
	 * @param number
	 * 				Numero a adicionar
	 * @return
	 * 				Novo array de numeros com o numero adicionado
	 */
	protected static int[] push(int[] uniqueNumbers, int number) {
		int[] newArr = new int[uniqueNumbers.length + 1];
		
		for(int i = 0; i < uniqueNumbers.length; i++) {
			newArr[i] = uniqueNumbers[i];
		}
		
		newArr[newArr.length - 1] = number;
		
		return newArr;
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
	 * Verifica de um determinado numero existe num array.
	 * 
	 * @param arr
	 * 				Array de numeros
	 * @param number
	 * 				Numero a ser verificado
	 * @return
	 * 				True caso o numero exista no array
	 */
	public static boolean checkNumberInArray(int[] arr, int number) {
		for(int j = 0; j < arr.length; j++) {
			if(arr[j] == number) {
				return true;
			}
		}		
		
		return false;
	}
	
	/**
	 * Verifica de um determinado numero existe num array sem contar com ele proprio.
	 * 
	 * @param arr
	 * 				Array de numeros
	 * @param idx
	 * 				Indice do numero a ser verificado
	 * @param numberBelongsToArray
	 * 				Caso o numero pertenca ao array introduzido
	 * @return
	 * 				True caso o numero exista no array
	 */
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
