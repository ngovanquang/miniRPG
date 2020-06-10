package com.oop.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.oop.game.Screens.PlayGameScreen;

public class PlayerDraw extends BaseActor {
    public enum Stage {STANDING,WALKTOP, WALKBOTTOM,WALKLEFT,WALKRIGHT};

    public Stage currentState;
    public Stage previousState;

    Texture spritesheet;

    TextureRegion[] walkFrame;
    TextureRegion[] walkLFrame;
    TextureRegion[] walkRFrame;
    TextureRegion[] walkUFrame;
    TextureRegion[] walkDFrame;



    public Animation playerStand;
    public Animation playerWalkL;
    public Animation playerWalkR;
    public Animation playerWalkD;
    public Animation playerWalkU;



    private float stateTimer;


    private PlayGameScreen screen;

    public PlayerDraw() {
        this.screen = screen;
        currentState = Stage.STANDING;
        previousState = Stage.STANDING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        // get Standing animation frames and add them to Standing Animation;

        spritesheet = new Texture(Gdx.files.internal("Sprites/ada_0.png"));
        TextureRegion[][] tmp = TextureRegion.split(spritesheet, spritesheet.getWidth()/4,spritesheet.getHeight()/4);
        walkFrame = new TextureRegion[4 * 4];
        int index = 0;
        for (int i = 0; i < 4; i ++) {
                walkFrame [index++] = tmp[1][1];
                //walkLFrame[index++] = tmp[i][1];
                //walkDFrame[index++] = tmp[i][2];
                //walkRFrame[index++] = tmp[i][3];
                //walkUFrame[index++] = tmp[i][4];

        }

        playerStand = new Animation(0.25f, walkFrame);
       // playerWalkL = new Animation(0.25f, walkLFrame);
      //  playerWalkR = new Animation(0.25f, walkRFrame);
       // playerWalkU = new Animation(0.25f, walkUFrame);
       // playerWalkD = new Animation(0.25f, walkDFrame);

    }

}
