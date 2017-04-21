package tps.tp2.pack2Percursos;

import java.util.Arrays;

/**
 * Classe que suporta um percurso composto por varios percursos simples
 */
public class PercursoComposto {

	private final int maxPercursos;
	/**
	 * Nome do percurso, deve respeitar a regra de validacao dos nomes em
	 * percurso simples
	 */
	private String nome;

	/**
	 * Array com os percursos simples. Os elementos devem ser colocados nos
	 * indices menores. Tem de ter pelo menos um percurso. Nao admite
	 * localidades repetidas. Os percursos teem de estar em sequencia, ou seja,
	 * onde termina o percurso de indice 0 tem de ser onde se inicia o percurso
	 * de indice 1 e assim sucessivamente ...
	 */
	private PercursoSimples[] percursos;

	/**
	 * Numero de percursos
	 */
	private int nPercursos;

	/**
	 * Constructor que recebe apenas um percurso, alem do nome e do no maximo de
	 * percursos. Este constructor deve chamar o constructor que a ele se segue.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param percurso
	 *            Percurso a ser guardado
	 * @param maxPercursos
	 *            Numero maximo de percursos suportado
	 */
	public PercursoComposto(String nome, PercursoSimples percurso, int maxPercursos) {
		this(nome, new PercursoSimples[] { percurso }, maxPercursos);
	}

	/**
	 * Constructor que recebe o nome, um array de percursos e o maximo de
	 * percursos a suportar. Em caso de argumentos invalidos deve ser lancada a
	 * excepcao IllegalArgumentException com uma mensagem a indicar o erro
	 * ocorrido e o argumento invalido.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param percursos
	 *            Percursos a serem guardados. O array nao pode conter nulls,
	 *            tem de conter pelo menos um percurso e os seus percursos devem
	 *            estar em sequencia.
	 * @param maxPercursos
	 *            Numero maximo de percursos suportado
	 */
	public PercursoComposto(String nome, PercursoSimples[] percursos, int maxPercursos) {
		this.nome = nome;
		this.percursos = percursos;
		this.maxPercursos = maxPercursos;

		this.validate();
	}

	protected boolean validate() throws IllegalArgumentException {
		final String INVALID_SEQUENCE = "Beginning and end sequence does not match";
		final String INVALID_QUANTITY = "Invalid paths quantity";
		final String REPEATED_LOCATIONS = "There are paths with repeated locations";

		PercursoSimples[] paths = this.percursos;

		if (paths.length == 0 || paths.length > this.maxPercursos) {
			throw new IllegalArgumentException(INVALID_QUANTITY);
		}

		String[] beginnings = new String[paths.length];
		String[] endings = new String[paths.length];

		for (int i = 0; i < paths.length; i++) {

			if (i < paths.length - 1 && !paths[i].validate(paths[i + 1])) {
				throw new IllegalArgumentException(INVALID_SEQUENCE);
			}

			beginnings[i] = paths[i].getInicio();
			endings[i] = paths[i].getFim();

			for (int j = i + 1; j < paths.length; j++) {
				if (beginnings[i].equalsIgnoreCase(paths[j].getInicio())
					|| endings[i].equalsIgnoreCase(paths[j].getFim())
						) {
					throw new IllegalArgumentException(REPEATED_LOCATIONS);

				}
			}
		}

		return true;
	}

	/**
	 * Copy constructor, deve criar uma copia do percurso recebido. Essa copia
	 * deve ser uma copia profunda.
	 * 
	 * @param pc
	 *            Percurso a copiar
	 */
	public PercursoComposto(PercursoComposto pc) {
		this(pc.getNome(), pc.getPercursos(), pc.getPercursos().length);
	}

	/**
	 * Deve criar uma copia profunda do percurso corrente
	 */
	public PercursoComposto clone() {
		return new PercursoComposto(this);
	}

	/**
	 * Deve adicionar o percurso no final, desde que este esteja em sequencia e
	 * haja espaco
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoNoFinal(PercursoSimples percurso) {
		this.insert(-1, percurso);

		try {
			this.validate();
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	private boolean insert(int idx, PercursoSimples path) {
		int n = this.percursos.length;

		if (n + 1 > this.maxPercursos) {
			return false;
		}

		if (idx == -1) {
			idx = n;
		}

		PercursoSimples[] paths = new PercursoSimples[++n];

		for (int i = 0, j = 0; i < n; i++) {
			if (i == idx) {
				paths[i] = path;
				continue;
			}

			paths[i] = this.percursos[j++];
		}

		this.percursos = paths;

		return true;
	}

	/**
	 * Deve adicionar o percurso no inicio, desde que este esteja em sequencia e
	 * haja espaco
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoNoInicio(PercursoSimples percurso) {
		this.insert(0, percurso);

		try {
			this.validate();
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	private PercursoSimples[] remove(boolean factor, String local) {
		int n = -1;

		for (int i = 0; i < this.percursos.length; i++) {
			if (this.percursos[i].getInicio().equalsIgnoreCase(local)) {
				n = i;
				break;
			}
		}

		if (n == -1) {
			return null;
		}

		PercursoSimples[] new_paths = new PercursoSimples[n];
		PercursoSimples[] old_paths = new PercursoSimples[n];

		for (int i = 0; i < n; i++) {
			new_paths[i] = this.percursos[i];
		}

		for (int i = n; i < this.percursos.length; i++) {
			old_paths[i - n] = this.percursos[i];
		}

		if (factor) {
			this.percursos = new_paths;
			return old_paths;
		}
		else {
			this.percursos = old_paths;
			return new_paths;
		}

	}

	/**
	 * Deve remover e devolver todos os percursos desde o ponto da localidade
	 * recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoFimDesde(c), deve resultar no percurso com a-b/b-c e
	 * deve devolver c-d/d-e.
	 * 
	 * @param localidade
	 *            Local a partir do qual se deve remover os percursos
	 * @return Os percursos removido ou null caso nao remova nada
	 */
	public PercursoSimples[] removerPercursosNoFimDesde(String localidade) {
		return this.remove(true, localidade);
	}

