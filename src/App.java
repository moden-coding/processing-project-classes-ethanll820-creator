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
        beaver = new NPCs("cow", 50, 10, 300, 300, this);

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

            if (b.isOffScreen()) {
                bullets.remove(i);
            }
        }
    }

public void keyPressed() {
    if (key == ' ') { // space
        bullets.add(new Bullets(500, 400 - 20, 10, 8, this));
    }
    }
}
