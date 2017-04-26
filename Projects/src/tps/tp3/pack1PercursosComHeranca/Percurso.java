package tps.tp3.pack1PercursosComHeranca;

import tps.tp2.Path;

public abstract class Percurso implements Path {

	/**
	 * nome do percurso, deve conter s� letras, digitos e espa�os, deve come�ar
	 * por uma letra e ter pelo menos mais uma letra ou d�gito
	 */
	private String nome;

	/**
	 * Devolve o nome do percurso
	 */
	public String getNome() {
		return this.nome;
	}

	public abstract String get_beginning();
	public abstract String get_ending();
	public abstract String getDescricao();
	public abstract int getDistancia();
	public abstract int getDeclive();
	public abstract Object clone();
	public abstract String[] getLocalidades();

	public Percurso(String nome) {
		if (!Percurso.validarNomeDeLocal(nome)) {
			throw new IllegalArgumentException();
		}

		this.nome = nome;
	}

	/**
	 * Deve validar se cont�m s� letras, digitos e espa�os, deve come�ar por uma
	 * letra e ter pelo menos mais uma letra ou d�gito
	 * 
	 * @param local
	 *            Nome a validar
	 */
	protected static boolean validarNomeDeLocal(String local) {
		return Path.validate(local);
	}

	/**
	 * ToString, deve devolver uma String tal como:
	 * "A2 de Lisboa para Faro, com 278000 metros e com 0 de declive"
	 */
	public String toString() {
		return "percurso " + getDescricao() + " " + getNome() + " de "
				+ get_beginning() + " para " + get_ending() + ", com " + getDistancia()
				+ " metros e com " + getDeclive() + " de declive";
	}

	/**
	 * Print, deve imprimir na consola o prefixo seguido da informa��o que se
	 * obt�m com o toString
	 * 
	 * @param prefix
	 *            Prefixo a colocar antes da informa��o do toString
	 */
	public void print(String prefix) {
		System.out.println(prefix + this.toString());
	}

}