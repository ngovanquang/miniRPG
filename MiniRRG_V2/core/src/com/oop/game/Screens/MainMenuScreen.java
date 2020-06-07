package com.oop.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainMenuScreen extends BaseScreen {
    // không cần khởi tạo  biến Game hay Stage vì baseScreen đã xử lý rồi
    public MainMenuScreen(Game g){
        super(g);
    }


    @Override
    public void create() {
        BaseActor background = new BaseActor();
        background.setTexture (new Texture(Gdx.files.internal("sprites/background.png")));
        uiStage.addActor(background);

        BaseActor titleText = new BaseActor();
        titleText.setTexture (new Texture((Gdx.files.internal("sprites/text.png"))));
        titleText.setPosition(10, 160);
        uiStage.addActor(titleText);

        BitmapFont font = new BitmapFont();
        String text = "Press S to Start, M for main menu";
        LabelStyle style = new LabelStyle(font, Color.YELLOW);
        Label instructions = new Label(text, style);
        instructions.setFontScale(2);
        instructions.setPosition(100, 50);
        // repeating color pulse effect
        instructions.addAction(
                Actions.forever(
                        Actions.sequence(
                                Actions.color(new Color(1,1,0, 1), 0.5f),
                                Actions.delay(0.5f),
                                Actions.color(new Color(0.5f, 0.5f, 0, 1 ), 0.5f)

                        )
                )

        );
        uiStage.addActor(instructions);

    }

    @Override
    public void update(float delta) {


    }

    public boolean keyDown(int keycode){
        if (keycode == Keys.S)
            game.setScreen(new PlayGameScreen(game));

        return false;
    }

    @Override
    public void render() {

    }
}
