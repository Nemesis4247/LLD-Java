package DesignSnakeFoodGame;

import DesignSnakeFoodGame.foodSpawnStrategy.RandomSingleFoodSpawnStrategy;
import DesignSnakeFoodGame.render.ConsoleRenderer;
import DesignSnakeFoodGame.snake.Direction;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class SnakeFoodGame {
    SnakeGameManager snakeGameManager;
    ExecutorService service;
    boolean isRunning;
    Map<Character, Direction> directionMap;

    public SnakeFoodGame(int height, int width) {
        snakeGameManager = new SnakeGameManager(height, width, new RandomSingleFoodSpawnStrategy(), new ConsoleRenderer());
        service = Executors.newSingleThreadExecutor();
        isRunning = true;
        directionMap = Map.of('w', Direction.UP, 'a', Direction.LEFT, 's', Direction.DOWN, 'd', Direction.RIGHT);
    }

    public void start() throws InterruptedException {
        Scanner in = new Scanner(System.in);
//        service.submit(() -> {
//            while (isRunning)
//                input.set(in.nextInt());
//        });
        while (isRunning) {
            snakeGameManager.render();
            char input = in.next(Pattern.compile("[wasd]")).toCharArray()[0];
            snakeGameManager.move(directionMap.get(input));
            if (!snakeGameManager.isSessionLive()) {
                System.out.println("Game Over !! Final Score : " + snakeGameManager.getScore());
                isRunning = false;
                service.shutdownNow();
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SnakeFoodGame game = new SnakeFoodGame(10, 10);
        game.start();
    }
}
