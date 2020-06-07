package com.oop.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;


public class PlayGameScreen extends BaseScreen {

    private BaseMap map;


    public PlayGameScreen(Game g) {
        super(g);
    }

    @Override
    public void create() {
        BaseMap map = new BaseMap();
        map.getMap("maps/Map01.tmx");

    }

    @Override
    public void update(float delta) {

    }

    public boolean keyDown(int keycode){
        if (keycode == Input.Keys.M)
            game.setScreen(new MainMenuScreen(game));
        return false;
    }

    @Override
    public void render() {

    }
}
