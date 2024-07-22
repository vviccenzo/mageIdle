package mage.idle.enemy.definition;

public class GenericEnemy {

    protected int maxHealth;

    protected int health;

    protected boolean isAlive;

    public GenericEnemy(int maxHealth, int health, boolean isAlive) {
        this.maxHealth = maxHealth;
        this.health = health;
        this.isAlive = isAlive;
    }

}
