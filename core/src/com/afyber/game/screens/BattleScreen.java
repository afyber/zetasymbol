package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class BattleScreen extends MyScreenAdapter {

    public Music music;
    public float beatsPerSec;
    public float secsPerBeat;

    public float posInSong;
    public int currentMeasureNum;
    public int currentBeatNum;
    public float deltaFromBeat;
    private boolean triedToHit;

    public int timeSigTop;

    public boolean usesEighths;
    // first list is measures
    // second list is beats
    // 0: no note
    // 1: "A" note
    // 2: "B" note
    // 3: both notes
    public int[][] noteData;

    public BattleScreen(ZetaSymbol game, int monsterID, int areaID) {
        super(game);
        LoadSave.loadMusic(this, monsterID, areaID);
        setupScreen();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.031f, 0.102f, 0.047f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        game.batch.begin();

        game.font.draw(game.batch, String.valueOf(noteData[currentMeasureNum][currentBeatNum]), 50, 50);

        game.font.draw(game.batch, String.valueOf(currentBeatNum), 0, 16);
        game.font.draw(game.batch, String.valueOf(deltaFromBeat), 0, 32);

        game.batch.end();

        update(delta);
    }

    public void update(float delta) {
        if (!music.isPlaying()) {
            posInSong = 0;
            music.play();
        }
        else {
            posInSong += delta;
            int currentBeat = currentBeatNum;
            currentBeatNum = (int)Math.floor(posInSong / secsPerBeat) % timeSigTop;
            currentMeasureNum = (int)Math.floor(posInSong / (secsPerBeat * timeSigTop));
            // the closer to 0 this is the closer to on-beat you are
            deltaFromBeat = (posInSong + 0.5f + game.calibration) % secsPerBeat - 0.5f;

            if (currentBeat != currentBeatNum) {
                triedToHit = false;
            }

            if (!triedToHit) {
                if (noteData[currentMeasureNum][currentBeatNum] == 1) {
                    if (ZetaSymbol.input[4]) {
                        gradeHit();
                    }
                }
                if (noteData[currentMeasureNum][currentBeatNum] == 2) {
                    if (ZetaSymbol.input[5]) {
                        gradeHit();
                    }
                }
                if (noteData[currentMeasureNum][currentBeatNum] == 3) {
                    if (ZetaSymbol.input[4] && ZetaSymbol.input[5]) {
                        gradeHit();
                    }
                }
            }
        }
    }

    private void gradeHit() {
        float gradeAbs = Math.abs(deltaFromBeat);

        if (gradeAbs < 0.02f) {
            System.out.println("Absolutely Perfect!");
        }
        else if (gradeAbs < 0.05f) {
            System.out.println("Perfect!");
        }
        else if (gradeAbs < 0.125f) {
            System.out.println("Good!");
        }
        else if (gradeAbs < 0.2f) {
            System.out.println("Alright.");
        }
        else {
            System.out.println("Miss.");
        }
        System.out.println(deltaFromBeat * secsPerBeat);

        triedToHit = true;
    }
}
