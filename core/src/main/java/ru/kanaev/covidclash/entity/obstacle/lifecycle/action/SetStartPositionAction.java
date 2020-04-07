package ru.kanaev.covidclash.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.math.MathUtils;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import ru.kanaev.covidclash.entity.obstacle.Event;
import ru.kanaev.covidclash.entity.obstacle.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.Config.VIEW_HEIGHT;
import static ru.kanaev.covidclash.Config.VIEW_WIDTH;

public class SetStartPositionAction implements Action<State, Event> {

    private static final float MIN_Y_POS = 0;
    private static final float START_X_POS = VIEW_WIDTH + 1;

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();

        float startYPos = MathUtils.random(MIN_Y_POS, VIEW_HEIGHT - obstacle.getHeight());
        obstacle.setPosition(START_X_POS, startYPos);
    }
}