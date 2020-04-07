package ru.kanaev.covidclash.entity.item.bullet.lifecycle.action;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.item.bullet.Event;
import ru.kanaev.covidclash.entity.item.bullet.State;
import ru.kanaev.covidclash.lifecycle.api.Action;
import ru.kanaev.covidclash.lifecycle.api.StateContext;

import static ru.kanaev.covidclash.Config.BULLET_HEIGHT;
import static ru.kanaev.covidclash.Config.BULLET_WIDTH;
import static ru.kanaev.covidclash.Config.DEFAULT_FRAME_LENGTH;

public class CreateAnimationAction implements Action<State, Event> {

    @Override
    public void execute(StateContext<State, Event> context) {
        Bullet bullet = context.getStatefulObject();
        bullet.setAnimation(
                new Animation<>(
                        DEFAULT_FRAME_LENGTH,
                        TextureRegion.split(
                                Bullet.texture.getTexture(),
                                BULLET_WIDTH,
                                BULLET_HEIGHT)[0]));
    }
}
