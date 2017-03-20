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
                    continue;
                }

                if (values[i] > max) {
                    max = values[i];
                }
            }
        } while(invalid_number);


        for (int i = max; i > 0; i--) {
            System.out.printf(String.valueOf(i) + " ");

            for (int j = 0; j < values.length; j++) {
                if (values[j] >= i) {
                    System.out.printf("# ");
                }
                else {
                    System.out.printf("  ");
                }
            }
            System.out.printf("\n");
        }
    }
}
