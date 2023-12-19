package game;


import java.util.ArrayList;

public class Passage extends Structure {
    public String name;
    public int id1;
    public int id2;
    public ArrayList<Integer> xVals= new ArrayList<>();
    public ArrayList<Integer> yVals= new ArrayList<>();
    
    public Passage() {
        System.out.println("Construct Passage");
    }
    
    public void setName() {
        System.out.println("Set Passage Name: ");
    }
    
    public void setID(int room1, int room2) {
        id1 = room1;
        id2 = room2;    
        System.out.println("Set Passage ID room1: " + room1 + " room2: " + room2);
    }
    
    @Override
    public void setPosX(int x) {
        xVals.add(x);
    }
    
    @Override
    public void setPosY(int y) {
        yVals.add(y);
    }
}