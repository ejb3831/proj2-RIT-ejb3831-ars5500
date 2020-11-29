package ptui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class fileTools {
    public static List<String> openDocRead(String name){
        Path fileName = Paths.get(name);

        try {
            
            return Files.readAllLines(fileName);
            

        } catch (IOException e) {
            System.out.println("Error Reading File: ");
            e.printStackTrace();
            return null;
        }

    }
    public static File newDocWrite(String name){
        File unComped = new File(name);
        try{
            if(unComped.createNewFile()){
                System.out.println("Output File: "+ name);
                return unComped;
            }else{
                System.out.println("Error generating output file: File already exists");
                return null;
            }
        }catch(IOException e){
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return null;
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
    public static void saveUnCompressed(LinkedList<LinkedList<Integer>> unCompressed, String name){
        File unComped = newDocWrite(name);    
        try {
            //open the file in writing mode
            FileWriter writeFile = new FileWriter(unComped);
            //iterate as if iterating over the uncompressed list
            for(LinkedList<Integer> list : unCompressed){
                for(int value : list){
                    writeFile.write("" + value + "\n");
                }
            }   
            //close the file
            writeFile.close(); 
        } catch (IOException e) {
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return;
        }
    }
    public static void saveCompressed(LinkedList<Integer> compressedAsList, String name){
        File unComped = newDocWrite(name);    
        try {
            //open the file in writing mode
            FileWriter writeFile = new FileWriter(unComped);
            //iterate as if iterating over the uncompressed list
            for(int value : compressedAsList){
                writeFile.write("" + value + "\n");
            }
            //close the file
            writeFile.close(); 
        } catch (IOException e) {
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return;
        }
    }

    /**
     * Loops throught the entire list of values, and then converts them
     * to a 2D array...also its done column first like HEATHENS >:c
     * @param textIn the array of values to be converted
     * @return the newly generated 2D array
     * @TODO change to get input of the sidelength instead of relying on the class variable, its bad for reusability rn but i thought i was going to need to recurse or something idk
     */
    public static LinkedList<LinkedList<Integer>> readUnCompressed(String name){
        List<String> textIn = openDocRead(name);
        //get the size of each side of the square
        int sideLength = (int) Math.sqrt(textIn.size());
        //im sure this is probably the intended implementation, but im very proud of thinking of this
        //it checks the int sideLength against the long from Math.sqrt(file.size)
        //when comparing an int and a long, the int is converted to a long like 1.0
        //if the file is square, the sqare root function will return a nice number
        //where as if it is not a square, it will return an image with decimal points
        //comparing that against the int version will compare say 1.0 and 1.42blahblahblah
        //and then since those are not equal we know the image is not square
        //sorry about the long comment, just thought it was interesting how that worked
        if(sideLength != Math.sqrt(textIn.size())){
            System.out.println("Error: Image is not a square");
            return null;
        }
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

    public static LinkedList<Integer> readCompressed(String name){
        LinkedList<Integer> textNums = new LinkedList<Integer>();
        //read the .rit file input into the textNums LinkedList
        List<String> fileIn = openDocRead(name);
        if(((int) Math.sqrt(Integer.valueOf(fileIn.get(0)))) != (Math.sqrt(Integer.valueOf(fileIn.get(0))))){
            System.out.println("Error: Image is not a square");
            return null;
        }
        fileIn.forEach((x) -> {
            textNums.add(Integer.valueOf(x));
        });;
        return textNums;
    }

}
