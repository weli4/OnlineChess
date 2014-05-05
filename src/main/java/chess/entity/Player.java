package chess.entity;


public class Player {
    private String name;
    private String color;
    private ChessTimer timer;

    public Player(){
        timer=new ChessTimer();
    }
    public ChessTimer getTimer() {
        return timer;
    }

    public void setTimer(ChessTimer timer) {
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
