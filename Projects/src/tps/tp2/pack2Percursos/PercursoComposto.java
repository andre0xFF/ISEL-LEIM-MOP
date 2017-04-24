package tps.tp2.pack2Percursos;

import tps.tp2.Path;

import java.util.Arrays;

/**
 * Classe que suporta um percurso composto por varios percursos simples
 */
public class PercursoComposto implements Path {

	private final int maxPercursos;

	private String nome;

	private PercursoSimples[] percursos;

	private int nPercursos;

	/**
	 * Constructor que recebe apenas um percurso, alem do nome e do no maximo de
	 * percursos.
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
	 * percursos a suportar.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param percursos
	 *            Percursos a serem guardados.
	 * @param maxPercursos
	 *            Numero maximo de percursos suportado
	 */
	public PercursoComposto(String nome, PercursoSimples[] percursos, int maxPercursos) {
		this.nome = nome;
		this.percursos = percursos;
		this.maxPercursos = maxPercursos;

		Path.validate(this.percursos, this.maxPercursos);
	}

	/**
	 * Copy constructor, cria uma copia profunda do percurso recebido.
	 * 
	 * @param pc
	 *            Percurso a copiar
	 */
	public PercursoComposto(PercursoComposto pc) {
		this(pc.getNome(), pc.percursos, pc.maxPercursos);
	}

	/**
	 * Cria uma copia profunda do actual percurso
	 */
	public PercursoComposto clone() {
		return new PercursoComposto(this);
	}

	/**
	 * Adiciona o percurso no final, desde que este esteja em sequencia e
	 * haja espaco
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoNoFinal(PercursoSimples percurso) {
		Path[] path = Path.insert(
				this.percursos.clone(),
				this.maxPercursos,
				-1,
				percurso
				);

		try {
			Path.validate(path, this.maxPercursos);
		}
		catch (Exception e) {
			return false;
		}

		this.percursos = new PercursoSimples[path.length];

		for (int i = 0; i < path.length; i++) {
			this.percursos[i] = (PercursoSimples) path[i];
		}

		return true;
	}

	/**
	 * Adiciona o percurso no inicio, desde que este esteja em sequencia e
	 * haja espaco
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoNoInicio(PercursoSimples percurso) {
		Path[] path = Path.insert(
				this.percursos.clone(),
				this.maxPercursos,
				0,
				percurso
		);

		try {
			Path.validate(path, this.maxPercursos);
		}
		catch (Exception e) {
			return false;
		}

		this.percursos = new PercursoSimples[path.length];

		for (int i = 0; i < path.length; i++) {
			this.percursos[i] = (PercursoSimples) path[i];
		}

		return true;
	}

	/**
	 * Remove e devolve todos os percursos desde o ponto da localidade
	 * recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoFimDesde(c), deve resultar no percurso com a-b/b-c e
	 * deve devolver c-d/d-e.
	 * 
	 * @param localidade
	 *            Local a partir do qual se deve remover os percursos
	 * @return Os percursos removido ou null caso nao remova nada
	 */
	public PercursoSimples[] removerPercursosNoFimDesde(String localidade) {
		Path[] aux = this.percursos;
		this.percursos = (PercursoSimples[]) Path.remove(aux, true, localidade);
		return (PercursoSimples[]) Path.remove(aux, false, localidade);
	}

	/**
	 * Remove e devolver todos os percursos desde o inicio ate ao ponto da
	 * localidade recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoInicioAte(c), deve resultar no percurso com c-d/d-e e
	 * devolver a-b/b-c.
	 * 
	 * @param localidade
	 *            Local ate a' qual se deve remover os percursos
	 * @return Os percursos removido ou null caso nao remova nada
	 */
	public PercursoSimples[] removerPercursosNoInicioAte(String localidade) {
		Path[] aux = this.percursos;
		this.percursos = (PercursoSimples[]) Path.remove(aux, false, localidade);
		return (PercursoSimples[]) Path.remove(aux, true, localidade);
	}

	/**
	 * Devolve o indice do percurso em que a localidade e' inicio, ou -1
	 * caso nao encontre
	 * 
	 * @param localidade
	 *            Local a procurar
	 * @return o indice do percurso que tem a localidade recebida como inicio ou
	 *         null caso nao a encontre
	 */
	private int findLocalidade(String localidade) {
		for (int i = 0; i < this.percursos.length; i++) {
			if (this.percursos[i].get_beginning().equalsIgnoreCase(localidade)) {
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
	@Override
	public String get_beginning() {
		return this.percursos[0].get_beginning();
	}

	/**
	 * Devolve o fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	public String get_ending() {
		int n = this.percursos.length - 1;
		return this.percursos[n].get_ending();
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
				this.percursos[0].get_beginning(),
				this.percursos[n].get_ending(),
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