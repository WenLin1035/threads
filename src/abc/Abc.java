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
class Print1 extends Thread {
    private String name;
    Print1(String name){
        this.name = name;
    }
    @Override
    public void run(){
        System.out.print(name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("ERROR");
        }
    }
}

public class Abc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int k = 1;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        for(int i=0;i<12;i++){
            switch(k){
                case 1:
                    Print1 p1 = new Print1("A");
                    Future<String> future = (Future<String>) executor.submit(p1);
                    future.get();
                    k++;
                case 2:
                    p1 = new Print1("B");
                    future = (Future<String>) executor.submit(p1);
                    future.get();
                    k++;
                case 3:
                    p1 = new Print1("C");
                    future = (Future<String>) executor.submit(p1);
                    future.get();
                    k=1;
            }
        }
        executor.shutdown();
    }
    
}
