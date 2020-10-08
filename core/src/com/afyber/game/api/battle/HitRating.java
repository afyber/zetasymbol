package com.afyber.game.api.battle;

public enum HitRating {
    MISS(-1),
    ALRIGHT(1),
    GOOD(2),
    EXCELLENT(3),
    PERFECT(4);

    private final int damage;

    private HitRating(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
