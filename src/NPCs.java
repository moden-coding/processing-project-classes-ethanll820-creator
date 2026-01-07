import processing.core.PApplet;

public class NPCs{
    private String type;
    private int size;
    private int speed;
    private int color;

    private float x;
    private float y;

     private PApplet canvas;

    public NPCs(String type, int size, int speed, float x, float y, PApplet c) {
        this.type = type;
        this.size = size;
        this.speed = speed;
        this.canvas = c;
        this.x = x;
        this.y = y;
    }

    public void display(){
        canvas.fill(5);
        canvas.noStroke();
        canvas.circle(x, y, size);
    }

}