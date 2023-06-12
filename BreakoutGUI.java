import javafx.stage.Stage;
import java.util.Observer;
import java.util.Observable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class BreakoutGUI extends Stage implements Observer {

    private Circle circle;
    private Rectangle rectangle;
    private Rectangle[][] brickrects;
    private Text gameover;
    private Pane pane;
    private VBox vbox;
    private Button restartBtn;

    public BreakoutGUI(GameControl c, double width, double height) {
        String fxmlFile = "breakout.fxml";

        try {
            // Scene-Graph aus der fxml-Datei (Scene Builder) laden
            // und Scene-Objekt daraus erzeugen
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root, width, height);

            // einzelne GUI-Elemente anhand der fx:id aus dem Scene-Graph holen:
            pane = (Pane)root.lookup("#pane");
            circle = (Circle)root.lookup("#circle");
            rectangle = (Rectangle)root.lookup("#rectangle");
            gameover = (Text)root.lookup("#gameover");
            vbox = (VBox)root.lookup("#vbox");
            restartBtn = (Button)root.lookup("#restartBtn");

            vbox.setVisible(false);
            vbox.setMinWidth(scene.getWidth());
            vbox.setMinHeight(scene.getHeight());

            scene.setOnMouseMoved(event -> { c.mouseMoved(event); });
            scene.setOnKeyPressed(event -> { c.keyPressed(event); });
            restartBtn.setOnAction((e) -> { c.restartClicked(); });

            this.setScene(scene);
            this.show();
        } catch(IOException e) {
            System.err.println("FXML-Ressource konnte nicht aus der Datei geladen werden: " + fxmlFile + " / "+e);
        } catch(NullPointerException e) {
            System.err.println("Beim Laden der FXML-Datei wurde kein Objekt der Basisklasse javafx.scene.Parent erzeugt.");
        }
    }

    public void update(Observable obs, Object o) {
        if(obs instanceof Playfield) {
            Playfield p = (Playfield) obs;
            if(p.isGameover()) {
                vbox.setVisible(true);
                pane.setCursor(Cursor.DEFAULT);
            } else {
                vbox.setVisible(false);
                pane.setCursor(Cursor.NONE);
            }
        }
        if(obs instanceof Ball) {
            Ball ball = (Ball) obs;
            circle.setRadius(ball.getRadius());
            circle.relocate(ball.getX()-ball.getRadius(),ball.getY()-ball.getRadius());
        }
        if(obs instanceof Paddle) {
            Paddle paddle = (Paddle) obs;
            rectangle.setWidth(paddle.getWidth());
            rectangle.setHeight(paddle.getHeight());
            rectangle.relocate(paddle.getX()-paddle.getWidth()/2,paddle.getY()-paddle.getHeight()/2);
        }
        if(obs instanceof Brick) {
            Brick brick = (Brick) obs;
            if(brickrects == null) {
                brickrects = new Rectangle[brick.getRows()][brick.getColumns()];
            }
            if(brickrects[brick.getRow()][brick.getColumn()] == null) {
                Rectangle newRect = new Rectangle();
                newRect.setStyle("-fx-fill: red; -fx-stroke: yellow; -fx-stroke-width: 2; -fx-stroke-type: inside");
                newRect.setWidth(brick.getWidth());
                newRect.setHeight(brick.getHeight());
                newRect.relocate(brick.getX(),brick.getY());
                brickrects[brick.getRow()][brick.getColumn()] = newRect;
                pane.getChildren().add(newRect);
            } else {
                brickrects[brick.getRow()][brick.getColumn()].setVisible(brick.isVisible());
            }

        }
    }
}
