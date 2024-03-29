package ru.kanaev.covidclash.lifecycle.api;

import java.util.HashMap;
import java.util.Map;

public class StateContext<S, E> {

    private final StatefulObject<S> statefulObject;
    private final E event;

    private Map<String, Object> variables = new HashMap<>();

    public StateContext(StatefulObject<S> statefulObject,
                        E event,
                        Map<String, Object> variables) {
        this.statefulObject = statefulObject;
        this.event = event;
        this.variables = new HashMap<>(variables);
    }

    public void putVariable(String key, Object value) {
        variables.put(key, value);
    }

    public void putIfAbsentVariable(String key, Object value) {
        variables.putIfAbsent(key, value);
    }

    @SuppressWarnings("unchecked")
    public  <T> T getVariable(String key, Class<T> type) {
        Object value = this.variables.get(key);
        if (value == null) {
            return null;
        }
        if (!type.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException("Incorrect type specified for variable '" +
                    key + "'. Expected [" + type + "] but actual type is [" + value.getClass() + "]");
        }
        return (T) value;
    }

    @SuppressWarnings("unchecked")
    public <T> T getStatefulObject() {
        return (T) statefulObject;
    }

    public E getEvent() {
        return event;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = new HashMap<>(variables);
    }
}
