import processing.core.PApplet;

public class Button {
private int x;
private int y;
private int width;
private int height;
private int roundness;
private String text;
private PApplet canvas;

public Button(String text, int x, int y, int width, int height, int roundness, PApplet c){
    this.x=x;
    this.y=y;
    this.width=width;
    this.height=height;
    this.roundness=roundness;
    this.text=text;
    this.canvas=c;
}
public void display(){
    canvas.rect(x, y, width, height, roundness);
    

}
}
