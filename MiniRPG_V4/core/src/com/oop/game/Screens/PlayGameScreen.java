package com.oop.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.oop.game.Entity.Player;
import com.oop.game.Sprites.BaseActor;

public class PlayGameScreen extends BaseScreen implements InputProcessor{
    SpriteBatch spriteBatch;
    float stateTime = 0f;
    TextureRegion currentFrame;
    private BaseActor texture;

    //PlayerDraw playerDraw = new PlayerDraw(this);

    public PlayGameScreen(Game game) {
        super(game);

    }

    @Override
    public void create() {
        Player player2 = new Player(Player.Stage.STANDING, Player.Stage.STANDING,110 ,120,4,5,5,6,5,false,false);
        Player player = new Player(game);


        mainStage = new Stage();

        texture = new BaseActor();

        texture.setTexture(new Texture(Gdx.files.internal("Sprites/badlogic.jpg")));
        texture.setPosition(player.getX(), player.getY());

        mainStage.addActor(texture);

    }

    @Override
    public void update(float delta) {

    }

    public void render(float delta) {
        uiStage.act(delta);
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();

    }

    public void handleInput(float dt) {

    }

}
