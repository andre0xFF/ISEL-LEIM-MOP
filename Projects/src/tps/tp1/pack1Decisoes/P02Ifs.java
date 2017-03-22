package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P02Ifs corresponde ao 2o exercicio do capitulo de Decisoes da serie de
 * exercicios.<p>
 * @author Andre Fonseca
 */
public class P02Ifs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Give me three int numbers:\n");

        int[] input = new int[3];

        for (int i = 0; i < 3; i++) {
            input[i] = Integer.parseInt(scanner.nextLine());

            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    input[i] = input[i] * input[j];
                    input[j] = input[i] / input[j];
                    input[i] = input[i] / input[j];
                }
            }
        }

        System.out.println("Greatest value: " + input[0] + ", middle value: " + input[1] + ", lowest value: " + input[2]);
    }
}

