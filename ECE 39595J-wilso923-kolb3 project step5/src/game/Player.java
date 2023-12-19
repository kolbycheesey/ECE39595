package game;

import java.util.ArrayList;

public class Player extends Creature {
    public int room;
    public int serial;
    public Armor armor;
    public Sword weapon;
    public ArrayList<Item> pack = new ArrayList<>();
    
    public Player() {
        System.out.println("Construct Player");
    }
    
    public void setWeapon(Sword _weapon) {
        weapon = _weapon;
        //pack.add(weapon);
        System.out.println("Set Player Weapon");
    }
    
    public void setArmor(Armor _armor) {
        armor = _armor;
        //pack.add(armor);
        System.out.println("Set Player Armor");
    }
    
    public void setItem(Item _item) {
        pack.add(_item);
        System.out.println("Add item to pack");
    }
    
    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
        System.out.println("Set Player ID room: " + room + " serial: " + serial);
    }
}