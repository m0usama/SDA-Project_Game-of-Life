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
        int flag=1;
        while(flag==1)
        {
            if (this.status == 0) {
                TimeUnit.SECONDS.sleep(speed);
                Next(status);

            }
        }
        return 1;
    }
    public int Next(int status)
    {

        if(this.status==0)
        {
            for(int i=0;i<Rows;i++)
            {

                for(int j=0;j<columns;j++)
                {
                    int neighbour=0;
                    if(i>0 && j> 0 && GameGrid.Cells[i-1][j-1].GetStatus()==1)    //left top  diagonal
                    {
                        neighbour++;
                    }
                    if(i>0 && GameGrid.Cells[i-1][j].GetStatus()==1)        //top
                    {
                        neighbour++;
                    }
                    if(i>0 && j< 9999 && GameGrid.Cells[i-1][j+1].GetStatus()==1)   //right top Diagonal
                    {
                        neighbour++;
                    }
                    if(j> 0 && GameGrid.Cells[i][j-1].GetStatus()==1)     //left
                    {
                        neighbour++;
                    }
                    if(j< 9999 && GameGrid.Cells[i][j+1].GetStatus()==1)  //right
                    {
                        neighbour++;
                    }
                    if(i<9999 && j> 0 && GameGrid.Cells[i+1][j-1].GetStatus()==1)  //left bottom diagonal
                    {
                        neighbour++;
                    }
                    if(i<9999 && GameGrid.Cells[i+1][j].GetStatus()==1)     //down
                    {
                        neighbour++;
                    }
                    if(i<9999 && j<9999 && GameGrid.Cells[i+1][j+1].GetStatus()==1)  //right bottom diagonal
                    {
                        neighbour++;
                    }
                    GameGrid.Cells[i][j].SetNeighbour(neighbour);
                }
            }
            for(int i=0;i<Rows;i++)
            {
                for(int j=0;j<columns;j++)
                {
                    int neighbour=0;
                    neighbour=GameGrid.Cells[i][j].GetNeighbour();
                    if(neighbour != 3 && neighbour !=2 && GameGrid.Cells[i][j].GetStatus()==1)
                    {
                        GameGrid.Cells[i][j].SetStatus(0);    //removing dead cells
                    }
                    else if(neighbour==3 &&GameGrid.Cells[i][j].GetStatus()==0)
                    {
                        GameGrid.Cells[i][j].SetStatus(1);    //Activating dead cells
                    }
                }

            }
        }
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
    public int Reset(int status)
    {
        if(status==0)
        {

        }
        return 1;
    }
    public int[][] GetGrid()  //bl to db
    {
        int[][] arr=new int[Rows][2];
        int size=0;
        for(int i=0;i<Rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if(GameGrid.Cells[i][j].GetStatus()==1)
                {
                    arr[size][0]=i;
                    arr[size][1]=j;
                    size++;
                }
            }
        }
        return arr;
    }
    public int[][] GetGridToUI()  //bl to UI
    {
        int[][] arr=new int[Rows][columns];
        for(int i=0;i<Rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                arr[i][j]=GameGrid.Cells[i][j].GetStatus();
            }
        }
        return arr;
    }
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
    public void LoadState(int[][] arr,int size)
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<2;j++)
            {
                GameGrid.Cells[arr[i][0]][arr[i][1]].SetStatus(1);
            }
        }

    }
    public int saveState(Grid CurrentState)
    {


    }
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
        else if(GameGrid.Cells[x][y].GetStatus()==1)
        {
            GameGrid.Cells[x][y].SetStatus(0);
        }
        return 1;
    }


}

