import java.util.Scanner;

public class X0 {
    private static char[][] board = new char[3][3];
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Jugador 1, ingrese su nombre (será X): ");
        String player1 = scanner.nextLine();
        System.out.println("Jugador 2, ingrese su nombre (será O): ");
        String player2 = scanner.nextLine();

        currentPlayer = 'X';
        initializeBoard();
        boolean gameWon = false;

        while (!gameWon && !isBoardFull()) {
            printBoard();
            System.out.println("Turno de " + (currentPlayer == 'X' ? player1 : player2) + " (" + currentPlayer + ")");
            int[] move = getMove(scanner);
            if (isValidMove(move)) {
                makeMove(move);
                gameWon = checkWin();
                if (gameWon) {
                    printBoard();
                    System.out.println("¡Felicidades " + (currentPlayer == 'X' ? player1 : player2) + "! Has ganado.");
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Movimiento no válido, por favor intenta de nuevo.");
            }
        }

        if (!gameWon) {
            printBoard();
            System.out.println("¡Es un empate!");
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] getMove(Scanner scanner) {
        int[] move = new int[2];
        System.out.print("Ingresa la fila (1-3): ");
        move[0] = scanner.nextInt() - 1;
        System.out.print("Ingresa la columna (1-3): ");
        move[1] = scanner.nextInt() - 1;
        return move;
    }

    private static boolean isValidMove(int[] move) {
        return move[0] >= 0 && move[0] < 3 && move[1] >= 0 && move[1] < 3 && board[move[0]][move[1]] == '-';
    }

    private static void makeMove(int[] move) {
        board[move[0]][move[1]] = currentPlayer;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
