package com.company;
import java.io.IOException;
import java.util.Scanner;
public class Main {
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
                        System.out.print("x  ");
                    }
                    else {
                        System.out.print("x ");
                    }
                }
            }
            System.out.println();
        }


    }
    
    public static void main(String[] args) throws IOException {



        int length=10;
        int width=10;
        int[][] array = new int[10000][10000];
       

        for (int i=0;i<10000;i++) {
            for(int j=0;j<10000;j++) {
                array[i][j]=0;
        }
        }
        int option=-1,loop=0;
        while(loop!=-1) {
            System.out.println(System.lineSeparator().repeat(100));
            printing(array, width, length);
            //option Input

            System.out.println("0-Change Grid");
            System.out.println("1-Save State");
            System.out.println("2-Load States");
            System.out.println("3-Start");
            System.out.println("4-Stop");
            System.out.println("5-Zoom Level");

            // create an object of Scanner
            Scanner input = new Scanner(System.in);
            // take input from the user
            option = input.nextInt();

            if(option==0){
                // create an object of Scanner
                System.out.print("Enter X value: ");
                Scanner input1 = new Scanner(System.in);
                // take input from the user
                int x = input1.nextInt();
                System.out.print("Enter Y value: ");
                Scanner input2 = new Scanner(System.in);
                // take input from the user
                int y = input2.nextInt();
                  array[x][y]=1;
            }
            if(option==1){
                //Save Current State
            }
            if(option==2){
                //Load State
            }
            if(option==3){
                //Start State

            }
            if(option==4){
                //Start State

            }
            if(option==5){
                // create an object of Scanner
                System.out.print("Enter Level From 1 to 4: ");
                Scanner input1 = new Scanner(System.in);
                // take input from the user
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
            }

        }
        
    }
}
