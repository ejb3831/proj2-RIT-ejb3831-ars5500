package ptui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import model.RITQTNode;

public class RITUncompress {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
            return;
        }
        System.out.println("Uncompressing: " + args[0]);
        LinkedList<Integer> textNums = new LinkedList<Integer>();
        try {
            Files.readAllLines(Paths.get(args[0])).forEach((x) -> {
                textNums.add(Integer.valueOf(x));
            });;
        } catch (IOException e) {
            System.out.println("Error opening input file: ");
            e.printStackTrace();
            return;
        }
        int size = textNums.remove(0);
        System.out.print("QTree: ");
        RITQTNode quadTree = qtform(textNums);
        System.out.println("");
        
        LinkedList<Integer> imAsArr = convertToArr(quadTree, (int) Math.sqrt(size), args[1]); 
        
        

    }
    

    public static RITQTNode qtform(List<Integer> image){
        int value = image.remove(0);
        System.out.print(""+value+", ");
        if(value==-1){
            return new RITQTNode(value, qtform(image), qtform(image), qtform(image), qtform(image));
        }else{
            return new RITQTNode(value);
        }
    }

    public static Integer getNodeVal(int x, int y, int scope, RITQTNode tree){
        int newScope = scope/2;
        if(tree.getVal()!=-1){
            return tree.getVal();
        }else{
            if((x<=newScope)&&(y<=newScope)){
                return getNodeVal(x,y,newScope,tree.getUpperLeft());
            }else if((x>newScope)&&(y<=newScope)){
                return getNodeVal(x-newScope,y,newScope,tree.getUpperRight());
            }else if((x<=newScope)&&(y>newScope)){
                return getNodeVal(x,y-newScope,newScope,tree.getLowerLeft());
            }else{
                return getNodeVal(x-newScope, y-newScope, newScope, tree.getLowerRight());
            }
            
        }
    }
    public static LinkedList<Integer> convertToArr(RITQTNode tree, int size, String name){
        File unComped = new File(name);
        try{
            if(unComped.createNewFile()){
                System.out.println("Output File: "+ name);
            }else{
                System.out.println("Error generating output file: File already exists");
            }
        }catch(IOException e){
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return null;
        }
        LinkedList<Integer> imageList = new LinkedList<Integer>();
        try {
            FileWriter writeFile = new FileWriter(unComped);
            for(int i=1;i<=size;i++){
                for(int j=1;j<=size;j++){
                    int tVal = getNodeVal(j,i,size,tree);
                    imageList.add(tVal);
                    writeFile.write(""+tVal+"\n");
                }
            }
            writeFile.close(); 
        } catch (IOException e) {
            System.out.println("Error generating output file: ");
            e.printStackTrace();
            return null;
        }
        
        
        return imageList;
    }

    
}