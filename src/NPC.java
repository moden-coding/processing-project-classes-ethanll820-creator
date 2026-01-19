import processing.core.PApplet;

public class NPC {
    private int size;
    private int speed;
    private int color;
    private float x;
    private float y;
    private boolean alive = true;
    private PApplet canvas;
    private Hunter hunter;

    public NPC(float x, float y, int size, int speed, PApplet c, Hunter h) {
        this.size = size;
        this.speed = speed;
        this.canvas = c;
        this.x = x;
        this.y = y;
        this.hunter = h;

    }

    public void display() {
        if (!alive) {
            return;
        }
        canvas.fill(color);//color
        canvas.ellipse(x, y, size, size);//draw circle
    }

    public void movement() {
        if (!alive) {
            return;
        }
        float dx = hunter.getX() - x;
        float dy = hunter.getY() - y;

        float distance = canvas.sqrt(dx * dx + dy * dy);
        if (distance == 0) {
            return;
        }
        dx = dx / distance; // normalize components
        dy = dy / distance;

        x = x + (dx * speed);// mulitply components by speed and set equal to position
        y = y + (dy * speed);

        if (x < size / 2 || x > canvas.width - size / 2) {
            distance *= -1;
        }
    }

    public float getNPCsX() {
        return x;
    }

    public float getNPCsY() {
        return y;
    }

    public int getNPCsSize() {
        return size;
    }

    public void kill() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
    public boolean collidesWithPlayer(Hunter h) {
    float npcRadius = this.size / 2;
    float hRadius = h.getSize() / 2;

    float dx = this.x - h.getX();//find x component
    float dy = this.y - h.getY();//find y component

    float distance = PApplet.sqrt(dx * dx + dy * dy);//distance formula

    return distance <= npcRadius + hRadius;
}

}