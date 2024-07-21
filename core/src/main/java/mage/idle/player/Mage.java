package mage.idle.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mage.idle.MageIdle;
import mage.idle.settings.ApplicationAnimation;
import mage.idle.settings.ApplicationAssets;
import mage.idle.utils.AnimationUtils;

public class Mage {

    private final MageIdle game;

    private final Texture mageImage;

    private Rectangle mage;

    private float stateTime = 0f;

    private Animation<TextureRegion> walkAnimation;

    public Mage(MageIdle game) {
        this.game = game;
        this.mageImage = new Texture(Gdx.files.internal(ApplicationAssets.MAGE_IMAGE));
        this.initMage();
    }

    public void initMage() {
        this.mage = new Rectangle();
        this.mage.width = 480;
        this.mage.height = 120;
        this.mage.x = 20;
        this.mage.y = 98;
    }

    public void show() {
        this.walkAnimation = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_WALK, 7, 1, 0.1f);
    }

    public void renderMage(float delta) {
        this.stateTime += delta;
        this.game.batch.draw(this.walkAnimation.getKeyFrame(this.stateTime, true), this.mage.getX(), this.mage.getY(), this.mage.getWidth(), this.mage.getHeight());
    }

    public void dispose() {
        this.mageImage.dispose();
    }

}
