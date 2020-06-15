package com.oop.game.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oop.game.Animation.Box2DActor;
import com.oop.game.Animation.GameUtils;
import com.oop.game.Screens.BaseScreen;

import static com.badlogic.gdx.Input.Keys.*;

public class MainCharacter extends Box2DActor {

    public BaseScreen Game;
    public enum Direction {N, S, W, E, NOLMAL};
    public enum State {STAND, ATTACK, ATTACKED, WALK};

    public Direction direction;
    public State currentState;

    private Texture img = new Texture(Gdx.files.internal("MainCharacter.png"));

    private Animation<TextureRegion> standLeft;
    private Animation<TextureRegion> standRight;
    private Animation<TextureRegion> standUp;
    private Animation<TextureRegion> standDown;

    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> walkDown;

    private Animation<TextureRegion> attackLeft;
    private Animation<TextureRegion> attackRight;
    private Animation<TextureRegion> attackUp;
    private Animation<TextureRegion> attackDown;

    private Animation<TextureRegion> attacked;

    private float stateTimer;

    public int HP;
    public int Attack;
    public float ToaDoX;
    public float ToaDoY;
    public boolean isAttack = false;

    float broundX = img.getWidth()/3;
    float broundY = img.getHeight()/4;

    public MainCharacter(BaseScreen game, int HP, int attack, float toaDoX, float toaDoY) {
        this.Game = game;
        this.HP = HP;
        Attack = attack;

        isAttack = false;

        ToaDoX = toaDoX;
        ToaDoY = toaDoY;

        stateTimer = 0;
        direction = Direction.S;
        currentState = State.STAND;
        this.setActiveAnimation("standDown");

        // add animation

        standLeft = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        standRight = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        standUp = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        standDown = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );

        walkLeft = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,3, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        walkRight = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,3, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        walkUp = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,3, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        walkDown = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,3, 0.15f, Animation.PlayMode.LOOP_PINGPONG );

        attackLeft = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        attackRight = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        attackUp = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );
        attackDown = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );

        attacked = GameUtils.parseSpriteSheet("MainCharacter.png", 3, 4,1, 0.15f, Animation.PlayMode.LOOP_PINGPONG );

        //store Animation
        this.storeAnimation("standLeft", standLeft);
        this.storeAnimation("standRight", standRight);
        this.storeAnimation("standUp", standUp);
        this.storeAnimation("standDown", standDown);

        this.storeAnimation("walkLeft", walkLeft);
        this.storeAnimation("walkRight", walkRight);
        this.storeAnimation("walkUp", walkUp);
        this.storeAnimation("walkDown", walkDown);

        this.storeAnimation("attackLeft", attackLeft);
        this.storeAnimation("attackRight", attackRight);
        this.storeAnimation("attackUp", attackUp);
        this.storeAnimation("attackDown", attackDown);

        this.storeAnimation("attacked", attacked);

        // create mainCharacter in Box2d




    }


       public void move() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            direction = Direction.N;
            if (isAttack) {
                currentState = State.ATTACK;
                this.setActiveAnimation("attackUp");

            }
            else {
                currentState = State.WALK;
                this.setActiveAnimation("walkUp");
            }

            ToaDoX += 10f;
            System.out.println(ToaDoX);
        }

        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            direction = Direction.S;
            if (isAttack) {
                currentState = State.ATTACK;
                this.setActiveAnimation("attackDown");
            }
            else {
                currentState = State.WALK;
                this.setActiveAnimation("walkDown");
            }

            ToaDoX -= 10f;
            System.out.println(ToaDoX);
        }

        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            direction = Direction.W;
            if (isAttack) {
                currentState = State.ATTACK;
                this.setActiveAnimation("attackLeft");
            }
            else {
                currentState = State.WALK;
                this.setActiveAnimation("walkLeft");
            }

            ToaDoY -= 10f;
            System.out.println(ToaDoY);
        }

        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(R)) {
            direction = Direction.E;
            if (isAttack) {
                currentState = State.ATTACK;
                this.setActiveAnimation("attackRight");
            }
            else {
                currentState = State.WALK;
                this.setActiveAnimation("walkRight");
            }

            ToaDoX += 10f;
            System.out.println(ToaDoY);
        }

        if (Gdx.input.isKeyPressed(SPACE)) {
             isAttack = true;
        }

    }


    public boolean isAttacked() {
        return false;
    }


    public boolean attack() {
        return false;
    }

    public BaseScreen getGame() {
        return Game;
    }

    public Animation<TextureRegion> getStandLeft() {
        return standLeft;
    }

    public Animation<TextureRegion> getStandRight() {
        return standRight;
    }

    public Animation<TextureRegion> getStandUp() {
        return standUp;
    }

    public Animation<TextureRegion> getStandDown() {
        return standDown;
    }

    public Animation<TextureRegion> getWalkLeft() {
        return walkLeft;
    }

    public Animation<TextureRegion> getWalkRight() {
        return walkRight;
    }

    public Animation<TextureRegion> getWalkUp() {
        return walkUp;
    }

    public Animation<TextureRegion> getWalkDown() {
        return walkDown;
    }

    public Animation<TextureRegion> getAttackLeft() {
        return attackLeft;
    }

    public Animation<TextureRegion> getAttackRight() {
        return attackRight;
    }

    public Animation<TextureRegion> getAttackUp() {
        return attackUp;
    }

    public Animation<TextureRegion> getAttackDown() {
        return attackDown;
    }

    public Animation<TextureRegion> getAttacked() {
        return attacked;
    }

}
