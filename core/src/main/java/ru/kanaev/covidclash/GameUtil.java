package ru.kanaev.covidclash;

import com.badlogic.gdx.math.Rectangle;
import ru.kanaev.covidclash.entity.GameObject;

public class GameUtil {

    public static boolean isCollided(GameObject o1, GameObject o2) {
        Rectangle c1 = o1.getCollider();
        Rectangle c2 = o2.getCollider();

        return c1.getX() < c2.getX() + c2.width / 2 &&
                c1.getX() + c1.getWidth() / 2 > c2.x &&
                c1.getY() < c2.y + c2.height / 2 &&
                c1.getY() + c1.getHeight() / 2 > c2.y;
    }
}
