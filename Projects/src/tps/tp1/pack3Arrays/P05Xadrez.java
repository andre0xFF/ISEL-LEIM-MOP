package tps.tp1.pack3Arrays;

import java.util.Random;
import java.util.Scanner;

public class P05Xadrez {

    private static final int SIZE = 8;
    private static final int TOTAL_QUEENS = SIZE * 3 / 4;
    private static final int EMPTY_SPACE = 0;
    private static final int QUEEN = 1;
    private static final char[] SYMBOLS = { ' ', 'Q' };

    private static int[][] board;
    private static int queens = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            start();
            print_solution(board);
        } while(scanner.nextLine().equals(""));
    }

    private static void print_solution(int[][] board) {
        System.out.printf("Board:\n\n");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("| %c ", SYMBOLS[board[i][j]]);
            }

            System.out.printf("|\n");
        }

        System.out.printf("\n");
    }

    private static boolean is_safe(int[][] board, int row, int column) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != QUEEN) {
                    continue;
                }

                if (i == row || j == column || Math.abs(j - column) == Math.abs(i - row)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void start() {
        board =  new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            // top
            board[0][i] = EMPTY_SPACE;
            // bottom
            board[SIZE - 1][i] = EMPTY_SPACE;
            // left
            board[i][0] = EMPTY_SPACE;
            // right
            board[i][SIZE - 1] = EMPTY_SPACE;
        }

        solve(board);
    }

    private static void solve(int[][] board) {
        for (int j = 0; j < TOTAL_QUEENS; j++) {
            int line;
            int column;

            do {
                line = new Random().nextInt(SIZE);
                column = new Random().nextInt(SIZE);

                if (board[line][column] != EMPTY_SPACE) {
                    continue;
                }

            } while(!is_safe(board, line, column));

            board[line][column] = QUEEN;
        }
    }
}
