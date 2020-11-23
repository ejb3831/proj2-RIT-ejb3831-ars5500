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
            System.out.println("Error Reading File: ");
            e.printStackTrace();
            return;
        }
        this.sideLength = (int) Math.sqrt(file.size());
        if(this.sideLength != Math.sqrt(file.size())){
            System.out.println("Error: Image is not a square");
            return;
        }
        this.unComIm = this.genList(file);
        System.out.println("finished loading");
        
    }
    public LinkedList<LinkedList<Integer>> genList(List<String> textIn){
        LinkedList<LinkedList<Integer>> numIn = new LinkedList<LinkedList<Integer>>();
        
        for(int i =0; i<sideLength;i++){
            
            for(int j =0; j<sideLength;j++){
                if(i==0){
                    numIn.add(new LinkedList<Integer>());
                }
                int tVal = Integer.valueOf(textIn.remove(0));
                if(tVal<0 || tVal>255){
                    System.out.println("Error: Value " + tVal + " is not in the range 0-255");
                    return null;
                }
                numIn.get(j).add(tVal);
                
            }
        }
        return numIn;
    }

    public int getSideLength(){return sideLength;};
    public LinkedList<LinkedList<Integer>> getImage(){return unComIm;}
    

    
}
