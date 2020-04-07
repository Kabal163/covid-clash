package ru.kanaev.covidclash;

import ru.kanaev.covidclash.entity.Outsider;

import static ru.kanaev.covidclash.Config.VIEW_WIDTH;

public class OutsiderManagerImpl implements OutsiderManager {

    private GameScreen gameScreen;

    public OutsiderManagerImpl(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float delta) {
        checkOutsiderObstacles();
        checkOutsiderBullet();
        checkOutsiderPickedUpItems();
    }

    private void checkOutsiderObstacles() {
        gameScreen.getObstacles().stream()
                .filter(o -> o.getPosition().x < 0 - o.getWidth())
                .forEach(Outsider::markOutsider);
    }

    private void checkOutsiderBullet() {
        gameScreen.getBullets().stream()
                .filter(b -> b.getPosition().x > VIEW_WIDTH + 1)
                .forEach(Outsider::markOutsider);
    }

    private void checkOutsiderPickedUpItems() {
        gameScreen.getItems().stream()
                .filter(i -> i.getPosition().x < 0 - i.getWidth())
                .forEach(Outsider::markOutsider);
    }
}
