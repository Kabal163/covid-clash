package ru.kanaev.covidclash.entity;

public interface Size2D extends Coordinate2D {

    float getWidth();

    float getHeight();

    void setSize(float width, float height);
}
