package game;

public class Teleport extends CreatureAction {
    public String name;
    
    public Teleport(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        
        System.out.println("Created Teleport with name: " + name + " owner: " + _owner.name);
    }
    
}