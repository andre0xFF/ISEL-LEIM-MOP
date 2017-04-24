package tps.tp2.pack1Recursive;

public class P02WorkWithStrings {

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
	 * Este metodo recebe duas Stings s1 e s2 e procede a' sua comparacao
	 * lexicografica e em termos de tamanho.
	 * 
	 * @param s1
	 *            string a comparar
	 * @param s2
	 *            string a comparar
	 * @return Resultado da comparacao lexicografica
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
		// Get the max length value
		int range = --len1 < --len2 ? len2: len1;

        // Evaluate the LSB of the string
		int count = (int) s1.charAt(len1) - (int) s2.charAt(len2);
        // Remove the LSB of both strings and send to recursion
		count += compareStrings(s1.substring(0, len1), s2.substring(0, len2));

		// Add the level of max length value
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
