import java.util.Observable;

public class Brick extends Observable {

    private double x;
    private double y;
    private double width;
    private double height;
    private int column;
    private int row;

    private Playfield playfield;
    protected int collisionCounter;
    private boolean isColliding;

    private static final int NONE = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private static final int TOP = 4;
    private static double offsetY = 40;
    private static int columnCounter = 0;
    private static int rowCounter = 0;

    public Brick(Playfield playfield) {
        this.playfield = playfield;
        this.column = columnCounter;
        this.row = rowCounter;
        columnCounter++;
        if(columnCounter >= playfield.getBrickColumns()) {
            columnCounter = 0;
            rowCounter++;
        }
        this.width = playfield.getWidth() / playfield.getBrickColumns();
        this.height = 16;
        this.x = this.column*width;
        this.y = this.row*height + offsetY;
        init();
    }

    public void init() {
        collisionCounter = 0;
        setChanged();
        notifyObservers();
    }

    public boolean isVisible() {
        return (collisionCounter == 0);
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void checkCollisionBall() {
        int edge = checkCollision();
        
        if(edge != Ball.NONE) {
            isColliding = true;
            collisionCounter++;
            playfield.getBall().bounce(edge);
        } else {
            isColliding = false;
        }
        setChanged();
        notifyObservers();
    }

    private int checkCollision() {
        if(!isVisible()) return Ball.NONE;
        Ball ball = playfield.getBall();
        double bx = ball.getX();
        double by = ball.getY();
        double br = ball.getRadius();

        double distanceX = Math.abs(bx - x-width/2);
        double distanceY = Math.abs(by - y-height/2);

        if (distanceX > (width/2 + br)) { return Ball.NONE; }
        if (distanceY > (height/2 + br)) { return Ball.NONE; }

        if (distanceX <= (width/2)) {
            if(bx < x) return Ball.LEFT;
            else return Ball.RIGHT;
        }
        if (distanceY <= (height/2)) { 
            if(by < y) return Ball.BOTTOM;
            else return Ball.TOP;
        }
       return Ball.NONE;
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getColumns() {
        return playfield.getBrickColumns();
    }

    public int getRows() {
        return playfield.getBrickRows();
    }
}