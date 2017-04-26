package tps.tp2.pack3PercursosComPercursos;

import org.apache.commons.lang.NotImplementedException;
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
public class PercursoComposto implements Path {

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
		int size = this.percursosCompostos.length;
		PercursoComposto[] pc = new PercursoComposto[size];

		for (int i = 0 ; i < pc.length; i++) {
			pc[i] = new PercursoComposto(this.percursosCompostos[i]);
		}

		size = this.percursosSimples.length;
		PercursoSimples[] ps = new PercursoSimples[size];

		for (int i = 0; i < ps.length; i++) {
			ps[i] = this.percursosSimples[i];
		}

		return new PercursoComposto(
				this.nome,
				ps,
				pc,
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
	    if (this.percursosSimples.length == 0) {
	        return false;
        }

        Path[] aux = Path.insert(this.percursosSimples, this.maxPercursos, -1, percurso);;

        try {
            Path.validate(aux, this.maxPercursos);
        }
        catch(IllegalArgumentException e) {
	        return false;
        }

        this.percursosSimples = new PercursoSimples[aux.length];

        for (int i = 0; i < aux.length; i++) {
            this.percursosSimples[i] = (PercursoSimples) aux[i];
        }

        return true;
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
        Path[] aux = Path.insert(this.percursosCompostos.clone(), this.maxPercursos, -1, percurso);


		try {
			Path.validate(aux, this.maxPercursos);
		}
		catch(Exception e) {
			return false;
		}

		this.percursosCompostos = new PercursoComposto[aux.length];

		for (int i = 0; i < aux.length; i++) {
			this.percursosCompostos[i] = (PercursoComposto) aux[i];
		}

        return true;
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
		for (int i = 0; i < locs1.length; i++) {
		    for (int j = 0; i < locs2.length; j++) {
		        if (locs1[i].equalsIgnoreCase(locs2[j])) {
                    return false;
                }
            }
        }

        return true;
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
		String locals = this.percursosSimples[0].get_beginning();

		for (int i = 0; i < this.percursosSimples.length; i++) {
			locals = locals.concat(String.format("_%s", this.percursosSimples[0].get_ending()));
		}

		for (int i = 0; i < this.percursosCompostos.length; i++) {
			String[] collection = this.percursosCompostos[i].getLocalidades();;

			for (int j = 0; j < collection.length; j++) {
				if (locals.indexOf(collection[j]) == -1) {
					locals = locals.concat(String.format("_%s", collection[j]));
				}
			}
		}

		return locals.split("_");
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
		return this.getLocalidades().length;
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
		Path[] aux = Path.insert(this.percursosSimples.clone(), this.maxPercursos, 0, percurso);

		try {
			Path.validate(aux, this.maxPercursos);
		}
		catch(Exception e) {
			return false;
		}

        this.percursosSimples = new PercursoSimples[aux.length];

		for (int i = 0; i < aux.length; i++) {
		    this.percursosSimples[i] = (PercursoSimples) aux[i];
        }

		return true;
	}

	public boolean validate() {
	    if (this.percursosSimples.length > 1) {
            for(int i = 1; i < this.percursosSimples.length; i++) {
                try {
                    Path.validate(this.percursosSimples[i - 1], this.percursosSimples[i]);
                }
                catch(IllegalArgumentException e) {
                    return false;
                }
            }
        }

        for (int i = 0; i < this.percursosCompostos.length; i++) {
	        boolean eval = this.percursosCompostos[i].validate();

	        if (!eval) {
	            return false;
            }

            PercursoComposto p = this.percursosCompostos[i];

            if (this.percursosSimples.length == 0 || p.percursosSimples.length == 0) {
                continue;
            }

            eval = Path.validate(
                    p.percursosSimples,
                    p.maxPercursos,
                    this.percursosSimples,
                    this.maxPercursos
            );

            if (!eval) {
                return false;
            }
        }

        return true;
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
	    Path[] aux = Path.insert(this.percursosCompostos.clone(), this.maxPercursos, 0, percurso);

        try {
            Path.validate(
                    this.percursosSimples,
                    this.maxPercursos,
                    this.percursosCompostos,
                    this.maxPercursos);

        }
        catch (Exception e) {
            return false;
        }

        this.percursosCompostos = new PercursoComposto[aux.length];

        for (int i = 0; i < aux.length; i++) {
            this.percursosCompostos[i] = (PercursoComposto) aux[i];
        }

        return true;
	}

