package mage.idle.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import mage.idle.MageIdle;
import mage.idle.player.Mage;

public class GameScreen implements Screen {

    private final MageIdle game;

    private final Mage mage;

    private final Background background;

    private final OrthographicCamera camera;

    public GameScreen(final MageIdle game) {
        this.game = game;
        this.mage = new Mage(game);
        this.background = new Background(game);
        this.background.initAssets();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.updateCamera();
        this.background.renderBackground(delta);
        this.mage.renderMage();
    }

    private void clearScreen() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }

    private void updateCamera() {
        this.camera.update();
        this.game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        this.mage.dispose();
        this.background.dispose();
    }
}
