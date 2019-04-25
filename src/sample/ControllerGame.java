package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
    @FXML
    private Canvas mainCanvas;

    float proportion = 1f;
    KTimer time = new KTimer();
    final String[] secondsFinal = new String[1];
    Circle targetData = new Circle(306, 200, 32);
    IntValue points = new IntValue(0);
    Stage stage;
    AnimationTimer timer;
    GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setGame(Stage stage) throws IOException {
        this.stage = stage;
        gc = mainCanvas.getGraphicsContext2D();
        final Image[] ball = {new Image("sample/futbol.png")};
        Image field = new Image("sample/field.jpg");


        // cuando hacemos click
        stage.getScene().setOnMouseClicked(

                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {


                        if (targetData.containsPoint(e.getX(), e.getY())) {
                            float x = (float) (50 + 306 * Math.random());
                            float y = (float) (50 + 200 * Math.random());
                            targetData.setCenter(x, y);
                            points.value++;

                            if (points.value == 1) {
                                time.startTimer(10);
                            }

                            if (points.value == 10) {
                                proportion = 0.5f;
                                targetData.setRadius(targetData.getRadius() * proportion);
                            }

                            if (points.value == 20) {
                                proportion = 0.3f;
                                targetData.setInitialRadius();
                                targetData.setRadius(targetData.getRadius() * proportion);
                            }


                        } else {
                            try {
                                gameOver();
                            }

                            catch (InterruptedException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }

                    }
                });


// CREAMOS LA FUENTE
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);

        gc.setFont(theFont);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);


        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Clear the canvas
                gc.setFill(new ImagePattern(field));
                gc.fillRect(0, 0, 612, 400);

                gc.drawImage(ball[0],
                        targetData.getX() - targetData.getRadius(),
                        targetData.getY() - targetData.getRadius(), ball[0].getWidth() * proportion, ball[0].getHeight() * proportion);

                gc.setFill(Color.BLUE);

                String pointsText = "Goals: " + points.value;
                gc.fillText(pointsText, 460, 35);
                gc.strokeText(pointsText, 460, 35);

                String timeText = "Time: + " + time.getTime();
                gc.fillText(timeText, 60, 35);
                gc.strokeText(timeText, 60, 35);

            }
        };
        timer.start();

        stage.show();

    }

    public void gameOver() throws IOException, InterruptedException {

        long milisFinal = 0;
        milisFinal = time.getTime();
        secondsFinal[0] = time.milisToSecond(milisFinal);

        gc.setFill(Color.RED);

        String textGameOver = "GAME OVER";
        gc.fillText(textGameOver, 232, 205);
        gc.strokeText(textGameOver, 232, 205);

        String timeFinal = "Tu tiempo ha sido de: " + secondsFinal[0];
        gc.fillText(timeFinal, 100, 250);
        gc.strokeText(timeFinal, 100, 250);

        String pointsFinal = "Tu numero de toques ha sido de: " + points.value;
        gc.fillText(pointsFinal, 100, 290);
        gc.strokeText(pointsFinal, 100, 290);

        System.out.println("Tu tiempo ha sido de: " + secondsFinal[0]);
        System.out.println("Tu numero de toques ha sido de: " + points.value);

        time.stopTimer();
        time.startTimer(10);
        proportion = 1;
        points.value = 0;
        targetData.setInitialRadius();
        time.stopTimer();
        timer.stop();


        stage.getScene().setOnMouseClicked(

                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent e) {

                        try {
                            reset();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

    }


    public void reset() throws IOException, InterruptedException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

        Parent rootMenu = loader.load();
        Scene menu = new Scene( rootMenu );
        ControllerMenu controllerMenu = loader.getController();
        controllerMenu.setScene(menu, stage);

        stage.setScene(menu);
    }

}