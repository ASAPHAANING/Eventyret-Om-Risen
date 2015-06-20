package com.haning.eventyromgris.handlers;

import com.badlogic.gdx.Game;
import com.haning.eventyromgris.EventyretOmGrisen;
import com.haning.eventyromgris.states.GameState;
import com.haning.eventyromgris.states.Play;

import java.util.Stack;

/**
 * Created by haaning on 6/17/15.
 */
public class GameStateManager {

    private EventyretOmGrisen game;
    private Stack<GameState> gameStates;

    public static final int PLAY = 912837;

    public GameStateManager(EventyretOmGrisen game)
    {
     this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public EventyretOmGrisen game() { return game; }

    public void update(float dt)
    {
        gameStates.peek().update(dt);
    }

    public void render()
    {
        gameStates.peek().render();
    }

    private GameState getState(int state)
    {
        if(state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state)
    {
        popState();
        pushState(state);
    }

    public void pushState(int state)
    {
        gameStates.push(getState(state));
    }

    public void popState()
    {
        GameState g = gameStates.pop();
        g.dispose();
    }

}
