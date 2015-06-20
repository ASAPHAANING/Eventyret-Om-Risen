package com.haning.eventyromgris.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by haaning on 6/19/15.
 */
public class MyInputProcessor extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.Z)
        {
            MyInput.setKey(MyInput.BUTTON1, true);
        }
        if(keycode == Input.Keys.X)
        {
            MyInput.setKey(MyInput.BUTTON2, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.Z)
        {
            MyInput.setKey(MyInput.BUTTON1, false);
        }
        if(keycode == Input.Keys.X)
        {
            MyInput.setKey(MyInput.BUTTON2, false);
        }
        return true;
    }
}
