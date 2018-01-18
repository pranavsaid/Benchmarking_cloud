/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.*;  
import java.io.*; 
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pranavdeenumsetti
 */
public class TCPclient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        float tp=0,lat=0;
        int[] threadSizes = {1, 2, 4, 8};
        for (int i = 0; i < 4; i++) {
        Socket client = new Socket("localhost",8888);
        List<Thread> threads = new ArrayList<>();
        List<TCPclient_thread> objects = new ArrayList<>();
        int numberofThreads = threadSizes[i], k = 0;
        int numberofloops = 1000/numberofThreads;
        System.out.println("------ Execituing  for " + numberofThreads + " Thread-----");
        while (k < numberofThreads) {       
        TCPclient_thread object = new TCPclient_thread(numberofloops,client);
        objects.add(object);
        Thread t = new Thread(object);
        t.start();
        threads.add(t);
        k++;
        
        }
        try {
                    for (Thread t : threads) {
                        t.join();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
        for (TCPclient_thread m : objects) {
            tp = tp+m.get_throughput();
            lat = lat+m.get_latency();
        }
        lat = lat/numberofThreads;
        String Throughput = String.format("%.02f", tp);
        String latency = String.format("%.02f", lat);
        System.out.println("\nThroughput is "+Throughput+"MB/sec");
        System.out.println("\nLatency is "+latency+"Millisec");
        client.close();
        }
    }
    
    
    
}
