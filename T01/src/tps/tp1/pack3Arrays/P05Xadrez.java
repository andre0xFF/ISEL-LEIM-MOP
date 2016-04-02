package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

/**
 * A classe P05Xadrez corresponde ao 5o exercicio do capitulo de Arrays da serie de
 * exercicios. Esta classe um tabuleiro de xadrez composto por diversas pecas
 * (Bispo, Cavalo e Torre) em que e' simulado e representado todos os movimentos
 * possiveis de cada peca sem que haja ataques possiveis entre as pecas presentes
 * no tabuleiro.
 * <p>
 * Alem do pedido no enunciado, a simulacao permite gerar um certo numero e tipo de
 * pecas dinamicamente atraves das constantes definidas inicialmente.
 * <p>
 * 
 * @author Andre Fonseca
 */
public class P05Xadrez {
	/**
	 * Tamanho do tabuleiro
	 */
	public static final int BOARD_SIZE = 6;
	/**
	 * Numero total de pecas presentes
	 */
	public static final int TOTAL_PIECES = 4;
	/**
	 * Simbolo representativo da peca Cavalo
	 */
	public static final char KNIGHT = 'C';
	/**
	 * Simbolo representativo da peca Torre
	 */
	public static final char TOWER = 'T';
	/**
	 * Simbolo representativo da peca Bispo
	 */
	public static final char BISHOP = 'B';
	/**
	 * Simbolo representativo de nao existir qualquer ataque possivel
	 */
	public static final char _OK = 'o';
	/**
	 * Simbolo representativo de existir pelo menos um ataque possivel
	 */
	public static final char _1K = '-';
	/**
	 * Simbolo representativo de existir um ou mais ataques possiveis
	 */
	public static final char _K = '+';
	public static final char LINE = '-';
	public static final char COLUMN = '|';
	public static final char EMPTY_SPACE = ' ';
	/**
	 * Pecas possiveis a serem geradas aleatoriamente
	 */
	public static final char[] PIECES = new char[] { BISHOP, TOWER, KNIGHT };

	/**
	 * A simulacao de xadrez inicia-se com todas as posicoes livres de qualquer ataque e qualquer peca.
	 * De seguida sao geradas um numero total de pecas sem que haja qualquer ataque entre elas. Esta
	 * condicao e' feita recorrendo a simulacao de ataques entre as pecas e todas as posicoes do tabuleiro,
	 * caso esta condicao seja verdade e' entao gerada outra peca noutras coordenadas aleatoriamente
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			char[][] board = initializeBoard(BOARD_SIZE);
			
			for (int i = 0; i < TOTAL_PIECES; i++) {
				char pieceType;
				int[] pieceCoords;
				int[][] pieceAttacks = new int[0][0];
				boolean possibleAttack;
				
				do {
					// generate a new piece, with random coordinates
					pieceType = PIECES[randomNumber(0, PIECES.length - 1)];
					pieceCoords = new int[] { randomNumber(0, BOARD_SIZE - 1), randomNumber(0, BOARD_SIZE - 1) };
					
					// check if piece can be attacked by other piece / meaning: if the piece coords is not _OK
					possibleAttack = board[pieceCoords[0]][pieceCoords[1]] != _OK;
					
					if(possibleAttack) {
						continue;
					}
					
					// get piece possible attack coords
					pieceAttacks = getAttacks(board, pieceType, pieceCoords);
					
					// check if piece can attack another piece
					for (int j = 0; j < pieceAttacks.length && !possibleAttack; j++) {						
						for (int k = 0; k < PIECES.length && !possibleAttack; k++) {
							possibleAttack = board[pieceAttacks[j][0]][pieceAttacks[j][1]] == PIECES[k];
						}
					}
					
				} while(possibleAttack);
				
				// add piece to board
				board[pieceCoords[0]][pieceCoords[1]] = pieceType;
				
				// paint piece attacks on board
				for (int j = 0; j < pieceAttacks.length; j++) {
					char currentChar = board[pieceAttacks[j][0]][pieceAttacks[j][1]];
					board[pieceAttacks[j][0]][pieceAttacks[j][1]] = currentChar == _OK ? _1K : currentChar == _1K ? _K : _K;
				}
			}
			
			printBoard(board);
			while(scanner.nextLine().length() != 0);			
		}

	}
	
	/**
	 * Devolve as coordenadas de todos os ataques possiveis de uma determinada peca.
	 * Para determinar todos os ataques possiveis de uma determinada peca e'
	 * percorrido todas as posicoes do tabuleiro com recorrendo a dois ciclo
	 * encadeados em que em cada posicao do tabuleiro e' testado um ataque da peca.
	 * Caso seja possivel a peca realizar um ataque na determinada posicao entao
	 * essa posicao e' guardada num array multidimensional que sera' posteriormente
	 * devolvido.
	 * 
	 * @param board
	 * 				Tabuleiro das pecas
	 * @param pieceType
	 * 				Tipo de peca
	 * @param pieceCoords
	 * 				Coordenadas da peca
	 * @return
	 * 				Array multidimensional com todos os possiveis ataques de uma peca
	 */
	public static int[][] getAttacks(char[][] board, char pieceType, int[] pieceCoords) {
		int[][] attacksList = new int[0][0];
		
		for (int l = 0; l < board.length; l++) {
			for (int c = 0; c < board.length; c++) {				
				int[] currentCoords = new int[] {l, c};
				boolean attack = false;
				
				if(pieceCoords[0] == currentCoords[0] && pieceCoords[1] == currentCoords[1]) {
					continue;
				}
				
				switch(pieceType) {
				case KNIGHT:
					attack = knightStrikes(pieceCoords, currentCoords);
					break;
				case TOWER:
					attack = towerStrikes(pieceCoords, currentCoords);
					break;
				case BISHOP:
					attack = bishopStrikes(pieceCoords, currentCoords);
					break;
				}
				
				if(attack) {
					attacksList = pushToArray(attacksList, currentCoords);
				}
			}
		}
		
		return attacksList;
	}
	
