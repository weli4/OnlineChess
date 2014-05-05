package chess.entity;


public class Player {
    private String name;
    private String color;
    private ChessTimer timer;
    private Double rating;
    private Integer win;
    private Integer loose;
    private Integer withdraw;

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLoose() {
        return loose;
    }

    public void setLoose(Integer loose) {
        this.loose = loose;
    }

    public Integer getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Integer withdraw) {
        this.withdraw = withdraw;
    }
}
