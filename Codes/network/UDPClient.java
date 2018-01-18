/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pranavdeenumsetti
 */
public class UDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        float tp=0,lat=0;
        int[] threadSizes = {1,2,4,8};
        for (int i = 0; i < 4; i++) {
        
        List<Thread> threads = new ArrayList<>();
        List<UDPclient_thread> objects = new ArrayList<>();
        int numberofThreads = threadSizes[i], k = 0;
        int numberofloops = 10/numberofThreads;
        System.out.println("------ Execituing UDP for "+numberofThreads+"Thread-----");
        while (k < numberofThreads) {       
        UDPclient_thread object = new UDPclient_thread(numberofloops);
        objects.add(object);
        Thread t = new Thread(object);
        t.start();
        t.join();
        threads.add(t);
        k++;
        
        }
        /*try {
                    for (Thread t : threads) {
                        t.join();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }*/
        for (UDPclient_thread m : objects) {
            tp = tp+m.get_throughput();
            lat = lat+m.get_latency();
        }
        lat = lat/numberofThreads;
        String Throughput = String.format("%.02f", tp);
        String latency = String.format("%.02f", lat);
        System.out.println("\nThroughput is "+Throughput+"MB/sec");
        System.out.println("\nLatency is "+latency+"Millisec");
        
        }
    }
    
    
}
