package tps.tp1.pack3Arrays;

public class P01ArrayIntsInit {

    public static void main(String[] agrs) {
        int[] numbers = new int[] { 0, 1, 2, 3, 5, 6, 7, 8, 9 };

        System.out.println("Numbers: " + print_array(numbers));
        System.out.println("Max even number: " + max_even_number(numbers));
        System.out.println("Numbers modified: " + print_array(modify_array(numbers)));
    }

    /**
     * Constroi uma string com os valores de um array de numeros inteiros.
     *
     * @param array
     * 				Array de numeros
     * @return
     * 				String com a representacao dos numeros no array
     */
    public static String print_array(int[] array) {
        String msg = "[";

        for(int i = 0; i < array.length; i++) {
            msg += " " + array[i] + " ";
        }

        return msg + "]";
    }

    /**
     * Determina o numero impar mais alto presente num array.
     *
     * @param numbers
     * 					Array de numeros
     * @return
     * 					Numero mais alto presente num array
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
