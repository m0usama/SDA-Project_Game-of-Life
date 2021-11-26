package SDASQL;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;


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
        this.grid = new Grid();
    }

    public State(int id , int r, int [][]a) {
        this.stateID = id;
        this.grid = new Grid(r,a);
    }

    public int getStateID (){

        return this.stateID;
    }

    public State loadState () {
        SQL obj=new SQL();
        State output = new State();
        try
        {
            output.grid = obj.loadState( stateID );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public State deleteState (){
        SQL obj=new SQL();
        State output = new State();
        try
        {
            output.grid = obj.deleteState( stateID );
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;

    }

    public State[] viewState ( int sID ){
        SQL obj = new SQL();
        State[] output = new State[sID];
        try
        {
            for ( int i=0 ; i<sID ; i++)
            {
                output[i].grid = obj.viewState(i);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public void saveState ( String FileName) {
        int ro = CountLines(FileName);
        SQL obj = new SQL();
        int[][] ar = new int[ro - 1][2];               //ro = rows
        try {
            File myObj = new File("FileName.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return output;
        }
    }
    public class Main {

        public static void main(String[] args) {
            // write your code here
        }
    }
