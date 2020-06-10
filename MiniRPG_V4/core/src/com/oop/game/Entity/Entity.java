package com.oop.game.Entity;

public abstract class Entity {

    protected float speed;
    protected float X;
    protected  float Y;
    protected float velocityX;
    protected float velocityY;

    protected boolean dead;
    protected boolean attacked;

    protected int HP;
    protected int MP;
    protected int Defense;
    protected int Attack;

    abstract void move();
    abstract void attack();
    abstract void isDead();

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }
}
