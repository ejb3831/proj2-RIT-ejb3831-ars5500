package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import gui.RITQuadTree;
public class RITViewer extends Application {
    RITQuadTree qIn;
    @Override
    public void start(Stage stage) throws Exception {
        RITQuadTree qIn = new RITQuadTree("images\\uncompressed\\simple32x32.txt");
        Canvas pixels = new Canvas(qIn.sideLength,qIn.sideLength);
        GraphicsContext gc = pixels.getGraphicsContext2D();
        for(int i = 0; i<qIn.sideLength-1;i++){
            for(int j = 0; j < qIn.sideLength-1;j++){
                int tVal = qIn.getImage().get(i).get(j);
                try{
                    Color tColor = Color.rgb(tVal, tVal, tVal);
                    gc.setFill(tColor);
                    gc.fillRect(i, j, 1, 1);  
                }catch(Exception e){
                    e.printStackTrace();
                    return;
                }
                
            }
        }
        Group pixelGroup = new Group(pixels);
        Scene scene = new Scene(pixelGroup);
        stage.setScene(scene);
        try{
        stage.show();
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}
