package ptui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import model.RITQTNode;

public class RITUncompress {
    public static void main(String[] args) {
        // if (args.length != 2) {
        //     System.out.println("Usage: java RITUncompress compressed.rit uncompressed.txt");
        //     return;
        // }
        LinkedList<Integer> textNums = new LinkedList<Integer>();
        try {
            Files.readAllLines(Paths.get("images/compressed/simple8x8.rit")).forEach((x) -> {
                textNums.add(Integer.valueOf(x));
            });;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int size = textNums.remove(0);
        RITQTNode quadTree = qtform(textNums);
        System.out.println("Finished Reading");
        

    }
    

    public static RITQTNode qtform(List<Integer> image){
        int value = image.remove(0);
        if(value==-1){
            return new RITQTNode(value, qtform(image), qtform(image), qtform(image), qtform(image));
        }else{
            return new RITQTNode(value);
        }
    }

    
}