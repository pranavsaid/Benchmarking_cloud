/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.*;


/**
 *
 * @author pranavdeenumsetti
 */
public class UDPclient_thread implements Runnable{
    
    
    int bufferSize = 256;
    long start, end=0,endlat =0;
    float latency, throughput,latencyend=0;
    double th;
    int loop;
    
    public UDPclient_thread (int loop)
    {
        this.loop = loop;
        
    }
    public byte[] getByte()
    {
        byte[] b=new byte[bufferSize];
        for(int j=0;j<bufferSize;j++)
		 {
			 b[j]=(byte)(Math.random()*bufferSize);
		 }
        return b;
    }
        
    synchronized public void run()
    {
        try{
        this.benchmarking();
        }catch(Exception e){System.out.println(e);}
    }
    
    
    synchronized public void benchmarking() throws Exception{
        for(int i=0;i<this.loop;i++)
        {
        DatagramSocket client = null;
        DatagramPacket packet = null;
        client = new DatagramSocket();
        //byte[] b = this.getByte();
        InetAddress address = InetAddress.getByName("localhost");
        byte b[] = null;
        b = getByte();
        
       //for(int i=0;i<this.loop;i++)
        //{
            
            packet = new DatagramPacket(b,b.length,address,8080);
            start = System.currentTimeMillis();
            client.send(packet);
            endlat =  (System.currentTimeMillis()-start);
            packet = new DatagramPacket(b,b.length,address,8080);
            client.receive(packet);
            String input = new String(packet.getData(),0,packet.getLength());
            //System.out.println("Server says"+packet.getData());
            end = (System.currentTimeMillis()-start);
            //Thread.sleep(3000);
            client.close();
        } 
        //client_out.println("bye");
        latencyend = endlat;
        latency = (float)(end*1000);
        th =  (double)((bufferSize)/end);
        throughput = (float) ( (th*1000*8)/(1024*1024));
        
        //client.close();
}
     public float get_throughput() {
        return throughput;
    } 
     public float get_latency() {
        return latency;
    } 
     
     
}

