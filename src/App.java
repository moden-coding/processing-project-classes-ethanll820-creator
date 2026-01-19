import java.util.ArrayList;

import processing.core.*;
import processing.sound.*;

public class App extends PApplet {
    Hunter hunter;
    NPCSpawner spawner;
    PImage background;
    float moveX = 0;
    float moveY = 0;
    float speed = 5;
    int lastShotTime;
    boolean shooting;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();// array list to update and store bullets
    ArrayList<NPC> NPCs = new ArrayList<NPC>();// array list to update and store NPCs
    HighScore highScore;
    int highScoreCoins;
    int coins;
    int level = 1;
    int scene = 0;
    Button PLAY;
    Button SHOP;
    Button BACK;
    Button UPGRADE_RELOAD;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        highScore = new HighScore();
        highScoreCoins = highScore.loadHighScore();
        coins = highScoreCoins; // loads coins from file

        hunter = new Hunter(50, 5, 4, 500, 400, 1000, this);
        spawner = new NPCSpawner(this, hunter);
        background = loadImage("grass background.jpg");
        background.resize(2000, 1600);
        PLAY = new Button("PLAY LEVEL: " + level, 375, 300, 250, 50, 10, this);// play button
        SHOP = new Button("SHOP", 375, 500, 250, 50, 10, this);// shop button
        BACK = new Button("BACK", 375, 400, 250, 50, 10, this);// back buttton
        UPGRADE_RELOAD = new Button("UPGRADE RELOAD: 100 coins", 375, 350, 500, 50, 10, this);// upgrade reload button

    }

    public void settings() {
        size(1000, 800);
    }

    public void draw() {
        if (scene == 0) {
            drawTitleScreen();// start by drawing title with all buttons

        } else if (scene == 1) {// when play is clicked draw the actual game
            background(0);
            hunter.move(moveX, moveY);

            pushMatrix();// chatgpt
            translate(width / 2 - hunter.getX(), height / 2 - hunter.getY());// move "coordinate grid" as a whole
                                                                             // constantly
                                                                             // as the hunter moves

            image(background, 0, 0);// draw background
            NPCMovement();

            bulletChecker();
            for (NPC n : NPCs) {
                if (n.isAlive() && n.collidesWithPlayer(hunter)) {

                    if (coins > highScoreCoins) {// only loads coins if need
                        highScore.saveHighScore(coins);
                        highScoreCoins = coins;
                    }
                    coins = 0;
                    scene = 0;
                    NPCs.clear();
                    bullets.clear();
                    break;
                }

            }

            popMatrix();// chatgpt

            hunter.display();
            hunter.displayGun();
        } else if (scene == 2) {
            drawShopScreen();
        }
        if (scene == 1 && NPCs.size() > 0 && allEnemiesDead() == true) {// go back to title screen but also make sure
                                                                        // all enemies are actually dead
            scene = 0;
            level++;
            coins = coins + 100;
            NPCs.clear();
        }

    }

    public void drawTitleScreen() { // Title
        textSize(24);
        text("High Score: " + highScoreCoins, width / 2, 220);

        background(30, 120, 60);
        textAlign(CENTER);
        textSize(64);
        fill(255);
        text("HUNTER", width / 2, 150);
        PLAY.setText("PLAY LEVEL: " + level);
        PLAY.display();
        SHOP.display();
    }

    public void drawShopScreen() {
        rectMode(CORNER);
        background(40); // clear old screen

        textAlign(CENTER);
        textSize(32);
        fill(255);

        text("SHOP", width / 2, 150);
        text("You have " + coins + " coins", width / 2, 250);
        textSize(24);
        text("Current reload: " + hunter.getReload() + " ms", width / 2, 300);

        UPGRADE_RELOAD.display();
        BACK.display();

        BACK.display();// Back button to go back to title
    }

    public boolean enemyCollisions(Bullet b, NPC e) {
        if (!e.isAlive()) {
            return false;
        }
        float bulletRadius = b.getBulletSize() / 2; // divide by 2 to get radius
        float enemyRadius = e.getNPCsSize() / 2;

        float dx = b.getBulletX() - e.getNPCsX();
        float dy = b.getBulletY() - e.getNPCsY();

        float distance = sqrt(dx * dx + dy * dy); // pythaogorean theorem/distance formula

        return distance <= bulletRadius + enemyRadius;
    }

    public void bulletChecker() {
        for (int i = bullets.size() - 1; i >= 0; i--) {// cycles backwards through bullet list to update position and
                                                       // draw
            Bullet b = bullets.get(i);
            b.update();
            b.display();

            boolean hitEnemy = false;

            for (NPC n : NPCs) {
                if (enemyCollisions(b, n)) {
                    n.kill(); // enemy dies
                    hitEnemy = true;
                    coins = coins + 10;// gives money for kill
                    break;
                }
            }

            if (hitEnemy || b.isOffScreen()) {//removes bullet from list if off screen to prevent lag
                bullets.remove(i);
            }
        }
    }

    public boolean allEnemiesDead() {//check if all eneimies have been killed
        for (NPC n : NPCs) {
            if (n.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public void NPCMovement() {//cycles through NPC list to move and update them
        for (NPC n : NPCs) {
            n.movement();
            n.display();
        }
    }

    public void shoot() {
        int currentTime = millis(); // current time in milliseconds
        if (currentTime - lastShotTime >= hunter.getReload()) {//stops bullets from shooting if reload cooldown isn't over
            bullets.add(new Bullet(hunter.getX(), hunter.getY(), 10f, 8f, mouseX + hunter.getX() - width / 2,
                    mouseY + hunter.getY() - height / 2, this));//adds new bullet to arraylist so it can be drawn and updated
            lastShotTime = currentTime; // update the last shot time when shooting occurs
        }
    }

    public void keyPressed() {//movement and shooting input from keyboard
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

    public void keyReleased() {//fixes releasing keys and inputs stopping
        if (keyCode == 'D' || keyCode == 'A')
            moveX = 0;
        if (keyCode == 'W' || keyCode == 'S')
            moveY = 0;
        if (key == ' ') {
            shooting = false;

        }
    }

    public void mousePressed() {
        if (scene == 0) {// makes buttons non clickable unless scene is correct to prevent bugs
            if (PLAY.hovered(mouseX, mouseY) == true) {
                NPCs.clear();
                spawner.spawnLevel(level, NPCs);
                scene = 1;
            }
        }
        if (scene == 0) {
            if (SHOP.hovered(mouseX, mouseY) == true) {
                scene = 2;
            }
        }
        if (scene == 2) {
            if (BACK.hovered(mouseX, mouseY)) {
                scene = 0;
            }
            if (UPGRADE_RELOAD.hovered(mouseX, mouseY)) {
                if (coins >= 100) { // cost
                    coins -= 100; // pay
                    hunter.upgradeReload(); // reduce reload to amount payed for
                }
            }
        }
    }
}