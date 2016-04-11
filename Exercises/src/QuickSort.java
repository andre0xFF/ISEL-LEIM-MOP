
public class QuickSort {
	
	public static void main(String[] args) {
		int[] numbers = new int[] { 5, 9, 2, 10, 90, 56, 23, 1, 65, 3 };
		
		QuickSort(numbers);
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
	}
	
	protected static int split(int[] vector, int high, int end) {
		int pivot = vector[high];
		int i = high + 1;
		int f = end;
		      
		while (i <= f) {
			if (vector[i] <= pivot) {
				i++;
			} 
			else if (pivot < vector[f]) {
				f--;
			} 
			else {
				int swap = vector[i];
				vector[i] = vector[f];
				vector[f] = swap;
				i++;
				f--;
			}
		}
		      
		vector[high] = vector[f];
		vector[f] = pivot;
		      
		return f;
	}
	   
	public static int[] QuickSort(int[] vector) {
		QuickSort(vector, 0, vector.length - 1); 
		return vector;
	}
	   
	public static int[] QuickSort(int[] vector, int high, int low) {
		if (high < low) {
			int pivot = split(vector, high, low);
			QuickSort(vector, high, pivot - 1);
			QuickSort(vector, pivot + 1, low);
		}
		      
		return vector;
	}
}
