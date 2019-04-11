package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable
{

    @FXML
    Button btnst;
    private GraphicsContext gc;
    ControllerGame controllerGame;
    private Scene scene;
    private Stage stage;


    @FXML
    public void initGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();

        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        ControllerGame controllerGame = loader.getController();
        controllerGame.setGame(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setCanvas(GraphicsContext gc) {
        this.gc=gc;
    }

    public void setScene(Scene sc, Stage theStage) {
        scene = sc;
        this.stage = theStage;
    }

    @FXML
    public void exit(){
        stage.close();
    }

}
