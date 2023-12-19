package game;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class Rouge implements Runnable {

    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static int WIDTH;
    private static int HEIGHT;

    public static Dungeon dungeon = null;
    private static Player player = null;

    public Rouge(int width, int height) {
        displayGrid = new ObjectDisplayGrid(width, height);
    }

    @Override
    public void run() {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                displayGrid.addObjectToDisplay(new Char(' '), i, j);
            }
        }
        
        for (Room room : dungeon.rooms) {
            for (int i = room.x; i < room.x+room.width; i += 1) {
                for (int j = room.y; j < room.y+room.height; j += 1) {
                    if (i == room.x | i == room.x+room.width-1) {
                        displayGrid.addObjectToDisplay(new Char('X'), i, j+dungeon.topHeight);
                    } else if (j == room.y | j == room.y+room.height-1) {
                        displayGrid.addObjectToDisplay(new Char('X'), i, j+dungeon.topHeight);
                    } else {
                        displayGrid.addObjectToDisplay(new Char('.'), i, j+dungeon.topHeight);
                    }
                }
            }

            for (Item item : room.items) {
                System.out.println(item.name);
                if (item.name.contains("Armor")) {
                    displayGrid.addObjectToDisplay(new Char(']'), item.x + room.x, item.y + room.y + dungeon.topHeight);
                } else if (item.name.contains("Sword")) {
                    displayGrid.addObjectToDisplay(new Char(')'), item.x + room.x, item.y + room.y + dungeon.topHeight);
                } else if (item.name.contains("Scroll")) {
                    displayGrid.addObjectToDisplay(new Char('?'), item.x + room.x, item.y + room.y + dungeon.topHeight);
                }
                displayGrid.addDisplayableToDisplay(item, item.x + room.x, item.y + room.y + dungeon.topHeight);
            }

            for (Creature creature : room.creatures) {
                if ("Player".equals(creature.name)) {
                    player = (Player) creature;
                    displayGrid.addObjectToDisplay(new Char('@'), creature.x + room.x, creature.y + room.y + dungeon.topHeight);
                } else {
                    displayGrid.addObjectToDisplay(new Char(creature.t), creature.x + room.x, creature.y + + room.y + dungeon.topHeight);
                }
                displayGrid.addDisplayableToDisplay(creature, creature.x + room.x, creature.y + room.y + dungeon.topHeight);
            }
        }

        for (Passage passage : dungeon.passages) {
            int points = passage.xVals.size();
            for (int i = 0; i < points-1; i++) {
                if (Objects.equals(passage.xVals.get(i+1), passage.xVals.get(i))) {
                    if (passage.yVals.get(i) < passage.yVals.get(i+1)) {
                        for (int y = passage.yVals.get(i); y <= passage.yVals.get(i+1); y++) {
                            displayGrid.addObjectToDisplay(new Char('#'), passage.xVals.get(i), y+dungeon.topHeight);
                        }
                    } else {
                        for (int y = passage.yVals.get(i+1); y <= passage.yVals.get(i); y++) {
                            displayGrid.addObjectToDisplay(new Char('#'), passage.xVals.get(i), y+dungeon.topHeight);
                        }
                    }
                } else if (Objects.equals(passage.yVals.get(i), passage.yVals.get(i+1))) {
                    if (passage.xVals.get(i) < passage.xVals.get(i+1)) {
                        for (int x = passage.xVals.get(i); x <= passage.xVals.get(i+1); x++) {
                            displayGrid.addObjectToDisplay(new Char('#'), x, passage.yVals.get(i)+dungeon.topHeight);
                        }
                    } else {
                        for (int x = passage.xVals.get(i+1); x <= passage.xVals.get(i); x++) {
                            displayGrid.addObjectToDisplay(new Char('#'), x, passage.yVals.get(i)+dungeon.topHeight);
                        }
                    }
                }
            }
            displayGrid.addObjectToDisplay(new Char('+'), passage.xVals.get(0), passage.yVals.get(0)+dungeon.topHeight);
            displayGrid.addObjectToDisplay(new Char('+'), passage.xVals.get(points-1), passage.yVals.get(points-1)+dungeon.topHeight);
        }
        
        displayGrid.writeStringToTerminal("HP: " + player.Hp + "      Score: 0", 0, 0);
        
        displayGrid.writeStringToTerminal("Pack: ", 0, dungeon.topHeight+dungeon.gameHeight+dungeon.bottomHeight-3);
        displayGrid.writeStringToTerminal("Info: ", 0, dungeon.topHeight+dungeon.gameHeight+dungeon.bottomHeight-1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        //displayGrid.initializeDisplay();
    }

    public static void main(String[] args) throws Exception {

	// check if a filename is passed in
        String fileName = null;
        switch (args.length) {
        case 1:
           // note that the relative file path may depend on what IDE you are
	   // using.  This worked for NetBeans.
           fileName = "src/xmlFiles/" + args[0];
           break;
        default:
           System.out.println("java Test <xmlfilename>");
	   return;
        }

	// Create a saxParserFactory, that will allow use to create a parser
	// Use this line unchanged
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
	    // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLParser handler = new XMLParser();
	    // This will parse the xml file given by fileName
            saxParser.parse(new File(fileName), handler);
	    // This will change depending on what kind of XML we are parsing
            ArrayList<Displayable> displayables = handler.getDisplayables();
            dungeon = handler.getDungeon();
	    // print out all of the displayables.
            System.out.println(dungeon.rooms);
            for (Room room : dungeon.rooms) {
                System.out.println(room.creatures);
                System.out.println(room.items);
            }
            System.out.println(dungeon.passages);

            for (Displayable displayed : displayables) {
                System.out.println(displayed);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        HEIGHT = dungeon.bottomHeight + dungeon.gameHeight + dungeon.topHeight;
        WIDTH = dungeon.width;

        Rouge test = new Rouge(WIDTH, HEIGHT);
        Thread testThread = new Thread(test);
        testThread.start();

        test.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
        
        test.keyStrokePrinter.start();

        testThread.join();
        test.keyStrokePrinter.join();
    }
    
    
    public static Player getPlayer() {
        return player;
    }
}