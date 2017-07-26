package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

/**
 * A classe P04BaralhadorDeNomes corresponde ao 4o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe gera nomes aleatorios, tantos quantos introduzidos, cada nome com
 * o respectivo primeiro, ultimo recorrendo aos nomes previamente introduzidos
 * pelo utilizador sem mais repeticoes do que o numero de vezes ja introduzidos pelo utilizador.
 * <p>
 *
 * @author Andre Fonseca
 */
public class P04BaralhadorDeNomes {

    /**
     * O programa inicialmente pede ao utilizador nomes, com um minimo de 5 e maximo de 10 nomes
     * ou ate que seja introduzido a palavra "fim". Consoante a introducao de cada nome este
     * sao verificados para os requisitos. Cada nome deve de ser composto por pelo menos 3 palavras,
     * cada palavra deve de ter no minimo 4 caracteres e o nome devera de ter um maximo de 120
     * caracteres. Caso algum nome nao seja valido, o utilizador sera informado e devera de
     * introduzir novamente outro nome.<p>
     * Quando forem introduzidos os nomes sera' entao gerado num novo array de nomes, tantos
     * quantos introduzidos pelo utilizador, que nao contenham repeticoes de nomes, tantos
     * quantos presentes em todos os nomes.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[0];
        String input = new String();

        System.out.println("Names:");

        while(names.length < 10) {
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("fim") || input.equalsIgnoreCase("end")) {
                if(names.length >= 5) {
                    break;
                }
                else {
                    System.out.printf("Not enough names!\n");
                    continue;
                }
            }

            if(checkNameLength(input) && splitString(input, ' ').length > 1 && checkMinCharInName(input)) {
                names = pushToArray(names, input);
            }
            else {
                System.out.printf("Invalid name!\n");
            }
        }

//        names = new String[] {
//            "Carla Sofia Santos",
//            "Andre Fonseca",
//            "Raul Raulini",
//            "Trica Strinka",
//            "Orto Venus",
//            "Yigor Rosmanov",
//            "Rostafini Rastafan",
//            "Ilia iliku",
//            "Igort trave",
//            "Vaimais umquejachega",
//        };

        System.out.printf("[X] Entered names:\n");

        for(int i = 0; i < names.length; i++) {
            System.out.printf("[%d] %s\n", i, names[i]);
        }

        System.out.printf("\n[X] Generated names:\n");

        String[] shuffledNames = shuffleNames(names);

        for(int i = 0; i < shuffledNames.length; i++) {
            System.out.printf("[%d] %s\n", i, shuffledNames[i]);
        }
    }

    private static String[] splitString(String string, char chr) {
        String[] stringArray = new String[0];

        int first = 0;
        int last = idxOf(chr, string, 0);

        while(last != -1) {
            stringArray = pushToArray(stringArray, string.substring(first, last));
            first = idxOf(chr, string, last);
            last = idxOf(chr, string, ++first);
        }

        stringArray = pushToArray(stringArray, string.substring(first, string.length()));

        return stringArray;
    }

    private static int idxOf(char character, String string, int start_idx) {
        for (int i = start_idx; i < string.length(); i++) {
            if (string.charAt(i) == character) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Constroi uma string com os valores de um determinado array.
     *
     * @param str
     * 				Array de strings
     * @return
     * 				String com a representacao dos numeros no array
     */
    private static String arrayToString(String[] str) {
        String newStr = new String();

        for(int i = 0; i < str.length - 1; i++) {
            newStr += str[i] + " ";
        }

        newStr += str[str.length - 1];

        return newStr;
    }

    /**
     * Adiciona uma certa string na ultima posicao de um array de strings.
     * O novo array e' construido com uma dimensao superior ao recebido
     * de forma a alocar a nova string na ultima posicao.
     *
     * @param names
     * 				Array de strings
     * @param name
     * 				String a adicionar
     * @return
     * 				Novo array de strings com a string adicionada
     */
    private static String[] pushToArray(String[] names, String name) {
        String[] newNames = new String[names.length + 1];

        for(int i = 0; i < names.length; i++) {
            newNames[i] = names[i];
        }

        newNames[newNames.length - 1] = name;

        return newNames;
    }

