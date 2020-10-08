package com.afyber.game.api.battle;

public enum HitRating {
    MISS(0),
    ALRIGHT(1),
    GOOD(2),
    EXCELLENT(3),
    PERFECT(4);

    public int damage;

    private HitRating(int damage) {
        this.damage = damage;
    }
}
