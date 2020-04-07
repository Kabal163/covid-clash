package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.math.Vector2;

public interface Coordinate2D {

    Vector2 getPosition();

    void setPosition(float x, float y);
}
