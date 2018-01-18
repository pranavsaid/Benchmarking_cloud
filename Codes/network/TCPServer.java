/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author pranavdeenumsetti
 */
public class TCPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket myserver = null;
        Socket server = null;
        try{
            while(true){
            myserver = new ServerSocket(8888);
            
            server = myserver.accept();
            System.out.println("Socket connection Accepted");
            BufferedReader server_in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter server_out = new PrintWriter(server.getOutputStream(),true);
            String input="", output="";
            
            while ((input = server_in.readLine()) != null)
            {
                
                server_out.println(input);
                if(input.equalsIgnoreCase("bye"))
                    break;
               
            }
            server.close();
            myserver.close();
            }            
        }catch(Exception e){System.out.print(e);}
        
        
        
    }
    
}
