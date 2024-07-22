package mage.idle.enemy.definition;

public interface Enemy {

    void render(float delta);

    boolean isAlive();

    void takeDamage(int damage);

}
