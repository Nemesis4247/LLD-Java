package DesignSnakeFoodGame.snake;

import lombok.Data;

@Data
public class SnakeCell {
    public int x;
    public int y;
    SnakeCell next;

    public SnakeCell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
