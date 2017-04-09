package tps.tp1.pack2Ciclos;

import java.util.Scanner;

/**
 * A classe P02Cardinais corresponde ao 2o exercicio do capitulo de Ciclos da serie de
 * exercicios. Um objecto desta classe mostra um conjunto de caracteres '#' com base
 * nos dois limites introduzidos pelo utilizador.
 * <p>
 * @author Andre Fonseca
 */
public class P02Cardinais {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number_1;
        int number_2;

        do {
            System.out.println("Two numbers (>= 0)?");
            String[] input = scanner.nextLine().split(" ");

            number_1 = Integer.parseInt(input[0]);
            number_2 = Integer.parseInt(input[1]);

            if(number_1 < 0) {
                System.out.println("Number must be positive: " + number_1);
            }
            if(number_2 < 0) {
                System.out.println("Number must be positive: " + number_2);
            }
        } while(number_1 < 0 || number_2 < 0);

        int step = (number_2 - number_1) / Math.abs(number_2 - number_1);

        while(number_1 != number_2) {
            draw_row(number_1);
            System.out.printf("\n");
            number_1 += step;
        }

        draw_row(number_1);
    }

    protected static void draw_row(int n) {
        for (int i = 0; i < n; i++) {
            System.out.printf("#");
        }
    }
}
