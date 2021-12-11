package UI;
import BL.ForUI;
import BL.GameOfLife;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class UIFun {

    /*------------------------Printing of Grid-------------------*/
    public static void printing(int[][] array,int width,int length){
        System.out.print("\t");
        for(int i=0;i<width;i++){
            System.out.print(i+" ");
        } System.out.println();
        for (int i=0;i<length;i++) {
            System.out.print(i+"\t");
            for(int j=0;j<width;j++) {
                if(array[i][j]==0){
                    if(j>9){
                        System.out.print("*  ");
                    }else {
                        System.out.print("* ");
                    }
                }else{
                    if(j>9) {
                        System.out.print("$  ");
                    }
                    else {
                        System.out.print("$ ");
                    }
                }
            }
            System.out.println();
        }
    }

    /*Interrupt for Stop*/
    static class MyKeyListener extends KeyAdapter {
        public int m;
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyChar() == 'a') {
                m=1;
            }
            if (evt.getKeyChar() == 'b') {
                m=0;
            }
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {

        //GameOfLife Game= new GameOfLife();
        ForUI Game=new GameOfLife();

        int length=10;
        int width=10;
        int[][] array = new int[10000][10000];
        for (int i=0;i<10000;i++) {
            for(int j=0;j<10000;j++) {
                array[i][j]=0;
            }
        }
        array=Game.GetGridToUI();
        int option=-1,loop=0;

        /*Loop Until Game Ends*/
        while(loop!=-1) {

            /*-----------------Call Print Function After Every Iteration----------*/
            System.out.println(System.lineSeparator().repeat(100));
            printing(array, width, length);

            /*----------USer Can See this Menu and Enter Some Value-------------*/
           
            System.out.println("0-Change Grid");
            System.out.println("1-Save State");
            System.out.println("2-Load Save States");
            System.out.println("3-Start");
            System.out.println("4-Zoom Level");
            System.out.println("5-Reset");
            System.out.println("6-Next State");
            System.out.println("7-Speed Control");
            System.out.println("8-Delete Save State");
            System.out.print("Counter: ");
            System.out.println(Game.getCounter());
            System.out.println();


            // take input that what Option User want to Choose
            Scanner input = new Scanner(System.in);
            option = input.nextInt();

            if(option==0){
                // Enter X Value
                System.out.print("Enter X value: ");
                Scanner input1 = new Scanner(System.in);
                int x = input1.nextInt();
                // Enter X Value
                System.out.print("Enter Y value: ");
                Scanner input2 = new Scanner(System.in);
                int y = input2.nextInt();
                /*Call BL Layer Function to Just pass x and y Coordinates to Make changes in Grid*/
                  Game.ManuallyDrawPattren(x,y);
                  array=Game.GetGridToUI();
            }
            if(option==1) {
                //Save Current State
               Game.saveState();
            }
            if(option==2){
                //Load State
                System.out.println("Enter State Number : ");
                Scanner input1 = new Scanner(System.in);
                int State = input1.nextInt();
                Game.LoadState(State);
                array=Game.GetGridToUI();
            }
            if(option==3){
                //Start State
                JTextField component = new JTextField();
                MyKeyListener My=new MyKeyListener();
                component.addKeyListener(My);

                JFrame f = new JFrame();

                f.add(component);
                f.setSize(300, 300);
                f.setVisible(true);
                int Loop1=-1;
                if(Game.getCounter()==0){
                Game.Start(1);
                }
                while(Loop1==-1){
                    Game.Next(0);
                    array=Game.GetGridToUI();
                    System.out.println(System.lineSeparator().repeat(100));
                    printing(array, width, length);
                    int m = My.m;
                    if(m==1){
                        Loop1=1;
                    }
                    TimeUnit.SECONDS.sleep(Game.getSpeed());
                }
            }
            if(option==4){
                // User Can Select Level From 1 to 4
                System.out.print("Enter Level From 1 to 4: ");
                Scanner input1 = new Scanner(System.in);
                int x = input1.nextInt();
                if(x==1){
                    length=10;
                    width=10;
                }if(x==2){
                    length=20;
                    width=20;
                }if(x==3){
                    length=30;
                    width=30;
                }if(x==4){
                    length=40;
                    width=40;
                }
            } if(option==5){
                //Reset the State
                Game.Clear(0);
                array=Game.GetGridToUI();
            }
            if(option==6){
                    //Next State
                if(Game.getCounter()==0){
                    Game.Start(1);
                }
                Game.Next(0);
                array=Game.GetGridToUI();
            }
            if(option==7){
                // User Can Select Level From 1 to 4
                System.out.print("Enter Speed From 1 to 4: ");
                Scanner input1 = new Scanner(System.in);
                int x = input1.nextInt();
                if(x==1){
                    Game.setSpeed(5);
                }if(x==2){
                    Game.setSpeed(4);
                }if(x==3){
                    Game.setSpeed(3);
                }if(x==4){
                    Game.setSpeed(2);
                }
            }
            if(option==8) {
                //Delete Save State
                Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
                System.out.print("Enter State Name: ");
                String S= sc.nextLine(); //reads string.
                Game.Deletestate(S);
            }
            if(option==9){


            }
        }
        
    }
}
