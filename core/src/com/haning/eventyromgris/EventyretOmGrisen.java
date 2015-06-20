package com.haning.eventyromgris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haning.eventyromgris.handlers.Content;
import com.haning.eventyromgris.handlers.GameStateManager;
import com.haning.eventyromgris.handlers.MyInput;
import com.haning.eventyromgris.handlers.MyInputProcessor;

public class EventyretOmGrisen extends ApplicationAdapter {

	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudcam;

	public static final String TITLE = "Eventyret om Risen";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;

	public static final float STEP = 1 / 60f;
	private float accum;

	private GameStateManager gsm;

	public static Content res;



	@Override
	public void create () {

		Gdx.input.setInputProcessor(new MyInputProcessor());

		res = new Content();
		res.loadTexture("images/risen3.png","ris");

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
				cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudcam = new OrthographicCamera();
				hudcam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP)
		{
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}


	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	public OrthographicCamera getHUDCamera() { return hudcam; }

}
