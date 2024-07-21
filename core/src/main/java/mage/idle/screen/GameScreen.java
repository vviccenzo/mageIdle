package mage.idle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import mage.idle.Mage;
import mage.idle.settings.ApplicationAssets;
import mage.idle.settings.ApplicationSettings;

public class GameScreen implements Screen {

    private final Mage game;

    private Texture mageImage;
    private Texture backgroundImage;
    private Sprite backgroundSprite1;
    private Sprite backgroundSprite2;
    private OrthographicCamera camera;
    private Rectangle mage;

    public GameScreen(final Mage game) {
        this.game = game;

        initAssets();
        initCamera();
        initMage();
    }

    private void initAssets() {
        mageImage = new Texture(Gdx.files.internal(ApplicationAssets.BUCKET_DROP_IMAGE));
        backgroundImage = new Texture(Gdx.files.internal(ApplicationAssets.BACKGROUND_IMAGE));

        backgroundSprite1 = new Sprite(backgroundImage);
        backgroundSprite2 = new Sprite(backgroundImage);
        backgroundSprite2.setPosition(backgroundSprite1.getWidth(), 0);
    }

    private void initCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void initMage() {
        mage = new Rectangle();
        mage.width = 194;
        mage.height = 64;
        mage.x = (800 - mage.width) / 2;
        mage.y = 20;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        renderBackground(delta);
        renderMage();
    }

    private void clearScreen() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
    }

    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    private void renderBackground(float deltaTime) {
        backgroundSprite1.setX(backgroundSprite1.getX() - ApplicationSettings.BACKGROUND_SPEED * deltaTime);
        backgroundSprite2.setX(backgroundSprite2.getX() - ApplicationSettings.BACKGROUND_SPEED * deltaTime);

        if (backgroundSprite1.getX() + backgroundSprite1.getWidth() <= 0) {
            backgroundSprite1.setX(backgroundSprite2.getX() + backgroundSprite2.getWidth());
        }

        if (backgroundSprite2.getX() + backgroundSprite2.getWidth() <= 0) {
            backgroundSprite2.setX(backgroundSprite1.getX() + backgroundSprite1.getWidth());
        }

        game.batch.begin();
        backgroundSprite1.draw(game.batch);
        backgroundSprite2.draw(game.batch);
        game.batch.end();
    }

    private void renderMage() {
        game.batch.begin();
        game.batch.draw(mageImage, mage.x, mage.y, mage.width, mage.height);
        game.batch.end();
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
        mageImage.dispose();
        backgroundImage.dispose();
    }
}
