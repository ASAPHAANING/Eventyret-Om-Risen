package com.haning.eventyromgris.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haning.eventyromgris.EventyretOmGrisen;
import com.haning.eventyromgris.handlers.GameStateManager;

/**
 * Created by haaning on 6/17/15.
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected EventyretOmGrisen game;

    protected SpriteBatch sb;
    protected OrthographicCamera cam;
    protected OrthographicCamera hudcam;

    protected GameState(GameStateManager gsm)
    {
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        cam = game.getCamera();
        hudcam = game.getHUDCamera();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();

}
