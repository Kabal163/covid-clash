package ru.kanaev.covidclash.entity.item;

import ru.kanaev.covidclash.entity.Expirable;
import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.Outsider;
import ru.kanaev.covidclash.entity.Size2D;

public interface PickUpItem extends GameObject, Expirable, Outsider, Size2D {

    void use();

    void pickUp();

    void drop();

    boolean isPickedUp();
}
