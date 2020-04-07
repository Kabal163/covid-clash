package ru.kanaev.covidclash.entity.item.weapon.lifecycle;

import ru.kanaev.covidclash.entity.item.weapon.Event;
import ru.kanaev.covidclash.entity.item.weapon.State;
import ru.kanaev.covidclash.entity.item.weapon.lifecycle.action.SetSizeAction;
import ru.kanaev.covidclash.entity.item.weapon.lifecycle.action.SetStartPositionAction;
import ru.kanaev.covidclash.entity.item.weapon.lifecycle.action.UpdatePositionAction;
import ru.kanaev.covidclash.entity.item.weapon.lifecycle.action.UpdateTimeElapsedAction;
import ru.kanaev.covidclash.lifecycle.TransitionConfigurer;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.LifecycleConfiguration;

import static ru.kanaev.covidclash.entity.item.weapon.Event.CREATE;
import static ru.kanaev.covidclash.entity.item.weapon.Event.DROP;
import static ru.kanaev.covidclash.entity.item.weapon.Event.EXPIRE;
import static ru.kanaev.covidclash.entity.item.weapon.Event.FLY_AWAY;
import static ru.kanaev.covidclash.entity.item.weapon.Event.PICK_UP;
import static ru.kanaev.covidclash.entity.item.weapon.Event.UPDATE;
import static ru.kanaev.covidclash.entity.item.weapon.State.EXPIRED;
import static ru.kanaev.covidclash.entity.item.weapon.State.INIT;
import static ru.kanaev.covidclash.entity.item.weapon.State.NOT_PICKED_UP;
import static ru.kanaev.covidclash.entity.item.weapon.State.OUTSIDER;
import static ru.kanaev.covidclash.entity.item.weapon.State.PICKED_UP;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private Action<State, Event> setSizeAction;
    private Action<State, Event> setStartPositionAction;
    private Action<State, Event> updatePositionAction;
    private Action<State, Event> updateTimeElapsedAction;

    public LifecycleConfig() {
        setSizeAction = new SetSizeAction();
        setStartPositionAction = new SetStartPositionAction();
        updatePositionAction = new UpdatePositionAction();
        updateTimeElapsedAction = new UpdateTimeElapsedAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(NOT_PICKED_UP)
                .event(CREATE)
                .action(setSizeAction)
                .action(setStartPositionAction)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(NOT_PICKED_UP)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateTimeElapsedAction)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(EXPIRED)
                .event(EXPIRE)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(PICKED_UP)
                .event(PICK_UP)

                .with()
                .sourceState(PICKED_UP)
                .targetState(PICKED_UP)
                .event(UPDATE)
                .action(updateTimeElapsedAction)

                .with()
                .sourceState(PICKED_UP)
                .targetState(EXPIRED)
                .event(EXPIRE)

                .with()
                .sourceState(NOT_PICKED_UP)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)

                .with()
                .sourceState(PICKED_UP)
                .targetState(NOT_PICKED_UP)
                .event(DROP)

                // All bullets will be removed from the screen on the next game loop iteration
                .with()
                .sourceState(EXPIRED)
                .targetState(EXPIRED)
                .event(UPDATE)

                .with()
                .sourceState(OUTSIDER)
                .targetState(OUTSIDER)
                .event(UPDATE);
    }
}
