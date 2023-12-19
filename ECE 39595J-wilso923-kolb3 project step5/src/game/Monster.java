package game;

public class Monster extends Creature {
    public int room;
    public int serial;
            
    public Monster() {
        System.out.println("Contructed Monster");
    }
    
    public void setID(int _room, int _serial) {
        room = _room;
        serial = _serial;
        System.out.println("Set Monster ID room: " + room + " serial: " + serial);
    }
}