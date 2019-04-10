package sample;

import javafx.application.Application;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

// Keyboard events
public class Main extends Application{
    float proportion =1f;
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Golpea la pelota" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 500, 500 );
        //Image restart = new Image("restart.png");

        root.getChildren().add( canvas );

        Circle targetData = new Circle(100,100,32);
        IntValue points = new IntValue(0);
        Image bullseye = new Image( "sample/futbol.png" );
        Image field = new Image( "sample/field.jpg" );

        theScene.setOnMouseClicked(
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent e) {
                    if ( targetData.containsPoint( e.getX(), e.getY() ) )
                    {
                        float x = (float) (50 + 400 * Math.random());
                        float y = (float) (50 + 400 * Math.random());
                        targetData.setCenter(x,y);
                        points.value++;

                        if(points.value==5){
                            proportion =0.5f;
                            targetData.setRadius(targetData.getRadius()*proportion);
                        }

                    }
                    else {
                        proportion = 1;
                        points.value = 0;
                        targetData.setInitialRadius();
                    }

                }
            });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.setFill(new ImagePattern(field));
                gc.fillRect(0,0, 512,512);


                gc.drawImage( bullseye, 
                    targetData.getX() - targetData.getRadius(),
                    targetData.getY() - targetData.getRadius(),bullseye.getWidth()*proportion,bullseye.getHeight()*proportion );

                gc.setFill( Color.BLUE );

                String pointsText = "Goals: " + points.value;
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();


        theStage.show();
    }
}