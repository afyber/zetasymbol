package com.afyber.game;

import com.afyber.game.api.InputHandling;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.afyber.game.screens.*;

// this project is so hacked and bodged you wouldn't believe some of it

public class ZetaSymbol extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public static final boolean DEBUG = true;

	// and yes I do realise there's a lot of static variables ok
	public static InputHandling input;

	// just for use globally to store the overworld in-between battles
	public OverworldScreen overworld;

	public static final int SCREEN_WIDTH = 160;
	public static final int SCREEN_HEIGHT = 144;
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 576;

	public static final String WINDOW_TITLE = "zetasymbol";

	// you are now in static variable land
	public static float calibration = -0.1f;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		input = new InputHandling();

		setScreen(new TitleScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
