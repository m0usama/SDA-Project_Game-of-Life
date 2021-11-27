public class Cell {
    int status;
    int neighbours;
    public Cell()
    {
        status=0;
        neighbours=0;
    }
    public int GetStatus()
    {
        return status;
    }
    public int GetNeighbour()
    {
        return neighbours;
    }
    public void SetNeighbour(int n)
    {
        this.neighbours=n;
    }
    public void SetStatus(int stat)
    {
        status=stat;
    }

}
