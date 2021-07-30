/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;
import static abc.randnums_bigarray.ar;
import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.SourceVersion;
/**
 *
 * @author Wenjuan Lin
 */

public class randnums_bigarray {
    public static double ar;
    public static int current=0;
    public static void main(String[] args){
        double sum[] = new double[100];
        ar = Math.random();
        sum[0] = ar;
        Printings p1 = new Printings(sum);
        Printings1 p2 = new Printings1(sum);
        p1.start();
        p2.start();
    }
}

class Printings extends Thread{
    private double sum[];
    Printings(double thing[]){
        sum = thing;
    }
    @Override
    public void run() {
        while(sum[sum.length-1] == 0){
            synchronized (sum){
                while(ar != sum[randnums_bigarray.current] && randnums_bigarray.current<100){
                    try{
                        ar = Math.random();
                        sum[randnums_bigarray.current] = ar;
                        sum.notify();
                    }catch(Exception e){
                        System.out.println("error");
                    }
                }
                    try {
                        sum.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Printings.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }
}

class Printings1 extends Thread{
    private double sum[];
    Printings1(double thing[]){
        sum = thing;
    }
    @Override
    public void run() {
        while(sum[0] != 0.0 && sum[sum.length-1]==0.0){
            synchronized (sum){
                while(ar == sum[randnums_bigarray.current] && sum[randnums_bigarray.current] != 0.0 && randnums_bigarray.current<100){
                    try{
                        System.out.println(sum[randnums_bigarray.current]);
                        randnums_bigarray.current+=1;
                        ar = -1;
                        sum.notify();
                    }catch(Exception e){
                        System.out.println("error");
                    }
                }
                try {
                    sum.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Printings1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}