package mage.idle.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import mage.idle.MageIdle;
import mage.idle.settings.ApplicationAssets;

public class Mage {

    private final MageIdle game;

    private final Texture mageImage;

    private Rectangle mage;

    public Mage(MageIdle game) {
        this.game = game;
        this.mageImage = new Texture(Gdx.files.internal(ApplicationAssets.MAGE_IMAGE));
        this.initMage();
    }

    public void initMage() {
        this.mage = new Rectangle();
        this.mage.width = 120;
        this.mage.height = 40;
        this.mage.x = 100;
        this.mage.y = 100;
    }

    public void renderMage() {
        this.game.batch.begin();
        this.game.batch.draw(this.mageImage, this.mage.x, this.mage.y, this.mage.width, this.mage.height);
        this.game.batch.end();
    }

    public void dispose() {
        this.mageImage.dispose();
    }
}
