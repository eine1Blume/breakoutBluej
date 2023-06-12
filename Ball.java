import java.lang.Math;
import java.util.Observable;

public class Ball extends Observable
{
    private double x; // x-middle
    private double y; // y-middle
    private double dx;
    private double dy;
    private double direction; // right: 0, down: 90, left: 180, up: 270 degrees
    private double speed;
    private double radius;
    private Playfield playfield;

    public static final int NONE = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int TOP = 4;

    public Ball(Playfield playfield) {
        this.playfield = playfield;
        init();
    }

    public void init() {
        x = playfield.getWidth()/2;
        y = playfield.getHeight()/2;
        direction = Math.random()*90 + 225;
        speed = 3;
        radius = 10;
        setChanged();
        notifyObservers();
    }

    public void move() {
        // calculate next moving distances
        dx = speed*Math.cos(Math.toRadians(direction));
        dy = speed*Math.sin(Math.toRadians(direction));

        // check future collision with playfield edges
        int edge = checkCollisionPlayfield();
        if(edge != NONE) {
            if(edge == BOTTOM) {
                // TODO: call the playfields gameOver()-method    
            } else {
                // future collision detected:
                // change direction according to colliding edge
                // TODO: call the balls bounce-method with "edge" as parameter
            }
        }
        // check future collision with paddle
        edge = checkCollisionPaddle();
        if(edge == BOTTOM) bounce(edge);

        // recalculate next moving distances
        dx = speed*Math.cos(Math.toRadians(direction));
        dy = speed*Math.sin(Math.toRadians(direction));
        
        // make move in (new) direction
        x = x + dx;
        y = y + dy;
        
        setChanged();
        notifyObservers();
    }

    public int checkCollisionPlayfield() {
        return NONE;
    }

    public int checkCollisionPaddle() {
        return NONE;
    }

    public void bounce(int edge) {
        double tmp;

        if(edge == RIGHT) {
            tmp = 0-direction;             
            direction = 180+tmp;
        }
        if(edge == BOTTOM) {
            tmp = 90-direction;             
            direction = 270+tmp;
        }
        if(edge == LEFT) {
            tmp = 180-direction;             
            direction = 0+tmp;
        }
        if(edge == TOP) {
            tmp = 270-direction;             
            direction = 90+tmp;
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius;
    }
}
