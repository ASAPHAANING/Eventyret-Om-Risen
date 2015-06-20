package com.haning.eventyromgris.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.haning.eventyromgris.EventyretOmGrisen;

/**
 * Created by haaning on 6/20/15.
 */
public class Player extends B2DSprite {

    private int numCrystals;
    private int totalCrystals;

    public Player(Body body)
    {
        super(body);

        Texture tex = EventyretOmGrisen.res.getTexture("ris");

        TextureRegion[] sprites = TextureRegion.split(tex,30,70)[0];

        animation.setFrames(sprites,1/12f);

        setAnimation(sprites, 1 / 12f);
    }

    public void collectCrystal() { numCrystals++; }
    public int getNumCrystals() { return numCrystals; }
    public void setTotalCrystals (int i) { totalCrystals = i; }
    public int getTotalCrystals() { return totalCrystals; }


}
