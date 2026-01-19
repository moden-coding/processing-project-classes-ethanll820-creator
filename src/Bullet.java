import processing.core.PApplet;

public class Bullet {
    private float x;
    private float y;
    private float size;
    private float speed;
    private PApplet canvas;
    private float vx;
    private float vy;

    public Bullet(float x, float y, float speed, float size, float targetX, float targetY, PApplet canvas) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.canvas = canvas;

        float dx = targetX - x;
        float dy = targetY - y; 


        float mag = (float) Math.sqrt(dx * dx + dy * dy); // normalize
        dx /= mag;
        dy /= mag;

    
        vx = dx * speed;  // scale by speed
        vy = dy * speed;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public void display() {
        canvas.fill(255, 255, 0);
        canvas.noStroke();
        canvas.ellipse(x, y, size, size);
    }

    public boolean isOffScreen() {
        return x < -size || x > 2000 + size || y < -size || y > 1600 + size; // check if coordinates leave screen
    }

    public float getBulletX() {
        return x;
    }

    public float getBulletY() {
        return y;
    }

    public float getBulletSize() {
        return size;
    }

}