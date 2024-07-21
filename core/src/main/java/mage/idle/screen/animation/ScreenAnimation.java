package mage.idle.screen.animation;

import com.badlogic.gdx.Gdx;

import mage.idle.settings.ApplicationSettings;

public class ScreenAnimation {

    private boolean isWalking = true;

    private boolean wasTouchedLastFrame = false;

    public float checkBackgroundSpeed() {
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
