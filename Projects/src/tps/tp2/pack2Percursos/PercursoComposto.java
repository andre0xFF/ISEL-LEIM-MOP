package tps.tp2.pack2Percursos;

import java.util.Arrays;

/**
 * Classe que suporta um percurso composto por vários percursos simples
 */
/**
 * @author António Teófilo
 *
 */
public class PercursoComposto {

	/**
	 * Nome do percurso, deve respeitar a regra de validação dos nomes em
	 * percurso simples
	 */
	private String nome;

	/**
	 * Array com os percursos simples. Os elementos devem ser colocados nos
	 * índices menores. Tem de ter pelo menos um percurso. Não admite
	 * localidades repetidas. Os percursos têm de estar em sequência, ou seja,
	 * onde termina o percurso de índice 0 tem de ser onde se inicia o percurso
	 * de índice 1 e assim sucessivamente ...
	 */
	private PercursoSimples[] percursos;

	/**
	 * Nº de percursos
	 */
	private int nPercursos;

	/**
	 * Constructor que recebe apenas um percurso, além do nome e do nº máximo de
	 * percursos. Este constructor deve chamar o constructor que a ele se segue.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param percurso
	 *            Percurso a ser guardado
	 * @param maxPercursos
	 *            Nº máximo de percursos suportado
	 */
	public PercursoComposto(String nome, PercursoSimples percurso, int maxPercursos) {

		// TODO
	}

	/**
	 * Constructor que recebe o nome, um array de percursos e o máximo de
	 * percursos a suportar. Em caso de argumentos inválidos deve ser lançada a
	 * excepção IllegalArgumentException com uma mensagem a indicar o erro
	 * ocorrido e o argumento inválido.
	 * 
	 * @param nome
	 *            Nome do percurso
	 * @param percursos
	 *            Percursos a serem guardados. O array não pode conter nulls,
	 *            tem de conter pelo menos um percurso e os seus percursos devem
	 *            estar em sequência.
	 * @param maxPercursos
	 *            Nº máximo de percursos suportado
	 */
	public PercursoComposto(String nome, PercursoSimples[] percursos, int maxPercursos) {
		// TODO
	}

	/**
	 * Copy constructor, deve criar uma cópia do percurso recebido. Essa cópia
	 * deve ser uma cópia profunda.
	 * 
	 * @param pc
	 *            Percurso a copiar
	 */
	public PercursoComposto(PercursoComposto pc) {
		// TODO
	}

	/**
	 * Deve criar uma cópia profunda do percurso corrente
	 */
	public PercursoComposto clone() {
		// TODO
		return null;
	}

	/**
	 * Deve adicionar o percurso no final, desde que este esteja em sequência e
	 * haja espaço
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean addicionarPercursoNoFinal(PercursoSimples percurso) {
		// TODO
		return false;
	}

	/**
	 * Deve adicionar o percurso no início, desde que este esteja em sequência e
	 * haja espaço
	 * 
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean addicionarPercursoNoInicio(PercursoSimples percurso) {
		// TODO
		return false;
	}

	/**
	 * Deve remover e devolver todos os percursos desde o ponto da localidade
	 * recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoFimDesde(c), deve resultar no percurso com a-b/b-c e
	 * deve devolver c-d/d-e.
	 * 
	 * @param localidade
	 *            Local a partir do qual se deve remover os percursos
	 * @return Os percursos removido ou null caso não remova nada
	 */
	public PercursoSimples[] removerPercursosNoFimDesde(String localidade) {
		// TODO
		return null;
	}

	/**
	 * Deve remover e devolver todos os percursos desde o início até ao ponto da
	 * localidade recebida. Exemplo: percurso com a-b/b-c/c-d/d-e,
	 * removerPercursoNoInicioAte(c), deve resultar no percurso com c-d/d-e e
	 * devolver a-b/b-c.
	 * 
	 * @param localidade
	 *            Local até à qual se deve remover os percursos
	 * @return Os percursos removido ou null caso não remova nada
	 */
	public PercursoSimples[] removerPercursosNoInicioAte(String localidade) {
		// TODO
		return null;
	}

	/**
	 * Deve devolver o índice do percurso em que a localidade é início, ou -1
	 * caso não encontre
	 * 
	 * @param localidade
	 *            Local a procurar
	 * @return o índice do percurso que tem a localidade recebida como início ou
	 *         null caso não a encontre
	 */
	private int findLocalidade(String localidade) {
		// TODO
		return -1;
	}

	/**
	 * Devolve o início do percurso
	 * 
	 * @return O local de início do percurso
	 */
	public String getInicio() {
		// TODO
		return null;
	}

