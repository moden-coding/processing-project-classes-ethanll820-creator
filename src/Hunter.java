import processing.core.PApplet;

public class Hunter {
    private int size;
    private int color;
    private int speed;

    private float x;
    private float y;

    private PApplet canvas;

    public Hunter(int size, int color, int speed, float x, float y, PApplet c) {
    this.size = size;
    this.color = color;
    this.speed = speed;
    this.x = x;
    this.y = y;
    this.canvas = c;
}

public void display() {
        canvas.fill(color);
        canvas.noStroke();
        canvas.circle(x, y, size);
    }

    public void displayGunSlope() {
    float dx = canvas.mouseX - x;
    float dy = canvas.mouseY - y;

    float gunLength = size; 

    float distance = PApplet.sqrt(dx*dx + dy*dy); 
    if (distance == 0) distance = 1; 

    float x2 = x + dx / distance * gunLength;
    float y2 = y + dy / distance * gunLength;

    canvas.stroke(0);
    canvas.strokeWeight(4);
    canvas.line(x, y, x2, y2);
}

    
    
}
