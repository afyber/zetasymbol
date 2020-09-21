package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TitleScreen extends ScreenAdapter {
    ZetaSymbol game;

    Viewport viewport;
    OrthographicCamera camera;

    public TitleScreen(ZetaSymbol game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZetaSymbol.SCREEN_WIDTH, ZetaSymbol.SCREEN_HEIGHT);
        viewport = new ScalingViewport(Scaling.stretch, ZetaSymbol.WINDOW_WIDTH, ZetaSymbol.WINDOW_HEIGHT);

        Gdx.input.setInputProcessor(ZetaSymbol.handler);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, ZetaSymbol.WINDOW_TITLE, 50f, 150f);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        if (ZetaSymbol.input[4]) {
            game.setScreen(new OverworldScreen(game));
        }
    }
}
