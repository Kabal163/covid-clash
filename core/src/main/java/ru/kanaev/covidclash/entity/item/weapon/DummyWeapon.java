package ru.kanaev.covidclash.entity.item.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.kanaev.covidclash.CovidClash;

import static ru.kanaev.covidclash.Assets.LASER;
import static ru.kanaev.covidclash.entity.item.weapon.State.NOT_PICKED_UP;
import static ru.kanaev.covidclash.entity.item.weapon.State.PICKED_UP;

/**
 * This is simple dummy weapon, so we don't use lifecycle manager for that
 */
public class DummyWeapon extends AbstractWeapon {

    private static TextureRegion texture = new TextureRegion(new Texture(LASER));

    public DummyWeapon(CovidClash gameContext) {
        super(gameContext);
    }

    @Override
    public void create() {
        sprite = new Sprite(texture);
        sprite.setPosition(0, 0);
        sprite.setSize(0, 0);
    }

    @Override
    public void update(float delta) {}

    /**
     * Dummy weapon is not drawn
     */
    @Override
    public void draw(SpriteBatch batch) {}

    @Override
    public void pickUp() {
        state = PICKED_UP;
    }


    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void drop() {
        state = NOT_PICKED_UP;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    /**
     * Dummy weapon cannot fire
     */
    @Override
    public void use() {}
}
