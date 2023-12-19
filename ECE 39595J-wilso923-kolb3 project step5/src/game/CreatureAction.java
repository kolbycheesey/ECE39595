package game;

public class CreatureAction extends Action {
    public Creature owner;
    
    public CreatureAction(Creature _owner) {
        owner = _owner;
        System.out.println("Constructed CreatureAction with owner: " + owner.name);
    }
}