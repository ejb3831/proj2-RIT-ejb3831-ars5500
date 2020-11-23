package ptui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import model.RITQTNode;
/**
 * RITUncompress or Rich Image Text Uncompress takes files compressed in the RIT file format
 * and uncompresses them into regular TXT files
 * @author Evelyn/Evan Barnes
 * @author Alex Smith  
 */
public class RITUncompress {
    /**
     * this class is the uhhh *mental note think of synonym of meat and potatoes* of RITUncompress
     * it takes in the compressed .RIT file and uncompresses the file into a .TXT file
     * @param args compressed.rit uncompressed.txt
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
            return;
        }
        System.out.println("Uncompressing: " + args[0]);
        LinkedList<Integer> textNums = new LinkedList<Integer>();
        //read the .rit file input into the textNums LinkedList
        try {
            Files.readAllLines(Paths.get(args[0])).forEach((x) -> {
                textNums.add(Integer.valueOf(x));
            });;
        } catch (IOException e) {
            System.out.println("Error opening input file: ");
            e.printStackTrace();
            return;
        }
        //get the size by removing the first member of the List
        int size = textNums.remove(0);
        System.out.print("QTree: ");
        //Load the data from the list into a RITQTNode quad tree
        RITQTNode quadTree = qtform(textNums);
        System.out.println("");
        //convert the RITQTNode quad tree into a uncompressed list and save it to a file
        LinkedList<Integer> imAsArr = convertToArr(quadTree, (int) Math.sqrt(size), args[1]); 
        
        

    }
    
    /**
     * Recursively builds the quad tree out of RITQTNodes
     * @param image the input list
     * @return the fully formed RITQTNode quad tree
     */
    public static RITQTNode qtform(List<Integer> image){
        //get first value of the list
        int value = image.remove(0);
        System.out.print(""+value+", ");
        if(value==-1){
            //value is a "split" node, recursion continues
            return new RITQTNode(value, qtform(image), qtform(image), qtform(image), qtform(image));
        }else{
            //value is a leaf node and returns a RITQTNode reflecting that
            return new RITQTNode(value);
        }
    }
    /**
     * this is another method that im pretty proud of, I was stumped for a couple hours before thinking of this implementation
     * getNodeVal finds the value of a x,y input by recursively searching the tree and throwing out quadrents it cant be in
     * only problem this semester that made me get out graph paper and a pencil and draw out the search
     * 
     * @param x x coordinate to search for
     * @param y y coordinate to  search for
     * @param scope the scope of the search, initial input should be the size of one side of the square
     * @param tree the RITQTNode tree to search in
     * @return the value at (x,y)
     */
    public static Integer getNodeVal(int x, int y, int scope, RITQTNode tree){
        //shorten the scope to check by half
        int newScope = scope/2;
        
        if(tree.getVal()!=-1){
            //found what we were looking for, return that value up the chain
            return tree.getVal();
        }else{
            if((x<=newScope)&&(y<=newScope)){
                //the point is in the top left quadrent
                return getNodeVal(x,y,newScope,tree.getUpperLeft());
            }else if((x>newScope)&&(y<=newScope)){
                //the point is in the top right quadrent
                return getNodeVal(x-newScope,y,newScope,tree.getUpperRight());
            }else if((x<=newScope)&&(y>newScope)){
                //the point is in the bottom left quadrent
                return getNodeVal(x,y-newScope,newScope,tree.getLowerLeft());
            }else{
                //the point is in the bottom right quadrent
                return getNodeVal(x-newScope, y-newScope, newScope, tree.getLowerRight());
            }
            
        }
    }
    /**
     * the final step of the converstion process, takes the .RIT file, converts it to .TXT file
     * and then saves that file
     * @param tree the RITQTNode tree to search
     * @param size the size of the end image
     * @param name the name of the file to write too, should be a .rit file
     * @return the converted array
     */
    public static LinkedList<Integer> convertToArr(RITQTNode tree, int size, String name){
        //create the new file to write to
        File unComped = new File(name);
        try{
            if(unComped.createNewFile()){
                System.out.println("Output File: "+ name);
            }else{
                System.out.println("Error generating output file: File already exists");
                return null;
            }
        }catch(IOException e){
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return null;
        }
        LinkedList<Integer> imageList = new LinkedList<Integer>();
        try {
            //open the file in writing mode
            FileWriter writeFile = new FileWriter(unComped);
            //iterate as if iterating over the uncompressed list
            for(int i=1;i<=size;i++){
                for(int j=1;j<=size;j++){
                    //get the value of the tree at j,i. once agian IN COL FIRST FORMAT >:c
                    int tVal = getNodeVal(j,i,size,tree);
                    imageList.add(tVal);
                    writeFile.write(""+tVal+"\n");
                }
            }
            //close the file
            writeFile.close(); 
        } catch (IOException e) {
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return null;
        }
        
        
        return imageList;
    }

    
}