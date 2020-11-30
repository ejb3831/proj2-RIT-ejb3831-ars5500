package model;

import java.util.LinkedList;


import ptui.fileTools;

public class RITQT {
    private int size;
    private RITQTNode tree;
    private LinkedList<LinkedList<Integer>> treeUncompressed;
    private String name;
    public LinkedList<Integer> compAsList;
    public LinkedList<Integer> unCompAsList;

    public int getSize(){return this.size;}
    public String getName(){return this.name;}
    
    public RITQTNode getTree(){
        if(this.tree == null){
            this.fillOther();
        }
        return this.tree;
    }

    public LinkedList<LinkedList<Integer>> getTreeUncompressed(){
        if(this.treeUncompressed == null){
            this.fillOther();
        }
        return this.treeUncompressed;
    }
    public LinkedList<Integer> getCompAsList(){
        
        this.compAsList = RITQTTools.compressedAsList(this.getTree(), this.size);
        
        return this.compAsList;
    }
    public LinkedList<Integer> getUnCompAsList(){
        if(this.unCompAsList == null){
            this.unCompAsList = RITQTTools.unCompressedAsList(this.getTreeUncompressed());
        }
        return this.unCompAsList;
    }
    public RITQT(String name){
        this.name = name;
        if(name.contains(".txt")){
            this.treeUncompressed = fileTools.readUnCompressed(name);
            this.size = (int) Math.sqrt(this.treeUncompressed.size()*this.treeUncompressed.get(0).size());
        }else if(name.contains(".rit")){
            this.compAsList = fileTools.readCompressed(name);
            this.size = (int) Math.sqrt(compAsList.remove(0));
            this.tree= RITQTTools.qtform(compAsList);
        }else{
            System.out.println("Unsupported file type: "+name.substring(name.length()-4));
            return;
        }
    }

    public RITQT(String name, LinkedList<LinkedList<Integer>> treeUncompressed){
        this.name = name;
        this.treeUncompressed = treeUncompressed;
        this.size = (int) Math.sqrt(this.treeUncompressed.size()*this.treeUncompressed.get(0).size());
    }

    public RITQT(String name, RITQTNode tree,int size){
        this.name = name;
        this.tree = tree;
        this.size = size;
    }

    public RITQT(String name, LinkedList<LinkedList<Integer>> treeUncompressed, RITQTNode tree){
        this.name = name;
        this.treeUncompressed = treeUncompressed;
        this.tree = tree;
        this.size = (int) Math.sqrt(this.treeUncompressed.size()*this.treeUncompressed.get(0).size());
    }

    public RITQT(String name, LinkedList<LinkedList<Integer>> treeUncompressed, RITQTNode tree, int size){
        this.name = name;
        this.treeUncompressed = treeUncompressed;
        this.tree = tree;
        this.size = size;
    }
    
    private void compress(){
        if(this.treeUncompressed != null){
            this.tree = RITQTTools.compress(this.treeUncompressed);
        }else{
            System.out.println("This RIT QT already is compressed :)");
        }
    }

    private void unCompress(){
        if(this.tree != null){
            this.treeUncompressed = RITQTTools.unCompress(this.tree, this.size);
        }else{
            System.out.println("This RIT QT already is un-compressed :)");
        }
    }

    public void fillOther(){
        if(this.tree == null){
            this.compress();
        }else if(this.treeUncompressed == null){
            this.unCompress();
        }else{
            System.out.println("Nothing to fill :)");
        }
    }

    private void saveCompressed(String fileName){
        fileTools.save(this.getCompAsList(), fileName);        
    }

    private void saveUnCompressed(String fileName){
        
        fileTools.save(this.getUnCompAsList(), fileName); 
    }


    public void save(String fileName){
        
        if(fileName.contains(".txt")){
            this.saveUnCompressed(fileName);
        }else if(fileName.contains(".rit")){
            this.saveCompressed(fileName);
        }else{
            System.out.println("Unsupported file type: "+fileName.substring(fileName.length()-4));
        }
    }
    
    public void printCompressed(){
       
        for(int val : this.getCompAsList()){
            if(val != this.getCompAsList().get(0)&& val != this.size){System.out.print(""+val+" ");}
            
        }
        System.out.println("");
    }

    public void printUnCompressed(){
        System.out.print("QTree: ");
        for(int val : this.getUnCompAsList()){
            System.out.print(""+val+" ");
        }
        System.out.println("");
    }
    
}
