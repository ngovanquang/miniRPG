package com.oop.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.oop.game.Screens.BaseScreen;

public class AnimatedActor extends BaseActor {
    private float elapsedTime;
    private Animation animation;


    public AnimatedActor() {
        super();
        elapsedTime = 0;
    }

    public void setAnimation(Animation a) {
      //  Texture t = a.getKeyFrame(0).getTexture();
       // setTexture(t);
        animation = a;
    }

    public void act(float dt) {
        super.act(dt);
        elapsedTime += dt;
        if (velocityX != 0 || velocityY != 0)
            setRotation(MathUtils.atan2(velocityY, velocityX) * MathUtils.radiansToDegrees);
    }

    public void draw(Batch batch, float parentAlpha)
    {
        region.setRegion((TextureRegion) animation.getKeyFrame(elapsedTime));
        super.draw(batch, parentAlpha);
    }
}