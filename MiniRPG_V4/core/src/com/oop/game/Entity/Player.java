package com.oop.game.Entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import static com.oop.game.Entity.Player.Key.*;

public class Player{

    public enum Stage {STANDING,WALKTOP, WALKBOTTOM,WALKLEFT,WALKRIGHT, ATTACK, ISATTACKED};
    public enum Key { UP, DOWN, RIGHT, LEFT, SPACE};

    private boolean isUP = false;
    private boolean isDOWN = false;
    private boolean isLEFT = false;
    private boolean isRIGHT = false;
    private boolean isSPACE = false;
    
    private Stage currentStage = null;
    private Stage previousStage = null;

    private Key currentKey;


    private float speed;
    private float X;
    private  float Y;
    private float velocityX;
    private float velocityY;

    private boolean Dead;
    private boolean Attacked;
    private boolean isAttack;

    private int HP;
    private int MP;
    private int Defense;
    private int Attack;

    public Player(Game game) {
        this.currentStage = Stage.STANDING;
        this.previousStage = Stage.STANDING;
        this.HP = 100;
        this.MP = 100;
        this.X = 510;
        this.Y = 110;
        this.velocityX = 0;
        this.velocityY = 0;
        this.Defense = 10;
        this.Attack = 15;
        this.Dead = false;
        this.Attacked = false;
        this.isAttack = false;
    }



    public Player(Stage currentStage, Stage previousStage, int X, int Y, float velocityX, float velocityY, int hp, int mp, int defense, boolean dead, boolean attacked) {
        this.currentStage = currentStage;
        this.previousStage = previousStage;
        this.X = X;
        this.Y = Y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.HP = hp;
        this.MP = mp;
        this.Defense = defense;
        this.dead = dead;
        this.attacked = attacked;

    }

    void move() {
        switch (currentKey){
            // di chuyen len tren
            case UP:{
                currentStage = Stage.WALKTOP;
                velocityX += 100;
                break;
            }
            // di chuyen xuong duoi
            case DOWN:{
                currentStage = Stage.WALKBOTTOM;
                velocityX -= 100;
                break;
            }
            // di chuyen sang trai
            case LEFT:{
                currentStage = Stage.WALKLEFT;
                velocityY -= 100;
                break;
            }
            // di chuyen sang phai
            case RIGHT:{
                currentStage = Stage.WALKRIGHT;
                velocityY += 100;
                break;
            }
            case SPACE:{
                currentStage = Stage.ATTACK;
                break;
            }


        }

    }

    public boolean isDead (boolean dead) {
        return dead;
    }

    public void isAttacked(boolean attacked) {
        if (MonsterDanhGan.getIsAttack())
            HP -= MonsterDanhGan.getAttack();
        if (MonsterDanhXa.getIsAttack())
            HP -= MonsterDanhXa.getAttack();
        if (Boss.getIsAttack())
            HP -= Boss.getAttack();
        currentStage = Stage.ISATTACKED;
        return;
    }

    public void Attack (boolean isAttack) {
        currentStage = Stage.ATTACK;

        if (MonsterDanhGan.getAttacked())
            MonsterDanhGan.setHP() -= Attack;
        if (MonsterDanhXa.getAttacked())
            MonsterDanhXa.setHP() -= Attack;
        if (Boss.getAttacked())
            Boss.setHP() -= Attack;

        return;
    }
    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }
}
