package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.kanaev.covidclash.Assets.EXPLOSION;
import static ru.kanaev.covidclash.Config.EXPLOSION_FRAME_LENGTH;
import static ru.kanaev.covidclash.Config.EXPLOSION_HEIGHT;
import static ru.kanaev.covidclash.Config.EXPLOSION_WIDTH;

public class Explosion implements GameObject {

    private static Animation<TextureRegion> animation;

    private float stateTime;
    private boolean ended;

    private float x;
    private float y;

    public Explosion(float x, float y) {

        this.x = x;
        this.y = y;

        if (animation == null) {
            TextureRegion[][] frames2d = TextureRegion.split(new Texture(EXPLOSION), EXPLOSION_WIDTH, EXPLOSION_HEIGHT);
            List<TextureRegion> frames = new ArrayList<>();

            for (TextureRegion[] textureRegions : frames2d) {
                frames.addAll(Arrays.asList(textureRegions));
            }

            animation = new Animation(EXPLOSION_FRAME_LENGTH, frames.toArray());
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        if (animation.isAnimationFinished(stateTime)) {
            ended = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(stateTime), x, y);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return false;
    }

    @Override
    public Rectangle getCollider() {
        return null;
    }

    @Override
    public ObjectTag getTag() {
        return null;
    }

    public boolean isEnded() {
        return ended;
    }
}
