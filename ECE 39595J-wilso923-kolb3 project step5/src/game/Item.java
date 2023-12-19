package game;

public class Item extends Displayable {
    public Creature owner;
    public String name;
    public int room;
    public int serial;
    public Action ia;
    
    public Item() {
        System.out.println("Construct Item");
    }
    
    public Item(String _name) {
        name = _name;
    }
    
    public void setOwner(Creature _owner) {
        owner = _owner;
        System.out.println("Item setOwner: " + owner.name);
    }
    
    public void setAction(ItemAction _ia) {
        ia = _ia;
        System.out.println("Item setAction: " + ia);
    }
    
    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
        
        System.out.println("Set Item ID, room: " + room + ", serial: " + serial);
    }
    
    @Override
    public String toString() {
        return name;
    }
}