package com.afyber.game.api;

import com.afyber.game.ZetaSymbol;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyScreenAdapter extends ScreenAdapter {
    protected ZetaSymbol game;

    protected Viewport viewport;
    protected OrthographicCamera camera;

    public MyScreenAdapter(ZetaSymbol game) {
        this.game = game;
    }

    protected void setupScreen() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZetaSymbol.SCREEN_WIDTH, ZetaSymbol.SCREEN_HEIGHT);
        viewport = new ScalingViewport(Scaling.stretch, ZetaSymbol.WINDOW_WIDTH, ZetaSymbol.WINDOW_HEIGHT);
    }

    protected void updateScreen() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }
}
