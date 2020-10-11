package com.afyber.game.api.battle;

import com.afyber.game.ZetaSymbol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Rythm {

    public boolean playing;
    public boolean previewing;

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
    // 3: "C" note?
    public int[][] noteData;

    Texture square = new Texture(Gdx.files.internal("square.png"));

    // this is the only use of a list in the entire project and it's just because I want to be able to use add()
    // (aka because I'm lazy)
    // and I might even remove it for consistency
    public ArrayList<HitRating> ratings;

    public Rythm() {
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < 4; i++) {
            int dataToDraw = noteData[currentMeasureNum + (currentBeatNum + i >= 4 ? 1 : 0)][(currentBeatNum + i) % timeSigTop];
            int x = -10;
            if (dataToDraw == 1) {
                x = 30;
            }
            else if (dataToDraw == 2) {
                x = 80;
            }
            else if (dataToDraw == 3) {
                x = 130;
            }
            if (i == 0 && deltaFromBeat >= 0) {
                batch.draw(square, x, 60);
            }
            else {
                batch.draw(square, x, 60 + ((i - deltaFromBeat) * 30f));
            }
        }
    }

    public void update(float delta) {
        if (playing) {
            posInSong += delta;
            int currentBeat = currentBeatNum;
            currentBeatNum = Math.round((posInSong + ZetaSymbol.calibration) / secsPerBeat) % timeSigTop;
            currentMeasureNum = (int) Math.floor(posInSong / (secsPerBeat * timeSigTop));
            // the closer to 0 this is the closer to on-beat you are
            // hopefully finally stinking fixed this, now it should be a range from -0.5 to 0.5
            deltaFromBeat = ((posInSong + (secsPerBeat / 2) + ZetaSymbol.calibration) % secsPerBeat - (secsPerBeat / 2)) / secsPerBeat;

            if (currentBeat != currentBeatNum) {
                triedToHit = false;
            }

            if ((ZetaSymbol.input[4] || ZetaSymbol.input[5] || ZetaSymbol.input[6]) && !previewing) {
                attemptHit();
            }
        }
        //System.out.println("beat: " + currentBeatNum + " delta: " + deltaFromBeat);
    }

    public void attemptHit() {
        if (!triedToHit) {
            if (ZetaSymbol.input[4] && noteData[currentMeasureNum][currentBeatNum] == 1 &&
                !ZetaSymbol.input[5] && !ZetaSymbol.input[6]) {
                gradeHit();
            }
            else if (ZetaSymbol.input[5] && noteData[currentMeasureNum][currentBeatNum] == 2 &&
                    !ZetaSymbol.input[4] && !ZetaSymbol.input[6]) {
                gradeHit();
            }
            else if (ZetaSymbol.input[6] && noteData[currentMeasureNum][currentBeatNum] == 3 &&
                    !ZetaSymbol.input[4] && !ZetaSymbol.input[5]) {
                gradeHit();
            }
            else {
                System.out.println("Wrong note.");
            }
            triedToHit = true;
        }
    }

    private void gradeHit() {
        float gradeAbs = Math.abs(deltaFromBeat);

        if (gradeAbs < 0.03f) {
            System.out.println("Perfect!");
            ratings.add(HitRating.PERFECT);
        }
        else if (gradeAbs < 0.06f) {
            System.out.println("Excellent!");
            ratings.add(HitRating.EXCELLENT);
        }
        else if (gradeAbs < 0.1f) {
            System.out.println("Good!");
            ratings.add(HitRating.GOOD);
        }
        else if (gradeAbs < 0.16f) {
            System.out.println("Alright.");
            ratings.add(HitRating.ALRIGHT);
        }
        else {
            System.out.println("Miss.");
            ratings.add(HitRating.MISS);
        }
        System.out.println(deltaFromBeat);
    }

    public void play() {
        posInSong = 0;
        ratings.clear();
        playing = true;
    }
}
