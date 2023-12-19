package game;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;

    public KeyStrokePrinter(ObjectDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    boolean weararmor = false;
    boolean wieldsword = false;
    boolean readobject = false;
    boolean dropping = false;
    boolean endGame = false;
    boolean checkHelp = false;
    int hpMoves = 0;
    private boolean processInput() {

        char ch;
        char moveTo;

        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                if (Rouge.getPlayer().Hp < 0) { return false; }
                displayGrid.clearInfo();
                if (dropping == true) {
                    if (Character.isDigit(ch)) {
                        dropping = displayGrid.dropItem(Integer.parseInt(String.valueOf(ch)), displayGrid.playerX, displayGrid.playerY);
                    } else {
                        displayGrid.writeInfo("Pick a pack ID number");
                    }
                } else if (endGame == true) {
                    if ('y' == ch | 'Y' == ch) {
                        displayGrid.writeInfo("Game ended manually");
                        return false;
                    } else {
                        endGame = false;
                    }
                } else if(wieldsword == true) {
                    if (Character.isDigit(ch)) {
                        wieldsword = displayGrid.takeOut(Integer.parseInt(String.valueOf(ch)), displayGrid.playerX, displayGrid.playerY);
                    } else {
                        displayGrid.writeInfo("Pick a pack ID number");
                    }
                } else if(weararmor == true) {
                    if (Character.isDigit(ch)) {
                        weararmor = displayGrid.equipArmor(Integer.parseInt(String.valueOf(ch)), displayGrid.playerX, displayGrid.playerY);
                    } else {
                        displayGrid.writeInfo("Pick a pack ID number");
                    }
                } else if(readobject == true) {
                    if (Character.isDigit(ch)) {
                        readobject = displayGrid.readItem(Integer.parseInt(String.valueOf(ch)) , displayGrid.playerX, displayGrid.playerY);
                    } else {
                        displayGrid.writeInfo("Pick a pack ID number");
                    }
                } else if (checkHelp == true) {
                    switch (ch) {
                        case 'w':
                            displayGrid.writeInfo("move up 1 space");
                            break;
                        case 'a':
                            displayGrid.writeInfo("move left 1 space");
                            break;
                        case 's':
                            displayGrid.writeInfo("move down 1 space");
                            break;
                        case 'd':
                            displayGrid.writeInfo("move right 1 space");
                            break;
                        case 'i':
                            displayGrid.writeInfo("inventory -- show pack contents");
                            break;
                        case 'c':
                            displayGrid.writeInfo("take off/change armor");
                            break;
                        case 'h':
                            displayGrid.writeInfo("drop <item number> item from pack");
                            break;
                        case 'p':
                            displayGrid.writeInfo("pick up item under player and put into the pack");
                            break;
                        case 't':
                            displayGrid.writeInfo("take out weapon from pack");
                            break;
                        case 'q':
                            displayGrid.writeInfo("take out the armor which is item number <item number in pack> in the pack and put it on");
                            break;
                        case '?':
                            displayGrid.writeInfo("Lists all commands in this section");
                            break;
                        case 'H':
                            displayGrid.writeInfo("H <cmd> gives more detailed information about the <cmd>");
                            break;
                        case 'E':
                            displayGrid.writeInfo("If the next char is Y or y, game over, otherwise the game continues");
                            break;
                        default:
                            break;
                    }
                    checkHelp = false;
                } else {
                    switch (ch) {
                        case 'E':
                            endGame = true;
                            displayGrid.writeInfo("End Game? (press Y or y to end)");
                            break;
                        case 'a':
                            if (ObjectDisplayGrid.blind) {
                                displayGrid.resetHallucinate();
                            }
                            
                            moveTo = displayGrid.getCharAtLocation(displayGrid.playerX-1, displayGrid.playerY).getChar();
                            if (moveTo == 'S' | moveTo == 'T' | moveTo == 'H') {
                                attackMonster(displayGrid.playerX-1, displayGrid.playerY);
                            } else if  (moveTo == '.' | moveTo == '#' | moveTo == '+' | moveTo == '?' | moveTo == ']' | moveTo == ')') {
                                movePlayer(displayGrid.playerX-1, displayGrid.playerY);
                            } else {
                                displayGrid.writeInfo("You can't move there!");
                            }
                            hpMoves++;
                            if (hpMoves == Rouge.getPlayer().hpMoves) {
                                displayGrid.updateHP(1);
                                hpMoves = 0;
                            }
                            if (ObjectDisplayGrid.halluciate) {
                                displayGrid.Hallucinate();
                            }
                            break;
                        case 'd':
                            if (ObjectDisplayGrid.blind) {
                                displayGrid.resetHallucinate();
                            }
                            moveTo = displayGrid.getCharAtLocation(displayGrid.playerX+1, displayGrid.playerY).getChar();
                            if (moveTo == 'S' | moveTo == 'T' | moveTo == 'H') {
                                attackMonster(displayGrid.playerX+1, displayGrid.playerY);
                            } else if  (moveTo == '.' | moveTo == '#' | moveTo == '+' | moveTo == '?' | moveTo == ']' | moveTo == ')') {
                                movePlayer(displayGrid.playerX+1, displayGrid.playerY);
                            } else {
                                displayGrid.writeInfo("You can't move there!");
                            }
                            hpMoves++;
                            if (hpMoves == Rouge.getPlayer().hpMoves) {
                                displayGrid.updateHP(1);
                                hpMoves = 0;
                            }
                            if (ObjectDisplayGrid.halluciate) {
                                displayGrid.Hallucinate();
                            }
                            break;
                        case 'w':
                            if (ObjectDisplayGrid.blind) {
                                displayGrid.resetHallucinate();
                            }
                            moveTo = displayGrid.getCharAtLocation(displayGrid.playerX, displayGrid.playerY-1).getChar();
                            if (moveTo == 'S' | moveTo == 'T' | moveTo == 'H') {
                                attackMonster(displayGrid.playerX, displayGrid.playerY-1);
                            } else if  (moveTo == '.' | moveTo == '#' | moveTo == '+' | moveTo == '?' | moveTo == ']' | moveTo == ')') {;
                                movePlayer(displayGrid.playerX, displayGrid.playerY-1);
                            } else {
                                displayGrid.writeInfo("You can't move there!");
                            }
                            hpMoves++;
                            if (hpMoves == Rouge.getPlayer().hpMoves) {
                                displayGrid.updateHP(1);
                                hpMoves = 0;
                            }
                            if (ObjectDisplayGrid.halluciate) {
                                displayGrid.Hallucinate();
                            }
                            break;
                        case 's':
                            if (ObjectDisplayGrid.blind) {
                                displayGrid.resetHallucinate();
                            }
                            moveTo = displayGrid.getCharAtLocation(displayGrid.playerX, displayGrid.playerY+1).getChar();
                            if (moveTo == 'S' | moveTo == 'T' | moveTo == 'H') {
                                attackMonster(displayGrid.playerX, displayGrid.playerY+1);
                            } else if  (moveTo == '.' | moveTo == '#' | moveTo == '+' | moveTo == '?' | moveTo == ']' | moveTo == ')') {
                                movePlayer(displayGrid.playerX, displayGrid.playerY+1);
                            } else {
                                displayGrid.writeInfo("You can't move there!");
                            }
                            hpMoves++;
                            if (hpMoves == Rouge.getPlayer().hpMoves) {
                                displayGrid.updateHP(1);
                                hpMoves = 0;
                            }
                            if (ObjectDisplayGrid.halluciate) {
                                displayGrid.Hallucinate();
                            }
                            break;
                        case 'i':
                            displayGrid.displayPack();
                            break;
                        case 'p':
                            displayGrid.pickUpItem(displayGrid.playerX, displayGrid.playerY);
                            break;
                        case 'h':   //DROP ITEM
                            dropping = true;
                            displayGrid.writeInfo("Select pack ID number to drop Item");
                            break;
                        case 'c':   //change of take off armor
                            displayGrid.takeOff(displayGrid.playerX, displayGrid.playerY);
                            break;
                        case 'r':   //read item
                            readobject = true;
                            displayGrid.writeInfo("Select pack ID of scroll to read");
                            break;
                        case 't':   //take out weapon
                            wieldsword = true;
                            displayGrid.writeInfo("Select pack ID number to take out sword");
                            break;
                        case 'q':   //wear item moved to q since w is used
                            weararmor = true;
                            displayGrid.writeInfo("Select pack ID number to equip armor");
                            break;
                        case '?':   //info command
                            displayGrid.writeInfo("w,a,s,d,i,?,H,c,h,p,r,t,q,E, H <cmd> for more info");
                            break;
                        case 'H':   //help command
                            checkHelp = true;
                            displayGrid.writeInfo("w,a,s,d,i,?,H,c,h,p,r,t,q,E, choose a command to learn more");
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }

    public void movePlayer(int x, int y){
        displayGrid.movePlayer(x, y);
    }

    public void attackMonster(int x, int y) {
        displayGrid.attackMonster(x, y);
    }
}
