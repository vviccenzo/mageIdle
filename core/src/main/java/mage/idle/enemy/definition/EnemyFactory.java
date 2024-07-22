package mage.idle.enemy.definition;

import mage.idle.MageIdle;

public class EnemyFactory {

    public EnemyFactory() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class.");
    }

    public static CommonEnemy createCommonEnemy(int initialHealth, MageIdle game) {
        return new CommonEnemy(initialHealth, game);
    }
}
