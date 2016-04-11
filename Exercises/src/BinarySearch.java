
public class BinarySearch {
	
	public static void main(String[] args) {
		// array must be sorted before binary search
		int[] array = new int[] { 4, 6, 9, 12, 45, 67, 70, 75, 82, 84, 88, 90 };
		
		System.out.println(binarySearch(82, 0, array.length - 1, array));
	}

	public static int binarySearch(int target, int low, int high, int[] array) {
		if(low > high) {
			return -1;
		}
		
		int mid = (high + low) / 2;
		
		if(target == array[mid]) {
			return mid;
		}
		
		if(target < array[mid]) {
			binarySearch(target, low, mid - 1, array);
		}
		
		return binarySearch(target, mid + 1, high, array);
	}
}
