package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface GameObject {

    void create();

    void update(float delta);

    void draw(SpriteBatch batch);

    boolean isCollided(GameObject anotherObject);

    Rectangle getCollider();

    ObjectTag getTag();
}
