package ru.kanaev.covidclash.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import ru.kanaev.covidclash.entity.obstacle.Event;
import ru.kanaev.covidclash.entity.obstacle.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static ru.kanaev.covidclash.entity.obstacle.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Float delta = context.getVariable(DELTA, Float.class);
        Vector2 pos = obstacle.getPosition();

        float x = pos.x + obstacle.getVelocity() * clamp(delta, delta, 1/30f);
        obstacle.setPosition(x, pos.y);
    }
}
