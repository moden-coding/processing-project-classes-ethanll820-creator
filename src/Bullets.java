import processing.core.PApplet;

public class Bullets {
    private float x;
    private float y;
    private float size;
    private float speed;
    private PApplet canvas;

    public Bullets(float x, float y, float speed, float size, PApplet canvas) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.canvas = canvas;
    }

    public void update() {
        y -= speed;
    }

    public void display() {
        canvas.fill(255, 255, 0);
        canvas.noStroke();
        canvas.ellipse(x, y, size, size);
    }

    public boolean isOffScreen() {
        return y < -size;
    }

}