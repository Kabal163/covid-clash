package ru.kanaev.covidclash;

import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.Player;
import ru.kanaev.covidclash.entity.item.PickUpItem;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;

public interface EntityCreationManager {

    GameObject createBackground();

    Player createPlayer();

    AbstractObstacle createObstacle();

    PickUpItem createLaser();

    Bullet createBullet();
}
