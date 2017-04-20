package tps.tp2.pack1Recursive;

/**
 * Classe para conter exerc�cios recursivos com inteiros
 */
public class P01WorkWithInts {

	/**
	 * Main, m�todo de arranque da execu��o
	 */
	public static void main(String[] args) {

		// ====================================================
		// test method removeZeros
		test_removeZeros(0); // result = 0
		test_removeZeros(2); // result = 2
		test_removeZeros(10); // result = 1
		test_removeZeros(101); // result = 11
		test_removeZeros(10050); // result = 15
		test_removeZeros(-30100); // result = -301
		System.out.println();

		// ====================================================
		// test method intToString
		test_intToString(0); // result = 0 (String)
		test_intToString(12); // result = 12 (String)
		test_intToString(123); // result = 123 (String)
		test_intToString(-19); // Erro: o n� recebido tem de ser positivo: -19
		System.out.println();
	}

	/**
	 * Recebe um inteiro e deve devolv�-lo, mas com os d�gitos zero removidos.
	 * No caso de receber zero dever� devolver esse mesmo valor
	 * 
	 * @param n
	 *            o n� a processar
	 * @return o n� sem digitos zero
	 */
	private static int removeZeros(int n) {
		// TODO falta fazer
		return 0;
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
	 * Este m�todo recebe um inteiro positivo e deve devolver uma String com os
	 * seus d�gitos. Para tal deve trabalhar d�gito a d�gito e utilizar
	 * String.valueOf(int) com inteiros s� com um d�gito. Em caso de input
	 * inv�lido, deve lan�ar a exce��o IllegalArgumentException com a indica��o
	 * clara do erro.
	 * 
	 * @param n
	 * @return
	 */
	private static String intToString(int n) {
		if (n < 0)
			throw new IllegalArgumentException("o n� recebido tem de ser positivo: " + n);

		// TODO falta fazer

		return null;

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
