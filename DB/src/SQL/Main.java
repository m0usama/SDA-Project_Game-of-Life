package SQL;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


class Grid {
    public int rows;
    public int [][]arrays = new int [rows][2];                //grid array

    public Grid() {
        rows = 0;
        for ( int i=0 ; i<rows ; i++)
        {
            for ( int j=0 ; j<2 ; j++)
            {
                arrays[i][j] = -1;
            }
        }
    }

    public Grid( int r , int arr[][]) {
        rows = r;
        for ( int i=0 ; i<rows ; i++)
        {
            for ( int j=0 ; j<2 ; j++)
            {
                arrays[i][j] = arr[i][j];
            }
        }
    }

}

public class State {
    public int stateID;
    public Grid grid;

    public State()
    {
        stateID = 0;
    }

    public State(int id , int r, int [][]a) {
        this.stateID = id;
        this.grid = new Grid(r,a);
    }

    public int getStateID (){
        return 0;
    }

    public State loadState () {
        SQL obj=new SQL();
        State output;
        try
        {
            output = obj.loadState( stateID );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public State deleteState (){
        SQL obj=new SQmL();
        State output;
        try
        {
            output = obj.deleteState( stateID );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;

    }

    public State viewState (){

    }

    public State saveState (){

    }
    public class Main {

        public static void main(String[] args) {
            // write your code here
        }
    }
