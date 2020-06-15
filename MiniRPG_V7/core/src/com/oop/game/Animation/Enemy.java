package com.oop.game.Animation;

import com.badlogic.gdx.physics.box2d.World;
public class Enemy extends Box2DActor
{
    public Enemy()
    {  super();  }
    
    public void initializePhysics(World world)
    {
        setDynamic();
        setShapeCircle();
        fixtureDef.isSensor = true;
        super.initializePhysics(world);
    }
    
    public Enemy clone()
    {
        Enemy newbie = new Enemy();
        newbie.copy( this );
        return newbie;
    }
}