	/**
	 * Devolve o inicio do percurso
	 * 
	 * @return O local de inicio do percurso
	 */
	@Override
	public String get_beginning() {
	    Path p;

	    if (this.percursosSimples.length > 0) {
	        p = this.percursosSimples[0];
        }
        else {
	        p = this.percursosCompostos[0];
        }

		return p.get_beginning();
	}

	/**
	 * Devolve o fim do percurso
	 * 
	 * @return O local de fim do percurso
	 */
	@Override
	public String get_ending() {
	    if (this.percursosCompostos.length > 0) {
	        int c = this.percursosCompostos.length - 1;
	        return this.percursosCompostos[c].get_ending();
        }

        int n = this.percursosSimples.length - 1;
        return this.percursosSimples[n].get_ending();
	}

	/**
	 * Devolve a distancia do percurso, que deve ser o somatorio das distancias
	 * dos seus percursos
	 * 
	 * @return A distancia do percurso
	 */
	public int getDistancia() {
        int sum = 0;

        for (int i = 0; i < this.percursosSimples.length; i++) {
            sum += this.percursosSimples[i].getDistancia();
        }

        for (int i = 0; i < this.percursosCompostos.length; i++) {
            sum += this.percursosCompostos[i].getDistancia();
        }

        return sum;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos
	 * 
	 * @return O declive do percurso
	 */
	public int getDeclive() {
	    int sum = 0;

	    for (int i = 0; i < this.percursosSimples.length; i++) {
	        sum += this.percursosSimples[i].getDeclive();
        }

        for (int i = 0; i < this.percursosCompostos.length; i++) {
	        sum += this.percursosCompostos[i].getDeclive();
        }

		return sum;
	}

	/**
	 * Devolve o declive do percurso, que deve ser o somatorio dos declives dos
	 * seus percursos, mas so se deve considerar os declives positivos
	 * 
	 * @return O declive acumulado do percurso mas so considerando os declives
	 *         positivos
	 */
	public int getSubidaAcumulada() {
        int sum = 0;

        for (int i = 0; i < this.percursosSimples.length; i++) {
            int d = this.percursosSimples[i].getDeclive();

            if (d > 0) {
                sum += d;
            }
        }

        for (int i = 0; i < this.percursosCompostos.length; i++) {
            sum += this.percursosCompostos[i].getDeclive();
        }

        return sum;
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
	 * Devolve uma string com uma descricao do percurso, tal como: NORTE_SUL de
	 * Sagres para Lisboa, com 345000 metros, com 0 de declive, com 2 percursos
	 * simples e 1 percuros compostos
	 * 
	 * @return O string que descreve o percurso
	 */
	public String toString() {
	    return String.format(
	            "%s de %s para %s, com %d metros, com %d de declive, com "
                        + "%d percursos simples e %d percursos comportos",
                this.nome,
                this.get_beginning(),
                this.get_ending(),
                this.getDistancia(),
                this.getDeclive(),
                this.percursosSimples.length,
                this.percursosCompostos.length
        );
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
		System.out.printf("%s %s\n", prefix, this.toString());

		for (int i = 0; i < this.percursosSimples.length; i++) {
		    this.percursosSimples[i].print(String.format("    %s", prefix));
        }

        for (int i = 0; i < this.percursosCompostos.length; i++) {
            this.percursosCompostos[i].print(String.format("    %s", prefix));
        }
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