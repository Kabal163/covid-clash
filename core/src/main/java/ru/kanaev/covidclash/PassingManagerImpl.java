package ru.kanaev.covidclash;

import ru.kanaev.covidclash.entity.Player;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;

import java.util.List;

public class PassingManagerImpl implements PassingManager {

    private GameScreen gameScreen;

    public PassingManagerImpl(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        checkObstacleHasBeenPassed();
    }

    private void checkObstacleHasBeenPassed() {
        Player player = gameScreen.getPlayer();
        List<AbstractObstacle> obstacles = gameScreen.getObstacles();

        obstacles.stream()
                .filter(o -> !o.isPassed())
                .filter(o -> o.getPosition().x + o.getWidth() < player.getX())
                .forEach(AbstractObstacle::pass);
    }
}
