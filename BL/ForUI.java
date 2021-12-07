
public interface ForUI {
    public int Start(int status) throws InterruptedException;
    public int Next(int status);
    public int Stop(int status);
    public int Reset(int status);
    public int Clear(int status);
    public void SpeedControl(int speed);
    public int ManuallyDrawaPatren(int x,int y);
}
