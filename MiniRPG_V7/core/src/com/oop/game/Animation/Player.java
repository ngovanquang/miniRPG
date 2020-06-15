package com.oop.game.Animation;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.math.Vector2;

public class Player extends Box2DActor {
  //  public int groundCount;
 
    public Player()
    {
        super();
      //  groundCount = 0;
    }
    
//    public void adjustGroundCount(int i)
//    {  groundCount += i;  }
//
//    public boolean isOnGround()
//    {  return (groundCount > 0);  }
//
    // uses data to initialize object and add to world
    public void initializePhysics(World world)
    {
        super.initializePhysics(world);

        FixtureDef bottomSensor = new FixtureDef();
        bottomSensor.isSensor = true;

        PolygonShape sensorShape = new PolygonShape();

        // center coordinates of sensor box - offset from body center
        float x = 0;
        float y = 0;
        // dimensions of sensor box
        float w = getWidth();
        float h = getHeight();
        sensorShape.setAsBox( w /200, h/200, new Vector2(x/200, y/200), 0 );
        bottomSensor.shape = sensorShape;

        Fixture bottomFixture = body.createFixture(bottomSensor);
        bottomFixture.setUserData("bottom");

        sensorShape.dispose();
    }
    
    
}