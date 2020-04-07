package ru.kanaev.covidclash.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.entity.item.weapon.AbstractWeapon;
import ru.kanaev.covidclash.entity.item.weapon.Event;
import ru.kanaev.covidclash.entity.item.weapon.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.entity.item.weapon.lifecycle.Constants.DELTA;

public class UpdatePositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        Float delta = context.getVariable(DELTA, Float.class);
        Vector2 pos = weapon.getPosition();

        weapon.setPosition(
                pos.x + weapon.getVelocity() * delta,
                pos.y);
    }
}
