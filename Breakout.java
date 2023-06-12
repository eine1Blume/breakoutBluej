import javafx.application.Application;
import javafx.stage.Stage;

public class Breakout extends Application {

    private GameloopTimer gameloop;
    private Playfield playfield;
    
    @Override
    public void start(Stage s) {
        playfield = new Playfield(4,8);
        GameControl gameControl = new GameControl(playfield);
        BreakoutGUI gui = new BreakoutGUI(gameControl,playfield.getWidth(), playfield.getHeight());
        playfield.addObserver(gui);
        playfield.getBall().addObserver(gui);
        playfield.getPaddle().addObserver(gui);
        playfield.init();
        gameloop = new GameloopTimer(playfield, gameControl,100);
        gameloop.start();
    }
    
    public void reset() {
        playfield.init();
    }
    
    @Override
    public void stop() {
        gameloop.stop();
        try {
            super.stop();
        } catch(Exception e) { System.out.println(e); }
    }

    public static void main(String[] args) {
        launch();
    }
    
    

}