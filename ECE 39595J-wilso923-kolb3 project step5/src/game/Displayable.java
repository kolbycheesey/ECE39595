package game;

public class Displayable {
    public boolean visible;
    public int maxHit;
    public int hpMoves;
    public int Hp;
    public char t;
    public int v;
    public int x;
    public int y;
    public int width;
    public int height;
    
    public Displayable() {  
        System.out.println("Construct Displayable");
    }
    
    public void setInvisible() {
        visible = false;
        System.out.println("Displayable setInvisible");
    }
    
    public void setVisible() {
        visible = true;
        System.out.println("Displayable setVisible");
    }
    
    public void setMaxHit(int _maxHit) {
        maxHit = _maxHit;
        System.out.println("Displayable setMaxHit: " + maxHit);
    }
    
    public void setHpMoves(int _hpMoves) {
        hpMoves = _hpMoves;
        System.out.println("Displayable setHpMove: " + hpMoves);
    }
    
    public void setHp(int _hp) {
        Hp = _hp;
        System.out.println("Displayable setHp: " + Hp);
    }
    
    public void setType(char _t) {
        t = _t;
        System.out.println("Displayable setType: " + t);
    }
    
    public void setIntValue(int _v) {
        v = _v;
        System.out.println("Displayable setIntValue: " + v);
    }
    
    public void setPosX(int _x) {
        x = _x;
        System.out.println("Displayable setPosX: " + x);
    }
    
    public void setPosY(int _y) {
        y = _y;
        System.out.println("Displayable setPosY: " + y);
    }
    
    public void setWidth(int x) {
        width = x;
        System.out.println("Displayable setWidth: " + width);
    }
    
    public void setHeight(int y) {
        height = y;
        System.out.println("Displayable setHeight: " + height);
    }
}