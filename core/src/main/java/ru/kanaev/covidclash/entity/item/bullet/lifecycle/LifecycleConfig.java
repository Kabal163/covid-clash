package ru.kanaev.covidclash.entity.item.bullet.lifecycle;

import ru.kanaev.covidclash.entity.item.bullet.Event;
import ru.kanaev.covidclash.entity.item.bullet.State;
import ru.kanaev.covidclash.entity.item.bullet.lifecycle.action.CreateAnimationAction;
import ru.kanaev.covidclash.entity.item.bullet.lifecycle.action.SetStartPositionAction;
import ru.kanaev.covidclash.entity.item.bullet.lifecycle.action.UpdatePositionAction;
import ru.kanaev.covidclash.lifecycle.TransitionConfigurer;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.LifecycleConfiguration;

import static ru.kanaev.covidclash.entity.item.bullet.Event.CREATE;
import static ru.kanaev.covidclash.entity.item.bullet.Event.FLY_AWAY;
import static ru.kanaev.covidclash.entity.item.bullet.Event.HIT;
import static ru.kanaev.covidclash.entity.item.bullet.Event.UPDATE;
import static ru.kanaev.covidclash.entity.item.bullet.State.INIT;
import static ru.kanaev.covidclash.entity.item.bullet.State.LANDED;
import static ru.kanaev.covidclash.entity.item.bullet.State.MOVING;
import static ru.kanaev.covidclash.entity.item.bullet.State.OUTSIDER;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private Action<State, Event> setStartPositionAction;
    private Action<State, Event> createAnimationAction;
    private Action<State, Event> updatePosition;

    public LifecycleConfig() {
        setStartPositionAction = new SetStartPositionAction();
        createAnimationAction = new CreateAnimationAction();
        updatePosition = new UpdatePositionAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(MOVING)
                .event(CREATE)
                .action(setStartPositionAction)
                .action(createAnimationAction)

                .with()
                .sourceState(MOVING)
                .targetState(MOVING)
                .event(UPDATE)
                .action(updatePosition)

                .with()
                .sourceState(MOVING)
                .targetState(LANDED)
                .event(HIT)

                .with()
                .sourceState(MOVING)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)

                // All bullets will be removed from the screen on the next game loop iteration
                .with()
                .sourceState(LANDED)
                .targetState(LANDED)
                .event(UPDATE)

                .with()
                .sourceState(OUTSIDER)
                .targetState(OUTSIDER)
                .event(UPDATE);
    }
}
