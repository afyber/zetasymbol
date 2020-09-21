package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.LoadSave;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OverworldScreen extends ScreenAdapter {
    ZetaSymbol game;

    Overworld world;

    Viewport viewport;
    OrthographicCamera camera;

    public OverworldScreen(ZetaSymbol game) {
        this(game, 0);
    }
    public OverworldScreen(ZetaSymbol game, int levelID) {
        this.game = game;

        world = new Overworld();
        LoadSave.load(world, levelID);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZetaSymbol.SCREEN_WIDTH, ZetaSymbol.SCREEN_HEIGHT);
        viewport = new ScalingViewport(Scaling.stretch, ZetaSymbol.WINDOW_WIDTH, ZetaSymbol.WINDOW_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // TODO
        game.font.draw(game.batch, "test", 50f, 150f);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {

    }
}
