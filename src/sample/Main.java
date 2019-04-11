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
    KTimer time = new KTimer();
    final String[] secondsFinal = new String[1];
    Circle targetData = new Circle(100,100,32);
    IntValue points = new IntValue(0);
    Canvas canvas = new Canvas( 500, 500 );
    GraphicsContext gc = canvas.getGraphicsContext2D();
    boolean gameOver = false;

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

//        Scene menu = new Scene( root );
//        theStage.setScene(menu);

        root.getChildren().add( canvas );

        final Image[] ball = {new Image("sample/futbol.png")};
        Image field = new Image( "sample/field.jpg" );

//iniciamos el contador
        time.startTimer(10);

        // cuando hacemos click
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
                       gameOver();

                    }

                }
            });


// CREAMOS LA FUENTE
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );

        gc.setFont( theFont );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);


////


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.setFill(new ImagePattern(field));
                gc.fillRect(0,0, 512,512);


                gc.drawImage(ball[0],
                    targetData.getX() - targetData.getRadius(),
                    targetData.getY() - targetData.getRadius(), ball[0].getWidth()*proportion, ball[0].getHeight()*proportion );

                gc.setFill( Color.BLUE );


                String pointsText = "Goals: " + points.value;
                gc.fillText( pointsText, 320, 36 );
                gc.strokeText( pointsText, 320, 36 );

                String timeText = "Time: + "+ time.getTime();
                gc.fillText(timeText,250,60 );
                gc.strokeText(timeText,250,60);

                if(gameOver){

                String textGameOver = "GAME OVER";
                    gc.fillText(textGameOver,50,300);
                    gc.strokeText(textGameOver,50,300);

                    delay();

                String timeFinal = "Tu tiempo final ha sido de "+ secondsFinal[0];
                gc.fillText(timeFinal,20,260);
                gc.strokeText(timeFinal,20,260);

                }
            }
        }.start();


        theStage.show();
    }

    public void gameOver(){

        gameOver=true;
        long milisFinal = 0;
        milisFinal=time.getTime();
        secondsFinal[0] =time.milisToSecond(milisFinal);
        System.out.println("tu tiempo es de "+ secondsFinal[0]);
        time.stopTimer();
        time.startTimer(10);
        proportion = 1;
        points.value = 0;
        targetData.setInitialRadius();
        time.stopTimer();


    }

    public void iniciar(Stage theStage){
        start(theStage);

    }

    private static void delay()
    {
        try
        {
            Thread.sleep(2000);
        }catch(InterruptedException e){}
    }


}