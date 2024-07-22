package mage.idle.screen.definition;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import mage.idle.MageIdle;
import mage.idle.enemy.manager.EnemyManager;
import mage.idle.player.definition.Mage;
import mage.idle.settings.ApplicationSettings;

public class GameScreen implements Screen {

    private final Mage mage;
    private final MageIdle game;
    private final Background background;
    private final OrthographicCamera camera;
    private final EnemyManager enemyManager;

    public GameScreen(final MageIdle game) {
        this.game = game;
        this.enemyManager = new EnemyManager();
        this.mage = new Mage(game, enemyManager);
        this.background = new Background(game);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, ApplicationSettings.CAMERA_SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        this.mage.show();
        this.enemyManager.spawnEnemy(100, game);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        batchDraw(delta);
    }

    private void batchDraw(float delta) {
        this.background.render(delta);
        this.enemyManager.render(delta);
        this.mage.render(delta);
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
        // Handle resizing if needed
    }

    @Override
    public void pause() {
        // Handle pause if needed
    }

    @Override
    public void resume() {
        // Handle resume if needed
    }

    @Override
    public void hide() {
        // Handle hide if needed
    }

    @Override
    public void dispose() {
        mage.dispose();
        background.dispose();
        game.batch.dispose();
    }
}
