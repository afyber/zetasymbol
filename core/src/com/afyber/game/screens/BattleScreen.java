package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.battle.GUI;
import com.afyber.game.api.battle.HitRating;
import com.afyber.game.api.battle.Rythm;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class BattleScreen extends MyScreenAdapter {

    public Music music;
    private Rythm rythm;
    private GUI gui;

    // monster info
    public int monsterHealth;
    public int monsterMaxHealth;
    public int monsterDef;

    // What's happening in the battle
    // start
    // 0: The player is in the menu
    // 1: Preview of the rythm section
    // 2: The player is doing the rythm section
    // 3: The monster is taking damage
    // back to 0 unless the monster is dead
    public int battleState;
    // 0: Continue
    // 1: Item
    public int menuPos;
    // 0: Has not played this turn
    // 1: Is playing
    // 2: Played for the preview
    // 3: Finished playing this turn
    public int musicState;

    public BattleScreen(ZetaSymbol game, int monsterID, int areaID) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + monsterID + ".wav"));
        rythm = new Rythm();
        LoadSave.loadMusic(rythm, monsterID, areaID);
        LoadSave.loadMonster(this, monsterID, areaID);
        gui = new GUI();
        battleState = 0;
        menuPos = 0;
        musicState = 0;
        setupScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.031f, 0.102f, 0.047f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        gui.drawRect(64, 156, 32, 2, game.batch);

        game.font.draw(game.batch, String.valueOf(rythm.noteData[rythm.currentMeasureNum][rythm.currentBeatNum]), 50, 50);

        game.font.draw(game.batch, String.valueOf(rythm.currentBeatNum), 0, 16);
        game.font.draw(game.batch, String.valueOf(rythm.deltaFromBeat), 0, 32);

        game.font.draw(game.batch, "B" + battleState, 112, 16);
        game.font.draw(game.batch, "M" + musicState, 112, 32);
        game.font.draw(game.batch, "MH" + monsterHealth, 0, 96);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        switch (battleState) {
            case 0:
                if (ZetaSymbol.input[4]) {
                    battleState = 1;
                }
                break;
            case 1:
                if (!music.isPlaying()) {
                    if (musicState == 0) {
                        music.play();
                        musicState = 1;
                    }
                    else if (musicState == 1) {
                        musicState = 2;
                        battleState = 2;
                    }
                }
                break;
            case 2:
                if (!music.isPlaying()) {
                    if (musicState == 2) {
                        music.play();
                        rythm.play();
                        musicState = 1;
                    }
                    else if (musicState == 1) {
                        musicState = 3;
                        battleState = 3;
                    }
                }
                else {
                    rythm.update(delta);
                }
                break;
            case 3:
                ArrayList<HitRating> ratings = rythm.ratings;
                for (HitRating rating:
                        ratings) {
                    monsterHealth -= rating.getDamage();
                }
                battleState = 0;
                musicState = 0;
                if (monsterHealth >= monsterMaxHealth) {
                    monsterHealth = monsterMaxHealth;
                }
                if (monsterHealth <= 0) {
                    exitBattle();
                }
                break;
        }
    }

    private void exitBattle() {
        game.setScreen(game.overworld);
    }
}
