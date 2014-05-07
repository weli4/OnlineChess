package chess.entity;

import java.util.Calendar;
import java.util.Date;

public class ChessTimer extends Thread{
    private volatile Long total=600000L;  // milisecs = 10 mins
    private volatile Boolean pause=false; // 'volatile' means that this field can be changed while thread is running
    @Override
    public void run()
    {
        while(true){
            if(!pause && total>0) //pause if not current player turn
            {
                try{
                    total=total-1000;
                    this.sleep(1000);   //every second decrement total value;
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
            this.start();  //start the thread if it is not running
        }
        this.pause=false;
    }
    public Long getTotal() {
        return total;
    }
    public void stopTimer()
    {
        total=600000L;
        pause=false;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
