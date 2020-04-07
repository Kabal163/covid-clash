package ru.kanaev.covidclash.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import ru.kanaev.covidclash.GameScreen;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.item.bullet.Event;
import ru.kanaev.covidclash.entity.item.bullet.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

public class SetStartPositionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        Screen screen = bullet.gameContext.getScreen();
        GameScreen gameScreen;

        if (screen instanceof GameScreen) {
            gameScreen = (GameScreen) screen;
        } else {
            throw new IllegalStateException();
        }

        Rectangle player = gameScreen.getPlayer().getCollider();

        bullet.setPosition(
                player.getX() + player.getWidth() / 2,
                player.getY() + player.getHeight() / 3);
    }
}
