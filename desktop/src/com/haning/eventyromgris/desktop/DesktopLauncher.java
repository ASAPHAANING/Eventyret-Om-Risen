package com.haning.eventyromgris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.haning.eventyromgris.EventyretOmGrisen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new EventyretOmGrisen(), config);

		config.title = EventyretOmGrisen.TITLE;
		config.width = EventyretOmGrisen.V_WIDTH * EventyretOmGrisen.SCALE;
		config.height = EventyretOmGrisen.V_HEIGHT * EventyretOmGrisen.SCALE;
	}
}
