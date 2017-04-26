package tps.tp3.pack1PercursosComHeranca;

import org.apache.commons.lang.NotImplementedException;
import tps.tp2.Path;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe que suporta um percurso composto por varios percursos simples ou
 * compostos. A classe tem de ter pelo menos um percurso simples. Nao admite
 * localidades repetidas. Os nomes das localidades sao case-sensitive. Os
 * percursos teem de estar em sequencia, ou seja, onde termina o percurso de
 * indice 0 tem de ser onde se inicia o percurso de indice 1 e assim
 * sucessivamente ...
 */
public class PercursoComposto extends Percurso {

    /**
	 * Array com os percursos. Os elementos devem ser colocados nos indices
	 * menores.
	 */
	private Percurso[] percursos;

	/**
	 * Numero de percursos
	 */
	private int nPercursos;

	/**
	 * Constructor que recebe apenas um percurso, alem do nome e do numero maximo de
	 * percursos.
	 *
	 * @param nome
	 *            Nome do percurso
	 * @param percurso
	 *            Percurso a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, Percurso percurso, int maxPercursos) {
		this(nome, new Percurso[] { percurso }, maxPercursos);
	}

	/**
	 * Constructor que recebe um array percursos, alem do nome e do numero maximo de
	 * percursos. O array tem de ter pelo menos um percurso. Os percursos
	 * recebidos no array estao bem formados, mas que tem de estar em sequencia
	 * e nao pode haver repeticoes de localidades sob nenhuma forma.
	 *
	 * @param nome
	 *            Nome do percurso composto
	 * @param percursos
	 *            Percursos a guardar
	 * @param maxPercursos
	 *            Numero maximo de percursos a suportar
	 */
	public PercursoComposto(String nome, Percurso[] percursos, int maxPercursos) {
	    super(nome);

		final boolean CHECKLOCALIDADES = true;

		if (CHECKLOCALIDADES) {
            Path.validate(percursos, maxPercursos);
		}

		this.percursos = percursos;
		this.nPercursos = maxPercursos;
	}

	/**
	 * Cria uma copia profunda do percurso recebido.
	 *
	 * @param pc
	 *            Percurso a copiar
	 */
	public PercursoComposto(PercursoComposto pc) {
		this(
		        pc.getNome(),
                pc.percursos,
                pc.nPercursos
        );
	}

	/**
	 * Cria uma copia profunda do percurso corrente
	 *
	 * @return O percurso composto que e' uma copia profunda do percurso composto
	 *         corrente
	 */
	public Object clone() {
	    Percurso[] p = new Percurso[this.percursos.length];

	    for (int i = 0 ; i < this.percursos.length; i++) {
	        p[i] = (Percurso) this.percursos[i].clone();
        }

		return new PercursoComposto(
		        super.getNome(),
                p,
                this.nPercursos
        );
	}

    /**
     * Deve adicionar o percurso recebido no in�cio deste percurso composto. N�o
     * pode adicionar se n�o estiver em sequ�ncia, se provocar uma repeti��o de
     * localidades, ou se n�o existir espa�o. Sugest�o: verificar a repeti��o
     * das localidades utilizando os m�todos getLocalidades e haveRepetitions.
     *
     * @param percurso
     *            Percurso a adicionar
     * @return True se adicionou, ou false caso contr�rio
     */
    public boolean adicionarPercursoNoInicio(Percurso percurso) {
        Path[] aux = Path.insert(this.percursos.clone(), this.nPercursos, 0, percurso);

        try {
            Path.validate(aux, this.nPercursos);
        }
        catch(Exception e) {
            return false;
        }

        this.percursos = new Percurso[aux.length];

        for (int i = 0; i < aux.length; i++) {
            this.percursos[i] = (Percurso) aux[i];
        }

        return true;
    }
	/**
	 * Deve adicionar o percurso no final, desde que este esteja em sequencia e
	 * haja espaco e nao provocar uma repeticao de localidades
	 *
	 * @param percurso
	 *            Percurso a adicionar
	 * @return True se adicionou
	 */
	public boolean adicionarPercursoNoFinal(Percurso percurso) {
        Path[] aux = Path.insert(this.percursos.clone(), this.nPercursos, -1, percurso);

        try {
            Path.validate(aux, this.nPercursos);
        }
        catch(Exception e) {
            return false;
        }

        this.percursos = new Percurso[aux.length];

        for (int i = 0; i < aux.length; i++) {
            this.percursos[i] = (Percurso) aux[i];
        }

        return true;
	}

