package DesignSnakeFoodGame.foodSpawnStrategy;

import java.util.List;

public interface FoodSpawnStrategy {
    public List<int[]> spawnFood(List<int[]> emptyCells);
}
