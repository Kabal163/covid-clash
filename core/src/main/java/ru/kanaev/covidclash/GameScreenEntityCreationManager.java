package ru.kanaev.covidclash;

import ru.kanaev.covidclash.entity.Background;
import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.Player;
import ru.kanaev.covidclash.entity.item.PickUpItem;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.item.weapon.Laser;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import ru.kanaev.covidclash.entity.obstacle.FastObstacle;
import ru.kanaev.covidclash.entity.obstacle.SimpleObstacle;

public class GameScreenEntityCreationManager implements EntityCreationManager {

    private final GameScreen gameScreen;

    public GameScreenEntityCreationManager(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public GameObject createBackground() {
        Background background = new Background();
        gameScreen.setBackground(background);

        return background;
    }

    @Override
    public Player createPlayer() {
        Player player = new Player(gameScreen.game);
        gameScreen.setPlayer(player);

        return player;
    }

    @Override
    public AbstractObstacle createObstacle() {
        gameScreen.setObstacleCreationTimeElapsed(0);
        AbstractObstacle obstacle;
        if ((int) gameScreen.getFastObstacleCreationTimeElapsed() > 7) {
            obstacle = new FastObstacle(gameScreen.game);
            gameScreen.setFastObstacleCreationTimeElapsed(0);
        } else {
            obstacle = new SimpleObstacle(gameScreen.game);
        }
        obstacle.create();

        if (gameScreen.getLastAccelerationTimeElapsed() > 5) {
            gameScreen.setLastAccelerationTimeElapsed(0);
            gameScreen.setAccelerationCount(gameScreen.getAccelerationCount() + 1);
        }
        obstacle.accelerate(-10 * gameScreen.getAccelerationCount());
        gameScreen.getObstacles().add(obstacle);

        return obstacle;
    }

    @Override
    public PickUpItem createLaser() {
        Laser laser = new Laser(gameScreen.game);
        laser.create();
        gameScreen.getItems().add(laser);

        return laser;
    }

    @Override
    public Bullet createBullet() {
        Bullet bullet = new Bullet(gameScreen.game);
        bullet.create();
        gameScreen.getBullets().add(bullet);

        return bullet;
    }
}
