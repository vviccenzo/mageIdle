package mage.idle.enemy.manager;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mage.idle.MageIdle;
import mage.idle.enemy.definition.CommonEnemy;
import mage.idle.enemy.definition.Enemy;

public class EnemyManager {

    private final List<Enemy> enemies;
    private final Texture enemyTexture;
    private int enemiesDefeated = 0;

    public EnemyManager() {
        this.enemies = new ArrayList<>();
        this.enemyTexture = null; // Not used directly in this implementation
    }

    public void spawnEnemy(int initialHealth, MageIdle mage) {
        enemies.add(new CommonEnemy(initialHealth, mage)); // Passing null for the game object
    }

    public void render(float delta) {
        enemies.stream().filter(Enemy::isAlive).forEach(enemy -> enemy.render(delta));
    }

    public void attackEnemy(int damage) {
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.isAlive()) {
                enemy.takeDamage(damage);
                if (!enemy.isAlive()) {
                    enemiesDefeated++;
                    iterator.remove();
                }
            }
        }
    }

    public List<Enemy> getCommonEnemies() {
        return this.enemies;
    }
}
