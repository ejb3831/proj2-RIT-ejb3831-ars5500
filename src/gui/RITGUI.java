package gui;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RITGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        RITGUIBase gui = new RITGUIBase(stage);
        gui.setTitle("RITGUI");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        
        Button openFile = new Button();
        openFile.setText("Open File");
        

        Button saveComp = new Button();
        saveComp.setText("Save Compressed");
        saveComp.setTranslateX(68);
 
        Button saveUnComp = new Button();
        saveUnComp.setText("Save Un-Compressed");
        saveUnComp.setTranslateX(178);

        Button view = new Button();
        view.setText("View Image");
        view.setTranslateX(308.5);

        Button reset = new Button();
        reset.setText("RESET");
        reset.setTranslateX(337);
        reset.setTranslateY(25);

        Label selectedFile = new Label("Current file: ");
        selectedFile.setTranslateY(25);

        Label output = new Label("Output: ");
        output.setTranslateY(37);

        openFile.setOnAction(x ->{
            output.setText("Output: Select a file");
            File file = fileChooser.showOpenDialog(gui);
            
            if(file != null){
                output.setText("Output: file selected: "+ file.getName());
                try{
                    gui.setQT(file.getPath());
                    output.setText("Output: file loaded: "+ file.getName());
                    selectedFile.setText("Current file: " + file.getName());
                }catch(Exception e){
                    output.setText("Output: ERROR, please check console or try again");
                    e.printStackTrace();
                }
            }else{
                output.setText("Output: No file selected");
            }
        });

        saveComp.setOnAction(x -> {
            if(gui.qtExtists()){
                output.setText("Output: Enter file name and save location");
                String path = fileChooser.showSaveDialog(gui).getPath();
                if(path != null && path != ""){
                    output.setText("Output: file path selected: "+ path);
                    File tempFile = new File(path);
                    if(tempFile.exists()){
                        output.setText("Output: File already exists, please try again");
                    }else{
                        try{
                            gui.getQT().save(path);
                            output.setText("Output: file saved at path  "+ path);
                            
                        }catch(Exception e){
                            output.setText("Output: ERROR, please check console or try again");
                            e.printStackTrace();
                        }
                    }

                }else{
                    output.setText("Output: No file selected");
                }
            }else{
                output.setText("Output: No file currently loaded, please open a file and try again");
            }
        });


        saveUnComp.setOnAction(x -> {
            if(gui.qtExtists()){
                output.setText("Output: Enter file name and save location");
                String path = fileChooser.showSaveDialog(gui).getPath();
                if(path != null && path != ""){
                    output.setText("Output: file path selected: "+ path);
                    File tempFile = new File(path);
                    if(tempFile.exists()){
                        output.setText("Output: File already exists, please try again");
                    }else{
                        try{
                            gui.getQT().save(path);
                            output.setText("Output: file saved at path  "+ path);
                            
                        }catch(Exception e){
                            output.setText("Output: ERROR, please check console or try again");
                            e.printStackTrace();
                        }
                    }

                }else{
                    output.setText("Output: No file selected");
                }
            }else{
                output.setText("Output: No file currently loaded, please open a file and try again");
            }
        });

        view.setOnAction(x ->{
            if(gui.qtExtists()){
                output.setText("Output: Displaying selected file");
                try{
                    gui.genView();
                    gui.getViewStageClose(output);
                    output.setText("Output: Displayed selected file");
                    
                }catch(Exception e){
                    output.setText("Output: ERROR, please check console or try again");
                    e.printStackTrace();
                }
            }else{
                output.setText("Output: No file currently loaded, please open a file and try again");
            }
        });

        reset.setOnAction(x ->{
            output.setText("Output: reseting");
            gui.reset();
            selectedFile.setText("Current File: ");
            output.setText("Output: ");
        });
        Group buttonGroup = new Group();
        buttonGroup.getChildren().addAll(openFile, saveComp, saveUnComp, view, reset, selectedFile, output);
        Scene scene1 = new Scene(buttonGroup);
        gui.setScene(scene1);
        gui.setOnCloseRequest(x -> {
            output.setText("Output: Goodbye!");
        });
        gui.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
