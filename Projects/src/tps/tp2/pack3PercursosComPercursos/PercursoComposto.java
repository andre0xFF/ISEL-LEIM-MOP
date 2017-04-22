package tps.tp2.pack3PercursosComPercursos;

import tps.tp2.Path;
import tps.tp2.pack2Percursos.PercursoSimples;

/**
 * Classe que suporta um percurso composto por varios percursos simples ou
 * compostos. A classe tem de ter pelo menos um percurso. Nao admite localidades
 * repetidas. Os nomes das localidades sao case-sensitive. Os percursos teem de
 * estar em sequencia, ou seja, onde termina o percurso de indice 0 tem de ser
 * onde se inicia o percurso de indice 1 e assim sucessivamente ... Num percurso
 * composto considera-se que os seus percursos compostos estao em sequencia
 * (depois) de todos os percursos simples.
 */
public class PercursoComposto implements Path{

	private int maxPercursos;

	/**
	 * Nome do percurso, deve respeitar a regra de validacao dos nomes em
	 * percurso simples
	 */
	private String nome;

	/**
	 * Array com os percursos simples. Os elementos devem ser colocados nos
	 * indices menores.
	 */
	private PercursoSimples[] percursosSimples;

	/**
	 * Numero de percursos simples
	 */
	private int nPercursosSimples;

	/**
	 * Array com os percursos compostos. Os elementos devem ser colocados nos
	 * indices menores.
	 */
	private PercursoComposto[] percursosCompostos;

	/**
	 * Numero de percursos compostos
	 */
	private int nPercursosCompostos;

	/**
	 * Constructor que recebe apenas um percurso simples, alem do nome e do numero
	 * maximo de percursos. Sugestao: chamar outro construtor.
	 * 
	 * @param nome
	 *            Nome do percurso simples
	 * @param percursoSimples
	 *            Percurso a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, PercursoSimples percursoSimples,
			int maxPercursos) {
		this(nome, new PercursoSimples[] { percursoSimples }, maxPercursos);
	}

	/**
	 * Constructor que recebe apenas um percurso composto, alem do nome e do numero
	 * m�ximo de percursos. Sugestao: chamar outro construtor.
	 * 
	 * @param nome
	 *            Nome do percurso composto
	 * @param percursoComposto
	 *            Percurso composto a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, PercursoComposto percursoComposto,
			int maxPercursos) {
		this(nome, new PercursoComposto[] { percursoComposto }, maxPercursos);
	}

	/**
	 * Constructor que recebe um array percursos simples, alem do nome e do numero
	 * maximo de percursos. O array nao pode ter nulls, os seus percursos teem de
	 * estar em sequencia e nao pode haver repeticoes de localidades. Sugestao:
	 * chamar outro construtor.
	 * 
	 * @param nome
	 *            Nome do percurso simples
	 * @param percursosSimples
	 *            Percursos a guardar
	 * @param maxPercursos
	 *            N� m�ximo de percursos a suportar
	 */
	public PercursoComposto(String nome, PercursoSimples[] percursosSimples,
			int maxPercursos) {
		this(nome, percursosSimples, new PercursoComposto[] { }, maxPercursos);
	}

