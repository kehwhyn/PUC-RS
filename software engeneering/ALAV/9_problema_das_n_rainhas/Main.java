public class Main {
    final int NUM_QUEENS = 8;

    public static void main(String[] args) {
        Main Queen = new Main();
        Queen.solveNQ(8);
    }

    boolean solveNQ(int tamanho) {
        int[][] board = new int[tamanho][tamanho];

        if (solveNQUtil(board, 0) == false) {
            System.out.print("Solution does not exist");
            return false;
        }
        printSolution(board);
        return true;
    }

    boolean solveNQUtil(int board[][], int col) {
        if (col >= NUM_QUEENS)
            return true;

        for (int i = 0; i < NUM_QUEENS; i++) {

            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQUtil(board, col + 1) == true)
                    return true;

                board[i][col] = 0;
            }
        }
        return false;
    }

    boolean isSafe(int board[][], int row, int col) {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < NUM_QUEENS; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    void printSolution(int board[][]) {
        for (int i = 0; i < NUM_QUEENS; i++) {
            for (int j = 0; j < NUM_QUEENS; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }
}