    /**
     * Divide varios nomes por um array de strings com os respectivos
     * primeiros nomes.
     *
     * @param names
     * 				Array de nomes
     * @return
     * 				Array com todos os primeiros nomes de um determinado array
     */
    private static String[] getAllFirstNames(String[] names) {
        String[] newNames = new String[names.length];

        for(int i = 0; i < names.length; i++) {
            newNames[i] = splitString(names[i], ' ')[0];
        }

        return newNames;
    }

    /**
     * Divide varios nomes por um array de strings com os respectivos
     * ultimos nomes.
     *
     * @param names
     * 				Array de nomes
     * @return
     * 				Array com todos os ultimos nomes de um determinado array
     */
    private static String[] getAllLastNames(String[] names) {
        String[] newNames = new String[names.length];

        for(int i = 0; i < names.length; i++) {
            String[] name = splitString(names[i], ' ');
            newNames[i] = name[name.length - 1];
        }

        return newNames;
    }

    /**
     * Gera um array de nomes com base num array de nomes fornecidos sem que haja repeticoes
     * de primeiro, ultimo.
     * O metodo inicia-se com um ciclo que ira gerar o mesmo numero de nomes quanto os
     * recebidos em que sao verificadas as repeticoes no novo array e no array recebido,
     * caso haja alguma repeticao e' entao gerado um novo nome.
     *
     * @param names
     * 				Array de nomes
     * @return
     * 				Array de nomes gerados aleatoriamente sem repeticoes de primeiro, ultimo
     */
    private static String[] shuffleNames(String[] names) {
        String[] newNames = new String[0];

        for(int i = 0; i < names.length; i++) {
            String[] newName = new String[2];
            String[] randomName;

            do {
                randomName = splitString(names[randomNumber(0, names.length - 1)], ' ');

            } while(nameAlreadyExistsInArray(getAllFirstNames(newNames), getAllFirstNames(names), randomName[0]));

            newName[0] = randomName[0];

            do {
                randomName = splitString(names[randomNumber(0, names.length - 1)], ' ');

            } while(nameAlreadyExistsInArray(getAllLastNames(newNames), getAllLastNames(names), randomName[randomName.length - 1]));

            newName[1] = randomName[randomName.length - 1];

            newNames = pushToArray(newNames, arrayToString(newName));
        }

        return newNames;
    }

    /**
     * Verifica se um certo nome se repete mais do que as vezes introduzidas num array em
     * relacao a outro array.
     *
     * @param newAllNames
     * 				Array a ser comparado pelo numero maximo de repeticoes
     * @param allNames
     * 				Array com o numero maximo de repeticoes do nome
     * @param name
     * 				Nome a ser comparado
     * @return
     * 				True caso o nome verificado tenha mais repeticoes do que o permitido
     */
    private static boolean nameAlreadyExistsInArray(String[] newAllNames, String[] allNames, String name) {
        int allNamesCounter = 0;
        int newAllNamesCounter = 0;


        for (int i = 0; i < allNames.length; i++) {
            String[] nameSplit = splitString(allNames[i], ' ');

            for (int j = 0; j < nameSplit.length; j++) {
                if(nameSplit[j].compareTo(name) == 0) {
                    ++allNamesCounter;
                }
            }
        }

        for (int i = 0; i < newAllNames.length; i++) {
            String[] nameSplit = splitString(newAllNames[i], ' ');

            for (int j = 0; j < nameSplit.length; j++) {
                if(nameSplit[j].compareTo(name) == 0) {
                    ++newAllNamesCounter;
                }
            }
        }

        return allNamesCounter == newAllNamesCounter;
    }

    /**
     * Verifica se um determinado nome contem pelo menos 4 caracteres.
     *
     * @param name
     * 				Nome a ser verificado
     * @return
     * 				True se o nome tiver pelo menos 4 caracteres.
     */
    private static boolean checkMinCharInName(String name) {
        String[] names = splitString(name, ' ');

        for(int i = 0; i < names.length; i++) {
            if(names[i].length() < 4) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gera um numero aleatorio com base num intervalo de numero
     * minimo e maximo.
     *
     * @param min
     * 				Numero minimo que pode ser gerado.
     * @param max
     * 				Numero maximo que pode ser gerado.
     * @return
     * 				Numero aleatorio.
     */
    public static int randomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * Verifica se um determinado nome e' composto por mais de 120 caracteres.
     *
     * @param name
     * 				Nome a ser verificado
     * @return
     * 				True se o nome for comporto por mais de 120 caracteres
     */
    public static boolean checkNameLength(String name) {
        return name.length() < 120;
    }

}
