package game;

public class Armor extends Item {
    
    public Armor(String _name) {
       super(_name);
        System.out.println("Constuct Armor: " + name);
    }
    
    public void setName(String _name) {
        name = _name;
        System.out.println("set Armor name to: " + name);
    }
    
}