	/**
	 * Constructor que recebe um array percursos compostos, alem do nome e do numero
	 * maximo de percursos. O array nao pode ter nulls, os seus percursos t�m de
	 * estar em sequencia e nao pode haver repeticoes de localidades.
	 * Considera-se que os percursos compostos recebidos, em si, estao bem
	 * formados. Sugestao: chamar outro construtor.
	 * 
	 * @param nome
	 *            Nome do percurso composto
	 * @param percursosCompostos
	 *            Percursos compostos a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, PercursoComposto[] percursosCompostos,
			int maxPercursos) {
		this(nome, new PercursoSimples[] { }, percursosCompostos, maxPercursos);
	}

	/**
	 * Constructor que recebe um array percursos simples e um array de percursos
	 * compostos, alem do nome e do numero maximo de percursos. Os array teem de ter
	 * no conjunto pelo menos um percurso simples. Os percursos simples
	 * recebidos no array consideram-se como ficanado antes dos percursos
	 * compostos recebidos no array e teem de estar em sequencia e nao pode haver
	 * repeticoes de localidades sob nenhuma forma. Considera-se que os
	 * percursos compostos recebidos, em si, estao bem formados. Sugestao:
	 * chamar os metodos de adicionar no final.
	 * 
	 * @param nome
	 *            Nome do percurso composto, deve ser validado pelo metodo em
	 *            percurso simples
	 * @param percursosSimples
	 *            Percursos simples a guardar
	 * @param percursosCompostos
	 *            Percursos compostos a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, PercursoSimples[] percursosSimples,
			PercursoComposto[] percursosCompostos, int maxPercursos) {
		this.nome = nome;
		this.percursosSimples = percursosSimples;
		this.percursosCompostos = percursosCompostos;
		this.maxPercursos = maxPercursos;
	}

	/**
	 * Copy constructor, deve criar uma copia profunda do percurso recebido.
	 * 
	 * @param pc
	 *            Percurso a copiar
	 */
	public PercursoComposto(PercursoComposto pc) {
		this(
				pc.getNome(),
				pc.percursosSimples,
				pc.percursosCompostos,
				pc.maxPercursos
		);
	}

	/**
	 * Cria uma copia profunda do percurso corrente
	 * 
	 * @return O percurso composto que e' uma copia profunda do percurso composto
	 *         corrente
	 */
	public PercursoComposto clone() {
		return new PercursoComposto(
				this.nome,
				this.percursosSimples,
				this.percursosCompostos,
				this.maxPercursos
		);
	}

	/**
	 * Deve adicionar o percurso simples no final, desde que este esteja em
	 * sequencia e haja espaco. Nao pode adicionar se ja existirem percursos
	 * compostos ou se a nova localidade ja existir. Sugestao: utilizar os
	 * metodos de getLocalidades e haveRepeticoes.
	 * 
	 * @param percurso
	 *            Percurso simples a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoSimplesNoFinal(PercursoSimples percurso) {
	    if (this.percursosSimples.length > 0) {
	        return false;
        }

        try {
            this.percursosSimples = (PercursoSimples[]) Path.insert(this.percursosSimples, this.maxPercursos, -1, percurso);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
	}

	/**
	 * Deve adicionar o percurso composto no final, desde que este esteja em
	 * sequencia e haja espaco. Nao pode adicionar as novas localidades ja
	 * existirem. Sugestao: utilizar os metodos de getLocalidades e
	 * haveRepeticoes.
	 * 
	 * @param percurso
	 *            Percurso composto a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoCompostoNoFinal(PercursoComposto percurso) {
        if (this.percursosCompostos.length > 0) {
            return false;
        }

        try {
            this.percursosCompostos = (PercursoComposto[]) Path.insert(this.percursosCompostos, this.maxPercursos, -1, percurso);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
	}

	/**
	 * Metodo que retorna true se ha repeticoes entre os dois arrays. Sugere-se
	 * a ordenacao de um array, e percorrer o outro e para cada elemento dele
	 * fazer uma pesquisa binaria no array ordenado.
	 * 
	 * @param locs1
	 *            Array1 com localidades
	 * @param locs2
	 *            Array2 com localidades
	 * @return True se alguma localidade em Array1 existe em array2, false caso
	 *         contrario
	 */
	private static boolean haveRepetitions(String[] locs1, String[] locs2) {
		// TODO
		return false;
	}

	/**
	 * Obtem todas as localidades distintas existentes do percurso, mesmo as de
	 * inicio e de fim. Deve devolver as localidades num novo array e sem
	 * posicoes a null. Sugestao: utilizar o metodo getNumLocalidades para saber
	 * previamente quantas localidades ha.
	 * 
	 * @return O novo array com todas as localidades existentes no percurso
	 *         composto
	 */
	private String[] getLocalidades() {
		// TODO
		return null;
	}

