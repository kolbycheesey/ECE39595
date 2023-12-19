package game;

public class EndGame extends CreatureAction {
    public String name;
    
    public EndGame(String _name, Creature _owner) {
        super(_owner);
        name = _name;
        
        System.out.println("Created EndGame with name: " + name + " owner: " + _owner.name);
    }
    
}