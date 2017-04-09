package tps.tp1.pack2Ciclos;

import java.util.Scanner;

/**
 * A classe P01Ciclos corresponde ao 3o exercicio do capitulo de Ciclos da serie de
 * exercicios. Um objecto desta classe mostra todos os numeros multiplos de um
 * determinado numero introduzido pelo utilizador ate ao proprio numero.
 * <p>
 * @author Andre Fonseca
 */
public class P01Ciclos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number;

        do {
            System.out.println("Number (5-50)?");
            number = Integer.parseInt(scanner.nextLine());

            if(number < 5 || number > 50) {
                System.out.println("Invalid number: " + number);
            }
        } while(number < 5 || number > 50);

        System.out.printf("Multiple numbers of 5 between 5 and " + number + ":");

        for (int i = 5; i <= number; i += 5) {
            System.out.printf(" " + String.valueOf(i));
        }
    }
}
