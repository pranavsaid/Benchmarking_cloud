/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author pranavdeenumsetti
 */
public class TCPclient_thread implements Runnable{
    Socket client;
    int bufferSize = 65536;
    long start, end=0, latencystart;
    float latency, throughput,latencyend=0;
    double th;
    int loop;
    public TCPclient_thread (int loop, Socket client)
    {
        this.loop = loop;
        try{
        this.client = client;
        }catch(Exception e){System.out.println(e);}
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
    
        BufferedReader client_in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter client_out = new PrintWriter(client.getOutputStream(),true);
        String input="", output="";
       
        for(int i=0;i<this.loop;i++)
        {
            byte b[] = null;
            b = getByte();
            start = System.currentTimeMillis();
            client_out.println(b);
            client_in.readLine();
            end = end+(System.currentTimeMillis()-start);
        } 
        //client_out.println("bye");
        latencyend = end/loop;
        latency = (float)(end*1000);
        th =  (double)((loop*bufferSize)/end);
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
