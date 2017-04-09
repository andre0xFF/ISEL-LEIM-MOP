package tps.tp1.pack3Arrays;


import java.util.Arrays;
import java.util.Random;

public class P03ArraysExtractUniqsAndReps {

    public static void main(String[] args) {
        int[] ar_1 = new int[10];
        int[] ar_2 = new int[10];
        int[] ar_3 = new int[ar_1.length + ar_2.length];
        int[] ar_4 = new int[ar_1.length + ar_2.length];

        for (int i = 0; i < ar_1.length; i++) {
            int r;

            do {
                r = new Random().nextInt(20 + 1);
            }
            while(find_number(r, ar_1));

            ar_1[i] = r;

            do {
                r = new Random().nextInt(20 + 1);
            }
            while(find_number(r, ar_2));

            ar_2[i] = r;
        }

        System.out.printf("1st array: %s\n", Arrays.toString(ar_1));
        System.out.printf("2nd array: %s\n", Arrays.toString(ar_2));

        int j = 0;
        int k = 0;
        int h = 0;

        do {
            if (!find_number(ar_1[j], ar_2)) {
                ar_3[k++] = ar_1[j];
            }
            else if (!find_number(ar_1[j], ar_4)) {
                ar_4[h++] = ar_1[j];
            }
            if (!find_number(ar_2[j], ar_1)) {
                ar_3[k++] = ar_2[j];
            }
            else if (!find_number(ar_2[j], ar_4)) {
                ar_4[h++] = ar_2[j];
            }
        } while(++j < ar_1.length);

        ar_3 = trim_array(ar_3, k);
        ar_4 = trim_array(ar_4, h);

        Arrays.sort(ar_3);
        Arrays.sort(ar_4);

        System.out.printf("3rd array: %s\n", Arrays.toString(ar_3));
        System.out.printf("4th array: %s\n", Arrays.toString(ar_4));
    }

    public static int[] trim_array(int[] array, int limit) {
        int[] new_array = new int[limit];

        for (int i = 0; i < limit; i++) {
            new_array[i] = array[i];
        }

        return new_array;
    }

    public static boolean find_number(int n, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (n == array[i]) {
                return true;
            }
        }

        return false;
    }
}
