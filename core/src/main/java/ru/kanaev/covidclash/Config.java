package ru.kanaev.covidclash;

public final class Config {

    private Config() {}

    public static final float VIEW_WIDTH = 640;
    public static final float VIEW_HEIGHT = 480;

    public static final float BACKGROUND_X_POS = 0;
    public static final float BACKGROUND_Y_POS = 0;

    public static final float PLAYER_ROTATION_DEGREE = -60;
    public static final float PLAYER_INIT_X_POS = 50;
    public static final float PLAYER_INIT_Y_POS = 200;
    public static final float PLAYER_HEIGHT = 130;
    public static final float PLAYER_WIDTH = 130;

    public static final float OBSTACLE_HEIGHT = 130;
    public static final float OBSTACLE_WIDTH = 130;

    public static final float OBSTACLE_CREATION_INTERVAL = 1;
    public static final float DEFAULT_OBSTACLE_VELOCITY = -300;
    public static final float SIMPLE_OBSTACLE_VELOCITY = -500;
    public static final float FAST_OBSTACLE_VELOCITY = -700;
    public static final int FAST_OBSTACLE_SCORE = 3;
    public static final int SIMPLE_OBSTACLE_SCORE = 1;

    public static final float WEAPON_CREATION_TIME_INTERVAL = 2;
    public static final float WEAPON_WIDTH = 150;
    public static final float WEAPON_HEIGHT = 150;
    public static final float WEAPON_VELOCITY = -500;
    public static final float WEAPON_LIFE_LENGTH = 6; // seconds

    public static final float DEFAULT_FRAME_LENGTH = 0.2f;

    public static final float EXPLOSION_FRAME_LENGTH = 0.06f;
    public static final int EXPLOSION_HEIGHT = 192;
    public static final int EXPLOSION_WIDTH = 192;

    public static final int NUMBER_WIDTH = 81;
    public static final int NUMBER_HEIGHT = 78;

    public static final float SPINNER_CLASH_TITLE_X_POS = 20;
    public static final float SPINNER_CLASH_TITLE_Y_POS = 250;

    public static final float DEFAULT_LABEL_TRANSPARENCY = 1;



    public static final float DEFAULT_BULLET_VELOCITY = 400;
    public static final int BULLET_WIDTH = 45;
    public static final int BULLET_HEIGHT = 34;

}
