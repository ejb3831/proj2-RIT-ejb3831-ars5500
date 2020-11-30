package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.RITQT;

public class RITGUIBase extends Stage{
    public Stage stage;
    private RITQT qt;
    private Stage viewStage;
    public RITGUIBase(Stage stage){
        super();
        this.stage = stage;
        stage.close();
    }
    public void setQT(String name){this.qt=new RITQT(name);}

    public boolean qtExtists(){return this.qt!=null;}
    public RITQT getQT(){return this.qt;}

    public void genView(){
        this.viewStage = new Stage();
        viewStage.setTitle("Image Display");
        
        //create the canvas to show pixels
        Canvas pixels = new Canvas(qt.getSize(),qt.getSize());
        GraphicsContext gc = pixels.getGraphicsContext2D();
        //iterate through the 2D array of pixel values and draw them
        for(int i = 0; i<qt.getSize()-1;i++){
            for(int j = 0; j < qt.getSize()-1;j++){
                int tVal = qt.getTreeUncompressed().get(i).get(j);
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
        viewStage.setScene(scene);
        viewStage.show();
    }
    public void closeView(){
        this.viewStage.close();
    }
    public void getViewStageClose(Label label){
        this.viewStage.setOnCloseRequest(x->{
            label.setText("Output: View Window Closed");
        });
    }
    public void reset(){
        this.closeView();
        this.qt = null;
    }
}
