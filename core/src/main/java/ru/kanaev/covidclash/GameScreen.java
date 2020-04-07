package ru.kanaev.covidclash;

import com.badlogic.gdx.Gdx;
import ru.kanaev.covidclash.entity.Background;
import ru.kanaev.covidclash.entity.Explosion;
import ru.kanaev.covidclash.entity.GameOverLabel;
import ru.kanaev.covidclash.entity.Player;
import ru.kanaev.covidclash.entity.ScoreLabel;
import ru.kanaev.covidclash.entity.item.PickUpItem;
import ru.kanaev.covidclash.entity.item.bullet.Bullet;
import ru.kanaev.covidclash.entity.obstacle.AbstractObstacle;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.math.MathUtils.clamp;
import static ru.kanaev.covidclash.Config.OBSTACLE_CREATION_INTERVAL;
import static ru.kanaev.covidclash.Config.WEAPON_CREATION_TIME_INTERVAL;
import static ru.kanaev.covidclash.entity.item.bullet.State.LANDED;

@Setter
@Getter
public class GameScreen extends AbstractScreen {

    private EntityCreationManager creationManager;

    private Background background;
    private Player player;
    private Explosion explosion;
    private List<Explosion> obstacleExplosions;
    private List<AbstractObstacle> obstacles;
    private List<PickUpItem> items;
    private List<Bullet> bullets;
    private ScoreLabel scoreLabel;
    private GameOverLabel gameOverLabel;
    private OverlapManager overlapManager;
    private OutsiderManager outsiderManager;
    private PassingManager passingManager;

    private float obstacleCreationTimeElapsed;
    private float weaponCreationTimeElapsed;
    private float lastAccelerationTimeElapsed;
    private float accelerationCount;
    private float fastObstacleCreationTimeElapsed;
    private boolean failure;
    private boolean pause;

    public GameScreen(CovidClash game) {
        super(game);
    }

    @Override
    public void create() {
        obstacleCreationTimeElapsed = 0;
        weaponCreationTimeElapsed = 0;
        lastAccelerationTimeElapsed = 0;
        accelerationCount = 0;
        fastObstacleCreationTimeElapsed = 0;

        creationManager = new GameScreenEntityCreationManager(this);

        creationManager.createBackground();
        creationManager.createPlayer();

        obstacles = new ArrayList<>();
        items = new ArrayList<>();
        bullets = new ArrayList<>();
        obstacleExplosions = new ArrayList<>();
        overlapManager = new OverlapManagerImpl(this);
        outsiderManager = new OutsiderManagerImpl(this);
        passingManager = new PassingManagerImpl(this);

        scoreLabel = new ScoreLabel(this);
        gameOverLabel = new GameOverLabel();
    }

    @Override
    public void update(float delta) {
        float localDelta = clamp(delta, delta, 1/30f);
        if (pause) {
            return;
        }
        outsiderManager.update(localDelta);
        cleanGameObjects();

        overlapManager.update(localDelta);
        cleanGameObjects();

        passingManager.update(localDelta);
        cleanGameObjects();

        if (failure) {
            if (explosion == null) {
                explosion = new Explosion(player.getX(), player.getY());
            }
            explosion.update(localDelta);

            return;
        }

        obstacleCreationTimeElapsed += localDelta;
        weaponCreationTimeElapsed += localDelta;
        lastAccelerationTimeElapsed += localDelta;
        fastObstacleCreationTimeElapsed += localDelta;

        if (obstacleCreationTimeElapsed > OBSTACLE_CREATION_INTERVAL) {
            creationManager.createObstacle();
        }

        if (weaponCreationTimeElapsed > WEAPON_CREATION_TIME_INTERVAL) {
            weaponCreationTimeElapsed = 0;
            creationManager.createLaser();
        }

        player.update(localDelta);

        for (AbstractObstacle obstacle : obstacles) {
            obstacle.update(localDelta);
        }
        obstacles.removeIf(o -> o.isOutsider() || o.isExploded());

        for (PickUpItem item : items) {
            item.update(localDelta);
        }
        items.removeIf(i -> i.isOutsider() || i.isExpired());

        for (Bullet bullet : bullets) {
            bullet.update(localDelta);
        }
        bullets.removeIf(b -> b.isOutsider() || LANDED.equals(b.getState()));

        for (Explosion obstacleExplosion : obstacleExplosions) {
            obstacleExplosion.update(localDelta);
        }
        obstacleExplosions.removeIf(Explosion::isEnded);

        scoreLabel.update(localDelta);
    }

    public void setFailure(boolean b) {
        this.failure = b;
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();
        background.draw(game.batch);
        scoreLabel.draw(game.batch);

        if (failure) {
            if (!explosion.isEnded()) {
                explosion.draw(game.batch);
            }
            gameOverLabel.draw(game.batch);
            game.batch.end();
            return;
        }

        player.draw(game.batch);

        for (AbstractObstacle obstacle : obstacles) {
            obstacle.draw(game.batch);
        }

        for (PickUpItem item : items) {
            item.draw(game.batch);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(game.batch);
        }

        for (Explosion obstacleExplosion : obstacleExplosions) {
            obstacleExplosion.draw(game.batch);
        }

        game.batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.M) {
            game.setScreen(new MenuScreen(game));
        }

        if (keycode == Keys.P) {
            togglePause();
        }

        if (keycode == Keys.DOWN) {
            player.setVelocity(-250 + -accelerationCount * 5);
        }

        if (keycode == Keys.UP) {
            player.setVelocity(250 + accelerationCount * 5);
        }

        if (keycode == Keys.F) {
            player.useItem();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.DOWN) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.UP)) {
                player.setVelocity(250 + accelerationCount * 5);
            }
        }

        if (keycode == Keys.UP) {
            player.setVelocity(0);

            if (Gdx.input.isKeyPressed(Keys.DOWN)) {
                player.setVelocity(-250 + -accelerationCount * 5);
            }
        }

        return false;
    }

    private void togglePause() {
        pause = !pause;
    }

    private void cleanGameObjects() {
        obstacles.removeIf(o -> o.isOutsider() || o.isExploded());
        items.removeIf(i -> i.isOutsider() || i.isExpired());
        bullets.removeIf(b -> b.isOutsider() || LANDED.equals(b.getState()));
    }
}
