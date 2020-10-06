package com.afyber.game.api.battle;

import com.afyber.game.ZetaSymbol;

public class Rythm {

    public boolean playing;

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

    public Rythm() {

    }

    public void update(float delta) {
        if (playing) {
            posInSong += delta;
            int currentBeat = currentBeatNum;
            currentBeatNum = (int) Math.floor(posInSong / secsPerBeat) % timeSigTop;
            currentMeasureNum = (int) Math.floor(posInSong / (secsPerBeat * timeSigTop));
            // the closer to 0 this is the closer to on-beat you are
            deltaFromBeat = (posInSong + 0.5f + ZetaSymbol.calibration) % secsPerBeat - 0.5f;

            if (currentBeat != currentBeatNum) {
                triedToHit = false;
            }

            if (ZetaSymbol.input[4] || ZetaSymbol.input[5] || ZetaSymbol.input[6]) {
                attemptHit();
            }
        }
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
                System.out.println("Miss");
            }
            triedToHit = true;
        }
    }

    private void gradeHit() {
        float gradeAbs = Math.abs(deltaFromBeat);

        if (gradeAbs < 0.01f) {
            System.out.println("Absolutely Perfect!");
        }
        else if (gradeAbs < 0.04f) {
            System.out.println("Perfect!");
        }
        else if (gradeAbs < 0.1f) {
            System.out.println("Good!");
        }
        else if (gradeAbs < 0.15f) {
            System.out.println("Alright.");
        }
        else {
            System.out.println("Miss.");
        }
        System.out.println(deltaFromBeat * secsPerBeat);
    }

    public void play() {
        playing = true;
    }

    public void pause() {
        playing = false;
    }
}
