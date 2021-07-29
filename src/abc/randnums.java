/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;
import java.util.*;
/**
 *
 * @author Wenjuan Lin
 */
public class randnums {
    public static void main(String[] args){
        Printing p1 = new Printing();
        while(true){
            new Thread(p1).start();
            try{
                Thread.sleep(2000);
            } catch(Exception e){
                System.out.println("ER");
            }
        }
        
    }
}
class Printing implements Runnable{
    @Override
    public void run() {
        synchronized (Printing.class){
            while(true){
                System.out.println(Math.random());
                Printing.class.notify();
                try{
                    Printing.class.wait();
                } catch(Exception e){
                    System.out.println("ERROR");
                }
            }
        }
    }
}