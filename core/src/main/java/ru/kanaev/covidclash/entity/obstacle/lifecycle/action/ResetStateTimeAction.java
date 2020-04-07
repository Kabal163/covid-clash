package ru.kanaev.covidclash.entity.obstacle.lifecycle.action;

import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import ru.kanaev.covidclash.entity.obstacle.Event;
import ru.kanaev.covidclash.entity.obstacle.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

public class ResetStateTimeAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        obstacle.setStateTime(0);
    }
}
