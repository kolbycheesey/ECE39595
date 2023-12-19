package game;


import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {
    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "XMLParser";

    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
    // Student objects to this array so that it has a list of
    // all specified students.  Had we covered containers at the
    // time I put this file on the web page I would have made this
    // an ArrayList of Students (ArrayList<Student>) and not needed
    // to keep tract of the length and maxStudents.  You should use
    // an ArrayList in your project.
    private ArrayList<Displayable> displayables = new ArrayList<>();
    

    // The XML file contains a list of Students, and within each
    // Student a list of activities (clubs and classes) that the
    // student participates in.  When the XML file initially
    // defines a student, many of the fields of the object have
    // not been filled in.  Additional lines in the XML file
    // give the values of the fields.  Having access to the
    // current Student and Activity allows setters on those
    // objects to be called to initialize those fields.
    private Dungeon dungeon;
    private Displayable displayableBeingParsed = null;
    private Room roomBeingParsed = null;
    private Creature creatureBeingParsed = null;
    private Player playerBeingParsed = null;
    private Action actionBeingParsed = null;
    private Item ItemBeingParsed = null;
   

    // The bX fields here indicate that at corresponding field is
    // having a value defined in the XML file.  In particular, a
    // line in the xml file might be:
    // <instructor>Brook Parke</instructor>
    // The startElement method (below) is called when <instructor>
    // is seen, and there we would set bInstructor.  The endElement
    // method (below) is called when </instructor> is found, and
    // in that code we check if bInstructor is set.  If it is,
    // we can extract a string representing the instructor name
    // from the data variable above.
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bType = false;
    private boolean bMaxHit = false;
    private boolean bVisible = false;
    private boolean bHP = false;
    private boolean bHPMoves = false;
    private boolean bActionMessage = false;
    private boolean bActionIntValue= false;
    private boolean bActionCharValue = false;
    private boolean bItemIntValue = false;

    // Used by code outside the class to get the list of Student objects
    // that have been constructed.
    public ArrayList<Displayable> getDisplayables() {
        return displayables;
    }
    
    public Dungeon getDungeon() {
        return dungeon;
    }

    // A constructor for this class.  It makes an implicit call to the
    // DefaultHandler zero arg constructor, which does the real work
    // DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
    // imported above, and we don't need to write it.  We get its
    // functionality by deriving from it!
    public XMLParser() {
    }

    // startElement is called when a <some element> is called as part of
    // <some element> ... </some element> start and end tags.
    // Rather than explain everything, look at the xml file in one screen
    // and the code below in another, and see how the different xml elements
    // are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeon = new Dungeon(name, width, topHeight, gameHeight, bottomHeight);
        } else if (qName.equalsIgnoreCase("Rooms")) {
        } else if (qName.equalsIgnoreCase("Passages")) {
        } else if (qName.equalsIgnoreCase("Room")) {
            int roomID = Integer.parseInt(attributes.getValue("room"));
            String roomName = attributes.getValue("name");
            Room room = new Room(roomName);
            room.setID(roomID);
            dungeon.addRoom(room);//addRoom(room);
            displayableBeingParsed = room;
            roomBeingParsed = room;
            
            displayables.add(room);
        } else if (qName.equalsIgnoreCase("Monster")) {
            String monsterName = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setID(roomID, serial);
            monster.setName(monsterName);
            dungeon.addCreature(monster);
            roomBeingParsed.setCreature(monster);
            displayableBeingParsed = monster;
            creatureBeingParsed = monster;
            
            displayables.add(monster);
        } else if (qName.equalsIgnoreCase("Player")) {
            String playerName = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player(); 
            player.setID(room, serial);
            player.setName(playerName);
            dungeon.addCreature(player);
            roomBeingParsed.setCreature(player);
            displayableBeingParsed = player;
            creatureBeingParsed = player;
            playerBeingParsed = player;
            
            displayables.add(player);
        } else if (qName.equalsIgnoreCase("Scroll")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            scroll.setID(room, serial);
            dungeon.addItem(scroll);
            displayables.add(scroll);
            ItemBeingParsed = scroll;
            displayableBeingParsed = scroll;
            if (playerBeingParsed != null) {
                playerBeingParsed.setItem(scroll);
            } else {
                roomBeingParsed.addItem(scroll);
            } 
        } else if (qName.equalsIgnoreCase("Sword")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            sword.setID(room, serial);
            displayables.add(sword);
            ItemBeingParsed = sword;
            displayableBeingParsed = sword;
            if (playerBeingParsed != null) {
                playerBeingParsed.setItem(sword);
            } else {
                roomBeingParsed.addItem(sword);
            }         
        } else if (qName.equalsIgnoreCase("Armor")) {
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(name);
            armor.setID(room, serial);
            ItemBeingParsed = armor;
            displayableBeingParsed = armor;
            if (playerBeingParsed != null) {
                playerBeingParsed.setItem(armor);
            } else {
                dungeon.addItem(armor);
                roomBeingParsed.addItem(armor);
            }
            displayables.add(armor);
            
        } else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            dungeon.addPassage(passage);
            displayableBeingParsed = passage;
            displayables.add(passage);
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            String name = attributes.getValue("name");
            switch (name) {
                case "Teleport":
                    Teleport teleport = new Teleport(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(teleport);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(teleport);
                    }
                    actionBeingParsed = teleport;
                    break;
                case "Remove":
                    Remove remove = new Remove(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(remove);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(remove);
                    }
                    actionBeingParsed = remove;
                    break;
                case "ChangedDisplayedType":
                    ChangedDisplayedType cdp = new ChangedDisplayedType(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(cdp);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(cdp);
                    }
                    actionBeingParsed = cdp;
                    break;
                case "DropPack":
                    DropPack dropPack = new DropPack(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(dropPack);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(dropPack);
                    }
                    actionBeingParsed = dropPack;
                    break;
                case "YouWin":
                    YouWin youWin = new YouWin(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(youWin);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(youWin);
                    }
                    actionBeingParsed = youWin;
                    break;
                case "EndGame":
                    EndGame endGame = new EndGame(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(endGame);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(endGame);
                    }
                    actionBeingParsed = endGame;
                    break;
                case "UpdateDisplay":
                    UpdateDisplay updateDisplay = new UpdateDisplay(name, creatureBeingParsed);
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(updateDisplay);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(updateDisplay);
                    }
                    actionBeingParsed = updateDisplay;
                    break;
                default:
                    CreatureAction ca = new CreatureAction(creatureBeingParsed); 
                    if ("hit".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setHitAction(ca);
                    } else if ("death".equals(attributes.getValue("type"))) {
                        creatureBeingParsed.setDeathAction(ca);
                    }
                    actionBeingParsed = ca;
                    break;
            }
        } else if (qName.equalsIgnoreCase("ItemAction")) { 
            String name = attributes.getValue("name");
            switch (name) {
                case "BlessArmor":
                    BlessCurseOwner bco = new BlessCurseOwner(); 
                    if ("item".equals(attributes.getValue("type"))) {
                        ItemBeingParsed.setAction(bco);
                        ItemBeingParsed.ia.scrollName = name;
                    } 
                    actionBeingParsed = bco;
                    break;
                case "Hallucinate":
                    Hallucinate hallucinate = new Hallucinate(); 
                    if ("item".equals(attributes.getValue("type"))) {
                        ItemBeingParsed.setAction(hallucinate);
                        ItemBeingParsed.ia.scrollName = name;
                    } 
                    actionBeingParsed = hallucinate;
                    break;
                default:
                    ItemAction ia = new ItemAction(); 
                    if ("item".equals(attributes.getValue("type"))) {
                        ItemBeingParsed.setAction(ia);
                    }
                    actionBeingParsed = ia;
                    break;
            }
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            bPosX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bPosY = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxHit = true;
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bHP = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHPMoves = true; 
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true; 
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = true; 
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bItemIntValue = true; 
        } else {
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Displayable displayable;
        if (bPosX) {
            displayable = displayableBeingParsed;
            displayable.setPosX(Integer.parseInt(data.toString()));
            bPosX = false;
        } else if (bPosY) {
            displayable = displayableBeingParsed;
            displayable.setPosY(Integer.parseInt(data.toString()));
            bPosY = false;
        } else if (bWidth) {
            displayable = displayableBeingParsed;
            displayable.setWidth(Integer.parseInt(data.toString()));
            bWidth = false;
        } else if (bHeight) {
            displayable = displayableBeingParsed;
            displayable.setHeight(Integer.parseInt(data.toString()));
            bHeight = false;
        } else if (bType) {
            displayable = displayableBeingParsed;
            displayable.setType(data.toString().charAt(0));
            bType = false;
        } else if (bMaxHit) {
            displayable = displayableBeingParsed;
            displayable.setMaxHit(Integer.parseInt(data.toString()));
            bMaxHit = false;
        } else if (bVisible) {
            displayable = displayableBeingParsed;
            if (Integer.parseInt(data.toString()) == 1) {
                displayable.setVisible();
            } else {
                displayable.setInvisible();
            }
            bVisible = false;
        } else if (bHP) {
            displayable = displayableBeingParsed;
            displayable.setHp(Integer.parseInt(data.toString()));
            bHP = false;
        } else if (bHPMoves) {
            displayable = displayableBeingParsed;
            displayable.setHpMoves(Integer.parseInt(data.toString()));
            bHPMoves = false;
        } else if (bActionMessage) {
            String message = data.toString();
            actionBeingParsed.setMessage(message);
            bActionMessage = false;
        } else if (bActionIntValue) {
            actionBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            bActionIntValue = false;
        } else if (bActionCharValue) {
            char message = data.charAt(0);
            actionBeingParsed.setCharValue(message);
            bActionCharValue = false;
        } else if (bItemIntValue) {
            displayableBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            bItemIntValue = false;
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
        } else if (qName.equalsIgnoreCase("Rooms")) {
        } else if (qName.equalsIgnoreCase("Room")) {
        } else if (qName.equalsIgnoreCase("Monster")) {
        } else if (qName.equalsIgnoreCase("Player")) {
            playerBeingParsed = null;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "XMLParser\n";
//        str += "   maxStudents: " + maxStudents + "\n";
//        str += "   studentCount: " + studentCount + "\n";
//        for (Student student : students) {
//            str += student.toString() + "\n";
//        }
//        str += "   studentBeingParsed: " + studentBeingParsed.toString() + "\n";
//        str += "   activityBeingParsed: " + activityBeingParsed.toString() + "\n";
//        str += "   bInstructor: " + bInstructor + "\n";
//        str += "   bCredit: " + bInstructor + "\n";
//        str += "   bName: " + bInstructor + "\n";
//        str += "   bNumber: " + bInstructor + "\n";
//        str += "   bLocation: " + bInstructor + "\n";
//        str += "   bMeetingTime: " + bInstructor + "\n";
//        str += "   bMeetingDay: " + bInstructor + "\n";
        return str;
    }
}
