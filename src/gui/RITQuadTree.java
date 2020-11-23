package gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


import model.RITQTNode;

public class RITQuadTree {
    String fileName;
    List<String> file;
    LinkedList<LinkedList<Integer>> unComIm;
    int sideLength;

    public RITQuadTree(String fileName) {
        this.fileName = fileName;
        try {
            this.file = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sideLength = this.file.size()/4;
        this.unComIm = this.genList(file);
        System.out.println("finished loading");
        
    }
    public LinkedList<LinkedList<Integer>> genList(List<String> textIn){
        LinkedList<LinkedList<Integer>> numIn = new LinkedList<LinkedList<Integer>>();
        for(int i =0; i<sideLength-1;i++){
            numIn.add(new LinkedList<Integer>());
            for(int j =0; j<sideLength-1;j++){
                numIn.get(i).add(Integer.valueOf(textIn.get((this.sideLength-1*(i))+j)));
            }
        }
        return numIn;
    }

    public int getSideLength(){return sideLength;};
    public LinkedList<LinkedList<Integer>> getImage(){return unComIm;}
    

    
}
