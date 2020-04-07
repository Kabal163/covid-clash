package ru.kanaev.covidclash.entity.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.kanaev.covidclash.CovidClash;

import static ru.kanaev.covidclash.Assets.EXPLOSION;
import static ru.kanaev.covidclash.Assets.OBSTACLE_2;
import static ru.kanaev.covidclash.Config.FAST_OBSTACLE_SCORE;
import static ru.kanaev.covidclash.Config.FAST_OBSTACLE_VELOCITY;

/**
 * Not thread safe
 */
public class FastObstacle extends AbstractObstacle {

    private static TextureRegion obstacleTexture = new TextureRegion(new Texture(OBSTACLE_2));
    private static TextureRegion explosionTexture = new TextureRegion(new Texture(EXPLOSION));

    public FastObstacle(CovidClash gameContext) {
        super(gameContext);
        velocity = FAST_OBSTACLE_VELOCITY;
    }

    @Override
    public int getScore() {
        return FAST_OBSTACLE_SCORE;
    }

    @Override
    public TextureRegion getTexture() {
        return obstacleTexture;
    }
}
