package tps.tp2.pack2Percursos;

import tps.tp2.Path;

/**
 * Classe que suporta um percurso simples
 *
 */
public class PercursoSimples implements Path {

	/**
	 * nome do percurso, deve conter so letras, digitos e espacos, deve comecar
	 * por uma letra e ter pelo menos mais uma letra ou digito
	 */
	private String nome;

	/**
	 * Nome do ponto de inicio do percurso
	 */
	private String inicio;

	/**
	 * Nome do ponto de fim do percurso
	 */
	private String fim;

	/**
	 * Distancia em metros do percurso
	 */
	private int distancia;

	/**
	 * Declive em metros, positivo, se fim mais alto que inicio
	 */
	private int declive;

	/**
	 * Deve validar o nome, inicio e fim com o metodo de validacao
	 * validarNomeDelocal. A distancia tem de ser positiva (maior que 0). Em
	 * caso de argumentos invalidos deve ser lancada a excepcao
	 * IllegalArgumentException com uma mensagem a indicar o erro ocorrido e o
	 * argumento invalido.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param inicio
	 *            Local do inicio do percurso
	 * @param fim
	 *            Local de im do percurso
	 * @param distancia
	 *            Distancia do percurso
	 * @param declive
	 *            Declive do percurso
	 */
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

	/**
	 * Construtor de copia, deve copiar os valores do percurso recebido para o
	 * novo percurso.
	 * 
	 * @param p
	 *            O percurso a copiar
	 */
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
	 * Deve criar uma copia do percurso recebido
	 * 
	 * @return O novo percurso identico ao corrente
	 */
	public PercursoSimples clone() {
		return new PercursoSimples(this);
	}

	/**
	 * Deve validar se contem so letras, digitos e espacos, deve comecar por uma
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

	/**
	 * ToString, deve devolver uma String tal como: "A2 de Lisboa para Faro, com
	 * 278000 metros e com 0 de declive"
	 * 
	 * @return A string que descreve o percurso
	 */
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
	 * Equals, deve devolver true se o percurso recebido tem o mesmo nome, o
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
	 * Print, deve imprimir na consola o prefixo seguido da informacao que se
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
