package SDASQL;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;


import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.sql.*;
import java.util.*;

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
    public String stateID;
    public Grid grid;
    public static int numofStates;
    protected String url="jdbc:sqlserver://DESKTOP-SIK9Q81\\MSSQLSERVER;"+"databaseName=States;integratedSecurity=true;";

    public State() {
        stateID = " ";
        this.grid = new Grid();
    }

    public State(String id, int r, int[][] a) {
        this.stateID = id;
        this.grid = new Grid(r, a);
    }

    public int[][] loadState(String StateName,int ro)
    {
        String load=""; //for filing
        String load1=""; //for db
        String my_matrics[][] = new String [ro][2] ;
        int [][] arr =new int[ro][2];
        for(int i=0; i<ro ;i++)
        {
            for (int j = 0; j < 2; j++)
            {
                my_matrics[i][j] = " ";
            }
        }
        try

        {
            //filing
            String Filename = "state" + StateName + ".txt";
            try {
                File myObj = new File(Filename);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine())
                {
                    load = myReader.nextLine();
                    System.out.println(load);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //connectivity
            String Output=" ";
            Connection con = DriverManager.getConnection(url);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String SQL = "{call [LoadState](?,?,?)}"; // for Microsoft SQL Server
            CallableStatement Cmt = con.prepareCall(SQL);
            Cmt.setString(1,StateName);
            Cmt.registerOutParameter(2, Types.INTEGER);
            Cmt.execute();
            Output=Cmt.getString(2);
            load1 = Output;

            //load = LoadState(StateName);

            //string to int[][]
            load=load.replace("[","");//replacing all [ to ""
            load=load.substring(0,load.length()-1);//ignoring last two ]]
            String s1[]=load.split("],");//separating all by "],"

            my_matrics = new String[s1.length][s1.length];//declaring two dimensional matrix for input

            for(int i=0;i<s1.length;i++){
                s1[i]=s1[i].trim();//ignoring all extra space if the string s1[i] has
                String single_int[]=s1[i].split(",",0);//separating integers by ", "

                for(int j=0;j<single_int.length;j++){
                    my_matrics[i][j]=single_int[j];//adding single values
                }
            }
            //printing result

            for(int i=0;i<ro;i++){
                for(int j=0;j<2;j++){

                    arr[i][j]= Integer.parseInt(my_matrics[i][j]);

                }
                System.out.println("");
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return arr;
    }

    public void saveState(Grid state)
    {
        int ro = GetRows();
        Grid obj;
        String StateName = "state" + numofStates + ".txt";
        numofStates++;
        String mainStr = "";
        try
        {
            int[][] arr = GetGridsavstate(grid);
            for (int i = 0; i < ro; i++)
            {
                mainStr += "[";
                for (int j = 0; j < 2; j++)
                {
                    String s = Integer.toString(arr[i][j]);
                    mainStr += s;
                    if (j == 0)
                        mainStr += ',';
                }
                mainStr += "]";
                if(i!=ro-1)
                {
                    mainStr+=',';
                }
            }

            //------storing in  File ----

            System.out.println("StateName:  " + StateName);
            try
            {
                File myObj = new File(StateName);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
                try {
                    FileWriter myWriter = new FileWriter(StateName);
                    myWriter.write(mainStr);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //DB connectivity
            Connection con = DriverManager.getConnection(url);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String SQL = "{call [insertState](?,?,?)}"; // for Microsoft SQL Server
            CallableStatement Cmt = con.prepareCall(SQL);
            Cmt.setString(1,StateName);
            Cmt.setString(2,mainStr);
            Cmt.registerOutParameter(3, Types.INTEGER);
            Cmt.execute();

            System.out.println(mainStr);

        }
        catch (Exception e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void deleteState(String StateName)
    {
        try
        {
          //deleting from db
            Connection con = DriverManager.getConnection(url);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String SQL = "{call [DeleteState](?,?,?)}"; // for Microsoft SQL Server
            CallableStatement Cmt = con.prepareCall(SQL);
            Cmt.setString(1,StateName);
            Cmt.registerOutParameter(2, Types.INTEGER);
            Cmt.execute();

            //deleting from file
            File myObj = new File(StateName);
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int[][] viewState()
    {
        int ro = GetRows();
        String mainStr = "";
        try {


            // sending string to database
            Connection con = DriverManager.getConnection(url);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String SQL = "{call ViewState[](?,?,?)}"; // for Microsoft SQL Server
            CallableStatement Cmt = con.prepareCall(SQL);
            Cmt.setString(1, stateID);

            //seeing all saved states
            for (int j = 0; j < numofStates; j++) {


                try {

                    String State_name = "state" + j + ".txt";
                    File myObj = new File(State_name);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        mainStr = myReader.nextLine();
                        System.out.println(mainStr);
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                mainStr = mainStr.replace("[", "");//replacing all [ to ""
                mainStr = mainStr.substring(0, mainStr.length() - 2);//ignoring last two ]]
                String s1[] = mainStr.split("],");//separating all by "],"

                String my_matrics[][] = new String[s1.length][s1.length];//declaring 2-D matrix for input

                for (int i = 0; i < s1.length; i++) {
                    s1[i] = s1[i].trim();//ignoring all extra space if the string s1[i] has
                    String single_int[] = s1[i].split(",");//separating integers by ", "

                    for ( j = 0; j < single_int.length; j++) {
                        my_matrics[i][j] = single_int[j];//adding single values
                    }
                }
                //printing result
                for (int i = 0; i < ro; i++) {
                    for ( j = 0; j < 2; j++) {
                        System.out.print(my_matrics[i][j] + " ");
                    }
                    System.out.println("");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

}
public class Main {

    public static void main(String[] args) {

    }
}
