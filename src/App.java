import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    Hunter hunter;
    NPCs beaver;
    PImage background;
    float moveX = 0;
    float moveY = 0;
    float speed = 5;
    ArrayList<Bullets> bullets = new ArrayList<Bullets>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        hunter = new Hunter(50, 50, 5, 500, 400, this);
        beaver = new NPCs("cow", 500, 200, 50, 5, this);

        background = loadImage("grass background.jpg");
        background.resize(2000, 1600);

    }

    public void settings() {
        size(1000, 800);
    }

    public void draw() {
        background(0);
        hunter.move(moveX, moveY);

        pushMatrix();
        translate(width / 2 - hunter.getX(), height / 2 - hunter.getY());//move "coordinate grid" as a whole constantly as the hunter moves
    

        image(background, 0, 0);// draw background

        beaver.movement();// NPCs
        beaver.display();

        for (int i = bullets.size() - 1; i >= 0; i--) {// bullets
            Bullets b = bullets.get(i);
            b.update();
            b.display();

            if (enemyCollisions(b, beaver)) {
                bullets.remove(i);
                beaver.kill();
                continue;
            }

            if (b.isOffScreen()) {
                bullets.remove(i);
            }
        }

        popMatrix();

        hunter.display();
        hunter.displayGun();

    }

    public boolean enemyCollisions(Bullets b, NPCs e) {
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
            float worldMouseX = mouseX + hunter.getX() - width / 2;
            float worldMouseY = mouseY + hunter.getY() - height / 2;

            if (key == ' ') {
                bullets.add(new Bullets(hunter.getX(), hunter.getY(), 10f, 8f, mouseX + hunter.getX() - width / 2,
                        mouseY + hunter.getY() - height / 2, this));
            }
        }
    }

    public void keyReleased() {
        if (keyCode == 'D' || keyCode == 'A')
            moveX = 0;
        if (keyCode == 'W' || keyCode == 'S')
            moveY = 0;
    }
}
