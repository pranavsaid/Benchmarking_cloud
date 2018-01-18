import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author pranavdeenumsetti
 */
public class CpuBenchmarking {

    /**
     * @param args the command line arguments
     */
    void floatOperations(int NumOfInstructions)
    {
        float number = 38.36f;
        float number2 = 30.23f;
        for(int i=0;i<NumOfInstructions; i++)
        {
            number = number*number2;
            number = number/number2;
        }
        
    }
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        long start, end, totalTime;
        int[] threadSizes = {1,2,4,8};
        String output = "Output\n";
        System.out.println("------ Execituing FLOPS Tasks-----");
        output = output.concat("------ Execituing FLOPS Tasks-----");
        for(int i =0; i<4;i++)
        {
            float res;
            int noofinstructions = Integer.MAX_VALUE;
            List<Thread> threads = new ArrayList<>();
            int numberofThreads = threadSizes[i],j=0;
            System.out.println("------ Execituing GFLOPS for " +numberofThreads+ " Thread-----");
            output = output.concat("\n------ Execituing GFLOPS for " +numberofThreads+ " Thread-----");
            noofinstructions = noofinstructions/numberofThreads;
            //List<Floating_Operations> m = new ArrayList<>();
            Floating_Operations m1=new Floating_Operations(noofinstructions);
            start = System.nanoTime();
            while(j<numberofThreads)
            {
              
              //m.add(m1);
              Thread t =new Thread(m1); 
              t.start(); 
              threads.add(t);
              j++;
            }
            try{ 
            for (Thread t : threads) {
                t.join();
            }
            }catch(Exception e){System.out.println(e);} 
            end = System.nanoTime();
            totalTime = end-start;
            res = (float)(((double)4*noofinstructions*numberofThreads)/(float)(totalTime/1000000000f));
            res = (float)(res/1000000000f);
            String result = String.format("%.02f", res);
            /*for (Floating_Operations flop : m) {
                res = res+flop.TotalGigaFlops();
            }*/
            
            System.out.println("Toal GFLOPS for " +numberofThreads+ " thread is: "+result);
            output = output.concat("\nToal GFLOPS for " +numberofThreads+ " thread is: "+result);
        }
        
        System.out.println("------ Execituing IOPS Tasks-----");
        output = output.concat("\n------ Execituing IOPS Tasks-----");
          for(int i =0; i<4;i++)
        {
            float res;
            int noofinstructions = Integer.MAX_VALUE;
            List<Thread> threads = new ArrayList<>();
            int numberofThreads = threadSizes[i],j=0;
            System.out.println("------ Execituing GIOPS for " +numberofThreads+ " Thread-----");
            output = output.concat("\n------ Execituing GIOPS for " +numberofThreads+ " Thread-----");
            noofinstructions = noofinstructions/numberofThreads;
            Integer_Operations m2=new Integer_Operations(noofinstructions);
            start = System.nanoTime();
            while(j<numberofThreads)
            {
              
              Thread t =new Thread(m2); 
              t.start(); 
              threads.add(t);
              j++;
            }
            try{ 
            for (Thread t : threads) {
                t.join();
            }
            }catch(Exception e){System.out.println(e);} 
            end = System.nanoTime();
            totalTime = end-start;
            res = (float)(((double)4*noofinstructions*numberofThreads)/(float)(totalTime/1000000000f));
            res = (float)(res/1000000000f);
            String result = String.format("%.02f", res);
            System.out.println("Toal GIOPS for " +numberofThreads+ " thread is: "+result);
            output = output.concat("\nToal GIOPS for " +numberofThreads+ " thread is: "+result);
        }
          try {
            FileWriter writer = new FileWriter("CPU_Benchmarking_Output.txt", true);
            writer.write(output);
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
