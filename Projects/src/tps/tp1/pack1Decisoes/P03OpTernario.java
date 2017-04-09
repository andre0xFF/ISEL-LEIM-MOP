package tps.tp1.pack1Decisoes;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A classe P03OpTernario corresponde ao 3o exercicio do capitulo de Decisoes da serie de
 * exercicios. Um objecto desta classe ira gerar um elemento com base num outro elemento
 * introduzido pelo utilizador. Neste caso uma ligacao circular aerea entre as cidades
 * de Lisboa, Santander, Barcelona e Malaga.
 * <p>
 * @author Andre Fonseca
 */
public class P03OpTernario {

    public static void main(String[] args) {
        /**
         * Array de elementos que guarda todos os elementos possiveis.
         */
        String[] cities = new String[]{ "Lisboa", "Santander", "Barcelona", "Malaga" };

        Scanner scanner = new Scanner(System.in);

        System.out.println("City (Lisboa, Santander, Barcelona, Malaga)?");
        String input = scanner.nextLine();

        int j = Arrays.asList(cities).indexOf(input) + 1 % cities.length;

        if(j != -1) {
            System.out.println(input + " -> " + cities[(j == cities.length) ? 0 : j]);
        }
        else {
            System.out.println("City not found");
        }
    }
}
