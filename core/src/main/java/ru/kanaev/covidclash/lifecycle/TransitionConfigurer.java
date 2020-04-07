package ru.kanaev.covidclash.lifecycle;

import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.Condition;

public interface TransitionConfigurer<S, E> {

    TransitionConfigurer<S, E> with();

    TransitionConfigurer<S, E> sourceState(S state);

    TransitionConfigurer<S, E> targetState(S state);

    TransitionConfigurer<S, E> event(E event);

    TransitionConfigurer<S, E> condition(Condition<S, E> condition);

    TransitionConfigurer<S, E> action(Action<S, E> action);
}
