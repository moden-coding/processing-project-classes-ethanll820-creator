import processing.core.PApplet;

public class Hunter {
    private int size;
    private int color;
    private int speed;

    private float x, vX;
    private float y, vY;

    private PApplet canvas;

    private int reload;

    public Hunter(int size, int color, int speed, float x, float y, PApplet c) {
        this.size = size;
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.canvas = c;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getReload(){
        return reload;
    }

    public void display() {
        canvas.fill(color);
        canvas.noStroke();
        canvas.circle(canvas.width / 2, canvas.height / 2, size);

    }
    public void move(float xN, float yN) {
        x += xN;
        y += yN;
    }
    public void displayGun() {
    float hx = canvas.width / 2;//always drawn at center
    float hy = canvas.height / 2;

    float dx = canvas.mouseX - hx;
    float dy = canvas.mouseY - hy;

    float gunLength = 50;

    float distance = PApplet.sqrt(dx * dx + dy * dy);//pythagorean theorem
    if (distance == 0) distance = 1;

    float x2 = hx + dx / distance * gunLength;//make into unit vector
    float y2 = hy + dy / distance * gunLength;

    canvas.stroke(0);
    canvas.strokeWeight(4);
    canvas.line(hx, hy, x2, y2);
       
    }

}
