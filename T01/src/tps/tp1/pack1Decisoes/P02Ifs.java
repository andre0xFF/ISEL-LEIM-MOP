package tps.tp1.pack1Decisoes;

import java.util.Scanner;

public class P02Ifs {
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

   private static int[] QuickSort(int[] vector, int high, int low) {
      if (high < low) {
         int pivot = split(vector, high, low);
         QuickSort(vector, high, pivot - 1);
         QuickSort(vector, pivot + 1, low);
      }
      
      return vector;
   }

   private static int split(int[] vector, int high, int end) {
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
