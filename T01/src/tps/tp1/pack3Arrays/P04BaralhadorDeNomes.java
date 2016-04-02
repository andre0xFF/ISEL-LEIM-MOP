package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

/**
 * A classe P04BaralhadorDeNomes corresponde ao 4o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe gera nomes aleatorios, tantos quantos introduzidos, cada nome com
 * o respectivo primeiro, ultimo e nomes do meio recorrendo aos nomes previamente introduzidos
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

		while(names.length < 10) {
			input = scanner.nextLine();
			
			if(input.compareTo("fim") == 0) {
				if(names.length >= 5) {
					break;
				}
				else {
					System.out.printf("Not enough names!\n");
					continue;
				}
			}
					
			if(checkNameLength(input) && splitString(input, ' ').length > 3 && checkMinCharInName(input)) {
				names = pushToArray(names, input);
			} 
			else {
				System.out.printf("Invalid name!\n");
			}
		}
		
		System.out.printf("[x] Entered names:\n");
		
		for(int i = 0; i < names.length; i++) {
			System.out.printf("[%d] %s\n", i, names[i]);
			String[] nameArray = splitString(names[i], ' ');
		}
		
		System.out.printf("\n[x] Generated names:\n");
	
		String[] shuffledNames = shuffleNames(names);
		
		for(int i = 0; i < shuffledNames.length; i++) {
			System.out.printf("[%d] %s\n", i, shuffledNames[i]);
		}
	}
	
	/**
	 * Divide um uma string num array de strings dividido por um caracter.
	 * A divisao da string e' feita recorrendo ao metodo indexOf() que
	 * determina o indice de um caracter numa determinada string.
	 * Atraves deste indice a string e' divida por um ciclo que so
	 * termina quando nao existirem mais nenhum caracter de divisao.
	 * 
	 * @param string
	 * 				String a ser dividida
	 * @param chr
	 * 				Caracter pelo qual a string sera dividida
	 * @return
	 * 				Array de strings com a string dividida
	 */
	public static String[] splitString(String string, char chr) {
		String[] stringArray = new String[0];
		
		int first = 0;
		int last = string.indexOf(chr);
		
		while(last != -1) {
			stringArray = pushToArray(stringArray, string.substring(first, last));
			first = string.indexOf(chr, last);
			last = string.indexOf(chr, ++first);
		}
		
		stringArray = pushToArray(stringArray, string.substring(first, string.length()));
		
		return stringArray;
	}
	
	/**
	 * Constroi uma string com os valores de um determinado array.
	 * 
	 * @param str
	 * 				Array de strings
	 * @return
	 * 				String com a representacao dos numeros no array
	 */
	public static String arrayToString(String[] str) {
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
	public static String[] pushToArray(String[] names, String name) {
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
	public static String[] getAllFirstNames(String[] names) {
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
	public static String[] getAllLastNames(String[] names) {
		String[] newNames = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String[] name = splitString(names[i], ' ');
			newNames[i] = name[name.length - 1];
		}
		
		return newNames;
	}
	
	/**
	 * Divide varios nomes por um array de strings com os respectivos
	 * nomes dos meios.
	 * 
	 * @param names
	 * 				Array de nomes
	 * @return
	 * 				Array com todos os nomes dos meios de um determinado array
	 */
	public static String[] getAllMiddleNames(String[] names) {
		String[] newNames = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String[] name = splitString(names[i], ' ');
					
			newNames[i] = names[i].substring(
					names[i].indexOf(name[0]) + name[0].length() + 1,
					names[i].indexOf(name[name.length - 1]) - 1
					);
		}
		
		return newNames;
	}
	
	/**
	 * Gera um array de nomes com base num array de nomes fornecidos sem que haja repeticoes
	 * de primeiro, ultimo e nomes do meio.
	 * O metodo inicia-se com um ciclo que ira gerar o mesmo numero de nomes quanto os
	 * recebidos em que sao verificadas as repeticoes no novo array e no array recebido,
	 * caso haja alguma repeticao e' entao gerado um novo nome.
	 * 
	 * @param names
	 * 				Array de nomes
	 * @return
	 * 				Array de nomes gerados aleatoriamente sem repeticoes de primeiro, ultimo
	 * 				e nomes do meio
	 */
	public static String[] shuffleNames(String[] names) {
		String[] newNames = new String[0];
		
		for(int i = 0; i < names.length; i++) {
			String[] newName = new String[3];
			String[] randomName;
			
			do {
				randomName = splitString(names[randomNumber(0, names.length - 1)], ' ');
				
			} while(nameAlreadyExistsInArray(getAllFirstNames(newNames), getAllFirstNames(names), randomName[0]));
			
			newName[0] = randomName[0];
			
			int middleIdx;
			
			do {
				randomName = splitString(names[randomNumber(0, names.length - 1)], ' ');
				middleIdx = randomNumber(1, randomName.length - 2);

			} while(nameAlreadyExistsInArray(getAllMiddleNames(newNames), getAllMiddleNames(names), randomName[middleIdx]));
			
			newName[1] = randomName[middleIdx];
			
			do {
				randomName = splitString(names[randomNumber(0, names.length - 1)], ' ');

			} while(nameAlreadyExistsInArray(getAllLastNames(newNames), getAllLastNames(names), randomName[randomName.length - 1]));
			
			newName[2] = randomName[randomName.length - 1];
			
			newNames = pushToArray(newNames, arrayToString(newName));
		}
		
		return newNames;
	}

	/**
	 * Verifica se um certo nome repete-se mais do que as vezes introduzidas num array em
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
	protected static boolean nameAlreadyExistsInArray(String[] newAllNames, String[] allNames, String name) {		
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
	public static boolean checkMinCharInName(String name) {
		String[] names = splitString(name, ' ');
		
		for(int i = 0; i < names.length; i++) {
			if(names[i].length() < 4) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Verifica se um determinado nome e' composto por mais de 3 nomes.
	 * 
	 * @param name
	 * 				Nome a ser verificado
	 * @return
	 * 				True se o nome for composto por mais de 3 nomes
	 */
	public static boolean checkThreeNames(String name) {		
		return name.split(" ").length >= 3;
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

}