	/**
	 * Obtem o numero de localidades distintas existentes dentro do percurso
	 * composto actual. Devem ser incluidas as localidades de inicio e de fim do
	 * percurso.
	 * 
	 * @return Numero de localidades distintas existentes dentro deste percurso
	 *         composto
	 */
	private int getNumLocalidades() {
		// TODO
		return 0;
	}

	/**
	 * Deve adicionar o percurso simples no inicio, desde que este esteja em
	 * sequencia e haja espaco. Nao adicionar caso provoque uma repeticoes de
	 * localidades. Sugestao: utilizar o getLocalidades e haveRepetitions.
	 * 
	 * @param percurso
	 *            Percurso simples a adicionar
	 * @return True se adicionou, ou false em caso contrario
	 */
	public boolean adicionarPercursoSimplesNoInicio(PercursoSimples percurso) {
		// TODO
		return false;
	}

	/**
	 * Deve adicionar o percurso composto recebido no inicio deste percurso
	 * composto. Nao pode adicionar se ja existirem percursos simples, se
	 * provocar uma repeticao de localidades, ou se nao existir espaco.
	 * Sugestao: verificar a repeticao das localidades utilizando os metodos
	 * getLocalidades e haveRepetitions.
	 * 
	 * @param percurso
	 *            Percurso composto a adicionar
	 * @return True se adicionou, ou false caso contrario
	 */
	public boolean adicionarPercursoCompostoNoInicio(PercursoComposto percurso) {
		// TODO
		return false;
	}

	/**
	 * Devolve o inicio do percurso
	 * 
	 * @return O local de inicio do percurso
	 */
	@Override
	public String get_beginning() {
		return this.percursosSimples[0].get_beginning();
	}

