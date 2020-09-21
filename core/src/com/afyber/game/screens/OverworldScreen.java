package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.LoadSave;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class OverworldScreen extends MyScreenAdapter {

    Overworld world;

    public OverworldScreen(ZetaSymbol game) {
        this(game, 0);
    }
    public OverworldScreen(ZetaSymbol game, int levelID) {
        super(game);

        world = new Overworld();
        LoadSave.load(world, levelID, 0);

        setupScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        // TODO
        world.render(game.batch);
        game.font.draw(game.batch, "test", 50f, 150f);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {

    }
}
