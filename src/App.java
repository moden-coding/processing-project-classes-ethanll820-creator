import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    Hunter hunter;
    NPCs beaver;
    PImage background;
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

        pushMatrix();
        translate(width / 2 - hunter.getX(), height / 2 - hunter.getY());

        // draw background
        image(background, 0, 0);

        beaver.display();
        beaver.movement();
        popMatrix();
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullets b = bullets.get(i);
            b.update();
            b.display();

            if (enemyCollisions(b, beaver) == true) {
                bullets.remove(i);
                beaver.kill();
                break;
            }

            if (b.isOffScreen()) {
                bullets.remove(i);
            }
        }
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
        int speed = 5;
        if (keyCode == 'D') {
            hunter.move(speed, 0);
        } else if (keyCode == 'A') {
            hunter.move(-speed, 0);
        } else if (keyCode == 'W') {
            hunter.move(0, -speed);
        } else if (keyCode == 'S') {
            hunter.move(0, speed);
        }

        if (key == ' ') {
            bullets.add(new Bullets(hunter.getX(), hunter.getY(), 10f, 8f, mouseX + hunter.getX() - width / 2,
                    mouseY + hunter.getY() - height / 2, this));
        }
    }
}
