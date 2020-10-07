package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.battle.Rythm;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class BattleScreen extends MyScreenAdapter {

    public Music music;
    private Rythm rythm;

    // monster info
    public int health;
    public int def;

    public BattleScreen(ZetaSymbol game, int monsterID, int areaID) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + monsterID + ".wav"));
        rythm = new Rythm();
        LoadSave.loadMusic(rythm, monsterID, areaID);
        LoadSave.loadMonster(this, monsterID, areaID);
        setupScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.031f, 0.102f, 0.047f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        game.font.draw(game.batch, String.valueOf(rythm.noteData[rythm.currentMeasureNum][rythm.currentBeatNum]), 50, 50);

        game.font.draw(game.batch, String.valueOf(rythm.currentBeatNum), 0, 16);
        game.font.draw(game.batch, String.valueOf(rythm.deltaFromBeat), 0, 32);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        if (!music.isPlaying()) {
            rythm.posInSong = 0;
            music.play();
            rythm.play();
        }
        else {
            rythm.update(delta);
        }
    }
}
