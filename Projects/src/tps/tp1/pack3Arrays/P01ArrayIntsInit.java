package tps.tp1.pack3Arrays;

import java.util.Arrays;

/**
 * A classe P01ArrayIntsInit corresponde ao 1o exercicio do capitulo de Arrays da serie de
 * exercicios. Um objecto desta classe mostra um conjunto de caracteres '#' com base
 * nos dois limites introduzidos pelo utilizador.
 * <p>
 * @author Andre Fonseca
 */
public class P01ArrayIntsInit {

    public static void main(String[] agrs) {
        int[] numbers = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 9 };

        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Max even number: " + max_even_number(numbers));
        System.out.println("Numbers modified: " + Arrays.toString(modify_array(numbers)));
    }

    /**
     * Determina o numero par mais alto presente num array.
     *
     * @param numbers
     * 					Array de numeros
     * @return
     * 					Maior numero par presente no array
     */
    public static int max_even_number(int[] numbers) {
        int max = 0;

        for(int i = 0; i < numbers.length; i++) {
            if((numbers[i] % 2 != 0) && (numbers[i]) > max) {
                max = numbers[i];
            }
        }

        return max;
    }

    /**
     * Modifica um array de inteiros no qual a todos os numeros multiplos de 3
     * e'-lhes sumado o proximo numero par caso exista.
     * @param numbers
     *                  Array de numeros inteiros
     * @return
     *                  Array de numeros inteiros onde cada multiplo de 3
     *                  foi-lhe somado o proximo numero par
     */
    public static int[] modify_array(int[] numbers) {

        int[] aux = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            aux[i] = numbers[i];

            if (numbers[i] % 3 != 0) {
                continue;
            }

            int n = aux[i] + 1 % 2;

            if (n % 2 != 0) {
                n++;
            }

            aux[i] += n;
        }

        return aux;
    }
}
