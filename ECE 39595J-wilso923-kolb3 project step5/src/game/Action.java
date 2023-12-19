package game;

public class Action {
    public String scrollName = "";
    public String message = "";
    public int v;
    public char c;
    
    public Action() {
        System.out.println("Construct Action");
    }
    
    public void setMessage(String _message) {
        message = _message;
        System.out.println("Action setMessage: " + message);
    }
    
    public void setIntValue(int _v) {
        v = _v;
        System.out.println("Action setIntValue: " + v);
    }
    
    public void setCharValue(char _c) {
        c = _c;
        System.out.println("Action setCharValue " + c);
    }
}