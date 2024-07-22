package mage.idle.player.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mage.idle.settings.ApplicationAnimation;
import mage.idle.utils.AnimationUtils;

public class MageAnimation {

    private boolean isWalking = true;

    private boolean wasTouchedLastFrame = false;

    public Animation<TextureRegion> checkMageAnimationMovement(Animation<TextureRegion> mageAnimation, boolean isAfterKillEnemy) {

        Animation<TextureRegion> mageAnimationNew = mageAnimation;

        if (Gdx.input.isTouched() && !wasTouchedLastFrame) {
            isWalking = !isWalking;

            if (isWalking) {
                mageAnimationNew = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_WALK, 7, 1, 0.1f);
            } else {
                mageAnimationNew = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_RUN, 8, 1, 0.1f);
            }
        }

        if(isAfterKillEnemy) {
            mageAnimationNew = AnimationUtils.createAnimation(ApplicationAnimation.MAGE_ANIMATION_RUN, 8, 1, 0.1f);
        }

        wasTouchedLastFrame = Gdx.input.isTouched();

        return mageAnimationNew;
    }

}
