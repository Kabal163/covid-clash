package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.kanaev.covidclash.Assets.GAME_OVER;

public class GameOverLabel implements GameObject {

    private static TextureRegion texture;

    private final Sprite sprite;

    public GameOverLabel() {
        if (texture == null) {
            texture = new TextureRegion(new Texture(GAME_OVER));
        }

        sprite = new Sprite(texture);
        sprite.setPosition(100, 240);
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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
}
