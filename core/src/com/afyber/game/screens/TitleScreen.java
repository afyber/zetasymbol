package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.MyScreenAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class TitleScreen extends MyScreenAdapter {

    public TitleScreen(ZetaSymbol game) {
        super(game);

        setupScreen();

        Gdx.input.setInputProcessor(ZetaSymbol.handler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        game.font.draw(game.batch, ZetaSymbol.WINDOW_TITLE, 50f, 100f);

        game.font.draw(game.batch, String.format("calibration: %.03f sec", ZetaSymbol.calibration), 10, 32);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        if (ZetaSymbol.input[4]) {
            game.setScreen(new OverworldScreen(game));
        }
        if (ZetaSymbol.input[0]) {
            ZetaSymbol.calibration -= 0.001f;
        }
        if (ZetaSymbol.input[1]) {
            ZetaSymbol.calibration += 0.001f;
        }
    }
}
