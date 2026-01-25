package DesignSnakeFoodGame.foodSpawnStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSingleFoodSpawnStrategy implements FoodSpawnStrategy {

    Random random;

    public RandomSingleFoodSpawnStrategy() {
        random = new Random();
    }

    @Override
    public List<int[]> spawnFood(List<int[]> emptyCells) {
        int index = random.nextInt(0, emptyCells.size());
        return Collections.singletonList(emptyCells.get(index));
    }
}
