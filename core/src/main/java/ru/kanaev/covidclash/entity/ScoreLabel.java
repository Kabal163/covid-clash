package ru.kanaev.covidclash.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.kanaev.covidclash.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.kanaev.covidclash.Assets.NUMBERS;
import static ru.kanaev.covidclash.Config.NUMBER_HEIGHT;
import static ru.kanaev.covidclash.Config.NUMBER_WIDTH;
import static ru.kanaev.covidclash.Config.VIEW_WIDTH;
import static ru.kanaev.covidclash.entity.ObjectTag.LABEL;

/**
 * Not thread safe
 */
public class ScoreLabel implements GameObject {

    private static List<TextureRegion> numbers;

    private GameScreen gameScreen;
    private int score;
    private List<Sprite> toBeDrawn;

    public ScoreLabel(GameScreen screen) {
        this.gameScreen = screen;
        score = 0;

        if (numbers == null) {
            numbers = Arrays.asList(TextureRegion.split(new Texture(NUMBERS), NUMBER_WIDTH, NUMBER_HEIGHT)[0]);
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void update(float delta) {
        toBeDrawn = new ArrayList<>();

//        gameScreen.getObstacles().stream()
//                .filter(o -> !o.isPassed())
//                .forEach(o -> {
//                    if (o.getX() < gameScreen.getPlayer().getX()) {
//                        score = score + o.getScore();
//                        o.pass();
//                    }
//                });

        char[] chars = String
                .valueOf(score)
                .toCharArray();

        List<Integer> categories = new ArrayList<>();

        for (char c : chars) {
            categories.add(Integer.valueOf(String.valueOf(c)));
        }

        float startPosition = VIEW_WIDTH / 2 - (categories.size() * NUMBER_WIDTH * 0.8f / 2);

        for (int i = 0; i < categories.size(); i++) {
            Sprite sprite = new Sprite(numbers.get(categories.get(i)));
            sprite.setPosition(startPosition + (i * NUMBER_WIDTH * 0.8f), 400);
            toBeDrawn.add(sprite);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (Sprite sprite : toBeDrawn) {
            sprite.draw(batch);
        }
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
        return LABEL;
    }

    public void increaseScore(int delta) {
        score += delta;
    }
}
