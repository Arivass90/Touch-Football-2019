package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

// Keyboard events
public class Main extends Application{


    public static void main(String[] args) 
    {
        launch(args);
    }


    @Override
    public void start(Stage theStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

        Parent rootMenu = loader.load();
        Scene menu = new Scene( rootMenu );

        ControllerMenu controllerMenu = loader.getController();
        controllerMenu.setScene(menu, theStage);

        theStage.setScene( menu );
        theStage.setTitle( "Golpea la pelota" );


        theStage.show();


    }



}