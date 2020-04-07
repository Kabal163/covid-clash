package ru.kanaev.covidclash.entity.item.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.GameUtil;
import ru.kanaev.covidclash.CovidClash;
import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.ObjectTag;
import ru.kanaev.covidclash.entity.Outsider;
import ru.kanaev.covidclash.entity.Size2D;
import ru.kanaev.covidclash.lifecycle.api.LifecycleManager;
import ru.kanaev.covidclash.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import static ru.kanaev.covidclash.Assets.BULLET;
import static ru.kanaev.covidclash.Config.DEFAULT_BULLET_VELOCITY;
import static ru.kanaev.covidclash.entity.item.bullet.Event.CREATE;
import static ru.kanaev.covidclash.entity.item.bullet.Event.FLY_AWAY;
import static ru.kanaev.covidclash.entity.item.bullet.Event.HIT;
import static ru.kanaev.covidclash.entity.item.bullet.Event.UPDATE;
import static ru.kanaev.covidclash.entity.item.bullet.State.INIT;
import static ru.kanaev.covidclash.entity.item.bullet.State.OUTSIDER;
import static ru.kanaev.covidclash.entity.item.bullet.lifecycle.Constants.DELTA;
import static java.util.Collections.singletonMap;

@Setter
@Getter
public class Bullet implements GameObject, StatefulObject<State>, Outsider, Size2D {

    public final CovidClash gameContext;

    public static TextureRegion texture = new TextureRegion(new Texture(BULLET));
    protected final LifecycleManager<State, Event> lifecycleManager;
    protected float velocity;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected Sprite sprite;
    protected float width;
    protected float height;
    protected Vector2 position;

    private State state;

    public Bullet(CovidClash gameContext) {
        this.gameContext = gameContext;
        lifecycleManager = gameContext.lifecycleManagerFactory.newInstance(this.getClass());
        sprite = new Sprite(texture);
        stateTime = 0;
        velocity = DEFAULT_BULLET_VELOCITY;
        position = new Vector2();
        state = INIT;
    }

    @Override
    public void create() {
        lifecycleManager.execute(this, CREATE);
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        lifecycleManager.execute(this, UPDATE, singletonMap(DELTA, delta));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(stateTime), sprite.getX(), sprite.getY());
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
        return ObjectTag.BULLET;
    }

    @Override
    public boolean isOutsider() {
        return OUTSIDER.equals(state);
    }

    @Override
    public void markOutsider() {
        lifecycleManager.execute(this, FLY_AWAY);
    }

    /**
     * Bullet has successfully hit an enemy spaceship
     */
    public void hit() {
        lifecycleManager.execute(this, HIT);
    }

    /**
     * Bullet has gone outside of the screen view
     */
    public void dismiss() {
        lifecycleManager.execute(this, FLY_AWAY);
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        sprite.setSize(width, height);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    @Override
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        sprite.setPosition(x, y);
    }
}
