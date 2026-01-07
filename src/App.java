import processing.core.*;

public class App extends PApplet{
    Hunter hunter;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void setup(){
        hunter = new Hunter(50,50,5,500,400,this);
        
    }

    public void settings(){
        size(1000, 800);
    }

    public void draw(){
       background(54, 186, 41);
       hunter.display();
         hunter.displayGunSlope(); 
}

    }

