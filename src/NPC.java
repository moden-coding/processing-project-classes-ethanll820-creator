import processing.core.PApplet;

public class NPC {
    private String type;
    private int size;
    private int speed;
    private int color;
    int direction = 1;

    private float x;
    private float y;
    private boolean alive = true;

    private PApplet canvas;

    private Hunter hunter;

    NPC(String type, float x, float y, int size, int speed, PApplet c, Hunter h) {
        this.type = type;
        this.size = size;
        this.speed = speed;
        this.canvas = c;
        this.x = x;
        this.y = y;
        this.hunter = h;
        
    }

    public void display() {
        if(!alive){
            return;
        }
        canvas.fill(color);
        canvas.ellipse(x, y, size, size);
    }

    public void movement() {
        if(!alive){
            return;
        }
        float dx = hunter.getX() - x;
        float dy = hunter.getY() - y;

        float distance = canvas.sqrt(dx * dx + dy * dy);
        if (distance == 0){
            return;
        }
        dx = dx/distance; //normalize components
        dy = dy/distance;

        x = x+(dx * speed);//mulitply components by speed and set equal to position
        y = y+(dy * speed);

        

        if (x < size / 2 || x > canvas.width - size / 2) {
            distance *= -1;
        }

        // float wobbleAmplitude = canvas.random(2f, 17.5f); // max pixels that changes randomly

        // x += wobbleAmplitude * canvas.sin(canvas.frameCount * 0.1f); // moves sinusoidally
        // y += wobbleAmplitude * canvas.cos(canvas.frameCount * 0.1f);

    }

    public float getNPCsX() {
        return x;
    }

    public float getNPCsY() {
        return y;
    }
    
    public int getNPCsSize(){
        return size;
    }
    public void kill(){
        alive = false;
    }
    public boolean isAlive(){
        return alive;
    }
}