package mage.idle.screen.animation;

import com.badlogic.gdx.Gdx;

import mage.idle.settings.ApplicationSettings;

public class ScreenAnimation {

    private static ScreenAnimation instance = null;
    private boolean isWalking = true;
    private boolean wasTouchedLastFrame = false;
    private boolean isAttacking = false;

    private ScreenAnimation() {
    }

    public static ScreenAnimation getInstance() {
        if (instance == null) {
            instance = new ScreenAnimation();
        }
        return instance;
    }

    public void setAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public float checkBackgroundSpeed() {
        if (isAttacking) {
            return 0;
        }

        float backgroundSpeed = ApplicationSettings.BACKGROUND_SPEED_WALK;

        boolean isTouched = Gdx.input.isTouched();

        if (isTouched && !wasTouchedLastFrame) {
            isWalking = !isWalking;
        }

        if (!isWalking) {
            backgroundSpeed = ApplicationSettings.BACKGROUND_SPEED_RUN;
        }

        wasTouchedLastFrame = isTouched;

        return backgroundSpeed;
    }
}
