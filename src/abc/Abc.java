/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wenjuan Lin
 */
class Print1 implements Runnable {
    public int max=12;
    static int num = 0;
    static String letter="A";
    String name = new String();
    Print1(String name){
        this.name = name;
    }
    @Override
    public void run(){
        //amount of letters to be printed
        while(num <= max){
            synchronized (Abc.lock){
                //wait if letter and thread parameter not same
                while(!letter.equals(name)){
                    try{
                        Abc.lock.wait();
                    }catch(Exception e){
                        System.out.println("ERROR");
                    }
                }
                System.out.print(this.name);
                if(letter.equals("A")){
                    letter = "B";
                }else if(letter.equals("B")){
                    letter = "C";
                }else if(letter.equals("C")){
                    letter = "A";
                }
                num++;
                Abc.lock.notifyAll();
            }
        }
    }
}

public class Abc {

    /**
     * @param args the command line arguments
     */
    public static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Print1 p1 = new Print1("A");
        Print1 p2 = new Print1("B");
        Print1 p3 = new Print1("C");
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();
        
    }
    
}
