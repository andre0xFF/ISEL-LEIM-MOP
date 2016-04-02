package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

public class P05Xadrez {
	public static final int BOARD_SIZE = 6;
	public static final int TOTAL_PIECES = 4;
	public static final char KNIGHT = 'C';
	public static final char TOWER = 'T';
	public static final char BISHOP = 'B';
	public static final char _OK = 'o';
	public static final char _1K = '-';
	public static final char _K = '+';
	public static final char LINE = '-';
	public static final char COLUMN = '|';
	public static final char EMPTY_SPACE = ' ';
	public static final char[] PIECES = new char[] { BISHOP, TOWER, KNIGHT };

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
	
	public static char[] pushToArray(char[] array, char push) {
		char[] auxArray = new char[array.length + 1];
		
		for (int i = 0; i < array.length; i++) {
			auxArray[i] = array[i];
		}
		
		auxArray[auxArray.length - 1] = push;
		
		return auxArray;
	}
	
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
	
	public static boolean towerStrikes(int[] tower, int[] piece) {
		return tower[0] == piece[0] || tower[1] == piece[1];
	}
	
	public static boolean knightStrikes(int[] knight, int[] piece) {
		return (
			Math.abs(knight[1] - piece[1]) == 2 && Math.abs(knight[0] - piece[0]) == 1 ||
			Math.abs(knight[1] - piece[1]) == 1 && Math.abs(knight[0] - piece[0]) == 2
		);
	}
	
	public static boolean bishopStrikes(int[] bishop, int[] piece) {
		return (
				bishop[0] != piece[0] &&
				bishop[1] != piece[1] &&
				Math.abs((double)(bishop[1] - piece[1])/(bishop[0] - piece[0])) == 1
		);
	}
	
	public static boolean knightVStower(int[] knight, int[] tower) {
		return knightStrikes(knight, tower) || towerStrikes(tower, knight);
	}
	
	public static boolean towerVSbishop(int[] tower, int[] bishop) {
		return towerStrikes(tower, bishop) || bishopStrikes(bishop, tower);
	}
	
	public static boolean knightVSbishop(int[] knight, int[] bishop) {
		return knightStrikes(knight, bishop) || bishopStrikes(bishop, knight);
	}
	
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	public static char[][] initializeBoard(int size) {
		char[][] board = new char[size][size];
		
		for (int l = 0; l < size; l++) {
			for (int c = 0; c < size; c++) {
				board[l][c] = _OK;
			}
		}
		
		return board;
	}
	
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
