import javafx.animation.AnimationTimer;

public class GameloopTimer extends AnimationTimer {
    
    private Playfield playfield;
    private GameControl gameControl;
    private long lastAction;
    private int fps;
    private double frameDuration;
    
    public GameloopTimer(Playfield playfield, GameControl gameControl, int fps) {
        this.playfield = playfield;
        this.gameControl = gameControl;
        this.fps = fps;
        this.frameDuration = 1000000000/fps;
    }
    
    @Override
    public void handle(long now) {
        long duration = now - lastAction;
        
        if(duration > frameDuration) {
            lastAction = now; 
            if(!playfield.isPaused() && !playfield.isGameover()) gameControl.tick();
        }        
    }
    
    @Override
    public void start() {
        lastAction = System.nanoTime();
        super.start();
    }
}