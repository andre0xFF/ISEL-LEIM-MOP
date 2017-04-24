package tps.tp2.pack2Percursos;

import tps.tp2.Path;

/**
 * Classe que suporta um percurso simples
 */
public class PercursoSimples implements Path {

	private String nome;

	private String inicio;

	private String fim;

	private int distancia;

	private int declive;

	public PercursoSimples(String nome, String inicio, String fim, int distancia, int declive) {
		if (!PercursoSimples.validarNomeDeLocal(nome)) {
			throw new IllegalArgumentException(Path.INVALID_LOCATION);
		}

		if (!PercursoSimples.validarNomeDeLocal(inicio)) {
			throw new IllegalArgumentException(Path.INVALID_PATH_NAME);
		}

		if (!PercursoSimples.validarNomeDeLocal(fim)) {
			throw new IllegalArgumentException(Path.INVALID_PATH_NAME);
		}

		if (distancia < 1) {
			throw new IllegalArgumentException(Path.INVALID_DISTANCE);
		}

		this.nome = nome;
		this.inicio = inicio;
		this.fim = fim;
		this.distancia = distancia;
		this.declive = declive;
	}

	public PercursoSimples(PercursoSimples p) {
		this(
				p.getNome(),
				p.get_beginning(),
				p.get_ending(),
				p.getDistancia(),
				p.getDeclive()
		);
	}

	/**
	 * Criauma copia profunda do percurso recebido
	 * 
	 * @return O novo percurso identico ao corrente
	 */
	public PercursoSimples clone() {
		return new PercursoSimples(this);
	}

	/**
	 * Valida se contem so letras, digitos e espacos, deve comecar por uma
	 * letra e ter pelo menos mais uma letra ou digito
	 * 
	 * @param nome
	 *            Nome a validar
	 * @return True se o nome for valido
	 */
	private static boolean validarNomeDeLocal(String nome) {
		return Path.validate(nome);
	}

	/**
	 * Devolve o nome do percurso
	 * 
	 * @return O nome do percurso
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Devolve o local de inicio do percurso
	 * 
	 * @return O local de inicio do percurso
	 */
	@Override
	public String get_beginning() {
		return this.inicio;
	}

	/**
	 * Devolve o local de fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	@Override
	public String get_ending() {
		return this.fim;
	}

	/**
	 * Devolve a distancia do percurso
	 * 
	 * @return a distancia do percurso
	 */
	public int getDistancia() {
		return this.distancia;
	}

	/**
	 * Devolve o declive do percurso
	 * 
	 * @return O declive do percurso
	 */
	public int getDeclive() {
		return this.declive;
	}

	public String toString() {
		return String.format(
				"%s de %s para %s, com %d metros e com %d de declive",
				this.nome,
				this.inicio,
				this.fim,
				this.distancia,
				this.declive
		);
	}

	/**
	 * Devolve true se o percurso recebido tem o mesmo nome, o
	 * mesmo inicio e o mesmo fim.
	 * 
	 * @param percurso
	 *            Percurso a comparar
	 * @return True se o percurso corrente for igual, por nome, inicio e fim com
	 *         o percurso recebido
	 */
	public boolean equals(PercursoSimples percurso) {
		return (
				percurso.getNome().equalsIgnoreCase(this.nome)
				&& percurso.get_beginning().equalsIgnoreCase(this.inicio)
				&& percurso.get_ending().equalsIgnoreCase(this.fim)
		);
	}

	/**
	 * Print, imprime na consola o prefixo seguido da informacao que se
	 * obtem com o toString
	 * 
	 * @param prefix
	 *            Prefixo a colocar antes da informacao do toString
	 */
	public void print(String prefix) {
		System.out.printf("%s %s\n", prefix, this.toString());
	}

	/**
	 * Main, para realizar testes aos metodos
	 * 
	 * @param args
	 *            Argumentos do main
	 * 
	 */
	public static void main(String[] args) {
		PercursoSimples ps1 = new PercursoSimples("A2", "Lisboa", "Faro", 278_000, 0);
		ps1.print("ps1 -> ");

		PercursoSimples ps2 = new PercursoSimples("A1", "Lisboa", "Porto", 317_000, 0);
		ps2.print("ps2 -> ");

		boolean ps1ps2 = ps1.equals(ps2);
		System.out.println("ps1.equals(ps2) -> " + ps1ps2);

		System.out.println("ps1 toString -> " + ps1);
	}
}
