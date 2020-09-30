package com.afyber.game.screens;

import com.afyber.game.ZetaSymbol;
import com.afyber.game.api.MyScreenAdapter;
import com.afyber.game.api.loadsave.LoadSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;

public class BattleScreen extends MyScreenAdapter {

    private Music music;
    private float beatsPerSec;
    private float secsPerBeat;

    private float posInSong;
    private int currentMeasureNum;
    private int currentBeatNum;
    private float deltaFromBeat;

    private int timeSigTop;

    private boolean usesEighths;
    // first list is measures
    // second list is beats
    // 0: no note
    // 1: "A" note
    // 2: "B" note
    private int[][] noteData;

    public BattleScreen(ZetaSymbol game, int monsterID, int areaID) {
        super(game);
        loadMusic(monsterID, areaID);
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
            currentBeatNum = (int)Math.floor(posInSong / secsPerBeat) % timeSigTop;
            currentMeasureNum = (int)Math.floor(posInSong / (secsPerBeat * timeSigTop));
            deltaFromBeat = (posInSong + 0.5f) % secsPerBeat - 0.5f;
        }
    }

    public void loadMusic(int musicID, int areaID) {
        this.music = Gdx.audio.newMusic(Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + musicID + ".wav"));
        FileHandle data = Gdx.files.internal("music/" + LoadSave.areaIDToString(areaID) + "/" + musicID + ".txt");
        String[] textData = data.readString().split("\n");
        for (String line:
             textData) {
            if (!line.startsWith("|")) {
                String[] args = line.replaceAll("[ \r]", "").split(",");
                this.beatsPerSec = Integer.parseInt(args[0]);
                this.secsPerBeat = 60f / this.beatsPerSec;
                this.timeSigTop = Integer.parseInt(args[1]);
                this.usesEighths = Boolean.getBoolean(args[2]);
                this.noteData = new int[100][this.timeSigTop];
            }
            else {
                String[] args = line.replace("|", "").split(" ");
                for (int i = 0; i < this.timeSigTop; i++) {
                    this.noteData[0][i] = Integer.parseInt(args[i]);
                }
            }
        }
    }
}
