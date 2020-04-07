package ru.kanaev.covidclash.entity.item.weapon.lifecycle.action;

import com.badlogic.gdx.math.MathUtils;
import ru.kanaev.covidclash.entity.item.weapon.AbstractWeapon;
import ru.kanaev.covidclash.entity.item.weapon.Event;
import ru.kanaev.covidclash.entity.item.weapon.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.Config.VIEW_HEIGHT;
import static ru.kanaev.covidclash.Config.VIEW_WIDTH;

public class SetStartPositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();

        float posY = MathUtils.random(0, VIEW_HEIGHT - weapon.getHeight());
        weapon.setPosition(VIEW_WIDTH + 1, posY);
    }
}
