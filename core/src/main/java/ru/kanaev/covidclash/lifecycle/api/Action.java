package ru.kanaev.covidclash.lifecycle.api;

public interface Action<S, E> {

    void execute(StateContext<S, E> context);
}
