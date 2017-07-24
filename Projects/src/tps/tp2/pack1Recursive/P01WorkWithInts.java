package tps.tp2.pack1Recursive;

public class P01WorkWithInts {

	public static void main(String[] args) {

		// ====================================================
		// test method removeZeros
		test_removeZeros(0); // result = 0
		test_removeZeros(2); // result = 2
		test_removeZeros(10); // result = 1
		test_removeZeros(101); // result = 11
		test_removeZeros(10050); // result = 15
		test_removeZeros(-30100); // result = -31
		System.out.println();

		// ====================================================
		// test method intToString
		test_intToString(0); // result = 0 (String)
		test_intToString(12); // result = 12 (String)
		test_intToString(123); // result = 123 (String)
		test_intToString(-19); // Erro: o numero recebido tem de ser positivo: -19
		System.out.println();
	}

	/**
	 * Recebe um inteiro e deve devolve-lo, mas com os digitos zero removidos.
	 * No caso de receber zero devera devolver esse mesmo valor
	 *
	 * @param n o numero a processar
	 * @return o numero sem digitos zero
	 */
	private static int removeZeros(int n) {
		// convert @param n to array of char with digits
		String number = String.valueOf(n);
		char[] digits = number.toCharArray();

		// new digits to be passed recursively
		char[] new_digits;

		// positive / negative factor of the @param n
		int factor = digits[0] == '-' ? -1 : 0;

		// turn @param n to the first digit
		// (except left side signal - factor)
		n = Integer.valueOf(String.valueOf(digits[0 + Math.abs(factor)]));

		// initialize new digits array with the proper size
		new_digits = new char[digits.length - 1 + factor];

		number = "";

		// copy char array without the first digit
		for (int i = 1 + Math.abs(factor); i < digits.length; i++) {
			new_digits[i - 1 + factor] = digits[i];
		}

		// will store the value returned by the recursion
		int u = 0;

		// if there are more than one digit than run again
		if (new_digits.length > 1) {
			u = removeZeros(Integer.valueOf(String.valueOf(new_digits)));
		}

		// the first digit removed from @param n is different from 0
		if (n != 0) {
			number = number.concat(String.valueOf(n));
		}

		// if something was returned from the recursion
		if (u != 0) {
			number = number.concat(String.valueOf(u));
		}

		// if number is empty than nothing was returned from the recursion
		if (number.equals("")) {
			return 0;
		}

		// parse back to int
		n = Integer.parseInt(number);

		// was the number negative? than turn it negative
		return factor == -1 ? -1 * n : n;
	}

	/**
	 * Auxiliary method that call removeZeros from a int
	 */
	private static void test_removeZeros(int n) {
		try {

			System.out.print("removeZeros (" + n + ") = ");
			int res = removeZeros(n);
			System.out.println(res);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	/**
	 * Este metodo recebe um inteiro positivo e devolve uma String com os
	 * seus digitos. Para tal trabalha digito a digito e utiliza
	 * String.valueOf(int) com inteiros so com um digito. Em caso de input
	 * invalido, deve lancar a excecao IllegalArgumentException com a indicacao
	 * clara do erro.
	 *
	 * @param n Inteiro positivo
	 * @return Numero inteiro positivo em forma de string
	 */
	private static String intToString(int n) {
		// @param n can't be negative
		if (n < 0) {
			throw new IllegalArgumentException("o numero recebido tem de ser positivo: " + n);
		}

		// convert @param n to string
		String number = String.valueOf(n);

		// get and remove the 1st digit
		String string = number.substring(0, 1);

		// return value of the recursion
		String u = "";

		// the number has multiple digits
		// go into recursion
		if (number.length() > 1) {
			n = Integer.parseInt(number.substring(1));
			u = intToString(n);
		}

		// if something was returned by the recursion concat it
		if (u != "") {
			string = string.concat(u);
		}

		return string;
	}

	/**
	 * intToString Auxiliary method
	 */
	private static void test_intToString(int n) {
		try {
			System.out.println("intToString (" + n + ")");
			String res = intToString(n);
			System.out.println("Result = " + res);

		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
