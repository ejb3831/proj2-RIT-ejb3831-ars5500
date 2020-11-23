package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

import gui.RITQuadTree;
public class RITViewer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        List<String> args = getParameters().getRaw();
        RITQuadTree qIn = new RITQuadTree(args.get(0));
        Canvas pixels = new Canvas(qIn.sideLength-1,qIn.sideLength-1);
        GraphicsContext gc = pixels.getGraphicsContext2D();
        for(int i = 0; i<qIn.sideLength-1;i++){
            for(int j = 0; j < qIn.sideLength-1;j++){
                int tVal = qIn.getImage().get(i).get(j);
                try{
                    Color tColor = Color.rgb(tVal, tVal, tVal);
                    gc.setFill(tColor);
                    gc.fillRect(i, j, 1, 1);  
                }catch(Exception e){
                    System.out.println("Error "+ tVal + " is not an integer");
                    e.printStackTrace();
                    return;
                }
                
            }
        }
        Group pixelGroup = new Group(pixels);
        Scene scene = new Scene(pixelGroup);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Usage: java RITViewer uncompressed.txt");
            return;
        }
        Application.launch(args);

    }
}
