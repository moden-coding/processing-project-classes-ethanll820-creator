import processing.core.PApplet;

public class Obstacle {
    private int x;
    private int y;
    private int size;
    private String type;
    private PApplet canvas;

    public Obstacle(int x, int y, int size, String type, PApplet c) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
        this.canvas = c;
    }

    public void display() {
        canvas.fill(255, 0, 0);
        if (type.equals("rock")) {
            canvas.circle(x, y, size);
        }
    }

    public boolean collidesWith(float ox, float oy, float otherSize) {
        float d = canvas.dist(x, y, ox, oy);
        return d < (size / 2 + otherSize / 2);
    }

}
