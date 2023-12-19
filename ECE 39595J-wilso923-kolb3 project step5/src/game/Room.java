package game;


import java.util.ArrayList;

public class Room extends Structure {
    public String name;
    public int id;
    public ArrayList<Creature> creatures = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
            
    public Room(String _name) {
        name = _name;
        System.out.println("Construct Room with name: " + name);
    }
    
    public void setID(int room) {
        id = room;
        System.out.println("Set Room ID to " + id);
    }
    
    public void setCreature(Creature creature) {
        creatures.add(creature);
        System.out.println("Set Creature to Room: " + name + ", ID: " + id);
    }
    
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Set Item to Room: " + name + ", ID: " + id);
    }
}