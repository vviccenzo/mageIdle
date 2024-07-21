package mage.idle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import mage.idle.Mage;
import mage.idle.settings.ApplicationSettings;
import mage.idle.settings.ApplicationText;

public class MainMenuScreen implements Screen {

    private final Mage game;

    private final OrthographicCamera camera;

    public MainMenuScreen(final Mage game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, ApplicationSettings.APPLICATION_WIDTH, ApplicationSettings.APPLICATION_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, ApplicationText.WELCOME_TITLE, 400, 205);
        game.font.draw(game.batch, ApplicationText.WELCOME_BOTTOM_TITLE, 100, 100);
        game.batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

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

    }
}
