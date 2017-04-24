package tps.tp2.pack1Recursive;

/**
 * Classe para conter exerc�cios recursivos com inteiro
 */
public class P02WorkWithStrings {

	/**
	 * Main, m�todo de arranque da execu��o
	 */
	public static void main(String[] args) {

		// ====================================================
		// test method compareStrings
		test_compareStrings(null, null); // result = 0
		test_compareStrings(null, ""); // result = -1
		test_compareStrings("", null); // result = 1
		test_compareStrings("a", ""); // result = 1
		test_compareStrings("", "a"); // result = -1
		test_compareStrings("a", "a"); // result = 0
		test_compareStrings("b", "a"); // result = 1
		test_compareStrings("a", "b"); // result = -1
		test_compareStrings("aa", "a"); // result = 2
		test_compareStrings("a", "aa"); // result = -2
		test_compareStrings("aa", "aa"); // result = 0
		test_compareStrings("ab", "aa"); // result = 2
		test_compareStrings("ab", "ab"); // result = 0
		test_compareStrings("abc", "abc"); // result = 0
		test_compareStrings("abc", "abd"); // result = -3
		System.out.println();
	}

	/**
	 * Este m�todo recebe duas Stings s1 e s2 e procede � sua compara��o
	 * lexicogr�fica e em termos de tamanho. Devolve >0 se s2 � maior
	 * lexicogr�ficamente ou em termos de tamanho que s2, ou <0 se menor, ou 0
	 * se iguais. Se diferentes deve devolver o �ndice +1 do caractere que faz a
	 * diferen�a. Ex. s1="Bom", s2="Dia", deve devolver 1; s1="Boa", s2="Bom",
	 * deve devolver -3; s1="Bom", s2="Bo", deve devolver 3. Uma String a null �
	 * considerado menor que uma string n�o null.
	 * 
	 * @param s1
	 *            string a comparar
	 * @param s2
	 *            string a comparar
	 * @return o resultado da compara��o
	 */
	private static int compareStrings(String s1, String s2) {
		if (s1 == null && s2 == null) {
			return 0;
		}
		if (s1 == null && s2 != null) {
			return -1;
		}
		if (s1 != null && s2 == null) {
			return 1;
		}
		if (s1.equals("") && s2.equals("")) {
			return 0;
		}
		if (s1.equals("")) {
			return -1;
		}
		if (s2.equals("")) {
			return 1;
		}

		int len1 = s1.length();
		int len2 = s2.length();
		int range = --len1 < --len2 ? len2: len1;

		int count = (int) s1.charAt(len1) - (int) s2.charAt(len2);

		count += compareStrings(s1.substring(0, len1), s2.substring(0, len2));

		if (count > 0) {
		    return count + range;
        }
        if (count < 0) {
		    return count - range;
        }

        return count;
	}

	/**
	 * Auxiliary method that call compareStrings with two strings
	 */
	private static void test_compareStrings(String s1, String s2) {
		try {

			System.out.print("compareStrings (" + s1 + ", " + s2 + ") = ");
			int res = compareStrings(s1, s2);
			System.out.println(res);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
