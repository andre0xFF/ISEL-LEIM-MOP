package tps.tp3.pack1PercursosComHeranca;

/**
 * Classe que suporta um percurso simples
 *
 */
public class PercursoSimples extends Percurso {

	/**
	 * Nome do ponto de in�cio do percurso
	 */
	private String inicio;

	/**
	 * Nome do ponto de fim do percurso
	 */
	private String fim;

	/**
	 * Dist�ncia em metros do percurso
	 */
	private int distancia;

	/**
	 * Declive em metros, positivo, se fim mais alto que in�cio
	 */
	private int declive;

	/**
	 * Constructor. Deve validar o nome, inicio e fim com o m�todo de valida��o
	 * validarNomeDelocal. A dist�ncia tem de ser positiva (>0)
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param inicio
	 *            Local do in�cio do percurso
	 * @param fim
	 *            Local de im do percurso
	 * @param distancia
	 *            Distancia do percurso
	 * @param declive
	 *            Declive do percurso
	 */
	public PercursoSimples(String nome, String inicio, String fim, int distancia, int declive) {
		super(nome);

		if (!Percurso.validarNomeDeLocal(inicio)) {
			throw new IllegalArgumentException();
		}

		if (!Percurso.validarNomeDeLocal(fim)) {
			throw new IllegalArgumentException();
		}

		if (distancia < 0) {
			throw new IllegalArgumentException();
		}

		this.inicio = inicio;
		this.fim = fim;
		this.distancia = distancia;
		this.declive = declive;
	}

	public PercursoSimples(PercursoSimples p) {
		this(p.getNome(), p.get_beginning(), p.get_ending(), p.getDistancia(), p.getDeclive());
	}

	public PercursoSimples clone() {
		return new PercursoSimples(this);
	}

	/**
	 * Devolve o inicio do percurso
	 */
	public String get_beginning() {
		return this.inicio;
	}

	/**
	 * Devolve o fim do percurso
	 */
	public String get_ending() {
		return this.fim;
	}

	/**
	 * Devolve a distancia do percurso
	 */
	public int getDistancia() {
		return this.distancia;
	}

	/**
	 * Devolve o declive do percurso
	 */
	public int getDeclive() {
		return this.declive;
	}

	/**
	 * Equals, deve devolver true se o percurso � do tipo PercursoSimples, se
	 * tem o mesmo nome, o mesmo in�cio e o mesmo fim.
	 * 
	 * @param percurso
	 *            Percurso a comparar
	 */
	public boolean equals(Object percurso) {
		return (percurso instanceof PercursoSimples
				&& ((PercursoSimples) percurso).inicio.equals(this.inicio)
				&& ((PercursoSimples) percurso).fim.equals(this.fim)
				&& ((PercursoSimples) percurso).getNome().equals(super.getNome())
				);
	}

	/**
	 * Main, para realizar testes aos metodos
	 */
	public static void main(String[] args) {
		PercursoSimples ps1 = new PercursoSimples("A2", "Lisboa", "Faro",
				278_000, 0);
		ps1.print("ps1 -> ");

		PercursoSimples ps2 = new PercursoSimples("A1", "Lisboa", "Porto",
				317_000, 0);
		ps2.print("ps2 -> ");

		boolean ps1ps2 = ps1.equals(ps2);
		System.out.println("ps1.equals(ps2) -> " + ps1ps2);

		System.out.println("ps1 toString -> " + ps1);
	}

	@Override
	public String[] getLocalidades() {
		return new String[] { get_beginning(), get_ending() };
	}

	public String getDescricao() {
		return "simples";
	}

}
