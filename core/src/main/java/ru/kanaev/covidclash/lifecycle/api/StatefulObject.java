package ru.kanaev.covidclash.lifecycle.api;

public interface StatefulObject<S> {

    S getState();

    void setState(S state);
}
