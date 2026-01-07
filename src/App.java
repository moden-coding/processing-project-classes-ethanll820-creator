import java.util.ArrayList;

import processing.core.*;

public class App extends PApplet {
    Hunter hunter;
    NPCs beaver;
    ArrayList<Bullets> bullets = new ArrayList<Bullets>();

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        hunter = new Hunter(50, 50, 5, 500, 400, this);
        beaver = new NPCs("cow", 500, 200, 50, 5, this);

    }

    public void settings() {
        size(1000, 800);
    }

    public void draw() {
        background(54, 186, 41);
        hunter.display();
        hunter.displayGun();
        beaver.display();
        beaver.movement();

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
    }

    public boolean enemyCollisions(Bullets b, NPCs e) {
        if(!e.isAlive()){return false;}
    float bulletRadius = b.getBulletSize() / 2;   // divide by 2 to get radius
    float enemyRadius = e.getNPCsSize() / 2;

    float dx = b.getBulletX() - e.getNPCsX();
    float dy = b.getBulletY() - e.getNPCsY();

    float distance = sqrt(dx * dx + dy * dy);

    return distance <= bulletRadius + enemyRadius;
}


    public void keyPressed() {
        if (key == ' ') { // space
            bullets.add(new Bullets(hunter.getX(), hunter.getY(), 10f, 8f, mouseX, mouseY, this));
        }
    }
}
