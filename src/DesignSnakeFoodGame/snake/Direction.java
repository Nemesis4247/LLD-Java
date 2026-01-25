package DesignSnakeFoodGame.snake;

import lombok.Getter;

public enum Direction {
    UP(-1,0, 0),
    DOWN(1,0, 1),
    LEFT(0,-1, 2),
    RIGHT(0,1, 3);
    public int x;
    public int y;
    @Getter
    public int index;

    Direction(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
}
