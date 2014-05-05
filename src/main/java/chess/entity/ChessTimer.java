package chess.entity;

import java.util.Calendar;
import java.util.Date;

public class ChessTimer extends Thread{
    private Long total=600000L;
    private volatile Boolean pause=false;
    @Override
    public void run()
    {
        while(true){
            if(!pause && total>0)
            {
                try{
                    total=total-1000;
                    this.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void pause()
    {
        this.pause=true;
    }
    public void go()
    {
        if(!this.isAlive()){
            this.start();
        }
        this.pause=false;
    }
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
