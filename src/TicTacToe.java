import java.util.Scanner;

public class TicTacToe {
    private final char[][] board = new char[3][3];
    private boolean player1Turn = true;
    private boolean gameOver = false;
    private static final int[][] WINNING_POSITIONS = {
            {0,0, 0,1, 0,2}, {1,0, 1,1, 1,2}, {2,0, 2,1, 2,2},
            {0,0, 1,0, 2,0}, {0,1, 1,1, 2,1}, {0,2, 1,2, 2,2},
            {0,0, 1,1, 2,2}, {0,2, 1,1, 2,0}
    };

    public TicTacToe() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to TicTacToe!");

        while (!gameOver) {
            printBoard();
            System.out.println("Player " + getPlayerSymbolCol() + getPlayerSymbol() + GameColor.RESET.get() +
                               ", enter your move (row and column: 1 2 for row 1 column 2):");
            int row, col;
            while (true) {
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");
                if (parts.length == 2) {
                    try {
                        row = Integer.parseInt(parts[0]) - 1;
                        col = Integer.parseInt(parts[1]) - 1;
                        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                            break;
                        }
                    } catch (NumberFormatException e) {}
                }
                System.out.println("Invalid move. Try again (row and column: 1 2):");
            }

            board[row][col] = getPlayerSymbol();
            if (checkWin()) {
                printBoard();
                System.out.println(GameColor.WIN.get() + "Player " + getPlayerSymbol() + " wins!" + GameColor.RESET.get());
                gameOver = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println(GameColor.DRAW.get() + "It's a draw!" + GameColor.RESET.get());
                gameOver = true;
            } else {
                player1Turn = !player1Turn;
            }
        }
        scanner.close();
    }

    private char getPlayerSymbol() {
        return player1Turn ? 'X' : 'O';
    }

    private String getPlayerSymbolCol() {
        return player1Turn ? GameColor.X.get() : GameColor.O.get();
    }

    private void printBoard() {
        System.out.println("   1   2   3 ");
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1));
            for (int j = 0; j < 3; j++) {
                String color = " ";
                if (board[i][j] == 'X') {
                	color = GameColor.X.get() + "X" + GameColor.RESET.get();
                }
                else if (board[i][j] == 'O') {
                	color = GameColor.O.get() + "O" + GameColor.RESET.get();
                }
                else color = " ";
                System.out.print(" " + color + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---+---+---");
        }
    }

    private boolean checkWin() {
        char sym = getPlayerSymbol();
        for (int[] pos : WINNING_POSITIONS) {
            if (board[pos[0]][pos[1]] == sym &&
                board[pos[2]][pos[3]] == sym &&
                board[pos[4]][pos[5]] == sym) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false;
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}