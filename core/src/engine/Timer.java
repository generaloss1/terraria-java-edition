package engine;

public class Timer{


    private long lastMillis,millis;
    private boolean started;
    private boolean paused;


    public void start(){
        if(!started){
            started=true;
            lastMillis=System.currentTimeMillis();
            millis=System.currentTimeMillis();
        }
    }

    public void setMillis(long millis){
        lastMillis=this.millis-millis;
    }

    public void setSeconds(long seconds){
        setMillis(seconds*1000);
    }

    public void setMinutes(long minutes){
        setSeconds(minutes*60);
    }

    public void setHours(long hours){
        setMinutes(hours*60);
    }

    public long getMillis(){
        if(started){
            if(!paused)
                millis=System.currentTimeMillis();
            return millis-lastMillis;
        }
        return 0;
    }

    public long getSeconds(){
        return getMillis()/1000;
    }

    public long getMinutes(){
        return getSeconds()/60;
    }

    public long getHours(){
        return getMinutes()/60;
    }

    public void stop(){
        if(started)
            started=false;
    }

    public void pause(){
        paused=true;
    }

    public void resume(){
        paused=false;
    }


}
