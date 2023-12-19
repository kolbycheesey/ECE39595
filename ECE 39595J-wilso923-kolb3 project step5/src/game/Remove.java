package game;

public class Remove extends CreatureAction {
    public String name;
    
    public Remove(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        
        System.out.println("Created Remove with name: " + name + " owner: " + _owner.name);

    }
    
}