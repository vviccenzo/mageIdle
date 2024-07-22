package mage.idle.player.definition;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mage.idle.MageIdle;
import mage.idle.enemy.manager.EnemyManager;
import mage.idle.player.animation.MageAnimation;
import mage.idle.screen.animation.ScreenAnimation;
import mage.idle.settings.ApplicationAnimation;
import mage.idle.utils.AnimationUtils;

public class Mage {

    private final MageIdle game;
    private final MageAnimation mageAnimationUtils;
    private final EnemyManager enemyManager;
    private final ScreenAnimation screenAnimation;
    private Rectangle mage;
    private Animation<TextureRegion> mageAnimation;

    private float stateTime = 0f;
    private float attackCooldown = 0f;
    private float enemyRespawnCooldown = 0f;
    private boolean isAttacking = false;

    private static final float ATTACK_INTERVAL = 0.1f;
    private static final float ENEMY_RESPAWN_INTERVAL = 5.0f;

    public Mage(MageIdle game, EnemyManager enemyManager) {
        this.game = game;
        this.enemyManager = enemyManager;
        this.screenAnimation = ScreenAnimation.getInstance();
        this.mageAnimationUtils = new MageAnimation();
        initMage();
    }

    private void initMage() {
        this.mage = new Rectangle();
        this.mage.width = 480;
        this.mage.height = 120;
        this.mage.x = 10;
        this.mage.y = 98;
    }

    public void show() {
        this.mageAnimation = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_WALK, 7, 1, 0.1f);
    }

    public void render(float delta) {
        this.stateTime += delta;
        this.attackCooldown -= delta;
        this.enemyRespawnCooldown -= delta;
        checkMageAnimation();

        game.batch.begin();
        game.batch.draw(this.mageAnimation.getKeyFrame(this.stateTime, true), this.mage.getX(), this.mage.getY(), this.mage.getWidth(), this.mage.getHeight());
        game.batch.end();
    }

    private void checkMageAnimation() {
        boolean hasAliveEnemy = enemyManager.getCommonEnemies().stream().anyMatch(enemy -> enemy.isAlive());

        if (hasAliveEnemy) {
            this.screenAnimation.setAttacking(true);

            if (attackCooldown <= 0 && !isAttacking) {
                isAttacking = true;
                mageAnimation = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_ATTACK_1, 7, 1, 0.1f);
                stateTime = 0f;
            }

            if (isAttacking && mageAnimation.isAnimationFinished(stateTime)) {
                enemyManager.attackEnemy(50);
                isAttacking = false;
                attackCooldown = ATTACK_INTERVAL;
            }
        } else {
            this.screenAnimation.setAttacking(false);

            if (enemyRespawnCooldown <= 0) {
                enemyManager.spawnEnemy(100, game);
                enemyRespawnCooldown = ENEMY_RESPAWN_INTERVAL;
            }

            mageAnimation = mageAnimationUtils.checkMageAnimationMovement(mageAnimation, true);
        }
    }

    public void dispose() {
    }
}
