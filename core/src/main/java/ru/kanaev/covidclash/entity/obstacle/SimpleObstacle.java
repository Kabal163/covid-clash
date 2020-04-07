package ru.kanaev.covidclash.entity.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.kanaev.covidclash.CovidClash;

import static ru.kanaev.covidclash.Assets.OBSTACLE_1;
import static ru.kanaev.covidclash.Config.SIMPLE_OBSTACLE_SCORE;
import static ru.kanaev.covidclash.Config.SIMPLE_OBSTACLE_VELOCITY;

/**
 * Not thread safe
 */
public class SimpleObstacle extends AbstractObstacle {

    protected static TextureRegion texture = new TextureRegion(new Texture(OBSTACLE_1));

    public SimpleObstacle(CovidClash gameContext) {
        super(gameContext);
        velocity = SIMPLE_OBSTACLE_VELOCITY;
    }

    @Override
    public int getScore() {
        return SIMPLE_OBSTACLE_SCORE;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }
}
