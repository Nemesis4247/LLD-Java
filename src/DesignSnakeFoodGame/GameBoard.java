package DesignSnakeFoodGame;

import DesignSnakeFoodGame.foodSpawnStrategy.FoodSpawnStrategy;
import DesignSnakeFoodGame.render.GameRenderer;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    int height;
    int width;
    char[][] board; // ' '->empty, 'x'->snake, 'o'->food

    public GameBoard(int h, int w) {
        height = h;
        width = w;
        board = new char[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean isWithin(int x, int y) {
        return x>=0 && x<height && y>=0 && y<width;
    }

    public void putSnakeCell(int x, int y) {
        if (isWithin(x, y))
            board[x][y] = 'x';

    }

    public void removeSnakeCell(int x, int y) {
        if (isWithin(x, y))
            board[x][y] = ' ';

    }

    public boolean isFood(int x, int y) {
        if (isWithin(x, y))
            return board[x][y] == 'o';
        return false;
    }

    public boolean isBlocked(int x, int y) {
        return !isWithin(x, y) || board[x][y] == 'x';
    }

    public void spawnFood(FoodSpawnStrategy spawnStrategy) {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == ' ')
                    emptyCells.add(new int[]{i, j});
            }
        }
        List<int[]> foodCells = spawnStrategy.spawnFood(emptyCells);
        for (int[] foodCell : foodCells) {
            board[foodCell[0]][foodCell[1]] = 'o';
        }
    }

    public void render(GameRenderer renderer) {
        renderer.render(board);
    }
}
