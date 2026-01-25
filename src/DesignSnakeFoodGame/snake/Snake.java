package DesignSnakeFoodGame.snake;

import lombok.Data;

@Data
public class Snake {
    public Direction direction;
    public SnakeCell head;
    public SnakeCell tail;
    int size;

    public Snake(int x, int y, Direction direction) {
        head = new SnakeCell(x, y);
        tail = head;
        size = 1;
        this.direction = direction;
    }

    public void moveHead() {
        SnakeCell cell = new SnakeCell(head.x + direction.x, head.y + direction.y);
        head.setNext(cell);
        head = cell;
        size++;
    }

    public void moveTail() {
        if (tail.getNext() != null) {
            tail = tail.getNext();
            size--;
        }
    }
}
