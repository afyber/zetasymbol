package com.afyber.game;

import com.afyber.game.api.InputHandling;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.afyber.game.screens.*;

public class ZetaSymbol extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	// each index corresponds to a button
	// true means the button is currently being held down
	// 0: Left
	// 1: Right
	// 2: Up
	// 3: Down
	// 4: A
	// 5: B
	// 6: Pause/Start
	public static boolean[] input;
	// and yes I do realise there's a lot of static variables ok
	public static InputHandling handler;

	// just for use globally to store overworld position in-between battles
	public int overworldx;
	public int overworldy;

	public static final int SCREEN_WIDTH = 200;
	public static final int SCREEN_HEIGHT = 200;
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;

	public static final String WINDOW_TITLE = "zetasymbol";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		input = new boolean[7];
		handler = new InputHandling();

		setScreen(new TitleScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
