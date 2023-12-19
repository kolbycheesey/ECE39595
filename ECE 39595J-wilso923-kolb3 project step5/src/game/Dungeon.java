package game;


import java.util.ArrayList;

public class Dungeon {
    public String name;
    public int width;
    public int topHeight;
    public int gameHeight;
    public int bottomHeight;
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Creature> creatures = new ArrayList<>();
    public ArrayList<Passage> passages = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();

    public Dungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        name = _name;
        width = _width;
        topHeight = _topHeight;
        gameHeight = _gameHeight;
        bottomHeight = _bottomHeight;
        
        System.out.println("Contruct Dungeon with name: " + name + ", width: " + width + ", topHeight: " + topHeight +", gameHeight: " + gameHeight + ", bottomHeight: " + bottomHeight);
    }
    
    
    public Dungeon getDungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight) {
        if (_name.equals(name) & _width == width & _gameHeight == gameHeight) {
            return this;
        }
        return new Dungeon(_name, _width, _topHeight, _gameHeight, _bottomHeight);
    }
    
    public Dungeon getDungeon() {
        return this;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Add Room to Dungeon");
    }
    
    public void addCreature(Creature creature) {
        creatures.add(creature);
        System.out.println("Added Creature to Dungeon");
    }
    
    public void addPassage(Passage passage) {
        passages.add(passage);
        System.out.println("Added Passage to Dungeon");
    }
    
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Added Item to Dungeon");
    }
}