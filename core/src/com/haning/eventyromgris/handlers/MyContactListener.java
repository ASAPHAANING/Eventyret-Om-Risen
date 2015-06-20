package com.haning.eventyromgris.handlers;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by haaning on 6/19/15.
 */
public class MyContactListener implements ContactListener {

    private int numFootContact;

    public void beginContact(Contact c)
    {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot"))
        {
            numFootContact++;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot"))
        {
            numFootContact++;
        }

    }

    public void endContact(Contact c)
    {

        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot"))
        {
            numFootContact--;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot"))
        {
            numFootContact--;
        }
    }

    public boolean isPlayerOnGround() { return numFootContact > 0; }

    public void preSolve (Contact c, Manifold m) {}
    public void postSolve(Contact c, ContactImpulse ci) {}
}
