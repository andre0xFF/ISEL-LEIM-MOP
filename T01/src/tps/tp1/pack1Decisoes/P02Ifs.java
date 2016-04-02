package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P02Ifs corresponde ao 2o exercicio do capitulo de Decisoes da serie de
 * exercicios.<p>
 * @author Andre Fonseca
 */
public class P02Ifs {
	
	/**
	 * O programa inicia-se por pedir ao utilizador 3 numeros inteiros sendo estes guardados num array,
	 * de seguida estes sao organizados de forma crescente atraves do metodo QuickSort()
	 * <p>
	 * Neste caso, foi utilizado um algoritmo do tipo quicksort aumentando a complexidade do que era
	 * exigido apenas para tornar o problema mais interessante. Apos os numero terem sido ordenados
	 * estes sao entao apresentados ao utilizador.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Give me three int numbers:\n");
		
		int[] input = new int[3];
		
		for(int i = 0; i < 3; i++) {
			input[i] = Integer.parseInt(scanner.nextLine());
		}
		
		input = QuickSort(input);
		
		System.out.println("O maior valor e' " + input[2] + ", o valor do meio e' " + input[1] + " e o menor valor e' " + input[0] );
	}
	
	public static int[] QuickSort(int[] vector) {
      QuickSort(vector, 0, vector.length - 1);
      
      return vector;
   }

	/**
	 * O algoritmo quicksort divide o array em varias metades consoante o numero minimo e maximo de elementos
	 * tratando de casa metade individualmente e de forma recursiva tornando-se assim nm algoritmo dinamico
	 * e eficiente.
	 * @param vector Array a ser ordenado
	 * @param high Numero maximo de elementos do array
	 * @param low Numero minimo de elementos do array
	 * @return Array de forma ordenada de forma crescente
	 */
   public static int[] QuickSort(int[] vector, int high, int low) {
      if (high < low) {
         int pivot = split(vector, high, low);
         QuickSort(vector, high, pivot - 1);
         QuickSort(vector, pivot + 1, low);
      }
      
      return vector;
   }

   protected static int split(int[] vector, int high, int end) {
      int pivot = vector[high];
      int i = high + 1;
      int f = end;
      
      while (i <= f) {
         if (vector[i] <= pivot) {
            i++;
         } else if (pivot < vector[f]) {
            f--;
         } else {
            int troca = vector[i];
            vector[i] = vector[f];
            vector[f] = troca;
            i++;
            f--;
         }
      }
      
      vector[high] = vector[f];
      vector[f] = pivot;
      
      return f;
   }
}
