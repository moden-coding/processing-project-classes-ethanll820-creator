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
    int level = 1;
    int scene = 0;

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
        if (scene == 0) {
            drawTitleScreen();

        } else if (scene == 1) {
            background(0);
            hunter.move(moveX, moveY);

            pushMatrix();// chatgpt
            translate(width / 2 - hunter.getX(), height / 2 - hunter.getY());// move "coordinate grid" as a whole
                                                                             // constantly
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
        } else if (scene == 2) {
            drawShopScreen();
        }
        if (scene == 1 && allEnemiesDead()) {// go back to title screen
            scene = 0;
            level++;
            NPCs.clear();
        }

    }

    public void drawTitleScreen() { // Title
        background(30, 120, 60);

        textAlign(CENTER);
        textSize(64);
        fill(255);
        text("HUNTER", width / 2, 150);

        fill(0, 180, 0);// Play button
        rect(350, 300, 300, 80, 20);
        fill(255);
        textSize(32);
        text("PLAY LEVEL " + level, width / 2, 355);

        fill(180, 120, 0); // Shop button
        rect(350, 420, 300, 80, 20);
        fill(255);
        text("SHOP", width / 2, 475);
    }

    public void drawShopScreen() {
        rectMode(CORNER);
        background(40); // clear old screen

        textAlign(CENTER);
        textSize(32);
        fill(255);

        text("SHOP", width / 2, 150);
        text("You have " + coins + " coins", width / 2, 250);

        // Back button to go back to title
        fill(180, 0, 0);
        rect(350, 400, 300, 80, 20);
        fill(255);
        text("BACK", width / 2, 455);
    }

    public boolean enemyCollisions(Bullet b, NPC e) {
        if (!e.isAlive()) {
            return false;
        }
        float bulletRadius = b.getBulletSize() / 2; // divide by 2 to get radius
        float enemyRadius = e.getNPCsSize() / 2;

        float dx = b.getBulletX() - e.getNPCsX();
        float dy = b.getBulletY() - e.getNPCsY();

        float distance = sqrt(dx * dx + dy * dy); //pythaogorean theorem

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

    public boolean allEnemiesDead() {
        for (NPC n : NPCs) {
            if (n.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void cowMaker() {
        if (level == 1) {
            for (int i = 0; i < 5; i++) {
                NPCs.add(new NPC(
                        "cow",
                        random(100, 1900),
                        random(100, 1500),
                        50,
                        2,
                        this,
                        hunter));
            }
        } else if (level == 2) {
            for (int i = 0; i < 10; i++) {
                NPCs.add(new NPC(
                        "cow",
                        random(100, 1900),
                        random(100, 1500),
                        50,
                        4,
                        this,
                        hunter));
            }
        }
    }

    public void NPCMovement() {
        for (NPC n : NPCs) {
            n.movement();
            n.display();
        }
    }

    public void shoot() {
        bullets.add(new Bullet(hunter.getX(), hunter.getY(), 10f, 8f, mouseX + hunter.getX() - width / 2,
                mouseY + hunter.getY() - height / 2, this));

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

    public void mousePressed() {
        if (scene == 0 && mouseX > 350 && mouseX < 650 && mouseY > 300 && mouseY < 380) { // play button
            NPCs.clear();
            cowMaker();
            scene = 1;
        }
        if (scene == 0 && mouseX > 350 && mouseX < 650 && mouseY > 420 && mouseY < 600) {// shop button
            scene = 2; // go to shop
        }
        if (scene == 2 && mouseX > 350 && mouseX < 650 && mouseY > 400 && mouseY < 480) { // back from shop
            scene = 0;
        }
    }
}
