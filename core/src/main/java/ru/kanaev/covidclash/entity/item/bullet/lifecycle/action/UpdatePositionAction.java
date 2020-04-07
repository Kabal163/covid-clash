package ru.kanaev.covidclash.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.item.bullet.Event;
import ru.kanaev.covidclash.entity.item.bullet.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.entity.item.bullet.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        Float delta = context.getVariable(DELTA, Float.class);
        Vector2 pos = bullet.getPosition();

        bullet.setPosition(
                pos.x + bullet.getVelocity() * delta,
                pos.y);
    }
}
