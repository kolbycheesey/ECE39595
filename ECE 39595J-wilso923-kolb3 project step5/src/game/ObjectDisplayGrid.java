package game;

import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    private Stack<Char>[][] objectGrid;
    public Stack<Displayable>[][] displayableGrid;

    private List<InputObserver> inputObservers = null;

    public static int height;
    public static int width;
    
    public int playerX;
    public int playerY;
    
    public static boolean halluciate = false;  
    public int halMoves = 0;
    public static boolean firstHal = false;
    public static boolean blind = false;

    public ObjectDisplayGrid(int _width, int _height) {
        width = _width;
        height = _height;

        terminal = new AsciiPanel(width, height);

        objectGrid = (Stack<Char>[][]) new Stack[width][height];
        displayableGrid = (Stack<Displayable>[][]) new Stack[width][height];
        
        initializeDisplay();

        super.add(terminal);
        super.setSize(width * 10, height * 17);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }            
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Char ch = new Char('.');
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                objectGrid[i][j] = new Stack();
                displayableGrid[i][j] = new Stack();
                addObjectToDisplay(ch, i, j);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y].push(ch);
                writeToTerminal(x, y);
                if (ch.getChar() == '@') {
                    playerX = x;
                    playerY = y;
                }
            }
        }
    }
    
    public void addDisplayableToDisplay(Displayable disp, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                displayableGrid[x][y].push(disp);
            }
        }
    }
    
    public void popObjectFromDisplay(int x, int y) {
        objectGrid[x][y].pop();
        writeToTerminal(x, y);
    }
    
    public Displayable popDisplayableFromDisplay(int x, int y) {
        if (displayableGrid[x][y].isEmpty()) {
            return null;
        }
        return displayableGrid[x][y].pop();
    }
    
    public Char getCharAtLocation(int x, int y) {
        return objectGrid[x][y].peek();
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].peek().getChar();
        terminal.write(ch, x, y);
        terminal.repaint();
    }
    
    public void writeStringToTerminal(String str, int x, int y) {
        Char ch;
        for (int i = 0; i < str.length(); i++) {
            ch = new Char(str.charAt(i));
            objectGrid[x][y].pop();
            objectGrid[x][y].push(ch);
            terminal.write(ch.getChar(), x, y);
            x++;
        }
        terminal.repaint();
    }
    
    public void movePlayer(int x, int y) {
        Player player = Rouge.getPlayer();
        popObjectFromDisplay(playerX, playerY);
        popDisplayableFromDisplay(playerX, playerY);
        addDisplayableToDisplay(player, x, y);
        addObjectToDisplay(new Char('@'), x, y);
        player.setPosX(x);
        player.setPosY(y);
    }
    
    public void attackMonster(int x, int y){
        if (displayableGrid[x][y].isEmpty()) {
            movePlayer(x, y);
            return;
        }
        Monster monster = (Monster) displayableGrid[x][y].peek();
        Player player = Rouge.getPlayer();
        
        Random rand = new Random();
        int playerHit = rand.nextInt(player.maxHit + 1);
        if (player.weapon != null) {
            playerHit += player.weapon.v;
        }
        monster.setHp(monster.Hp - playerHit);
        if (monster.Hp > 0 ) {
            boolean teleported = doHitActions(monster, x, y);
            if (teleported == false) {
                int monsterHit = rand.nextInt(monster.maxHit + 1);
                if (player.armor != null) {
                    monsterHit = monsterHit - player.armor.v;
                }
                if (monsterHit > 0) {
                    updateHP(-monsterHit);
                } 
                if (player.Hp <= 0) {
                    doPlayerDeathActions(player, player.x, player.y);
                } else {
                    doPlayerHitActions(player, player.x, player.y);
                }
            }
        } else {
            doDeathActions(monster, x, y);
            popDisplayableFromDisplay(x, y);
        }
    }
    
    public boolean doHitActions(Monster creature, int x, int y) {
        Action action;
        boolean teleported = false;
        int i = 0;
        while (i < creature.ha.size()) {
            action = creature.ha.get(i);
            System.out.println(action);
            if (action instanceof Remove) {
                popObjectFromDisplay(x, y);
                writeInfo(action.message);
            } else if (action instanceof YouWin) {
                writeInfo(action.message);
            } else if (action instanceof ChangedDisplayedType) {
                popObjectFromDisplay(x, y);
                addObjectToDisplay(new Char(action.c), x, y);
            } else if (action instanceof Teleport) {
                Teleport(creature, x, y);
                writeInfo(action.message);
                teleported = true;
            } 
            i++;
        }
        return teleported;
        
    }
    
    public void doPlayerHitActions(Player creature, int x, int y) {
        Action action;
        int i = 0;
        while (i < creature.ha.size()) {
            action = creature.ha.get(i);
            if (action instanceof Remove) {
                popObjectFromDisplay(x, y);
                writeInfo(action.message);
            } else if (action instanceof YouWin) {
                writeInfo(action.message);
            } else if (action instanceof ChangedDisplayedType) {
                popObjectFromDisplay(x, y);
                addObjectToDisplay(new Char(action.c), x, y);
            } else if (action instanceof Teleport) {
                Teleport(creature, x, y);
                writeInfo(action.message);
            } else if (action instanceof EndGame) {
                writeInfo(action.message);
            } else if (action instanceof DropPack) {
                if (Rouge.getPlayer().pack.isEmpty()) {
                    writeInfo("Pack already empty");
                } else {
                    int idx = 0;
                    Item item = Rouge.getPlayer().pack.get(idx);
                    if (item == Rouge.getPlayer().armor | item == Rouge.getPlayer().weapon) {
                        idx++;
                        if (idx < Rouge.getPlayer().pack.size()) {
                            item = Rouge.getPlayer().pack.get(idx);
                        }
                    }
                    if (item == Rouge.getPlayer().armor | item == Rouge.getPlayer().weapon) {
                        idx++;
                    }
                    idx++;
                    if (idx < Rouge.getPlayer().pack.size()+1) {
                        dropItem(idx, x, y);
                        writeInfo(action.message);
                    }
                }
            } 
            i++;
        }
    }
    
    public void doDeathActions(Monster creature, int x, int y) {
        Action action;
        while (creature.da.isEmpty() == false) {
            action = creature.da.remove(0);
            System.out.println(action);
            if (action instanceof Remove) {
                popObjectFromDisplay(x, y);
                writeInfo(action.message);
            } else if (action instanceof YouWin) {
                writeInfo(action.message);
            } else if (action instanceof ChangedDisplayedType) {
                popObjectFromDisplay(x, y);
                addObjectToDisplay(new Char(action.c), x, y);
            } else if (action instanceof Teleport) {
                Teleport(creature, x, y);
                writeInfo(action.message);
            } 
        }
    }
    
    public void doPlayerDeathActions(Player creature, int x, int y) {
        Action action;
        while (creature.da.isEmpty() == false) {
            action = creature.da.remove(0);
            System.out.println(action);
            if (action instanceof Remove) {
                popObjectFromDisplay(x, y);
                writeInfo(action.message);
            } else if (action instanceof YouWin) {
                writeInfo(action.message);
            } else if (action instanceof ChangedDisplayedType) {
                popObjectFromDisplay(x, y);
                addObjectToDisplay(new Char(action.c), x, y);
            } else if (action instanceof Teleport) {
                Teleport(creature, x, y);
                writeInfo(action.message);
            } else if (action instanceof EndGame) {
                writeInfo(action.message);
            }
        }
    }
    
    public void Teleport(Monster creature, int x, int y) { 
        popDisplayableFromDisplay(x, y);
        popObjectFromDisplay(x, y);
        
        Random rand = new Random();
        while (true) {
            int newX = rand.nextInt(width);
            int newY = rand.nextInt(height);
            
            if (isValid(objectGrid[newX][newY].peek())) {
                addObjectToDisplay(new Char(creature.t), newX, newY);
                addDisplayableToDisplay(creature, newX, newY);
                return;
            }
        }
    }
    
    public void Teleport(Player creature, int x, int y) { 
        popDisplayableFromDisplay(x, y);
        popObjectFromDisplay(x, y);
        
        Random rand = new Random();
        while (true) {
            int newX = rand.nextInt(width);
            int newY = rand.nextInt(height);
            
            if (isValid(objectGrid[newX][newY].peek())) {
                addObjectToDisplay(new Char(creature.t), newX, newY);
                addDisplayableToDisplay(creature, newX, newY);
                return;
            }
        }
    }
    
    public boolean isValid(Char ch) {
        return (ch.getChar() == '.' | ch.getChar() == '+' | ch.getChar() == '#');
    }
    
    public void updateHP(int change) {
        Rouge.getPlayer().setHp(Rouge.getPlayer().Hp+change);
        Char ch;
        for (int i = 4; i < Integer.toString(Rouge.getPlayer().Hp).length()+5; i++) {
            objectGrid[i][0].pop();
        }
        for (int i = 4; i < Integer.toString(Rouge.getPlayer().Hp).length()+5; i++) {
            if (i-4 < Integer.toString(Rouge.getPlayer().Hp).length()) {
                ch = new Char(Integer.toString(Rouge.getPlayer().Hp).charAt(i-4));
            } else { ch = new Char(' '); }
            objectGrid[i][0].push(ch);
            terminal.write(ch.getChar(), i, 0);
        }
        terminal.repaint();
    }
    
    public void pickUpItem(int x, int y) {
        Player player = (Player) displayableGrid[x][y].pop();
        popObjectFromDisplay(x, y);
        if (displayableGrid[x][y].isEmpty() != true) {
            Item item = (Item) displayableGrid[x][y].pop();
            popObjectFromDisplay(x, y);
            player.setItem(item);
            writeInfo("You picked up an Item: " + item.name);
            if (item instanceof Scroll) { writeInfo(item.ia.message); }
            
        }
        addObjectToDisplay(new Char('@'), x, y);
        displayableGrid[x][y].push(player);       
        displayPack();
    }
    
    public boolean dropItem(int idx, int x, int y) {
        Player player = (Player) displayableGrid[x][y].pop();
        popObjectFromDisplay(x, y);
        idx--;
        displayPack();
        if (player.pack.isEmpty()) {
            writeInfo("Pack already empty");
        } else if (idx < player.pack.size() & idx >= 0) {
            Item item = player.pack.remove(idx);
            if (item == player.armor) {
                player.armor = null;
            } else if (item == player.weapon) {
                player.weapon = null;
            } 
            if (item.name.contains("Armor")) {
                addObjectToDisplay(new Char(']'),x, y);
            } else if (item.name.contains("Sword")) {
                addObjectToDisplay(new Char(')'), x, y);
            } else if (item.name.contains("Scroll")) {
                addObjectToDisplay(new Char('?'), x, y);
            }
            addDisplayableToDisplay(item, x, y);
            writeInfo("You dropped an Item: " + item.name);
        } else {
            writeInfo("Not a valid pack ID number");
        }
        addObjectToDisplay(new Char('@'), x, y);
        displayableGrid[x][y].push(player);    
        displayPack();
        return false;
    }
    
    public void displayPack() {
        Player player = Rouge.getPlayer();
        String str = "";
        for (int i = 0; i < 10; i++) {
            if (i < player.pack.size()) {
                if (player.pack.get(i) instanceof Armor) {
                    str += Integer.toString(i+1) + ":";
                    if (player.pack.get(i).v >= 0) { str += "+";}
                    str += player.pack.get(i).v + " Armor "; 
                    if (player.pack.get(i) == player.armor) {
                        str += "(a) ";
                    }
                } else if (player.pack.get(i) instanceof Sword) {
                    str += Integer.toString(i+1) + ":";
                    if (player.pack.get(i).v >= 0) { str += "+";}
                    str += player.pack.get(i).v + " Sword "; 
                    if (player.pack.get(i) == player.weapon) {
                        str += "(w) ";
                    } 
                } else if (player.pack.get(i) instanceof Scroll) {
                    str += Integer.toString(i+1) + ":";
                    if ("Hallucinate".equals(player.pack.get(i).ia.scrollName)) {
                        str += "Hallucinate ";
                    } else {
                        if (player.pack.get(i).ia.v >= 0) {
                            str += "Bless ";
                        } else { str += "Curse "; }
                        if (player.pack.get(i).ia.c == 'a') {
                            str += "Armor";
                        } else { str += "Weapon"; }
                    }
                } else {
                    str += Integer.toString(i+1) + ":";
                    str += player.pack.get(i).name + " ";
                }
            } else { break; }
        }
        Char ch;
        for (int i = 6; i < width; i++) {
            if (i-6 < str.length()) {
                ch = new Char(str.charAt(i-6));
            } else { ch = new Char(' '); }
            objectGrid[i][height-3].pop();
            objectGrid[i][height-3].push(ch);
            terminal.write(ch.getChar(), i, height-3);
        }
        terminal.repaint();
    }
    
    public void clearPack() {
        Char ch = new Char(' ');
        for (int i = 6; i < width; i++) {
            objectGrid[i][height-3].pop();
            objectGrid[i][height-3].push(ch);
            terminal.write(ch.getChar(), i, height-3);
        }
        terminal.repaint();
    }
    
    public void writeInfo(String str) {
        Char ch;
        clearInfo();
        for (int i = 6; i < width; i++) {
            if (i-6 < str.length()) {
                ch = new Char(str.charAt(i-6));
            } else { ch = new Char(' '); }
            objectGrid[i][height-1].pop();
            objectGrid[i][height-1].push(ch);
            terminal.write(ch.getChar(), i, height-1);
        }
        terminal.repaint();
    }
    
    public void clearInfo() {
        Char ch = new Char(' ');
        for (int i = 6; i < width; i++) {
            objectGrid[i][height-1].pop();
            objectGrid[i][height-1].push(ch);
            terminal.write(ch.getChar(), i, height-1);
        }
        terminal.repaint();
    }
    
    public void takeOff(int x, int y) {
       Player player = Rouge.getPlayer();
       
       displayPack();
       if (player.armor == null) {  
           writeInfo("You are not wearing any armor");
       } else {
           Item item = player.armor;
           player.setArmor(null);
           writeInfo("You have removed your armor");
       }
       displayPack();
    }
    
    public boolean takeOut(int idx, int x, int y){
        Player player = Rouge.getPlayer();
        idx--;
        displayPack();
        if (player.pack.isEmpty()) {
            writeInfo("Pack is empty");
            return false;
        } else if (idx < player.pack.size() & idx >= 0) {
            Item item = player.pack.get(idx);
            if (item.name.contains("Sword")){
                writeInfo("Your sword has been taken out");
                player.setWeapon((Sword) item);
            } else {
                writeInfo("Thats not a sword try again");
            }
        } else {
            writeInfo("Not a valid pack ID number");
        }
        displayPack();
        return false;
    }
    
    public boolean equipArmor(int idx, int x, int y){
        Player player = Rouge.getPlayer();
        idx--;
        displayPack();
        if (player.pack.isEmpty()){
            writeInfo("Pack is empty");
        } else if (idx < player.pack.size() & idx >= 0){
            Item item = player.pack.get(idx);
            if(item.name.contains("Armor")){
                writeInfo("Your armor has been equipped");
                player.setArmor((Armor) item);
            } else {
                writeInfo("Thats not armor try again");
            }
        } else {
            writeInfo("Not a valid pack ID number");
        }
        displayPack();
        return false;
    }
    
    public boolean readItem(int idx, int x, int y){
        Player player = Rouge.getPlayer();       
        idx--;
        displayPack();
        if (player.pack.isEmpty()) {
            writeInfo("Pack is empty");
        } else if(idx < player.pack.size() & idx >= 0){
            Item item = player.pack.get(idx);
            if(item.name.contains("Scroll")){
                if ("Hallucinate".equals(item.ia.scrollName)) {
                    halMoves = item.ia.v;
                    firstHal = true;
                    halluciate = true;
                } else if (item.ia.c == 'a' & player.armor != null) {
                    player.armor.v += item.ia.v;
                    if (item.ia.v < 0) {
                        writeInfo(player.armor.name + " cursed! " + item.ia.v + " taken from its effectiveness");
                        player.armor.name = "Cursed " + player.armor.name;
                    }
                } else if (item.ia.c == 'w' & player.weapon != null) {
                    player.weapon.v += item.ia.v;
                    if (item.ia.v < 0) {
                        writeInfo(player.weapon.name + " cursed! " + item.ia.v + " taken from its effectiveness");
                    }
                } else {
                    writeInfo("Scroll does nothing because item was not being used");
                }
                player.pack.remove(idx);
            } else {
                writeInfo("That is not a scroll");
            }
        } else {
            writeInfo("Not a valid pack ID number");
        }
        displayPack();
        return false;
    }
    
    public void Hallucinate() {
        if (halMoves > 0) {
            for (int x = 0; x < width; x++) {
                for (int y = Rouge.dungeon.topHeight; y < Rouge.dungeon.topHeight+Rouge.dungeon.gameHeight; y++) {
                    if (objectGrid[x][y].peek().getChar() != ' ') {
                        Random rand = new Random();
                        int ch = rand.nextInt(94);
                        ch += 33;
                        if (ch == 46 | ch == 43 | ch == 35 | ch == 64) { ch++; }
                        addObjectToDisplay(new Char((char) ch), x, y);
                    }
                }
            }
            blind = true;
            firstHal = false;
            halMoves--;
        }
    }
    
    public void resetHallucinate() {
        for (int x = 0; x < width; x++) {
                for (int y = Rouge.dungeon.topHeight; y < Rouge.dungeon.topHeight+Rouge.dungeon.gameHeight; y++) {
                if (objectGrid[x][y].peek().getChar() != ' ') {
                    popObjectFromDisplay(x, y);
                }
            }
        }
        blind = false;
    }
    
    public char getHallucinateMove(int x, int y) {
        char ch;
        Char tmp;
        tmp = objectGrid[x][y].pop();
        ch = objectGrid[x][y].peek().getChar();
        objectGrid[x][y].push(tmp);
        return ch;
    }
    
    
//    void registerInputObserver(KeyStrokePrinter aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}

