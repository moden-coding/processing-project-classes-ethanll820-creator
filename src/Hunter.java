import processing.core.PApplet;

public class Hunter {
    private int size;
    private int color;
    private int speed;

    private float x;
    private float y;

    public Hunter(int size, int color, int speed, float x, float y) {
    this.size = size;
    this.color = color;
    this.speed = speed;
    this.x = x;
    this.y = y;
}

public void display(PApplet p) {
        p.fill(color);
        p.noStroke();
        p.ellipse(x, y, size, size);
    }

    public void displayGunSlope(PApplet p) {
    float dx = p.mouseX - x;
    float dy = p.mouseY - y;

    float gunLength = size; 

    float distance = PApplet.sqrt(dx*dx + dy*dy); 
    if (distance == 0) distance = 1; 

    float x2 = x + dx / distance * gunLength;
    float y2 = y + dy / distance * gunLength;

    p.stroke(0);
    p.strokeWeight(4);
    p.line(x, y, x2, y2);
}

    
    
}
