package ru.kanaev.covidclash.lifecycle.exception;

public class TransitionNotFoundException extends TransitionException {

    public TransitionNotFoundException() {
    }

    public TransitionNotFoundException(String message) {
        super(message);
    }
}
