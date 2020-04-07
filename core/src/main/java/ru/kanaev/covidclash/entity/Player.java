package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.kanaev.covidclash.GameUtil;
import ru.kanaev.covidclash.CovidClash;
import ru.kanaev.covidclash.entity.item.PickUpItem;
import ru.kanaev.covidclash.entity.item.weapon.DummyWeapon;

import static ru.kanaev.covidclash.Assets.SPINNER;
import static ru.kanaev.covidclash.Config.PLAYER_HEIGHT;
import static ru.kanaev.covidclash.Config.PLAYER_INIT_X_POS;
import static ru.kanaev.covidclash.Config.PLAYER_INIT_Y_POS;
import static ru.kanaev.covidclash.Config.PLAYER_ROTATION_DEGREE;
import static ru.kanaev.covidclash.Config.PLAYER_WIDTH;
import static ru.kanaev.covidclash.Config.VIEW_HEIGHT;
import static ru.kanaev.covidclash.entity.ObjectTag.PLAYER;

/**
 * Not thread safe
 */
public class Player implements GameObject {

    private final CovidClash gameContext;
    private float velocity;
    private final Sprite sprite;
    private static TextureRegion texture;
    private PickUpItem item;

    public Player(CovidClash gameContext) {
        if (texture == null) {
            texture = new TextureRegion(new Texture(SPINNER));
        }

        this.gameContext = gameContext;
        this.velocity = 0;
        this.sprite = new Sprite(texture);
        this.item = new DummyWeapon(gameContext);
        setSize();
        setOrigin();
        setPosition();
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {
        sprite.setY(sprite.getY() + velocity * delta);
        sprite.rotate(PLAYER_ROTATION_DEGREE * delta);

        if (sprite.getY() <= 0) {
            sprite.setY(0);
        }

        if (sprite.getY() >= VIEW_HEIGHT - sprite.getHeight()) {
            sprite.setY(VIEW_HEIGHT - sprite.getHeight());
        }

        if (item.isExpired()) {
            item = new DummyWeapon(gameContext);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public boolean isCollided(GameObject anotherObject) {
        return GameUtil.isCollided(this, anotherObject);
    }

    @Override
    public Rectangle getCollider() {
        return sprite.getBoundingRectangle();
    }

    @Override
    public ObjectTag getTag() {
        return PLAYER;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void setItem(PickUpItem item) {
        this.item.drop();
        this.item = item;
        item.pickUp();
    }

    public PickUpItem getItem() {
        return item;
    }

    public void useItem() {
        item.use();
    }

    private void setPosition() {
        sprite.setPosition(PLAYER_INIT_X_POS, PLAYER_INIT_Y_POS);
    }

    private void setSize() {
        sprite.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void setOrigin() {
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }
}
