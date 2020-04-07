package ru.kanaev.covidclash.entity.item.weapon;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.kanaev.covidclash.GameScreen;
import ru.kanaev.covidclash.CovidClash;

import static ru.kanaev.covidclash.Assets.LASER;
import static ru.kanaev.covidclash.entity.item.weapon.State.PICKED_UP;

public class Laser extends AbstractWeapon {

    private static TextureRegion texture = new TextureRegion(new Texture(LASER));

    public Laser(CovidClash gameContext) {
        super(gameContext);
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public void use() {
        if (!PICKED_UP.equals(state)) {
            return;
        }
        Screen screen = gameContext.getScreen();

        if (screen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) screen;
            gameScreen.getCreationManager().createBullet();
        }
    }
}