    /**
     * Deve remover todos os percursos desde o ponto de partida na localidade
     * recebida ate ao final. Devolve true se removeu algo.
     */
    public boolean removerPercursosNoFimDesde(String localidade) {
		return this.remove(localidade, false);
    }

	private boolean remove(String local, boolean factor) {
		if (this.percursos.length == 1) {
			return false;
		}

		final Path[] clone = this.percursos.clone();
		final Path[] aux1 = Path.remove(clone, factor, local);

		if (aux1 == null) {
			return false;
		}

		if (aux1.length == this.percursos.length) {
			return false;
		}

		this.percursos = new Percurso[aux1.length];

		for (int i = 0; i < aux1.length; i++) {
			this.percursos[i] = (Percurso) aux1[i];
		}

		return true;
	}
    /**
     * Remove desde o inicio ate ao local recebido. O local deve estar no
     * inicio de um percurso. Devolve true se removeu algum percurso.
     */
    public boolean removerPercursosNoInicioAte(String localidade) {
        return this.remove(localidade, true);
    }

	/**
	 * Metodo que retorna true se ha repeticoes entre os dois arrays.
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
	public String[] getLocalidades() {
	    String locals = this.percursos[0].get_beginning();

	    for (int i = 0; i < this.percursos.length; i++) {
	        String[] collection;

	        if (this.percursos[i] instanceof PercursoComposto) {
                collection = this.percursos[i].getLocalidades();
            }
            else {
	            collection = new String[] { this.percursos[i].get_ending() };
            }

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
	public int getNumLocalidades() {
        return this.getLocalidades().length;
	}

	/**
	 * Devolve o inicio deste percurso composto
	 *
	 * @return O local de inicio do percurso
	 */
	public String get_beginning() {
		return this.percursos[0].get_beginning();
	}

	/**
	 * Devolve o fim deste percurso composto
	 *
	 * @return O local de fim do percurso
	 */
	public String get_ending() {
		int idx = this.percursos.length - 1;
		return this.percursos[idx].get_ending();
	}

	/**
	 * Devolve a distancia do percurso, o somatario das distancias
	 * dos seus percursos
	 *
	 * @return A distancia do percurso
	 */
	public int getDistancia() {
		int d = 0;
		for (Percurso p : this.percursos) {
		    d += p.getDistancia();
        }

        return d;
	}

	/**
	 * Devolve o declive do percurso, o somatario dos declives dos
	 * seus percursos
	 *
	 * @return O declive do percurso
	 */
	public int getDeclive() {
        int d = 0;
        for (Percurso p : this.percursos) {
            d += p.getDeclive();
        }

        return d;
	}

	/**
	 * Devolve o declive do percurso, o somatario dos declives positivos dos
	 * seus percursos
	 *
	 * @return O declive acumulado do percurso considerando os declives
	 *         positivos
	 */
	public int getSubidaAcumulada() {
        int d = 0;
        for (Percurso p : this.percursos) {
            int aux = p.getDeclive();

            if (aux > 0) {
                d += aux;
            }
        }

        return d;
	}

	/**
	 * Imprime na consola o percurso. Depois mostra os
	 * seus percursos, um por linha mas prefixado de 2 espacos.
	 *
	 * @param prefix
	 *            Prefixo a colocar antes da informacao
	 */
	public void print(String prefix) {
		System.out.printf("%s %s\n", prefix, this.toString());

		for (Percurso p : this.percursos) {
		    p.print(String.format("  %s", prefix));
        }
	}

	public String getDescricao() {
		return "composto";
	}

