import java.util.Observable;

public class Paddle extends Observable {

    private double x;
    private double y;
    private double width;
    private double height;

    private Playfield playfield;

    public Paddle(Playfield playfield) {
        this.playfield = playfield;
        init();
    }

    public void init() {
        this.width = 80;
        this.height = 20;
        x = playfield.getWidth()/2;
        y = playfield.getHeight()-this.height/2;
        setChanged();
        notifyObservers();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setX(double x) {
        this.x = x; // this.x refers to the center of the paddle
        // TODO prevent paddle from partially leaving the window at the edge
        
        // partially moving out of window (left)
        // TODO: if(....) {
              this.x = this.width/2;
        // }
        // partially moving out of window (right) 
        if(this.x+this.width/2 > playfield.getWidth()) {
        // TODO ...    
        }
        setChanged();
        notifyObservers();
    }
}