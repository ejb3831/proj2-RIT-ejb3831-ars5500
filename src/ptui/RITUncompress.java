package ptui;

import model.RITQT;
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
        System.out.print("QTree: ");
        RITQT qt = new RITQT(args[0]);
        
        qt.printCompressed();
        //convert the RITQTNode quad tree into a uncompressed list and save it to a file
        qt.save(args[1]);

        

    }
    

    
    
}