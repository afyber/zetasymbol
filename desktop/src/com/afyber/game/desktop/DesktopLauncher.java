package com.afyber.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.afyber.game.ZetaSymbol;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = ZetaSymbol.WINDOW_WIDTH;
		config.height = ZetaSymbol.WINDOW_HEIGHT;
		config.title = ZetaSymbol.WINDOW_TITLE;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(new ZetaSymbol(), config);
	}
}
