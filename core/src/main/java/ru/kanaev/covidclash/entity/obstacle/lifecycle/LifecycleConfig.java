package ru.kanaev.covidclash.entity.obstacle.lifecycle;

import ru.kanaev.covidclash.entity.obstacle.Event;
import ru.kanaev.covidclash.entity.obstacle.State;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.CreateExplosionAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.RecountScoreAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.ResetStateTimeAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.SetSizeAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.SetStartPositionAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.UpdatePositionAction;
import ru.kanaev.covidclash.entity.obstacle.lifecycle.action.UpdateStateTimeAction;
import ru.kanaev.covidclash.lifecycle.TransitionConfigurer;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.LifecycleConfiguration;

import static ru.kanaev.covidclash.entity.obstacle.Event.CREATE;
import static ru.kanaev.covidclash.entity.obstacle.Event.EXPLODE;
import static ru.kanaev.covidclash.entity.obstacle.Event.FLY_AWAY;
import static ru.kanaev.covidclash.entity.obstacle.Event.PASS;
import static ru.kanaev.covidclash.entity.obstacle.Event.UPDATE;
import static ru.kanaev.covidclash.entity.obstacle.State.EXPLODED;
import static ru.kanaev.covidclash.entity.obstacle.State.INIT;
import static ru.kanaev.covidclash.entity.obstacle.State.MOVING;
import static ru.kanaev.covidclash.entity.obstacle.State.OUTSIDER;
import static ru.kanaev.covidclash.entity.obstacle.State.PASSED;

public class LifecycleConfig implements LifecycleConfiguration<State, Event> {

    private final Action<State, Event> setSizeAction;
    private final Action<State, Event> setStartPositionAction;
    private final Action<State, Event> updatePositionAction;
    private final Action<State, Event> resetStateTime;
    private final Action<State, Event> recountScoreAction;
    private final Action<State, Event> updateStateTime;
    private final Action<State, Event> createExplosionAction;

    public LifecycleConfig() {
        setSizeAction = new SetSizeAction();
        setStartPositionAction = new SetStartPositionAction();
        updatePositionAction = new UpdatePositionAction();
        resetStateTime = new ResetStateTimeAction();
        recountScoreAction = new RecountScoreAction();
        updateStateTime = new UpdateStateTimeAction();
        createExplosionAction = new CreateExplosionAction();
    }

    @Override
    public void configureTransitions(TransitionConfigurer<State, Event> configurer) {
        configurer
                .with()
                .sourceState(INIT)
                .targetState(MOVING)
                .event(CREATE)
                .action(setSizeAction)
                .action(setStartPositionAction)

                .with()
                .sourceState(MOVING)
                .targetState(PASSED)
                .event(PASS)
                .action(resetStateTime)
                .action(recountScoreAction)

                .with()
                .sourceState(MOVING)
                .targetState(EXPLODED)
                .event(EXPLODE)
                .action(resetStateTime)
                .action(recountScoreAction)
                .action(createExplosionAction)

                .with()
                .sourceState(MOVING)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)
                .action(resetStateTime)

                .with()
                .sourceState(MOVING)
                .targetState(MOVING)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateStateTime)

                .with()
                .sourceState(PASSED)
                .targetState(PASSED)
                .event(UPDATE)
                .action(updatePositionAction)
                .action(updateStateTime)

                .with()
                .sourceState(PASSED)
                .targetState(OUTSIDER)
                .event(FLY_AWAY)

                // All bullets will be removed from the screen on the next game loop iteration
                .with()
                .sourceState(OUTSIDER)
                .targetState(OUTSIDER)
                .event(UPDATE)

                .with()
                .sourceState(EXPLODED)
                .targetState(EXPLODED)
                .event(UPDATE);
    }
}
