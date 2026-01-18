import processing.core.PApplet;

public class Hunter {
    private int size;
    private int speed;
    private float x;
    private float y;
    private PApplet canvas;
    private int reload;
    private int reloadUpgradeLevel; // tracks number of upgrades
    private int minReload = 100; // minimum reload in ms

    public Hunter(int size, int color, int speed, float x, float y, int reload, PApplet c) {
        this.size = size;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.reload = reload;
        this.canvas = c;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getReload() {
        return reload;
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public void display() {
        canvas.fill(250, 213, 112);
        canvas.noStroke();
        canvas.circle(canvas.width / 2, canvas.height / 2, size);

    }

    public void move(float xN, float yN) {
    x += xN;
    y += yN;

    if (x < 0) x = 0;//border on X
    if (x > 2000) x = 2000;

    if (y < 0) y = 0;//border on y
    if (y > 1600) y = 1600;
}


    public void displayGun() {
        float hx = canvas.width / 2;// always drawn at center
        float hy = canvas.height / 2;

        float dx = canvas.mouseX - hx;
        float dy = canvas.mouseY - hy;

        float gunLength = 50;

        float distance = PApplet.sqrt(dx * dx + dy * dy);// pythagorean theorem
        if (distance == 0)
            distance = 1;

        float x2 = hx + dx / distance * gunLength;// make into unit vector
        float y2 = hy + dy / distance * gunLength;

        canvas.stroke(0);
        canvas.strokeWeight(4);
        canvas.line(hx, hy, x2, y2);

    }

    public void upgradeReload() {
        if (reload > minReload) {
            reload -= 100; // decrease reload by 100ms per upgrade
            reloadUpgradeLevel++; // optional, for tracking upgrades
            if (reload < minReload) {
                reload = minReload; // enforce minimum
            }
        }
    }

}
