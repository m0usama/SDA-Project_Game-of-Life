import java.util.concurrent.TimeUnit;

public class GameOfLife {
    public int speed;
    public int columns;
    public int Rows;
    public int Counter;
    public int NoOfStates;
    public Grid GameGrid;
    public int status;
    public GameOfLife()
    {
        speed=1;
        columns=10000;
        Rows=10000;
        Counter=0;
        NoOfStates=0;
        GameGrid=new Grid();
        status=0;
    }
    public int Start(int status) throws InterruptedException     //status=value coming from UI that start is clicked
    {

        if (status==0)
        {
            TimeUnit.SECONDS.sleep(speed);
            Next(status);
            return 1;
        }
        else{
            return 0;
        }
    }
    public int Next(int status)
    {
        return 1;
    }
    public int Stop(int status)
    {
        if(status==1)
        {
            status=0;
            return 1;
        }
        else {
            return 0;
        }
    }
    //public int Reset(int status);
    public int Clear(int status)
    {
        if(status==0)
        {
            GameGrid.Clear();
            return 1;
        }
        else {
            return 0;
        }
    }
    //public Grid LoadState(int stateId);
    //public int saveState(Grid CurrentState);
    public void SpeedControl(int speed)  //current speed of grid
    {
        this.speed=speed;
    }
    //public int DeleteSaveState();
    public int ManuallyDrawaPatren(int x,int y)
    {
        if(GameGrid.Cells[x][y].GetStatus()==0)
        {
            GameGrid.Cells[x][y].SetStatus(1);
        }
        if(GameGrid.Cells[x][y].GetStatus()==1)
        {
            GameGrid.Cells[x][y].SetStatus(0);
        }
        return 1;
    }


}

