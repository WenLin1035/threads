/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;
import static abc.randnums.ar;
import java.lang.reflect.Array;
import java.util.*;
/**
 *
 * @author Wenjuan Lin
 */
public class randnums {
    public static double ar;
    public static void main(String[] args){
        double sum[] = new double[1];
        ar = Math.random();
        sum[0] = ar;
        Printing p1 = new Printing(sum);
        Printing1 p2 = new Printing1(sum);
        p1.start();
        p2.start();
    }
}



class Printing extends Thread{
    private double sum[];
    Printing(double thing[]){
        sum = thing;
    }
    @Override
    public void run() {
        while(true){
            synchronized (sum){
                while(ar != sum[0]){
                    try{
                        ar = Math.random();
                        sum[0] = ar;
                        sum.notify();
                    }catch(Exception e){
                        System.out.println("error");
                    }
                }
                sum.notify();
            }
        }
    }
}

class Printing1 extends Thread{
    private double sum[];
    Printing1(double thing[]){
        sum = thing;
    }
    @Override
    public void run() {
        while(true){
            synchronized (sum){
                while(ar == sum[0]){
                    try{
                        System.out.println(sum[0]);
                        ar = 0;
                    }catch(Exception e){
                        System.out.println("error");
                    }
                }
                sum.notify();
            }
        }
    }
}