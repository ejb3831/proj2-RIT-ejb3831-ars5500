package ptui;

import java.util.LinkedList;
import java.util.List;

import gui.RITQuadTree; 

public class RITCompress {
    public static void main(String[] args) {
        
        // if (args.length != 2) {
        //     System.out.println("Usage: java RITCompress uncompressed-file.txt compressed-file.rit");
        //     return;
        // }
        RITQuadTree textIn = new RITQuadTree("images/input/uncompressed/simple8x8.txt");
        System.out.println(trimComp(textIn.getImage()));
    }
    public static String trimComp(LinkedList<LinkedList<Integer>> inArr){
        return "" + (inArr.size()*inArr.get(0).size())+"\n"+comp(inArr);
    }
    public static String comp(LinkedList<LinkedList<Integer>> inArr){
        
        for(LinkedList<Integer> list : inArr){
            for(int x : list){
                if(x!=inArr.get(0).get(0)){
                    return "-1\n" + comp(quadDivide(inArr, 1)) + "\n" + comp(quadDivide(inArr, 3)) + "\n" + comp(quadDivide(inArr, 2)) + "\n" + comp(quadDivide(inArr, 4));
                }
            }
        }
        return "" + inArr.get(0).get(0);
    }
    public static LinkedList<LinkedList<Integer>> quadDivide(LinkedList<LinkedList<Integer>> original, int quad){
        LinkedList<LinkedList<Integer>> quadrant= new LinkedList<LinkedList<Integer>>();
        
        switch(quad){
            case 1:
                for(int i =0; i<=(original.size()/2)-1;i++){
                    LinkedList<Integer> tList = new LinkedList<Integer>();
                    tList.addAll(original.get(i).subList(0, (original.size()/2)));
                    quadrant.add(tList);
                }
                break;
            case 2:
                for(int i = 0; i<=(original.size()/2)-1;i++){
                    LinkedList<Integer> tList = new LinkedList<Integer>();
                    tList.addAll(original.get(i).subList((original.size()/2), (original.size())));
                    quadrant.add(tList);
                }
                break;  
            case 3:
                for(int i = (original.size()/2); i<=(original.size())-1;i++){
                    LinkedList<Integer> tList = new LinkedList<Integer>();
                    tList.addAll(original.get(i).subList(0, (original.size()/2)));
                    quadrant.add(tList);
                }
                break;
            case 4:
                for(int i = (original.size()/2); i<=(original.size())-1;i++){
                    LinkedList<Integer> tList = new LinkedList<Integer>();
                    tList.addAll(original.get(i).subList((original.size()/2), (original.size())));
                    quadrant.add(tList);
                }
                break;
        }
        return quadrant;
    }                                                                                                                                                                                                                                                                                                                                                                           
}
