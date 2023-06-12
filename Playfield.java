import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Playfield extends Observable {
    private double width;
    private double height;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;
    private boolean isPaused;
    private boolean isAutoplay;
    private boolean isGameover;
    private int brickColumns;
    private int brickRows;

    public Playfield(int brickRows,int brickColumns) {
        this.brickColumns = brickColumns;
        this.brickRows = brickRows;
        init();
    }

    public void init() {
        this.width = 400;
        this.height = 300;
        if(this.ball == null) this.ball = new Ball(this);
        else this.ball.init();
        if(this.paddle == null) this.paddle = new Paddle(this);
        else this.paddle.init();
        if(this.bricks == null) {
            this.bricks = new ArrayList<Brick>();
            for(int i=0; i<brickColumns*brickRows; i++) {
                this.bricks.add(new Brick(this));
            }
        } else {
            for (Brick brick : bricks) {
                brick.init();
            }
        }
        isPaused = false;
        isGameover = false;
        setChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(Observer obs) {
        for (Brick brick : bricks) {
            brick.addObserver(obs);
        }
        super.addObserver(obs);
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Ball getBall() {
        return this.ball;
    } 

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public int getBrickColumns() {
        return brickColumns;
    }

    public int getBrickRows() {
        return brickRows;
    }

    public Paddle getPaddle() {
        return this.paddle;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isGameover() {
        return isGameover;
    }

    public boolean isAutoplay() {
        return isAutoplay;
    }

    public void pauseUnpause() {
        isPaused = !isPaused;
        setChanged();
        notifyObservers();
    }

    public void autoplay() {
        isAutoplay = !isAutoplay;
        setChanged();
        notifyObservers();
    }

    public void setGameover() {
        isGameover = true;
        setChanged();
        notifyObservers();
    }
}
