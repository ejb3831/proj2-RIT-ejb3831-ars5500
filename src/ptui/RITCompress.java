package ptui;

import java.util.LinkedList;
import java.util.List;

import model.RITQT; 
/**
 * this is the compressions file, not nearly as long as it was before due to my
 * helper classes
 * takes an uncompressed file and compresses it
 * @author Evelyn Barnes
 */
public class RITCompress {
    /**
     * main method for RITCompress
     * @param args uncompressed-file.txt compressed-file.rit
     */
    public static void main(String[] args) {
        
        if (args.length != 2) {
            System.out.println("Usage: java RITCompress uncompressed-file.txt compressed-file.rit");
            return;
        }
        System.out.println("Compressing: 1" + args[0]);
        RITQT qt = new RITQT(args[0]);
        qt.printCompressed();
        qt.save(args[1]);
        System.out.println("Raw image size: " + qt.getUnCompAsList().size());
        System.out.println("Compressed image size: " + qt.getCompAsList().size());
        System.out.println("Compression %: " + (((0.0+qt.getUnCompAsList().size()-qt.getCompAsList().size())/qt.getUnCompAsList().size())*100));
        
        
    }
                   
                                                                                                                                                                                                                                                                                                                                      
}
