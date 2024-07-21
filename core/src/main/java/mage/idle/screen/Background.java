package mage.idle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import mage.idle.MageIdle;
import mage.idle.settings.ApplicationAssets;
import mage.idle.settings.ApplicationSettings;

public class Background {

    private final MageIdle game;

    private Texture backgroundImage;

    private Sprite backgroundSprite1;
    private Sprite backgroundSprite2;

    Background(final MageIdle game) {
        this.game = game;
        this.initAssets();
    }

    public void initAssets() {
        backgroundImage = new Texture(Gdx.files.internal(ApplicationAssets.BACKGROUND_IMAGE));
        backgroundSprite1 = new Sprite(backgroundImage);
        backgroundSprite2 = new Sprite(backgroundImage);

        backgroundSprite1.setSize(ApplicationSettings.SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
        backgroundSprite2.setSize(ApplicationSettings.SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
        backgroundSprite2.setPosition(backgroundSprite1.getWidth(), 0);
    }

    public void renderBackground(float deltaTime) {
        float backgroundSpeed = ApplicationSettings.BACKGROUND_SPEED * deltaTime;

        backgroundSprite1.setX(backgroundSprite1.getX() - backgroundSpeed);
        backgroundSprite2.setX(backgroundSprite2.getX() - backgroundSpeed);

        if (backgroundSprite1.getX() + backgroundSprite1.getWidth() <= 0) {
            backgroundSprite1.setX(backgroundSprite2.getX() + backgroundSprite2.getWidth());
        }

        if (backgroundSprite2.getX() + backgroundSprite2.getWidth() <= 0) {
            backgroundSprite2.setX(backgroundSprite1.getX() + backgroundSprite1.getWidth());
        }

        backgroundSprite1.draw(game.batch);
        backgroundSprite2.draw(game.batch);
    }

    public void dispose() {
        backgroundImage.dispose();
    }
}
