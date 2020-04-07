package ru.kanaev.covidclash.entity.obstacle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.kanaev.covidclash.GameUtil;
import ru.kanaev.covidclash.CovidClash;
import ru.kanaev.covidclash.entity.Acceleratable;
import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.ObjectTag;
import ru.kanaev.covidclash.entity.Outsider;
import ru.kanaev.covidclash.entity.Size2D;
import ru.kanaev.covidclash.lifecycle.api.LifecycleManager;
import ru.kanaev.covidclash.lifecycle.api.StatefulObject;
import lombok.Getter;
import lombok.Setter;

import static ru.kanaev.covidclash.Config.DEFAULT_OBSTACLE_VELOCITY;
import static ru.kanaev.covidclash.entity.ObjectTag.OBSTACLE;
import static ru.kanaev.covidclash.entity.obstacle.Event.CREATE;
import static ru.kanaev.covidclash.entity.obstacle.Event.EXPLODE;
import static ru.kanaev.covidclash.entity.obstacle.Event.FLY_AWAY;
import static ru.kanaev.covidclash.entity.obstacle.Event.PASS;
import static ru.kanaev.covidclash.entity.obstacle.Event.UPDATE;
import static ru.kanaev.covidclash.entity.obstacle.State.EXPLODED;
import static ru.kanaev.covidclash.entity.obstacle.State.INIT;
import static ru.kanaev.covidclash.entity.obstacle.State.OUTSIDER;
import static ru.kanaev.covidclash.entity.obstacle.State.PASSED;
import static ru.kanaev.covidclash.entity.obstacle.lifecycle.Constants.DELTA;
import static java.util.Collections.singletonMap;

@Getter
@Setter
public abstract class AbstractObstacle implements
        GameObject,
        StatefulObject<State>,
        Acceleratable,
        Outsider,
        Size2D {

    protected final CovidClash gameContext;

    protected LifecycleManager<State, Event> lifecycleManager;
    protected float velocity;
    protected Sprite sprite;
    protected float stateTime;
    protected float lifeTime;
    protected State state;
    protected Vector2 position;
    protected float width;
    protected float height;

    public AbstractObstacle(CovidClash gameContext) {
        this.gameContext = gameContext;
        this.lifecycleManager = gameContext.lifecycleManagerFactory.newInstance(this.getClass());
        velocity = DEFAULT_OBSTACLE_VELOCITY;
        stateTime = 0;
        lifeTime = 0;
        sprite = new Sprite(getTexture());
        position = new Vector2();
        state = INIT;
    }

    @Override
    public void create() {
        lifecycleManager.execute(this, CREATE);
    }

    @Override
    public void update(float delta) {
        lifecycleManager.execute(this, UPDATE, singletonMap(DELTA, delta));
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
        return OBSTACLE;
    }

    @Override
    public boolean isOutsider() {
        return OUTSIDER.equals(state);
    }

    @Override
    public void markOutsider() {
        lifecycleManager.execute(this, FLY_AWAY);
    }

    @Override
    public void accelerate(float delta) {
        this.velocity += delta;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    @Override
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        sprite.setPosition(position.x, position.y);
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        sprite.setSize(width, height);
    }

    public boolean isPassed() {
        return PASSED.equals(state);
    }

    public void pass() {
        lifecycleManager.execute(this, PASS);
    }

    public void explode() {
        lifecycleManager.execute(this, EXPLODE);
    }

    public boolean isExploded() {
        return EXPLODED.equals(state);
    }

    public abstract int getScore();

    public abstract TextureRegion getTexture();
}
