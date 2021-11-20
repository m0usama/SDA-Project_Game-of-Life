public class Grid {
    int Columns;
    int Rows;
    Cell[][] Cells;
    public void SetDimentions(int R,int C)
    {
        Rows=R;
        Columns=C;
    }
    public Cell getcell(int x,int y)//x==rows, y==columns
    {
        return Cells[x][y];
    }
    public void Setcell(int x,int y,int status)
    {
        Cells[x][y].SetStatus(status);
    }

    public void UnSetcell(int x,int y)
    {
        Cells[x][y].SetStatus(0);
    }
    public void Clear()
    {
        for(int i=0;i<Rows;i++)
        {
            for(int j=0;j<Columns;j++)
            {
                Cells[i][j].SetStatus(0);
            }
        }
    }

}
