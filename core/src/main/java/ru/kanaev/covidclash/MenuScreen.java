package ru.kanaev.covidclash;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import ru.kanaev.covidclash.entity.Background;
import ru.kanaev.covidclash.entity.GameObject;
import ru.kanaev.covidclash.entity.TitleLabel;

import static com.badlogic.gdx.Input.Keys;
import static com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import static ru.kanaev.covidclash.Config.DEFAULT_LABEL_TRANSPARENCY;

public class MenuScreen extends AbstractScreen {

    private GameObject background;
    private GameObject spinnerClashTitle;
    private Label instructions;


    public MenuScreen(CovidClash game) {
        super(game);
    }

    @Override
    public void create() {
        background = new Background();
        spinnerClashTitle = new TitleLabel();
        instructions = createInstructions();
    }

    @Override
    public void update(float delta) {
        instructions.act(delta);
    }

    @Override
    public void renderScene(float delta) {
        game.batch.begin();

        background.draw(game.batch);
        spinnerClashTitle.draw(game.batch);
        instructions.draw(game.batch, DEFAULT_LABEL_TRANSPARENCY);

        game.batch.end();
    }


    private Label createInstructions() {
        BitmapFont font = new BitmapFont();
        String text = " Press S to start ";
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        Label instructions = new Label(text, style);
        instructions.setFontScale(2);
        instructions.setPosition(200, 230);

        return instructions;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.S) {
            game.setScreen(new GameScreen(game));
        }

        return false;
    }
}
