package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.RITQuadTree;
public class RITViewer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    public static void main(String[] args) {
        RITQuadTree qIn = new RITQuadTree("images\\uncompressed\\ritlogo128x128.txt");
        Application.launch(args);

    }
}
