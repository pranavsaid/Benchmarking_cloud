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
public class UDPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // TODO code application logic here
        DatagramSocket myserver=null;
        try{
            while(true){
            myserver = new DatagramSocket(8080);
            
           byte[] b = new byte[65536];
            System.out.println("Socket connection created for datagram");
            DatagramPacket myPacket = new DatagramPacket(b,b.length);
            myserver.receive(myPacket);
            String input = new String(myPacket.getData(),0,myPacket.getLength());
            System.out.println("Client says"+myPacket.getData());
            InetAddress clientAddress = myPacket.getAddress();
            int port = myPacket.getPort();
            myPacket = new DatagramPacket(input.getBytes(),(input.getBytes()).length, clientAddress, port);
            myserver.send(myPacket);
            myserver.close();
            
            
            }            
        }catch(Exception e){System.out.print(e);}
        
        
        
    }
    
}
