package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P04OpTernario corresponde ao 3o exercicio do capitulo de Decisoes da serie de
 * exercicios. A classe ira gerar um elemento com base num elemento introduzido pelo 
 * utilizador.<p>
 * @author Andre Fonseca
 */
public class P04OpTernario {

	/**
	 * Array de elementos que guarda todos os elementos possiveis.
	 */
	protected static String[] ELEMENTS = { "Fire", "Earth", "Metal", "Water", "Wood" };
	
	/**
	 * Ao ser fornecido o nome de um elemento pelo utilizador e' obtido o seu indice correspondente a
	 * sua posicao no array de elementos e somando-lhe mais 1 ao qual e' obtido o resto da divisao
	 * com o numero total de elementos.<p>
	 * De maneira a criar um incremento circular o indice do elemento gerado e' reposto a 0 sempre que
	 * este corresponda ao numero total de elementos, situacao realizada por uma 
	 * <i>conditional expression</i>.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Element? \n");
		String input = scanner.nextLine();
		
		int j = getElementIndex(input, ELEMENTS) + 1 % ELEMENTS.length;	
		
		if(j != -1) {
			System.out.println(input + " generates " + ELEMENTS[(j == ELEMENTS.length) ? 0 : j]);
		}
		else {
			System.out.println("Element not found");
		}
	}
	
	/**
	 * Verifica todos os elementos do array de elementos e compara-os um a um ate' ser encontrado o
	 * elemento pretendido.
	 * @param element Elemento a ser procurado
	 * @param elements Array de elementos
	 * @return O indice do elemento pretendido
	 */
	public static int getElementIndex(String element, String[] elements) {
		for(int i = 0; i < elements.length; i++) {
			if(element.compareToIgnoreCase(elements[i]) == 0) {
				return i;
			}
		}
		
		return -1;
	}

}
