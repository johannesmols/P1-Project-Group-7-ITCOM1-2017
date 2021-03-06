package itcom.gangstersquirrel.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import itcom.gangstersquirrel.Screens.PlayScreen;

import java.util.Random;

public abstract class Enemy extends Sprite {

    World world;
    Body body;

    final int ENEMY_PIXEL_WIDTH = 32;
    final int ENEMY_PIXEL_HEIGHT = 32;

    protected PlayScreen screen;
    public Vector2 velocity;

    // Gameplay relevant variables
    int[] damageMinMax;
    int health;

    public Enemy(PlayScreen screen, float spawnPositionX, float spawnPositionY){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(spawnPositionX * ENEMY_PIXEL_WIDTH, spawnPositionY * ENEMY_PIXEL_HEIGHT);

        defineEnemy();

//        velocity = new Vector2(-1, -2);
//        body.setActive(false);
    }

    protected int randomDamageValueBetweenMinAndMax() {
        return new Random().nextInt((damageMinMax[1] - damageMinMax[0]) + 1) + damageMinMax[0]; // ((max - min) + 1) + min
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void onPlayerHit();

    // Getter and Setter
    public abstract int[] getDamageMinMax();
    public abstract void setDamageMinMax(int[] newDamageMinMax);
    public abstract int getHealth();
    public abstract void setHealth(int newHealth);
}
