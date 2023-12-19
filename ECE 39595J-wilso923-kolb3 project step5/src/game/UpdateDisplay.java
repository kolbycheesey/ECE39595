package game;

public class UpdateDisplay extends CreatureAction {
    public String name;
    
    public UpdateDisplay(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        
        System.out.println("Created UpdateDisplay with name: " + name + " owner: " + _owner.name);
    }
    
}