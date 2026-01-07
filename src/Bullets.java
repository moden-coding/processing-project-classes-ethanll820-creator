import processing.core.PApplet;

public class Bullets{
    float x;
    float y;
    float size;
    float speed;
    PApplet canvas;

    Bullets(float x, float y, float speed, float size, PApplet canvas) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.canvas = canvas;
    }

    void update() {
        // move the bullet upwards
        y -= speed;
    }

     void display() {
        canvas.fill(255, 255, 0); // yellow bullet
        canvas.noStroke();
        canvas.ellipse(x, y, size, size);
    }

    public boolean isOffScreen() {
        return y < -size;
    }

}