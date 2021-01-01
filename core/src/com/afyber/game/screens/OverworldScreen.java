package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.loadsave.LoadSave;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.Overworld;
import com.afyber.game.api.overworld.AreaColor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class OverworldScreen extends MyScreenAdapter {

    Overworld world;

    int currentLevelID;
    int currentAreaID;

    public OverworldScreen(ZetaSymbol game) {
        this(game, 0, 0);
    }
    public OverworldScreen(ZetaSymbol game, int levelID, int areaID) {
        super(game);

        currentLevelID = levelID;
        currentAreaID = areaID;
        world = new Overworld();
        LoadSave.load(world, currentLevelID, currentAreaID);

        setupScreen();
    }

    @Override
    public void render(float delta) {
        AreaColor areaEnum = AreaColor.idToEnum(currentAreaID);
        Gdx.gl.glClearColor(areaEnum.getRed(), areaEnum.getGreen(), areaEnum.getBlue(), 1f);
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
