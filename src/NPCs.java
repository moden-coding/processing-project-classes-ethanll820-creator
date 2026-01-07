import processing.core.PApplet;

public class NPCs{
    private String type;
    private int size;
    private int speed;
    private int color;
    int direction = 1;

    private float x;
    private float y;

     private PApplet canvas;

    public NPCs(String type, int size, int speed, float x, float y, PApplet c) {
        this.type = type;
        this.size = size;
        this.speed = speed;
        this.canvas = c;
        this.x = canvas.width/2;
        this.y = 200;
    }

    public void display(){
        canvas.fill(color);
        
        canvas.ellipse(x, y, size, size);
    }
    public void movement() {
    // move horizontally
    x += speed * direction;

    
    if (x < size / 2 || x > canvas.width - size / 2) {
        direction *= -1;
    }

   float wobbleAmplitude = canvas.random(2f,17.5f); //max pixels that changes sinusoidally
   

x += wobbleAmplitude * canvas.sin(canvas.frameCount*0.1f);
y += wobbleAmplitude * canvas.cos(canvas.frameCount*0.1f);
}

}