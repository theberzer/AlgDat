package main;

import java.awt.Point;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author berzi
 *
 */

public class Springer {
	// Global Variables
	public static int boardSeize;
	public static int row;
	public static int col;
	private static int board[][];
	private static Stack<String> fullPath = new Stack<String>();

	// Ways to move
	private static final Point[] MOVES = new Point[] { new Point(2, 1), new Point(1, 2), new Point(-1, 2),
			new Point(-2, 1), new Point(-2, -1), new Point(-1, -2), new Point(1, -2), new Point(2, -1) };

	public static void main(String[] args) {
		// Inputs
		Scanner in = new Scanner(System.in);
		System.out.printf("Board size: ");
		boardSeize = in.nextInt();
		System.out.printf("Starting row: ");
		row = in.nextInt();
		System.out.printf("Starting column: ");
		col = in.nextInt();
		in.close();

		board();
		board[row][col] = 1;

		if (tour(row, col, 2)) {
			print();
		} else {
			System.out.printf("No Solution!");
		}
	}

	// Helper method for print
	public static void print() {
		for (String s : fullPath) {
			System.out.println(s);
		}

		for (int i = 0; i < boardSeize; i++) {
			for (int j = 0; j < boardSeize; j++) {
				System.out.printf("%-8d", board[i][j]);
			}
			System.out.printf("%n");
		}
	}

	// Creates chessboard
	public static void board() {
		board = new int[boardSeize][boardSeize];

		for (int i = 0; i < boardSeize; i++) {
			for (int j = 0; j < boardSeize; j++) {
				board[i][j] = -1;
			}
		}
	}

	// Creates path
	private static boolean tour(int row, int col, int move) {
		if (move > boardSeize * boardSeize) {
			return true;
		}
		for (Point point : MOVES) {
			int nextR = row + (int) point.x;
			int nextC = col + (int) point.y;

			// Checks if path is safe
			if ((nextR >= 0 && nextR < boardSeize) && (nextC >= 0 && nextC < boardSeize)
					&& (board[nextR][nextC] == -1)) {

				// Marks coordinates as move number
				board[nextR][nextC] = move;

				// adds path entry to stack
				fullPath.push("(" + row + "," + col + ")(" + nextR + "," + nextC + ")\n");

				move++;
				if (tour(nextR, nextC, move)) {
					return true;
				} else {
					// Fail, go back and retry
					board[nextR][nextC] = -1;
					move--;
					// Removes path entry from stack
					fullPath.pop();
				}
			}
		}
		return false;
	}
}
