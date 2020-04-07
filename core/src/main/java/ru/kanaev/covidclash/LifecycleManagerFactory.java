package ru.kanaev.covidclash;

import ru.kanaev.covidclash.lifecycle.api.LifecycleManager;

public interface LifecycleManagerFactory {

    <T, S, E> LifecycleManager<S, E> newInstance(Class<T> forClass);
}
