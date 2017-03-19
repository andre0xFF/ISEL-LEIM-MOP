package tps.tp1.pack1Decisoes;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A classe P03OpTernario corresponde ao 3o exercicio do capitulo de Decisoes da serie de
 * exercicios. A classe ira gerar um elemento com base num elemento introduzido pelo
 * utilizador.<p>
 * @author Andre Fonseca
 */
public class P03OpTernario {
    /**
     * Ao ser fornecido o nome de um elemento pelo utilizador e' obtido o seu indice correspondente a
     * sua posicao no array de elementos e somando-lhe mais 1 ao qual e' obtido o resto da divisao
     * com o numero total de elementos.<p>
     * De maneira a criar um incremento circular o indice do elemento gerado e' reposto a 0 sempre que
     * este corresponda ao numero total de elementos, situacao realizada por uma
     * <i>conditional expression</i>.
     */
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
