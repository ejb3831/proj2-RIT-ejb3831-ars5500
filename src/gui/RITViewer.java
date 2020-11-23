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
/**
 * RITViewer is an image rendering tool. the Rich Image Text Viewer or RITViewer 
 * loads uncompressed txt files into Grayscale images.
 * NOTE: If running this program in the IntelliJ editor instead of the VSCode editor you may encounter issues
 * this is because the file module-info.java was removed from the src directory. This is because while using
 * vscode this file causes errors if it is not deleted. In the case that you experience errors when running this program
 * attempt creating a the file "module-info.java" in the src folder and pasting the following into it
 * 
 *  module RIT {
 *  requires transitive javafx.controls;
 *  exports gui;
 *  }
 * 
 * Appologies if you experience this error and it gives you any troubles, as i do not have IntelliJ set up i cannot test it and
 * the submission is due in 14 minutes
 * if this does not fix it, make sure that the folder that the src directory is contained in containes the correct .idea folder
 * and subsequent files as well as the RIT.iml file, as I also removed those due to them being irrelevent and possibly causing errors.
 * however these are not contained in the src folder i am submitting so it might not matter anyways
 * also also mabey check the .classpath file
 * if you cant tell i just got super panicky that the different IDES are going to cause problems, so sorry about all this text 
 * @author Evan/Evelyn Barnes
 * @author Alex Smith
 */
public class RITViewer extends Application {
    /**
     * the start method is the meat and potatoes of the RIT Viewer,
     * if you like your meat and potatoes digital, and in the form
     * of unrelated words. The start function is called to start
     * the javafx program which displays the image
     * @param stage the stage on which everything is displayed
     */
    @Override
    public void start(Stage stage) throws Exception {
        //get command line arguments
        List<String> args = getParameters().getRaw();
        //generate a new RITQuadTree(ironicly, does not include any quad trees)
        RITQuadTree qIn = new RITQuadTree(args.get(0));
        //create the canvas to show pixels
        Canvas pixels = new Canvas(qIn.sideLength-1,qIn.sideLength-1);
        GraphicsContext gc = pixels.getGraphicsContext2D();
        //iterate through the 2D array of pixel values and draw them
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
        //finally show the result, our beutiful meat and potatoes...i mean image
        Group pixelGroup = new Group(pixels);
        Scene scene = new Scene(pixelGroup);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * The main method, in this case only used to check that the arguments are valid and start the application
     * @param args uncompressed.txt
     */
    public static void main(String[] args) {
        if(args.length!=1){
            System.out.println("Usage: java RITViewer uncompressed.txt");
            return;
        }
        Application.launch(args);

    }
}
