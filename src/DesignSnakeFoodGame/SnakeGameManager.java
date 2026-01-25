package DesignSnakeFoodGame;

import DesignSnakeFoodGame.foodSpawnStrategy.FoodSpawnStrategy;
import DesignSnakeFoodGame.render.GameRenderer;
import DesignSnakeFoodGame.snake.Direction;
import DesignSnakeFoodGame.snake.Snake;
import lombok.Getter;

@Getter
public class SnakeGameManager {
    GameBoard board;
    Snake snake;
    FoodSpawnStrategy spawnStrategy;
    GameRenderer gameRenderer;
    int score;
    boolean isSessionLive;

    public SnakeGameManager(int height, int width, FoodSpawnStrategy foodSpawnStrategy, GameRenderer gameRenderer) {
       board = new GameBoard(height, width);
       snake = new Snake(height / 2, width / 2, Direction.RIGHT);
       board.putSnakeCell(height / 2, width / 2);
       score = 0;
       isSessionLive = true;
       this.spawnStrategy = foodSpawnStrategy;
       this.gameRenderer = gameRenderer;
       spawnFood();
    }

    public void move() {
        if (isSessionLive) {
            snake.moveHead();
            if (board.isBlocked(snake.head.x, snake.head.y)) {
                isSessionLive = false;
                return;
            }
            if (board.isFood(snake.head.x, snake.head.y)) {
                score++;
                spawnFood();
            } else {
                board.removeSnakeCell(snake.getTail().getX(), snake.getTail().getY());
                snake.moveTail();
            }
            board.putSnakeCell(snake.head.getX(), snake.getHead().getY());
        }
    }

    public void move(Direction direction) {
        if (isSessionLive) {
            snake.setDirection(direction);
            move();
        }
    }

    public void render() {
        board.render(gameRenderer);
    }

    public void spawnFood() {
        board.spawnFood(this.spawnStrategy);
    }
}
