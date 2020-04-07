package ru.kanaev.covidclash.entity.obstacle.lifecycle.action;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.GameScreen;
import ru.kanaev.covidclash.entity.Explosion;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import ru.kanaev.covidclash.entity.obstacle.Event;
import ru.kanaev.covidclash.entity.obstacle.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

public class CreateExplosionAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        AbstractObstacle obstacle = context.getStatefulObject();
        Vector2 pos = obstacle.getPosition();
        Explosion explosion = new Explosion(pos.x, pos.y);

        Screen screen = obstacle.getGameContext().getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            gameScreen.getObstacleExplosions().add(explosion);
        }
    }
}