	/**
	 * Deve remover e devolver todos os percursos desde o inicio ate ao ponto da
	 * localidade recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoInicioAte(c), deve resultar no percurso com c-d/d-e e
	 * devolver a-b/b-c.
	 * 
	 * @param localidade
	 *            Local ate a' qual se deve remover os percursos
	 * @return Os percursos removido ou null caso nao remova nada
	 */
	public PercursoSimples[] removerPercursosNoInicioAte(String localidade) {
		return this.remove(false, localidade);
	}

	/**
	 * Deve devolver o indice do percurso em que a localidade e' inicio, ou -1
	 * caso nao encontre
	 * 
	 * @param localidade
	 *            Local a procurar
	 * @return o indice do percurso que tem a localidade recebida como inicio ou
	 *         null caso nao a encontre
	 */
	private int findLocalidade(String localidade) {
		for (int i = 0; i < this.percursos.length; i++) {
			if (this.percursos[i].getInicio().equalsIgnoreCase(localidade)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Devolve o inicio do percurso
	 * 
	 * @return O local de inicio do percurso
	 */
	public String getInicio() {
		return this.percursos[0].getInicio();
	}

	/**
	 * Devolve o fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	public String getFim() {
		int n = this.percursos.length - 1;
		return this.percursos[n].getFim();
	}

	/**
	 * Devolve a distancia do percurso, que deve ser o somatorio das distancias
	 * dos seus percursos
	 * 
	 * @return A distancia do percurso
	 */
	public int getDistancia() {
		int d = 0;

		for (PercursoSimples path : this.percursos) {
			d += path.getDistancia();
		}

		return d;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos
	 * 
	 * @return O declive do percurso
	 */
	public int getDeclive() {
		int d = 0;

		for (PercursoSimples path : this.percursos) {
			d += path.getDeclive();
		}

		return d;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos, mas so se deve considerar os declives positivos
	 * 
	 * @return O declive acumulado do percurso mas so considerando os declives
	 *         positivos
	 */
	public int getSubidaAcumulada() {
		int d = 0;

		for (PercursoSimples path : this.percursos) {
			int t = path.getDeclive();

			if (t > 0) {
				d += t;
			}
		}

		return d;
	}

	private PercursoSimples[] getPercursos() {
		return this.percursos;
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
	 * Altera o nome do percurso
	 * 
	 * @param nome
	 *            O novo nome do percurso
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Devolve uma string com uma descricao do percurso, tal como: "NORTE_SUL
	 * de Sagres para Lisboa, com 345000 metros, com 0 de declive e com 2
	 * percursos"
	 * 
	 * @return O string que descreve o percurso
	 */
	public String toString() {
		int n = this.percursos.length - 1;

		return String.format(
				"%s de %s para %s, com %d metros," +
				" com %d de declive e com %d percursos",
				this.nome,
				this.percursos[0].getInicio(),
				this.percursos[n].getFim(),
				this.getDistancia(),
				this.getDeclive(),
				this.percursos.length
		);
	}

	/**
	 * Imprime na consola o percurso. Deve mostrar na primeiro linha o prefixo
	 * seguido da informacao do toString deste objecto. Depois deve mostrar os
	 * seus percursos, um por linha, chamando os seus metodos de print, mas
	 * passando como prefixo o prefixo recebido e prefixado de 3 espacos.
	 * 
	 * @param prefix
	 *            Prefixo a colocar antes da informacao do toString e tambem na
	 *            parte de mostrar os percursos.
	 */
	public void print(String prefix) {
		System.out.printf("%s %s\n", prefix, this.toString());

		for (PercursoSimples p : this.percursos) {
			System.out.printf("   %s %s\n", prefix, p.toString());
		}
	}

	/**
	 * Main, para testes
	 * 
	 * @param args
	 *            Argumentos do main
	 */
	public static void main(String[] args) {
		PercursoSimples ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 0);
		PercursoSimples ps2 = new PercursoSimples("A1", "Lisboa", "Porto", 317_000, 0);
		PercursoSimples[] percursos = new PercursoSimples[] { ps1, ps2 };

		PercursoComposto pc1 = new PercursoComposto("NORTE_SUL", percursos, 20);
		pc1.print("> ");
		System.out.println();

		PercursoSimples ps3 = new PercursoSimples("A1", "Porto", "Viana Do Castelo", 73_800, 0);
		boolean result = pc1.adicionarPercursoNoFinal(ps3);
		pc1.print("> ");
		System.out.println();

		System.out.println("A adicao de " + ps3 + " deu -> " + result);
		System.out.println();

		PercursoSimples ps4 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, 0);
		pc1.adicionarPercursoNoInicio(ps4);
		pc1.print("> ");
		System.out.println();

		// clone
		System.out.println("Clone:");
		PercursoComposto pc2 = pc1.clone();
		pc2.print("> ");
		System.out.println();

		// removerPercursosNoFim
		PercursoSimples[] psx = pc1.removerPercursosNoFimDesde("Lisboa");
		pc1.print("> ");
		System.out.println("Percursos removidos -> " + Arrays.toString(psx));
		System.out.println();

		// removerPercursosNoInicio

		PercursoSimples[] psx2 = pc2.removerPercursosNoInicioAte("Lisboa");
		pc2.print("> ");
		System.out.println("Percursos removidos -> " + Arrays.toString(psx2));
	}
}