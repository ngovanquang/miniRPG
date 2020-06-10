package com.oop.game.Entity;

import com.badlogic.gdx.Game;

public class MonsterDanhGan {

        public enum Stage {STANDING,WALKTOP, WALKBOTTOM,WALKLEFT,WALKRIGHT, ATTACK, ISATTACKED};

        private Stage currentStage = null;
        private Stage previousStage = null;

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

        public MonsterDanhXa() {
            this.currentStage = Stage.STANDING;
            this.previousStage = Stage.STANDING;
            this.HP = 100;
            this.MP = 100;
            this.X = 400;
            this.Y = 110;
            this.velocityX = 0;
            this.velocityY = 0;
            this.Defense = 10;
            this.Attack = 15;
            this.Dead = false;
            this.Attacked = false;
            this.isAttack = false;
        }



        public MonsterDanhGan(Stage currentStage, Stage previousStage, int X, int Y, float velocityX, float velocityY, int hp, int mp, int defense, boolean dead, boolean attacked) {
            this.currentStage = currentStage;
            this.previousStage = previousStage;
            this.X = X;
            this.Y = Y;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.HP = hp;
            this.MP = mp;
            this.Defense = defense;
            this.Dead = dead;
            this.Attacked = attacked;

        }

        void move() {

        }

        public boolean isDead () {
            if (HP <= 0)
                return true;
            return false;
        }

        public void isAttacked(boolean attacked) {
            HP -= Player.getAttack();
            currentStage = Stage.ISATTACKED;
            return;
        }

        public void Attack (boolean isAttack) {
            currentStage = Stage.ATTACK;
            
            return;
        }
        public float getX() {
            return X;
        }

        public float getY() {
            return Y;
        }
    }


}
