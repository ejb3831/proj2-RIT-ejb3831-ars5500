package gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


import model.RITQTNode;
/**
 * This is a class made to handle the text processing and loading side of the image
 * for this one we didnt need to store in a QT because that would be 
 * cumbersome for the implementation, so despite the name, currently does
 * not include any quad trees
 * @author Evan/Evelyn Barnes
 */
public class RITQuadTree {
    String fileName;
    List<String> file;
    LinkedList<LinkedList<Integer>> unComIm;
    int sideLength;
    /**
     * This is the meat and potat-okay i guess i have overused that joke by now
     * anyways, this is the cconstructor for the RITQuadTree class. It loads
     * the file from text and then converts it to a 2D array
     * @param fileName the path to the file to be opened
     */
    public RITQuadTree(String fileName) {
        //open the file
        this.fileName = fileName;
        try {
            this.file = Files.readAllLines(Paths.get(fileName));

        } catch (IOException e) {
            System.out.println("Error Reading File: ");
            e.printStackTrace();
            return;
        }
        //get the size of each side of the square
        this.sideLength = (int) Math.sqrt(file.size());
        //im sure this is probably the intended implementation, but im very proud of thinking of this
        //it checks the int sideLength against the long from Math.sqrt(file.size)
        //when comparing an int and a long, the int is converted to a long like 1.0
        //if the file is square, the sqare root function will return a nice number
        //where as if it is not a square, it will return an image with decimal points
        //comparing that against the int version will compare say 1.0 and 1.42blahblahblah
        //and then since those are not equal we know the image is not square
        //sorry about the long comment, just thought it was interesting how that worked
        if(this.sideLength != Math.sqrt(file.size())){
            System.out.println("Error: Image is not a square");
            return;
        }
        //generates the 2D array
        this.unComIm = this.genList(file);
       
        
    }
    /**
     * Loops throught the entire list of values, and then converts them
     * to a 2D array...also its done column first like HEATHENS >:c
     * @param textIn the array of values to be converted
     * @return the newly generated 2D array
     * @TODO change to get input of the sidelength instead of relying on the class variable, its bad for reusability rn but i thought i was going to need to recurse or something idk
     */
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

    /**
     * getter methods for sideLength and the 2D array
     * @return sidelength
     * @return 2D array
     */
    public int getSideLength(){return sideLength;};
    public LinkedList<LinkedList<Integer>> getImage(){return unComIm;}
    

    
}
