package mage.idle.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import mage.idle.MageIdle;
import mage.idle.player.Mage;
import mage.idle.settings.ApplicationSettings;

public class GameScreen implements Screen {

    private final Mage mage;
    private final MageIdle game;
    private final Background background;
    private final OrthographicCamera camera;

    public GameScreen(final MageIdle game) {
        this.game = game;
        this.mage = new Mage(game);
        this.background = new Background(game);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, ApplicationSettings.CAMERA_SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        this.mage.show();
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.updateCamera();
        this.batchDraw(delta);
    }

    private void batchDraw(float delta) {
        this.game.batch.begin();

        this.background.renderBackground(delta);
        this.mage.renderMage(delta);

        this.game.batch.end();
    }

    private void clearScreen() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }

    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
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
        mage.dispose();
        background.dispose();
        game.batch.dispose();
    }
}
