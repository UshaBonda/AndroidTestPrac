package com.example.myapplication;

import java.util.Scanner;

public class sample {

    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int pointsCount = in.nextInt();
        int array[][] = new int[2][pointsCount];
        String points[] = new String[pointsCount];
        for(int i=0; i<pointsCount; i++){
            points[i] = in.nextLine();
            for(int j=0; j<points[i].length(); j++){
                if(points[i].equals(" ")){
                    array[i][0] = Integer.parseInt(points[i].substring(0, j));
                    array[i][1] = Integer.parseInt(points[i].substring(j+1, points[i].length()-1));
                }
            }
        }

        int xlarge=0, ylarge=0, xsmall=pointsCount, ysmall=pointsCount;
        for(int i=0; i<array.length;i++){
            if(array[i][0]>xlarge){
                xlarge=array[i][0];
            }
            if(array[i][1]>ylarge){
                ylarge=array[i][1];
            }
        }
        int newArray[][]=new int[2][pointsCount];
        for(int i=0,j=0;i<array.length; i++){
            if(array[i][0]!=xlarge && array[i][1]!=ylarge){
                newArray[j][0]= array[i][0];
                newArray[j][1]= array[i][1];
                j++;
            }
        }
        System.out.println(newArray.length);

    }
}
