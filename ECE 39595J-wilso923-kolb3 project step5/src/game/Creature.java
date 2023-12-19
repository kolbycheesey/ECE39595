package game;

import java.util.ArrayList;

public class Creature extends Displayable {
    public String name;
    public ArrayList<CreatureAction> da = new ArrayList<>();
    public ArrayList<CreatureAction> ha = new ArrayList<>();
    
    public Creature() {
        System.out.println("Constructed Creature");
    }
    
    public void setDeathAction(CreatureAction _da) {
        da.add(_da);
        System.out.println("Set Creature DeathAction");
    }
    
    public void setHitAction(CreatureAction _ha) {
       ha.add(_ha);
        System.out.println("Set Creature HitAction");
    }
    
    public void setName(String _name) {
        name = _name;
        System.out.println("Set Creature Name: " + name);
    }
}