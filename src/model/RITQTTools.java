package model;

import java.util.LinkedList;
import java.util.List;

import model.RITQT;
import model.RITQTNode;

public class RITQTTools {
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


    public static LinkedList<LinkedList<Integer>> unCompress(RITQTNode tree, int size){
        LinkedList<LinkedList<Integer>> unCompressed = new LinkedList<LinkedList<Integer>>();
        
        for(int i=1;i<=size;i++){
            
            for(int j=1;j<=size;j++){
                if(i==1){unCompressed.add(new LinkedList<Integer>());}
                //get the value of the tree at j,i. once agian IN COL FIRST FORMAT >:c
                int tVal = RITQTTools.getNodeVal(j,i,size,tree);
                unCompressed.get(j-1).add(tVal);
            }
        }
        return unCompressed;
    }
    public static LinkedList<Integer> compressedAsList(RITQTNode currentNode){
        LinkedList<Integer> subQuadrent = new LinkedList<Integer>();
        subQuadrent.add(currentNode.getVal());
        if(currentNode.getVal()==-1){
            subQuadrent.addAll(compressedAsList(currentNode.getUpperLeft()));
            subQuadrent.addAll(compressedAsList(currentNode.getUpperRight()));
            subQuadrent.addAll(compressedAsList(currentNode.getLowerLeft()));
            subQuadrent.addAll(compressedAsList(currentNode.getLowerRight()));    
        }
        return subQuadrent;
    }

    public static LinkedList<Integer> compressedAsList(RITQTNode tree, int size){
        LinkedList<Integer> compressedAsList = new LinkedList<Integer>();
        compressedAsList.add(size*size);
        compressedAsList.addAll(compressedAsList(tree));
        return compressedAsList;
    }

    public static LinkedList<Integer> unCompressedAsList(LinkedList<LinkedList<Integer>> inArr){
        LinkedList<Integer> unCompressedAsList = new LinkedList<Integer>();
        for(int i=0; i<inArr.size()-1;i++){
            for(int j=0; j<inArr.size()-1;j++){
                unCompressedAsList.add(inArr.get(j).get(i));
            }
        }   
        return unCompressedAsList;
    }

    public static LinkedList<Integer> compressToList(LinkedList<LinkedList<Integer>> inArr){
        
        LinkedList<Integer> compressedList =new LinkedList<Integer>();
        compressedList.add(inArr.get(0).size()*inArr.size());
        compressedList.addAll(compressedAsList(compress(inArr)));
        return compressedList;
        
    }

    public static RITQTNode compress(LinkedList<LinkedList<Integer>> inArr){
        
        for(LinkedList<Integer> list : inArr){
            for(int x : list){
                if(x!=inArr.get(0).get(0)){
                    
                    return new RITQTNode(-1, compress(quadDivide(inArr, 1)),compress(quadDivide(inArr, 3)), compress(quadDivide(inArr, 2)), compress(quadDivide(inArr, 4)));
                }
            }
        }
        
        return new RITQTNode(inArr.get(0).get(0));
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
