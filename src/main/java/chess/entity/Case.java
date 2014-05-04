package chess.entity;


public class Case {
    private int x;
    private int y;

    public Case(int y, int x) {
        this.x = x;
        this.y = y;
    }
    public Case(String y, String x)
    {
        this.x=Integer.valueOf(x);
        this.y=Integer.valueOf(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
