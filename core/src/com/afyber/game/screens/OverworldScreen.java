package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.loadsave.LoadSave;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.Overworld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class OverworldScreen extends MyScreenAdapter {

    Overworld world;

    int currentLevelID;
    int currentAreaID;

    public OverworldScreen(ZetaSymbol game) {
        this(game, 0);
    }
    public OverworldScreen(ZetaSymbol game, int levelID) {
        super(game);

        currentLevelID = levelID;
        currentAreaID = 0;
        world = new Overworld();
        LoadSave.load(world, currentLevelID, currentAreaID);

        setupScreen();
    }

    @Override
    public void render(float delta) {
        // there's no need for a one-color floor tile if the clear color is the same color as the tile :)
        Gdx.gl.glClearColor(0.031f, 0.102f, 0.047f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        world.render(game.batch);

        game.batch.end();

        update();
    }

    public void update() {
        ZetaSymbol.input.update();

        world.update();

        if (world.levelTransitionDirection != 0) {
            int tempDirection = world.levelTransitionDirection;
            currentLevelID += world.levelTransitionDirection;
            world = new Overworld();
            world.levelTransitionDirection = tempDirection;
            LoadSave.load(world, currentLevelID, 0);
            world.levelTransitionDirection = 0;
        }

        if (world.currentEncounterID != -1) {
            game.setScreen(new BattleScreen(game, world.currentEncounterID, 0));
            game.overworld = this;
            world.currentEncounterID = -1;
            world.framesSinceLastEncounter = 0;
        }
    }

    @Override
    public void dispose() {
        world.dispose();
        super.dispose();
    }
}
