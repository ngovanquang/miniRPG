package com.oop.game.Screens;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected Game game;
    protected Stage mainStage;
    protected Stage uiStage;

    public final int viewWidth = 640;
    public final int viewHeight = 480;

    private boolean paused;

    public BaseScreen(Game g) {
        game = g;
        mainStage = new Stage(new FitViewport(viewWidth, viewHeight));
        uiStage = new Stage(new FitViewport(viewWidth, viewHeight));

        paused = false;

        InputMultiplexer multiplexer = new InputMultiplexer(this, uiStage, mainStage);
        Gdx.input.setInputProcessor(multiplexer);

        create();
    }
    // create abstract methods create() and update
    public abstract void create();
    public abstract void update(float delta);

    @Override
    public void show() {

    }

    @Override
    // gameloop, update then render
    public void render(float delta) {
        uiStage.act(delta);
        // pause game
        if (!isPaused() ) {
            mainStage.act(delta);
            update(delta);
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    // pause methods
    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean p) {
        paused = p;
    }

    public void togglePaused() {
        paused = !paused;
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width,height,true);
        uiStage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public abstract void render();
}