	/**
	 * Devolve o indice do elemento do percurso, directamente dentro do percurso
	 * corrente, que tem a localidade recebida como uma partida
	 */
	private int getIdxLocalidadeEmInicio(String localidade) {
		for (int i = 0; i < this.percursos.length; i++) {
		    if (this.percursos[i].get_beginning().equals(localidade)) {
		        return i;
            }
        }

        return -1;
	}

	/**
	 * Metodos auxiliares para testarem as varias funcionalidades
	 */
	private static String strSeparator = " ---------------------------------------------------------------- ";

	/**
	 * Teste aos contrutores
	 */
	private static void testConstrutores() {
		System.out.println("=====================================================================================");
		System.out.println("Teste aos construtores ==============================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com PercursoSimples");
		PercursoComposto pc1 = new PercursoComposto("PC1", ps1, 20);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com PercursoComposto");
		PercursoComposto pc2 = new PercursoComposto("PC2", new PercursoComposto("PC2", new Percurso[] { ps1, ps2 }, 20),
				20);
		pc2.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao construtor com arrays de PercursoSimple e PercursoComposto");
		PercursoComposto pc3 = new PercursoComposto("PC3",
				new Percurso[] { ps0, ps1, ps2, new PercursoComposto("PCAUX", new Percurso[] { ps3, ps4 }, 20) }, 20);
		pc3.print("> ");
		System.out.println();

		System.out.println();
	}

	/**
	 * Teste ao numLocalidades
	 */
	private static void testNumLocalidades() {
		System.out.println("=====================================================================================");
		System.out.println("Teste aos getNumLocalidades =========================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc1 = new PercursoComposto("PC1", ps1, 20);
		pc1.print("> ");
		System.out.println("n� de localidades -> " + pc1.getNumLocalidades());
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps1, ps2 }, 20);
		pc2.print("> ");
		System.out.println("n� de localidades -> " + pc2.getNumLocalidades());
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc3 = new PercursoComposto("PC3", new Percurso[] { pc1 }, 20);
		pc3.print("> ");
		System.out.println("n� de localidades -> " + pc3.getNumLocalidades());
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc4 = new PercursoComposto("PC4", new Percurso[] { pc3 }, 20);
		pc4.print("> ");
		System.out.println("n� de localidades -> " + pc4.getNumLocalidades());
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc22 = new PercursoComposto("PC22", new Percurso[] { ps4, ps5 }, 20);
		pc22.print("> ");
		System.out.println("n� de localidades -> " + pc22.getNumLocalidades());
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc5 = new PercursoComposto("PC5", new Percurso[] { ps0, pc2, ps3, pc22 }, 20);
		pc5.print("> ");
		System.out.println("n� de localidades -> " + pc5.getNumLocalidades());
		System.out.println();

