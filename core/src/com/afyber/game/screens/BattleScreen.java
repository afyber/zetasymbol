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
    // 0: Fight
    // 1: Run
    public int menuPos;
    // 0: Has not played this turn
    // 1: Is playing
    // 2: Has played for the preview
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


        if (ZetaSymbol.DEBUG) {
            game.font.draw(game.batch, String.valueOf(rythm.currentBeatNum), 0, 16);
            game.font.draw(game.batch, String.valueOf(rythm.deltaFromBeat), 0, 32);

            game.font.draw(game.batch, "B" + battleState, 112, 16);
            game.font.draw(game.batch, "M" + musicState, 112, 32);
            game.font.draw(game.batch, "MH" + monsterHealth, 0, 96);
        }

        if (musicState == 0) {
            gui.drawSelector(game.batch, 69, 10 + (menuPos * 17));
            gui.drawRect(game.batch, 64, 156, 38, 2);
        }
        else if (musicState == 1) {
            rythm.draw(game.batch);
            for (int i = 0; i < 3; i++) {
                if (rythm.intrumentType == 0 || rythm.intrumentType == 1) {
                    gui.drawRect(game.batch, 28, 32, 62 + i * 30, 62 + i * 30, 1);
                }
                if (rythm.intrumentType == 1) {
                    gui.drawRect(game.batch, 78, 82, 62 + i * 30, 62 + i * 30, 1);
                }
                if (rythm.intrumentType == 0 || rythm.intrumentType == 1) {
                    gui.drawRect(game.batch, 128, 132, 62 + i * 30, 62 + i * 30, 1);
                }
            }
        }

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        ZetaSymbol.input.update();

        switch (battleState) {
            case 0:
                if (ZetaSymbol.input.keyJustPressed(4)) {
                    if (menuPos == 0) {
                        battleState = 1;
                    }
                    else if (menuPos == 1) {
                        if (Math.random() * 100 > 67) {
                            exitBattle();
                        }
                        else {
                            battleState = 1;
                        }
                    }
                }
                if (ZetaSymbol.input.keyJustPressed(3)) {
                    menuPos += 1;
                }
                if (ZetaSymbol.input.keyJustPressed(2)) {
                    menuPos -= 1;
                }
                if (menuPos > 1) {
                    menuPos = 0;
                }
                else if (menuPos < 0) {
                    menuPos = 1;
                }
                break;
            case 1:
                if (!music.isPlaying()) {
                    if (musicState == 0) {
                        music.play();
                        rythm.play();
                        rythm.previewing = true;
                        musicState = 1;
                    }
                    else if (musicState == 1) {
                        musicState = 2;
                        battleState = 2;
                    }
                }
                else {
                    rythm.update(delta);
                }
                break;
            case 2:
                if (!music.isPlaying()) {
                    if (musicState == 2) {
                        music.play();
                        rythm.play();
                        rythm.previewing = false;
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

    public void dispose() {
        rythm.dispose();
        music.dispose();
        gui.dispose();
    }
}
