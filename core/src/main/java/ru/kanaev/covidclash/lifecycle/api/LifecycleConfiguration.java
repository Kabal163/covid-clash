package ru.kanaev.covidclash.lifecycle.api;


import ru.kanaev.covidclash.lifecycle.TransitionConfigurer;

public interface LifecycleConfiguration<S extends Enum<S>, E extends Enum<E>> {

    void configureTransitions(TransitionConfigurer<S, E> configurer);
}
