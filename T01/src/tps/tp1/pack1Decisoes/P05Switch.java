package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P04OpTernario corresponde ao 3o exercicio do capitulo de Decisoes da serie de
 * exercicios. Semelhante ao exercicio anterior, sera gerado um elemento e destruido um
 * elemento com base num outro elemento introduzido pelo utilizador.<p>
 * @author Andre Fonseca
 */
public class P05Switch {

	protected static String[] ELEMENTS = { "Fire", "Earth", "Metal", "Water", "Wood" };
	
	/**
	 * Utilizando a mesma logica de incremento circular, o elemento a ser destruido e'
	 * determinado avancando duas posicoes no array de elementos.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Element? \n");
		String input = scanner.nextLine();
		
		int j = getElementIndex(input, ELEMENTS) + 1 % ELEMENTS.length;	
		
		if(j != -1) {	
			j = (j == ELEMENTS.length) ? 0 : j;
			
			System.out.println(input + " generates " + ELEMENTS[j]);
			
			int i = j + 1 % ELEMENTS.length;
			i = (i == ELEMENTS.length) ? 0 : i;
			
			System.out.println(input + " destroys " + ELEMENTS[i]);
		}
		else {
			System.out.println("Element not found");
		}
	}
	
	public static int getElementIndex(String element, String[] elements) {
		for(int i = 0; i < elements.length; i++) {
			if(element.compareToIgnoreCase(elements[i]) == 0) {
				return i;
			}
		}
		
		return -1;
	}
}
