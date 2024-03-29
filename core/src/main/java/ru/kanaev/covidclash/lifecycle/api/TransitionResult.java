package ru.kanaev.covidclash.lifecycle.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransitionResult<S, E> {

    private final boolean success;
    private final StateContext<S, E> stateContext;
    private final S sourceState;
    private final S targetState;
}