	/**
	 * Adiciona um certo caracter na ultima posicao de um array de caracters.
	 * O novo array e' construido com uma dimensao superior ao recebido
	 * de forma a alocar o novo caracter na ultima posicao.
	 * 
	 * @param array
	 * 				Array de caracters
	 * @param push
	 * 				Caracter a adicionar
	 * @return
	 * 				Novo array de numeros com o caracter adicionado
	 */
	public static char[] pushToArray(char[] array, char push) {
		char[] auxArray = new char[array.length + 1];
		
		for (int i = 0; i < array.length; i++) {
			auxArray[i] = array[i];
		}
		
		auxArray[auxArray.length - 1] = push;
		
		return auxArray;
	}
	
	/**
	 * Adiciona um certo array de numero na ultima posicao de um array de multidimensional.
	 * O novo array e' construido com uma dimensao superior ao recebido
	 * de forma a alocar o novo array de numeros na ultima posicao.
	 * 
	 * @param array
	 * 				Array multidimensional de numeros
	 * @param push
	 * 				Array de numeros
	 * @return
	 * 				Novo array multidimensional de numeros com o array de numeros adicionado
	 */
	public static int[][] pushToArray(int[][] array, int[] push) {
		int[][] auxArray = new int[array.length + 1][push.length];
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < push.length; j++) {
				auxArray[i][j] = array[i][j];
			}
		}
		
		auxArray[auxArray.length - 1] = push;
		
		return auxArray;
	}
	
	/**
	 * Verifica a possibilidade de a Torre realizar um ataque a uma determinada
	 * peca ou posicao.
	 * 
	 * @param tower
	 * 				Coordenadas da torre
	 * @param piece
	 * 				Coordenadas da peca ou posicao a ser verificada
	 * @return
	 * 				True se exitir a possibilidade de ataque
	 */
	public static boolean towerStrikes(int[] tower, int[] piece) {
		return tower[0] == piece[0] || tower[1] == piece[1];
	}
	
	/**
	 * Verifica a possibilidade de o Cavalo realizar um ataque a uma determinada
	 * peca ou posicao.
	 * 
	 * @param knight
	 * 				Coordenadas do cavalo
	 * @param piece
	 * 				Coordenadas da peca ou posicao a ser verificada
	 * @return
	 * 				True caso seja possivel realizar ataque
	 */
	public static boolean knightStrikes(int[] knight, int[] piece) {
		return (
			Math.abs(knight[1] - piece[1]) == 2 && Math.abs(knight[0] - piece[0]) == 1 ||
			Math.abs(knight[1] - piece[1]) == 1 && Math.abs(knight[0] - piece[0]) == 2
		);
	}
	
	/**
	 * Verifica a possibilidade de o Bispo realizar um ataque a uma determinada
	 * peca ou posicao.
	 * 
	 * @param bishop
	 * 			Coordenadas do bispo
	 * @param piece
	 * 			Coordenadas da peca ou posicao a ser verificada
	 * @return
	 * 			True caso seja possivel realizar ataque
	 */
	public static boolean bishopStrikes(int[] bishop, int[] piece) {
		return (
				bishop[0] != piece[0] &&
				bishop[1] != piece[1] &&
				Math.abs((double)(bishop[1] - piece[1])/(bishop[0] - piece[0])) == 1
		);
	}
	
	/**
	 * Verifica se exista um ataque mutuo ou unico entre duas peca
	 * 
	 * @param knight
	 * 				Coordenadas do cavalo
	 * @param tower
	 * 				Coordenadas da torre
	 * @return
	 * 				True se exitir a possibilidade de algum ataque
	 */
	public static boolean knightVStower(int[] knight, int[] tower) {
		return knightStrikes(knight, tower) || towerStrikes(tower, knight);
	}
	
	/**
	 * Verifica se exista um ataque mutuo ou unico entre duas peca
	 * 
	 * @param tower
	 * 				Coordenadas da torre
	 * @param bishop
	 * 				Coordenadas do bispo
	 * @return
	 * 				True se exitir a possibilidade de algum ataque
	 */
	public static boolean towerVSbishop(int[] tower, int[] bishop) {
		return towerStrikes(tower, bishop) || bishopStrikes(bishop, tower);
	}
	
	/**
	 * Verifica se exista um ataque mutuo ou unico entre duas peca
	 * 
	 * @param knight
	 * 				Coordenadas do cavalo
	 * @param bishop
	 * 				Coordenadas do bispo
	 * @return
	 * 				True se exitir a possibilidade de algum ataque
	 */
	public static boolean knightVSbishop(int[] knight, int[] bishop) {
		return knightStrikes(knight, bishop) || bishopStrikes(bishop, knight);
	}
	
	/**
	 * Gera um numero aleatorio com base num intervalo de numero
	 * minimo e maximo.
	 * 
	 * @param min
	 * 				Numero minimo que pode ser gerado.
	 * @param max
	 * 				Numero maximo que pode ser gerado.
	 * @return
	 * 				Numero aleatorio.
	 */
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	/**
	 * Cria e inicializa com valores de nenhum ataque o tabuleiro
	 * 
	 * @param size
	 * 				Tamanho do tabuleiro a ser criado
	 * @return
	 * 				Tabuleiro inicializado
	 */
	public static char[][] initializeBoard(int size) {
		char[][] board = new char[size][size];
		
		for (int l = 0; l < size; l++) {
			for (int c = 0; c < size; c++) {
				board[l][c] = _OK;
			}
		}
		
		return board;
	}
	
	/**
	 * Escreve na consola o estado actual do tabuleiro
	 * 
	 * @param board
	 * 				Tabuleiro de xadrez
	 */
	public static void printBoard(char[][] board) {		
		// print header
		System.out.printf("%c", LINE);
		for (int c = 0; c < board.length; c++) {			
			System.out.printf("%c%c%c%c", LINE, LINE, LINE, LINE);
		}
		System.out.printf("\n");
		
		// print board
		for (int l = 0; l < board.length; l++) {
			System.out.printf("%c", COLUMN);
			
			for (int c = 0; c < board.length; c++) {
				// swap x = c; y = l;
				System.out.printf("%c%c%c%c", EMPTY_SPACE, board[c][l], EMPTY_SPACE, COLUMN);
			}
			System.out.printf("\n");
		}
		
		// print footer
		System.out.printf("%c", LINE);
		for (int c = 0; c < board.length; c++) {			
			System.out.printf("%c%c%c%c", LINE, LINE, LINE, LINE);
		}
		System.out.printf("\n");
	}

}
