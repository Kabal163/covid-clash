package ru.kanaev.covidclash;

import ru.kanaev.covidclash.entity.Player;
import ru.kanaev.covidclash.entity.item.PickUpItem;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;

import java.util.List;

import static ru.kanaev.covidclash.GameUtil.isCollided;

public class OverlapManagerImpl implements OverlapManager {

    private GameScreen gameScreen;

    public OverlapManagerImpl(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        checkObstacleOverlapping();
        checkWeaponOverlapping();
        checkBulletOverlapping();
    }

    private void checkObstacleOverlapping() {
        Player player = gameScreen.getPlayer();
        List<AbstractObstacle> obstacles = gameScreen.getObstacles();

        for (AbstractObstacle obstacle : obstacles) {
            if (isCollided(obstacle, player)) {
                gameScreen.setFailure(true);
            }
        }
    }

    private void checkWeaponOverlapping() {
        Player player = gameScreen.getPlayer();
        List<PickUpItem> items = gameScreen.getItems();

        items.stream()
                .filter(i -> !i.isPickedUp())
                .filter(i -> isCollided(player, i))
                .findFirst()
                .ifPresent(player::setItem);
    }

    private void checkBulletOverlapping() {
        List<AbstractObstacle> obstacles = gameScreen.getObstacles();
        List<Bullet> bullets = gameScreen.getBullets();

        for (Bullet bullet : bullets) {
            for (AbstractObstacle obstacle : obstacles) {
                if (isCollided(obstacle, bullet)) {
                    bullet.hit();
                    obstacle.explode();
                }
            }
        }
    }
}
