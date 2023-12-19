package game;

public class ChangedDisplayedType extends CreatureAction {
    public String name;
    
    public ChangedDisplayedType(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        
        System.out.println("Created ChangedDisplayType with name: " + name + " owner: " + _owner.name);
    }
    
}