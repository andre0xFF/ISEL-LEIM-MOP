package tps.tp1.pack3Arrays;

import java.util.Scanner;

public class P02Histograma {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] values = new int[10];
        boolean invalid_number = false;
        int max = 0;

        do {
            System.out.printf("Insert histogram values: ");
            String[] input = scanner.nextLine().split(" ");

            for (int i = 0; i < input.length; i++) {
                values[i] = Integer.parseInt(input[i]);

                if (values[i] < 0 || values[i] > 20) {
                    invalid_number = true;
                }
            }
        } while(invalid_number);

//        String[] histogram = new String[values.length];

//        for (int i = 0; i < values.length; i++) {
//            histogram[i] = "";
//
//            for (int j = 0; j < values[i]; j++) {
//                histogram[i] += "#";
//            }
//
//            if (histogram[i].length() > max) {
//                max = histogram[i].length();
//            }
//        }

        for (int i = max; i > 0; i--) {
            for (int j = 0; j < values.length; i++) {
                if (j <= values[j]) {

                }
            }
        }
    }
}
