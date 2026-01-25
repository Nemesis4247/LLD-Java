package DesignSnakeFoodGame.render;

public class ConsoleRenderer implements GameRenderer {
    @Override
    public void render(char[][] board) {
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i <= height+1; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
        for (int i = 0; i < height; i++) {
            for (int j = -1; j <= width; j++) {
                if (j == -1 || j== width)
                    System.out.print("|");
                else
                    System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        for (int i = 0; i <= height+1; i++) {
            System.out.print("_");
        }
    }
}
