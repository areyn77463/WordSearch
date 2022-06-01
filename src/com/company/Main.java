package com.company;

import java.util.Random;
import java.util.Arrays;

public class Main {

    /**
     *
     * @return
     */
    public static char gen_letter() {
        Random rand = new Random();
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int rand_int = rand.nextInt(26);
        return alphabet[rand_int];
    }

    /**
     *
     * @return
     */
    public static char[][] create_board() {
        char[][] temp = new char[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                // temp[i][j] = gen_letter();
                temp[i][j] = ' ';
            }
        }
        return temp;
    }

    public static char[][] fill_space(char[][] board) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (board[i][j] == ' ') {
                    board[i][j] = gen_letter();
                }
            }
        }
        return board;
    }

    /**
     *
     * @param board
     */
    public static void print_board(char[][] board) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     *
     * @param dimension
     * @param row
     * @param col
     * @param word
     * @return
     */
    public static Integer[] possible_direction(Integer[] dimension, int row, int col, int word) {
        Integer[] dir = {1, 1, 1, 1};
        if ( row - word < 0 ) {
            dir[0] = 0;
        }
        if ( col + word > dimension[1] ) {
            dir[1] = 0;
        }
        if ( row + word > dimension[0] ) {
            dir[2] = 0;
        }
        if ( col - word < 0 ) {
            dir[3] = 0;
        }
        return dir;
    }

    /**
     *
     * @param possible
     * @return
     */
    public static Integer get_direction(Integer[] possible) {
        Random rand = new Random();
        int ret = rand.nextInt(4);
        while ( possible[ret] != 1) {
            ret = rand.nextInt(4);
        }
        return ret;
    }

    /**
     *
     * @param board
     * @param row
     * @param col
     * @param direction
     * @param word
     * @return
     */
    public static char[][] place_word(char[][] board, int row, int col, int direction, String word) {
        int i = row;
        int j = col;
        if ( direction == 0 ) {
            for ( int count = 0; count < word.length(); count++ ) {
                board[i][j] = word.charAt(count);
                i--;
            }
        }
        if ( direction == 1 ) {
            for ( int count = 0; count < word.length(); count++ ) {
                board[i][j] = word.charAt(count);
                j++;
            }
        }
        if ( direction == 2 ) {
            for ( int count = 0; count < word.length(); count++ ) {
                board[i][j] = word.charAt(count);
                i++;
            }
        }
        if ( direction == 3 ) {
            for ( int count = 0; count < word.length(); count++ ) {
                board[i][j] = word.charAt(count);
                j--;
            }
        }

        return board;
    }

    public static Integer[] check_position(char[][] board, int row, int col, Integer[] direction, String word) {
        if ( direction[0] == 1) {
            int i = row;
            int j = col;  
            for ( int count = 0; count < word.length(); count++ ) {
                if (board[i][j] != ' ' && board[i][j] != word.charAt(count)) {
                    direction[0] = 0;
                }
                i--;
            }
        }
        if ( direction[1] == 1) {
            int i = row;
            int j = col;
            for ( int count = 0; count < word.length(); count++ ) {
                if (board[i][j] != ' ' && board[i][j] != word.charAt(count)) {
                    direction[1] = 0;
                }
                j++;
            }
        }
        if ( direction[2] == 1) {
            int i = row;
            int j = col;
            for ( int count = 0; count < word.length(); count++ ) {
                if (board[i][j] != ' ' && board[i][j] != word.charAt(count)) {
                    direction[2] = 0;
                }
                i++;
            }
        }
        if ( direction[3] == 1) {
            int i = row;
            int j = col;
            for ( int count = 0; count < word.length(); count++ ) {
                if (board[i][j] != ' ' && board[i][j] != word.charAt(count)) {
                    direction[3] = 0;
                }
                j--;
            }
        }
        return direction;
    }

    /**
     *
     * @param board
     * @return
     */
    public static char[][] add_word(char[][] board, String word) {
        Random rand = new Random();

        // String word = "dynamo";
        int sizeOf = word.length();
        Integer[] dimension = {board.length, board[0].length};
        int row = 0;
        int col = 0;
        Integer[] direction = {0, 0, 0, 0};
        while ( !Arrays.asList(direction).contains(1) ) {
            row = rand.nextInt(10);
            col = rand.nextInt(10);
            direction = possible_direction(dimension, row, col, sizeOf);
            direction = check_position(board, row, col, direction, word);

        }

        int chosen = get_direction(direction);
        board = place_word(board, row, col, chosen, word);

        System.out.print(row + " " + col + "\n");
        return board;
    }

    public static void main(String[] args) {
	    char[][] board = create_board();
	    board = add_word(board, "dynamo");
	    board = add_word(board, "court");
        board = add_word(board, "fisher");
        board = add_word(board, "button");
        board = add_word(board, "hundred");
        board = add_word(board, "hassle");
	    board = add_word(board, "torn");
        board = add_word(board, "play");
        board = add_word(board, "software");
        board = add_word(board, "value");
        board = fill_space(board);
        
	    print_board(board);
    }
}
