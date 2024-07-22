package mage.idle.enemy.definition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import mage.idle.MageIdle;
import mage.idle.enemy.settings.Settings;
import mage.idle.settings.ApplicationAnimation;
import mage.idle.utils.AnimationUtils;

public class CommonEnemy extends GenericEnemy implements Enemy {

    private final MageIdle game;

    private final ShapeRenderer shapeRenderer;

    private float stateTime = 0f;

    private Rectangle enemy;

    private boolean isAppearing = true;

    private Animation<TextureRegion> enemyAnimation;

    private Animation<TextureRegion> idleAnimation;

    public CommonEnemy(int initialHealth, MageIdle game) {
        super(initialHealth, initialHealth, Boolean.TRUE);

        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        initEnemy();
    }

    private void initEnemy() {
        this.enemy = new Rectangle();
        this.enemy.width = 360;
        this.enemy.height = 90;
        this.enemy.x = 390;
        this.enemy.y = 100;
        loadAnimations();
    }

    private void loadAnimations() {
        this.enemyAnimation = AnimationUtils.createAnimation(ApplicationAnimation.GHOST_APPEARS, 6, 1, 0.2f);
        this.idleAnimation = AnimationUtils.createAnimation(ApplicationAnimation.GHOST_IDLE, 7, 1, 0.2f);
    }

    @Override
    public void render(float delta) {
        if (this.isAlive) {
            this.stateTime += delta;
            Animation<TextureRegion> currentAnimation = isAppearing ? enemyAnimation : idleAnimation;

            if (isAppearing && enemyAnimation.isAnimationFinished(stateTime)) {
                isAppearing = false;
                stateTime = 0f;
            }

            game.batch.begin();
            game.batch.draw(currentAnimation.getKeyFrame(stateTime, true), this.enemy.getX(), this.enemy.getY(), this.enemy.getWidth(), this.enemy.getHeight());
            game.batch.end();

            drawHealthBar();
        }
    }

    private void drawHealthBar() {
        shapeRenderer.setProjectionMatrix(game.batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(enemy.getX(), enemy.getY() + enemy.getHeight(), enemy.getWidth(), Settings.BAR_HEIGHT);
        shapeRenderer.end();

        shapeRenderer.setColor(Color.RED);
        float healthBarWidth = (health / (float) maxHealth) * enemy.getWidth();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(enemy.getX(), enemy.getY() + enemy.getHeight(), healthBarWidth, Settings.BAR_HEIGHT);
        shapeRenderer.end();
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int damage) {
        if (this.isAlive) {
            this.health -= damage;
            if (this.health <= 0) {
                this.isAlive = false;
            }
        }
    }

}