	/**
	 * Devolve o fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	public String getFim() {
		// TODO
		return null;
	}

	/**
	 * Devolve a distância do percurso, que deve ser o somatório das distâncias
	 * dos seus percursos
	 * 
	 * @return A distância do percurso
	 */
	public int getDistancia() {
		// TODO
		return 0;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatório dos declives dos
	 * seus percursos
	 * 
	 * @return O declive do percurso
	 */
	public int getDeclive() {
		// TODO
		return 0;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatório dos declives dos
	 * seus percursos, mas só se deve considerar os declives positivos
	 * 
	 * @return O declive acumulado do percurso mas só considerando os declives
	 *         positivos
	 */
	public int getSubidaAcumulada() {
		// TODO
		return 0;
	}

	/**
	 * Devolve o nome do percurso
	 * 
	 * @return O nome do percurso
	 */
	public String getNome() {
		// TODO
		return null;
	}

	/**
	 * Altera o nome do percurso
	 * 
	 * @param nome
	 *            O novo nome do percurso
	 */
	public void setNome(String nome) {
		// TODO
	}

	/**
	 * Devolve uma string com uma descriução do percurso, tal como: "NORTE_SUL
	 * de Sagres para Lisboa, com 345000 metros, com 0 de declive e com 2
	 * percursos"
	 * 
	 * @return O string que descreve o percurso
	 */
	public String toString() {
		// TODO
		return null;
	}

	/**
	 * Imprime na consola o percurso. Deve mostrar na primeiro linha o prefixo
	 * seguido da informação do toString deste objecto. Depois deve mostrar os
	 * seus percursos, um por linha, chamando os seus métodos de print, mas
	 * passando como prefixo o prefixo recebido e prefixado de 3 espaços.
	 * 
	 * @param prefix
	 *            Prefixo a colocar antes da informação do toString e também na
	 *            parte de mostrar os percursos.
	 */
	public void print(String prefix) {
		// TODO
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
		boolean result = pc1.addicionarPercursoNoFinal(ps3);
		pc1.print("> ");
		System.out.println();

		System.out.println("A adição de " + ps3 + " deu -> " + result);
		System.out.println();

		PercursoSimples ps4 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, 0);
		pc1.addicionarPercursoNoInicio(ps4);
		pc1.print("> ");
		System.out.println();

		// clone
		System.out.println("Clone:");
		PercursoComposto pc2 = pc1.clone();
		pc1.print("> ");
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

/*- Outputs esperados
> NORTE_SUL de Faro para Porto, com 595000 metros, com 0 de declive e com 2 percursos
   > A2 de Faro para Lisboa, com 278000 metros e com 0 de declive
   > A1 de Lisboa para Porto, com 317000 metros e com 0 de declive

> NORTE_SUL de Faro para Viana Do Castelo, com 668800 metros, com 0 de declive e com 3 percursos
   > A2 de Faro para Lisboa, com 278000 metros e com 0 de declive
   > A1 de Lisboa para Porto, com 317000 metros e com 0 de declive
   > A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive

A adição de A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive deu -> true

> NORTE_SUL de Sagres para Viana Do Castelo, com 735800 metros, com 0 de declive e com 4 percursos
  > A23 de Sagres para Faro, com 67000 metros e com 0 de declive
   > A2 de Faro para Lisboa, com 278000 metros e com 0 de declive
   > A1 de Lisboa para Porto, com 317000 metros e com 0 de declive
   > A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive

Clone:
> NORTE_SUL de Sagres para Viana Do Castelo, com 735800 metros, com 0 de declive e com 4 percursos
   > A23 de Sagres para Faro, com 67000 metros e com 0 de declive
   > A2 de Faro para Lisboa, com 278000 metros e com 0 de declive
   > A1 de Lisboa para Porto, com 317000 metros e com 0 de declive
   > A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive

> NORTE_SUL de Sagres para Lisboa, com 345000 metros, com 0 de declive e com 2 percursos
   > A23 de Sagres para Faro, com 67000 metros e com 0 de declive
   > A2 de Faro para Lisboa, com 278000 metros e com 0 de declive
Percursos removidos -> [A1 de Lisboa para Porto, com 317000 metros e com 0 de declive, A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive]

> NORTE_SUL de Lisboa para Viana Do Castelo, com 390800 metros, com 0 de declive e com 2 percursos
   > A1 de Lisboa para Porto, com 317000 metros e com 0 de declive
   > A1 de Porto para Viana Do Castelo, com 73800 metros e com 0 de declive
Percursos removidos -> [A23 de Sagres para Faro, com 67000 metros e com 0 de declive, A2 de Faro para Lisboa, com 278000 metros e com 0 de declive]
*/