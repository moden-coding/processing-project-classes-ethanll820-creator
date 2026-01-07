import processing.core.PApplet;

public class Bullets {
    private float x;
    private float y;
    private float size;
    private float speed;
    private PApplet canvas;
    private float targetX;
    private float targetY;
    private float vx;
    private float vy;

    public Bullets(float x, float y, float speed, float size, float targetX, float targetY, PApplet canvas) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.canvas = canvas;
        this.targetX=targetX;
        this.targetY=targetY;

        float dx = targetX - x;
        float dy = targetY - y;

        // normalize
        float mag = (float)Math.sqrt(dx * dx + dy * dy);
        dx /= mag;
        dy /= mag;

        // scale by speed
        vx = dx * speed;
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
        return x < -size || x > canvas.width + size || y < -size || y > canvas.height + size;
    }

    public float getBulletX(){
        return x;
    }
    public float getBulletY(){
        return y;
    }
    public float getBulletSize(){
        return size;
    }

}