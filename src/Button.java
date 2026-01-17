import processing.core.PApplet;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private int roundness;
    private String text;
    private PApplet canvas;
    private int r, g, b;

    public Button(String text, int x, int y, int width, int height, int roundness, PApplet c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.roundness = roundness;
        this.text = text;
        this.canvas = c;
        r = 255;
        g = 0;
        b = 0;
    }

    public void display() {
        canvas.fill(r, g, b);
        canvas.rect(x, y, width, height, roundness);
        canvas.fill(0);
        canvas.textAlign(PApplet.CENTER, PApplet.CENTER);
        canvas.textSize(32);
        canvas.text(text, x + width / 2, y + height / 2);

    }

    public boolean hovered(float mouseX, float mouseY) { // Checks if it was hovered over so if a click happens when
                                                         // this is true the button activates
        if (mouseX > x && mouseX < x + width &&
                mouseY > y && mouseY < y + height) {
            return true;
        }
        return false;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setText(String newText) {
        this.text = newText;
    }

}
