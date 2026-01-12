import java.util.ArrayList;

import processing.core.*;
import processing.sound.*;

public class App extends PApplet {
   Hunter hunter;
    Obstacle rock;
    PImage background;
    float moveX = 0;
    float moveY = 0;
    float speed = 5;
    int lastShotTime;
    boolean shooting;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<NPC> NPCs = new ArrayList<NPC>();
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    int coins;

    SoundFile music;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        hunter = new Hunter(50, 50, 5, 500, 400, this);
        cowMaker();
        rock = new Obstacle(300, 1000, 10, "rock", this);

        background = loadImage("grass background.jpg");
        background.resize(2000, 1600);
        music = new SoundFile(this, "music.mp3");
        // music.play();

    }

    public void settings() {
        size(1000, 800);
    }

    public void draw() {
        background(0);
        hunter.move(moveX, moveY);

        pushMatrix();// chatgpt
        translate(width / 2 - hunter.getX(), height / 2 - hunter.getY());// move "coordinate grid" as a whole constantly
                                                                         // as the hunter moves

        image(background, 0, 0);// draw background
        NPCMovement();

        bulletChecker();
        for (Obstacle o : obstacles) {

            
        }
        rock.display();
                if (rock.collidesWith(hunter.getX(), hunter.getX(), hunter.getSize())) {
                speed = 0;
            }


        popMatrix();// chatgpt

        hunter.display();
        hunter.displayGun();
    }

    public boolean enemyCollisions(Bullet b, NPC e) {
        if (!e.isAlive()) {
            return false;
        }
        float bulletRadius = b.getBulletSize() / 2; // divide by 2 to get radius
        float enemyRadius = e.getNPCsSize() / 2;

        float dx = b.getBulletX() - e.getNPCsX();
        float dy = b.getBulletY() - e.getNPCsY();

        float distance = sqrt(dx * dx + dy * dy);

        return distance <= bulletRadius + enemyRadius;
    }

    public void bulletChecker() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.update();
            b.display();

            boolean hitEnemy = false;

            for (NPC n : NPCs) {
                if (enemyCollisions(b, n)) {
                    n.kill(); // enemy dies
                    hitEnemy = true;
                    coins = coins + 10;// gives money
                    break;
                }
            }

            if (hitEnemy || b.isOffScreen()) {
                bullets.remove(i);
            }
        }
    }

    public void cowMaker() {
        NPCs.add(new NPC("cow", 500, 200, 50, 2, this, hunter));
    }

    public void NPCMovement() {
        for (NPC n : NPCs) {
            n.movement();
            n.display();
        }
    }

    public void keyPressed() {
        if (keyCode == 'D')
            moveX = speed;
        if (keyCode == 'A')
            moveX = -speed;
        if (keyCode == 'W')
            moveY = -speed;
        if (keyCode == 'S')
            moveY = speed;

        if (key == ' ') {
            // float worldMouseX = mouseX + hunter.getX() - width / 2;
            // float worldMouseY = mouseY + hunter.getY() - height / 2;
            shoot();
            shooting = true;

        }
    }

    public void keyReleased() {
        if (keyCode == 'D' || keyCode == 'A')
            moveX = 0;
        if (keyCode == 'W' || keyCode == 'S')
            moveY = 0;
        if (key == ' ') {
            shooting = false;

        }
    }

    public void shoot() {
        bullets.add(new Bullet(hunter.getX(), hunter.getY(), 10f, 8f, mouseX + hunter.getX() - width / 2,
                mouseY + hunter.getY() - height / 2, this));

    }
}
