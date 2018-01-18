/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author pranavdeenumsetti
 */
public class DiskBenchmarking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        String output = "Output\n";
        float throughput_sw=0,throughput_sr=0,throughput_rr=0,latency_rr=0, latency_sr=0;
        int[] threadSizes = {1, 2, 4, 8};
        int[] blocks = {8, 8 * 1024, 8 * 1024 * 1024, 8 * 10 * 1024 * 1024};
        String BlockName = "";
        for (int i = 0; i < 4; i++) {
            File file = new File("readwrite.txt");
            int blockSize = blocks[i];
            if(i==0)
            BlockName = "8 Byte";
            else if(i==1)
            BlockName = "8 KB";
            else if(i==2)
            BlockName = "8 MB";
            else
            BlockName = "80 MB";
            System.out.println("------ Executing " + BlockName + " Block Tasks-----");
            output = output.concat("------ Executing " + BlockName + " Block Tasks-----");
            for (int j = 0; j < 4; j++) {
                float res;
                int file_size;
                
                if(i==0)
                    file_size = 100000 * blockSize;
                else if(i==1)
                    file_size = 10000 * blockSize;
                else if(i==2)
                file_size = 100 * blockSize;
                else 
                file_size = 10* blockSize;
                
                List<Thread> threads = new ArrayList<>();
                List<Sequential_Random_Operations> objects = new ArrayList<>();
                int numberofThreads = threadSizes[j], k = 0;
                System.out.println("------ Execituing  for " + numberofThreads + " Thread-----");
                output = output.concat("------ Execituing  for " + numberofThreads + " Thread-----");
                file_size = file_size / (numberofThreads);
                
                while (k < numberofThreads) {
                    Sequential_Random_Operations sr = new Sequential_Random_Operations(blockSize, file, file_size);
                    objects.add(sr);
                    Thread t = new Thread(sr);
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
                for (Sequential_Random_Operations m : objects) {//adding throughputs for all threads
                    throughput_sw=throughput_sw+ m.get_throughput_sw();
                    throughput_sr=throughput_sr+ m.get_throughput_sr();
                    throughput_rr=throughput_rr+ m.get_throughput_rr(); 
                    latency_sr = latency_sr+m.get_latency_sr();
                    latency_rr = latency_rr+m.get_latency_rr();
                    }
                latency_rr = latency_rr/numberofThreads;//taking avg of latency in all threads
                latency_sr = latency_sr/numberofThreads;
                String resultsw = String.format("%.02f", throughput_sw);
                String resultsr = String.format("%.02f", throughput_sr);
                String resultrr = String.format("%.02f", throughput_rr);
                String result_lrr = String.format("%.02f", latency_rr);
                String result_lsr = String.format("%.02f", latency_sr);
               System.out.println("\n"+BlockName+" Block Sequential Read Throughput for "+numberofThreads+" Thread is: "+resultsr +"MB/SEC"); 
               System.out.println("\n"+BlockName+" Block Random Read Throughput for "+numberofThreads+" Thread is: "+resultrr+"MB/SEC");
               System.out.println("\n"+BlockName+" Block Sequential write Throughput for "+numberofThreads+" Thread is: "+resultsw+"MB/SEC");
               System.out.println("\n"+BlockName+" Block Sequential Read+write Throughput for "+numberofThreads+" Thread is: "+(throughput_sr+throughput_sw)+"MB/SEC");
               System.out.println("\n"+BlockName+" Block Sequential Read Latency for "+numberofThreads+" Thread is: "+result_lsr+"MSEC");
               System.out.println("\n"+BlockName+" Block Random Read Latency for "+numberofThreads+" Thread is: "+result_lrr+"MSEC");
               output = output.concat("\n"+BlockName+" Block Sequential Read Throughput for "+numberofThreads+" Thread is: "+resultsr+"MB/SEC");
               output = output.concat("\n"+BlockName+" Block Random Read Throughput for "+numberofThreads+" Thread is: "+resultrr+"MB/SEC");
               output = output.concat("\n"+BlockName+" Block Sequential write Throughput for "+numberofThreads+" Thread is: "+resultsw+"MB/SEC");
               output = output.concat("\n"+BlockName+" Block Sequential Read+write Throughput for "+numberofThreads+" Thread is: "+(throughput_sr+throughput_sw)+"MB/SEC");
               output = output.concat("\n"+BlockName+" Block Sequential Read Latency for "+numberofThreads+" Thread is: "+result_lsr+"MSEC");
               output = output.concat("\n"+BlockName+" Block Random Read Latency for "+numberofThreads+" Thread is: "+result_lrr+"MSEC");
            }
            file.delete();
        }
        
        try {
            FileWriter writer = new FileWriter("Disk_Benchmarking_Output.txt");
            writer.write(output);
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