		System.out.println();
	}

	/**
	 * Teste ao getLocalidades
	 */
	private static void testGetLocalidades() {
		System.out.println("=====================================================================================");
		System.out.println("Teste aos getLocalidades ============================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc1 = new PercursoComposto("PC1", ps1, 20);
		pc1.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc1.getLocalidades()));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps1, ps2 }, 20);
		pc2.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc2.getLocalidades()));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc3 = new PercursoComposto("PC3", new Percurso[] { pc1 }, 20);
		pc3.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc3.getLocalidades()));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc4 = new PercursoComposto("PC4", new Percurso[] { pc3 }, 20);
		pc4.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc4.getLocalidades()));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc22 = new PercursoComposto("PC22", new Percurso[] { ps4, ps5 }, 20);
		pc22.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc22.getLocalidades()));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc5 = new PercursoComposto("PC5", new Percurso[] { ps0, pc2, ps3, pc22 }, 20);
		pc5.print("> ");
		System.out.println("Localidades -> " + Arrays.toString(pc5.getLocalidades()));
		System.out.println();

		System.out.println();
	}

	/**
	 * Teste ao getIdxLocalidadesEmInicio
	 */
	private static void testGetIdxLocalidadeEmInicio() {
		System.out.println("=====================================================================================");
		System.out.println("Teste aos getIdxLocalidadeEmInicio ==================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc1 = new PercursoComposto("PC1", new Percurso[] { ps1, ps2 }, 20);
		pc1.print("> ");
		System.out.println();

		String local = "Faro";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc1.getIdxLocalidadeEmInicio(local));
		System.out.println();

		local = "Lisboa";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc1.getIdxLocalidadeEmInicio(local));
		System.out.println();

		local = "Coimbra";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc1.getIdxLocalidadeEmInicio(local));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps1, ps2 }, 20);
		pc2.print("> ");
		System.out.println();

		local = "Faro";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc2.getIdxLocalidadeEmInicio(local));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc3 = new PercursoComposto("PC3", new Percurso[] { pc1 }, 20);
		pc3.print("> ");
		System.out.println();

		local = "Faro";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc3.getIdxLocalidadeEmInicio(local));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc4 = new PercursoComposto("PC4", new Percurso[] { pc3 }, 20);
		pc4.print("> ");
		System.out.println();

		local = "Faro";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc4.getIdxLocalidadeEmInicio(local));
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		PercursoComposto pc22 = new PercursoComposto("PC22", new Percurso[] { ps4, ps5 }, 20);

		PercursoComposto pc5 = new PercursoComposto("PC5", new Percurso[] { ps0, pc2, ps3, pc22 }, 20);
		pc5.print("> ");
		System.out.println();

		local = "Faro";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc5.getIdxLocalidadeEmInicio(local));
		System.out.println();

		local = "Lisboa";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc5.getIdxLocalidadeEmInicio(local));
		System.out.println();

		local = "Porto";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc5.getIdxLocalidadeEmInicio(local));
		System.out.println();

		local = "Viana do Castelo";
		System.out.println("getIdxLocalidadeEmInicio " + local + " -> " + pc5.getIdxLocalidadeEmInicio(local));
		System.out.println();
	}

	/**
	 * Teste ao adicionarPercursoNoInicio
	 */
	private static void testAdicionarPercursoNoInicio() {
		System.out.println("=====================================================================================");
		System.out.println("Teste ao adicionarPercursoNoInicio ==================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		System.out.println("Original:");
		PercursoComposto pc1 = new PercursoComposto("PC1", new Percurso[] { ps3, ps4, ps5 }, 20);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no in�cio:");
		ps2.print("> ");
		System.out.println();

		System.out.println("Resultado:");
		pc1.adicionarPercursoNoInicio(ps2);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no in�cio:");
		PercursoComposto pc2 = new PercursoComposto("PC1", new Percurso[] { ps0, ps1 }, 20);
		pc2.print("> ");
		System.out.println();

		System.out.println("Resultado:");
		pc1.adicionarPercursoNoInicio(pc2);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar Percurso No Inicio com erro");
		System.out.println("Adicionar no in�cio:");
		pc2.print("> ");
		System.out.println();

		boolean result = pc1.adicionarPercursoNoInicio(pc2);
		System.out.println("A adi��o de " + pc2 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		System.out.println();

	}

	/**
	 * Teste ao adicionarPercursoNofinal
	 */
	private static void testAdicionarPercursoNoFinal() {
		System.out.println("=====================================================================================");
		System.out.println("Teste ao adicionarPercursoNoFinal ===================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		System.out.println("Original:");
		PercursoComposto pc1 = new PercursoComposto("PC1", new Percurso[] { ps0 }, 20);
		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no Final:");
		ps1.print("> ");
		System.out.println();

		boolean result = pc1.adicionarPercursoNoFinal(ps1);
		System.out.println("A adi��o de " + ps1 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Teste ao adicionar Percurso No Final com erro.\nAdicionar:");
		ps3.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoNoFinal(ps3);
		System.out.println("A adi��o de " + ps3 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no Final:");
		PercursoComposto pc2 = new PercursoComposto("PC2",
				new PercursoComposto("PC2Aux", new Percurso[] { ps2, ps3 }, 20), 20);
		pc2.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoNoFinal(pc2);
		System.out.println("A adi��o de " + pc2 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no Final:");
		ps4.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoNoFinal(ps4);
		System.out.println("A adi��o de " + ps4 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no Final:");
		ps5.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoNoFinal(ps5);
		System.out.println("A adi��o de " + ps5 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Adicionar no Final com erro:");
		ps5.print("> ");
		System.out.println();

		result = pc1.adicionarPercursoNoFinal(ps5);
		System.out.println("A adi��o de " + ps5 + " deu -> " + result);
		System.out.println();

		pc1.print("> ");
		System.out.println();

	}

	/**
	 * Teste aos metodos de getDeclive, getDistancia e getSubidaAcumulada. Por
	 * fazer...
	 */
	private static void testeDecliveDistanciaSubidaAcumulada() {
		// TODO Auto-generated method stub
		System.out.println("Not implemented yet...");
		System.out.println();
	}

	/**
	 * Teste ao clone
	 */
	private static void testClone() {
		System.out.println("=====================================================================================");
		System.out.println("Teste aos clone =====================================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// pc2
		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps1, ps2 }, 20);

		// pc22
		PercursoComposto pc22 = new PercursoComposto("PC22", new Percurso[] { ps4, ps5 }, 20);

		// pc5
		System.out.println("Original:");
		PercursoComposto pc5 = new PercursoComposto("PC5", new Percurso[] { ps0, pc2, ps3, pc22 }, 20);
		pc5.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		System.out.println("Clone:");
		PercursoComposto pcClone = (PercursoComposto) (pc5.clone());
		pcClone.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println("Alterar percurso original com adicionar no final de pc22 dentro do original:");
		PercursoSimples psAux0 = new PercursoSimples("ww", "Valenca", "Braga", 87_000, 30);
		psAux0.print("> ");
		System.out.println();

		pc22.adicionarPercursoNoFinal(psAux0);

		System.out.println("PC original alterado:");
		pc5.print("> ");
		System.out.println();

		System.out.println("PC clone (n�o deve estar alterado:");
		pcClone.print("> ");
		System.out.println();

	}

	/**
	 * Teste ao removerPercursoNoFinalDesde
	 */
	private static void testRemoverNoFinalDesde() {
		System.out.println("=====================================================================================");
		System.out.println("Teste ao removerNoFinalDesde ========================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5;

		ps0 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps1 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps2 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps3 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps4 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps5 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// =====================================================================

		PercursoComposto pc1 = new PercursoComposto("PC1", new Percurso[] { ps0, ps1 }, 20);

		PercursoComposto pc11 = new PercursoComposto("PC11", new Percurso[] { pc1 }, 20);

		PercursoComposto pc111 = new PercursoComposto("PC111", new Percurso[] { pc11 }, 20);

		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps2 }, 20);

		PercursoComposto pc45 = new PercursoComposto("PC45", new Percurso[] { ps4, ps5, }, 20);

		PercursoComposto pca = new PercursoComposto("PCA", new Percurso[] { pc111, pc2, ps3, pc45 }, 20);

		// =====================================================================
		System.out.println("Percurso original:");
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		String local = "Porto";
		System.out.println("Com remo��o no final desde -> " + local);
		pca.removerPercursosNoFimDesde(local);
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		local = "Lisboa";
		System.out.println("Com remo��o no final desde -> " + local);
		pca.removerPercursosNoFimDesde(local);
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		local = "Faro";
		System.out.println("Com remo��o no final desde -> " + local);
		pca.removerPercursosNoFimDesde(local);
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		local = "Sagres";
		System.out.println("Com remo��o no final desde -> " + local);
		pca.removerPercursosNoFimDesde(local);
		pca.print("> ");
		System.out.println();
	}

	/**
	 * Teste ao removerPercursoNoInicioAte
	 */
	private static void testRemoverNoInicioAte() {
		System.out.println("=====================================================================================");
		System.out.println("Teste ao removerNoInicioAte =========================================================");
		System.out.println();

		Percurso ps0, ps1, ps2, ps3, ps4, ps5, ps6;

		ps0 = new PercursoSimples("A23", "Aljezur", "Sagres", 42_900, -20);
		ps1 = new PercursoSimples("A23", "Sagres", "Faro", 67_000, -10);
		ps2 = new PercursoSimples("A2", "Faro", "Lisboa", 278_000, 10);
		ps3 = new PercursoSimples("A1 1", "Lisboa", "Coimbra", 204_000, 10);
		ps4 = new PercursoSimples("A1 2", "Coimbra", "Porto", 113_000, 20);
		ps5 = new PercursoSimples("A28", "Porto", "Viana do Castelo", 73_800, 30);
		ps6 = new PercursoSimples("N13", "Viana do Castelo", "Valenca", 51_000, 30);

		// =====================================================================
		PercursoComposto pc1 = new PercursoComposto("PC1", new Percurso[] { ps1, ps2 }, 20);

		PercursoComposto pc11 = new PercursoComposto("PC11", new Percurso[] { pc1 }, 20);

		PercursoComposto pc111 = new PercursoComposto("PC111", new Percurso[] { pc11 }, 20);

		PercursoComposto pc2 = new PercursoComposto("PC2", new Percurso[] { ps3 }, 20);

		PercursoComposto pc45 = new PercursoComposto("PC45", new Percurso[] { ps5, ps6, }, 20);

		PercursoComposto pca = new PercursoComposto("PCA", new Percurso[] { ps0, pc111, pc2, ps4, pc45 }, 20);

		// =====================================================================
		System.out.println("Percurso original:");
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		String local = "Sagres";
		System.out.println("Com remo��o no in�cio at� -> " + local);
		pca.removerPercursosNoInicioAte(local);
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		local = "Faro";
		System.out.println("Com remo��o no in�cio at� -> " + local);
		pca.removerPercursosNoInicioAte(local);
		pca.print("> ");
		System.out.println();

		// =====================================================================
		System.out.println(strSeparator);
		local = "Lisboa";
		System.out.println("Com remo��o no in�cio at� -> " + local);
		pca.removerPercursosNoInicioAte(local);
		pca.print("> ");
		System.out.println();

	}

	/**
	 * Espa�o para mais testes
	 */
	private static void testExtra() {
		System.out.println("=====================================================================================");
		System.out.println("Testes extra ========================================================================");
		System.out.println();

	}

	/**
	 * Main, para testes
	 *
	 * @param args
	 *            Argumentos do main
	 */
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		boolean terminar = false;

		do {
			// mostrar op��es
			System.out.println("# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #");
			System.out.println("a - Testar os contrutores");
			System.out.println("b - Testar getNumLocalidades");
			System.out.println("c - Testar getLocalidades");
			System.out.println("d - Testar Declive Distancia e SubidaAcumulada");
			System.out.println("e - Testar GetIdxLocalidadeEmInicio");
			System.out.println("f - Testar AdicionarPercursoNoInicio");
			System.out.println("g - Testar AdicionarPercursoNoFinal");
			System.out.println("h - Testar Clone");
			System.out.println("i - Testar RemoverNoFinalDesde");
			System.out.println("j - Testar RemoverNoInicioAte");
			System.out.println("k - Testes extra");
			System.out.println("x - Terminar");

			// ler escolha do utilizador
			char car = teclado.nextLine().trim().charAt(0);

			System.out.println();

			// executar pedido
			switch (Character.toLowerCase(car)) {
			case 'a':
				testConstrutores();
				break;
			case 'b':
				testNumLocalidades();
				break;
			case 'c':
				testGetLocalidades();
				break;
			case 'd':
				testeDecliveDistanciaSubidaAcumulada();
				break;
			case 'e':
				testGetIdxLocalidadeEmInicio();
				break;
			case 'f':
				testAdicionarPercursoNoInicio();
				break;
			case 'g':
				testAdicionarPercursoNoFinal();
				break;
			case 'h':
				testClone();
				break;
			case 'i':
				testRemoverNoFinalDesde();
				break;
			case 'j':
				testRemoverNoInicioAte();
				break;
			case 'k':
				testExtra();
				break;
			case 'x':
				terminar = true;
				System.out.println("Bye...");
				break;
			default:
				System.out.println("Op��o inv�lida -> " + car);
			}
		} while (!terminar);

		teclado.close();
	}
}
