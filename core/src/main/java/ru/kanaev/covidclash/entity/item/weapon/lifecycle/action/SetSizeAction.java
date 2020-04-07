package ru.kanaev.covidclash.entity.item.weapon.lifecycle.action;

import ru.kanaev.covidclash.entity.item.weapon.AbstractWeapon;
import ru.kanaev.covidclash.entity.item.weapon.Event;
import ru.kanaev.covidclash.entity.item.weapon.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.Config.WEAPON_HEIGHT;
import static ru.kanaev.covidclash.Config.WEAPON_WIDTH;

public class SetSizeAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractWeapon weapon = context.getStatefulObject();
        weapon.setSize(WEAPON_WIDTH, WEAPON_HEIGHT);
    }
}