	/**
	 * Devolve o fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	@Override
	public String get_ending() {
        return this.percursosSimples[0].get_ending();
	}

	/**
	 * Devolve a distancia do percurso, que deve ser o somatorio das distancias
	 * dos seus percursos
	 * 
	 * @return A distancia do percurso
	 */
	public int getDistancia() {
		// TODO
		return 0;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos
	 * 
	 * @return O declive do percurso
	 */
	public int getDeclive() {
		// TODO
		return 0;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos, mas so se deve considerar os declives positivos
	 * 
	 * @return O declive acumulado do percurso mas so considerando os declives
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
	 * Devolve uma string com uma descricao do percurso, tal como: NORTE_SUL de
	 * Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos
	 * simples e 1 percuros compostos
	 * 
	 * @return O string que descreve o percurso
	 */
	public String toString() {
		// TODO
		return null;
	}

	/**
	 * Imprime na consola o percurso. Deve mostrar na primeiro linha o prefixo
	 * seguido da informacao do toString deste objecto. Depois deve mostrar os
	 * seus percursos, um por linha, chamando os seus metodos de print, mas
	 * passando como prefixo o prefixo recebido mas prefixado de 2 espacos.
	 * 
	 * @param prefix
	 *            Prefixo a colocar antes da informacao do toString e tambem na
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
		PercursoSimples ps1, ps2, ps3, ps4;

		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1", "Lisboa", "Porto", 317_000, 20);
		ps3 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800,
				30);
		ps4 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);

		String strSeparator = " ---------------------------------------------------------------- ";

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com PercursoSimples");
		PercursoComposto pcA1 = new PercursoComposto("PSA1", ps1, 20);
		pcA1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com array de PercursoSimples");
		PercursoComposto pc1 = new PercursoComposto("NORTE_SUL",
				new PercursoSimples[] { ps1, ps2 }, 20);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com PercursoComposto");
		PercursoComposto pc2 = new PercursoComposto("NORTE_SUL_V2",
				new PercursoComposto("NORTE_SUL", new PercursoSimples[] { ps1,
						ps2 }, 20), 20);
		pc2.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com array de PercursoComposto");
		PercursoComposto pc3 = new PercursoComposto("NORTE_SUL_V2",
				new PercursoComposto[] { pc1 }, 20);
		pc3.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com arrays de PercursoSimples e PercursoComposto");
		PercursoComposto pc4 = new PercursoComposto("SUL_NORTE",
				new PercursoSimples[] { ps1, ps2 },
				new PercursoComposto[] { new PercursoComposto("NN", ps3, 20) },
				20);
		pc4.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoSimples No Inicio");

		pc1.adicionarPercursoSimplesNoInicio(ps4);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoSimples No Final");
		pc1.print("> ");
		System.out.println();

		boolean result = pc1.adicionarPercursoSimplesNoFinal(ps3);
		System.out.println("A adicao de " + ps3 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoSimples No Final com erro");
		pc1.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoSimplesNoFinal(ps3);
		System.out.println("A adicao de " + ps3 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoComposto No Inicio");
		pc2.print("> ");
		System.out.println();

		result = pc2.adicionarPercursoCompostoNoInicio(new PercursoComposto(
				"SAGRESFARO", ps4, 20));
		System.out.println("A adicao de " + ps4 + " deu -> " + result);
		System.out.println();

		pc2.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoComposto No Inicio com erro");
		pc1.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoCompostoNoInicio(pc2);
		System.out.println("A adicao de " + pc2 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar PercursoComposto No Final");
		PercursoComposto pc6 = new PercursoComposto("sul",
				new PercursoComposto("ss", new PercursoSimples[] { ps4, ps1 },
						20), 20);
		pc6.print("> ");
		System.out.println();

		PercursoComposto pc7 = new PercursoComposto("centro",
				new PercursoSimples[] { ps2, ps3 }, 20);

		result = pc6.adicionarPercursoCompostoNoFinal(pc7);
		System.out.println("A adicao de " + pc7 + " deu -> " + result);
		System.out.println();

		pc6.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao clone");

		pc6.print("> ");
		System.out.println();

		PercursoComposto pc8 = pc6.clone();
		pc8.print("> ");
		System.out.println();

		PercursoComposto pcA = new PercursoComposto("w",
				new PercursoSimples[] { new PercursoSimples("vVC",
						"Viana do Castelo", "Caminha", 70_000, 20) }, 20);

		result = pc6.adicionarPercursoCompostoNoFinal(pcA);

		System.out.println("Adicionado " + pcA + " ao pc original, deu -> "
				+ result);
		System.out.println();

		pc8.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao subidaAcumulada");
		pc6.print("> ");
		System.out.println();
		System.out.println(pc6 + " tem uma subida acumulada de -> "
				+ pc6.getSubidaAcumulada());
	}
}

/*- Outputs esperados

---------------------------------------------------------------- 
Teste ao construtor com PercursoSimples
> PSA1 de Faro para Lisboa, com 278000 metros, com 10 de declive, com 1 percursos simples e 0 percursos compostos
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive

 ---------------------------------------------------------------- 
Teste ao construtor com array de PercursoSimples
> NORTE_SUL de Faro para Porto, com 595000 metros, com 30 de declive, com 2 percursos simples e 0 percursos compostos
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

 ---------------------------------------------------------------- 
Teste ao construtor com PercursoComposto
> NORTE_SUL_V2 de Faro para Porto, com 595000 metros, com 30 de declive, com 0 percursos simples e 1 percursos compostos
  > NORTE_SUL de Faro para Porto, com 595000 metros, com 30 de declive, com 2 percursos simples e 0 percursos compostos
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

 ---------------------------------------------------------------- 
Teste ao construtor com array de PercursoComposto
> NORTE_SUL_V2 de Faro para Porto, com 595000 metros, com 30 de declive, com 0 percursos simples e 1 percursos compostos
  > NORTE_SUL de Faro para Porto, com 595000 metros, com 30 de declive, com 2 percursos simples e 0 percursos compostos
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

 ---------------------------------------------------------------- 
Teste ao construtor com arrays de PercursoSimple e PercursoComposto
> SUL_NORTE de Faro para Viana do Castelo, com 668800 metros, com 60 de declive, com 2 percursos simples e 1 percursos compostos
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > NN de Porto para Viana do Castelo, com 73800 metros, com 30 de declive, com 1 percursos simples e 0 percursos compostos
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoSimples No In�cio
> NORTE_SUL de Sagres para Porto, com 662000 metros, com 20 de declive, com 3 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoSimples No Final
> NORTE_SUL de Sagres para Porto, com 662000 metros, com 20 de declive, com 3 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

A adi��o de A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive deu -> true

> NORTE_SUL de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 4 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoSimples No Final com erro
> NORTE_SUL de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 4 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

A adi��o de A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive deu -> false

> NORTE_SUL de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 4 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoComposto No Inicio
> NORTE_SUL_V2 de Faro para Porto, com 595000 metros, com 30 de declive, com 0 percursos simples e 1 percursos compostos
  > NORTE_SUL de Faro para Porto, com 595000 metros, com 30 de declive, com 2 percursos simples e 0 percursos compostos
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

A adi��o de A23 de Sagres para Faro, com 67000 metros e com -10 de declive deu -> true

> NORTE_SUL_V2 de Sagres para Porto, com 662000 metros, com 20 de declive, com 0 percursos simples e 2 percursos compostos
  > SAGRESFARO de Sagres para Faro, com 67000 metros, com -10 de declive, com 1 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > NORTE_SUL de Faro para Porto, com 595000 metros, com 30 de declive, com 2 percursos simples e 0 percursos compostos
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoComposto No Inicio com erro
> NORTE_SUL de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 4 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

A adi��o de NORTE_SUL_V2 de Sagres para Porto, com 662000 metros, com 20 de declive, com 0 percursos simples e 2 percursos compostos deu -> false

> NORTE_SUL de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 4 percursos simples e 0 percursos compostos
  > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
  > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
  > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao adicionar PercursoComposto No Final
> sul de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 0 percursos simples e 1 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive

A adi��o de centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos deu -> true

> sul de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 0 percursos simples e 2 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao clone
> sul de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 0 percursos simples e 2 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

> sul de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 0 percursos simples e 2 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

Adicionado w de Viana do Castelo para Caminha, com 70000 metros, com 20 de declive, com 1 percursos simples e 0 percursos compostos ao pc original, deu -> true

> sul de Sagres para Viana do Castelo, com 735800 metros, com 50 de declive, com 0 percursos simples e 2 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive

 ---------------------------------------------------------------- 
Teste ao subidaAcumulada
> sul de Sagres para Caminha, com 805800 metros, com 70 de declive, com 0 percursos simples e 3 percursos compostos
  > ss de Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos simples e 0 percursos compostos
    > A23 de Sagres para Faro, com 67000 metros e com -10 de declive
    > A2 de Faro para Lisboa, com 278000 metros e com 10 de declive
  > centro de Lisboa para Viana do Castelo, com 390800 metros, com 50 de declive, com 2 percursos simples e 0 percursos compostos
    > A1 de Lisboa para Porto, com 317000 metros e com 20 de declive
    > A28 de Porto para Viana do Castelo, com 73800 metros e com 30 de declive
  > w de Viana do Castelo para Caminha, com 70000 metros, com 20 de declive, com 1 percursos simples e 0 percursos compostos
    > vVC de Viana do Castelo para Caminha, com 70000 metros e com 20 de declive

sul de Sagres para Caminha, com 805800 metros, com 70 de declive, com 0 percursos simples e 3 percursos compostos tem uma subida acumulada de -> 80
 */