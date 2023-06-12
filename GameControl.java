import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GameControl {

    private Playfield playfield;

    public GameControl(Playfield playfield) {
        this.playfield = playfield;
    }

    public void tick() {
        // gamelooptimer calls this method at short intervals
        // TODO get ball-Object from playfield an call its its method move()
        if(playfield.isAutoplay()) {
            playfield.getPaddle().setX(playfield.getBall().getX());
        }
        for (Brick brick : playfield.getBricks()) {
            // TODO let (each) brick check for collision with ball
        }
    }

    public void mouseMoved(MouseEvent event) {
        if(!playfield.isGameover() && !playfield.isAutoplay()) {
            Paddle paddle = playfield.getPaddle();
            paddle.setX(event.getX());
        }
    }

    public void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE) {
            // TODO toggle pause / unpause in playfield-class
        }
        if(event.getCode() == KeyCode.A) {
            // TODO activate / deactivate autoplay in playfield-class
        }
    }

    public void restartClicked() {
        // TODO call the playfields init()-method
    }
}