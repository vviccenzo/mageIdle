package mage.idle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import mage.idle.MageIdle;
import mage.idle.screen.animation.ScreenAnimation;
import mage.idle.settings.ApplicationAssets;
import mage.idle.settings.ApplicationSettings;

public class Background {

    private final MageIdle game;

    private Texture backgroundImage;

    private Sprite backgroundSprite1;

    private Sprite backgroundSprite2;

    private final ScreenAnimation screenAnimation = new ScreenAnimation();

    Background(final MageIdle game) {
        this.game = game;
        this.initAssets();
    }

    public void initAssets() {
        this.backgroundImage = new Texture(Gdx.files.internal(ApplicationAssets.BACKGROUND_IMAGE));
        this.backgroundSprite1 = new Sprite(this.backgroundImage);
        this.backgroundSprite2 = new Sprite(this.backgroundImage);

        this.backgroundSprite1.setSize(ApplicationSettings.SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
        this.backgroundSprite2.setSize(ApplicationSettings.SCREEN_WIDTH, ApplicationSettings.SCREEN_HEIGHT);
        this.backgroundSprite2.setPosition(this.backgroundSprite1.getWidth(), 0);
    }

    public void renderBackground(float deltaTime) {
        float backgroundSpeed = this.screenAnimation.checkBackgroundSpeed() * deltaTime;

        this.backgroundSprite1.setX(this.backgroundSprite1.getX() - backgroundSpeed);
        this.backgroundSprite2.setX(this.backgroundSprite2.getX() - backgroundSpeed);

        if (this.backgroundSprite1.getX() + this.backgroundSprite1.getWidth() <= 0) {
            this.backgroundSprite1.setX(this.backgroundSprite2.getX() + this.backgroundSprite2.getWidth());
        }

        if (this.backgroundSprite2.getX() + this.backgroundSprite2.getWidth() <= 0) {
            this.backgroundSprite2.setX(this.backgroundSprite1.getX() + this.backgroundSprite1.getWidth());
        }

        this.backgroundSprite1.draw(this.game.batch);
        this.backgroundSprite2.draw(this.game.batch);
    }

    public void dispose() {
        this.backgroundImage.dispose();
    }
}
