/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.io.File;

/**
 *
 * @author pranavdeenumsetti
 */
public class Sequential_Random_Operations implements Runnable {

    private SecureRandom random = new SecureRandom();
    File file;
    int file_size;
    int block_size;
    float throughput, throughput_sw, throughput_sr, throughput_rr;
    float latency_sr, latency_rr;
    long starttime = 0;
    float totalTime = 0.0000f;
    long endLatency;
    RandomAccessFile w, r, r2;
    byte[] b;
    public Sequential_Random_Operations(int block_size, File file, int file_size) {
        this.block_size = block_size;
        this.file = file;
        this.file_size = file_size;
        try {this.w = new RandomAccessFile(file, "rw");
        
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        try{
            w.writeChars(" ");
        }catch (Exception e) {
            System.out.println(e);
        }
        sr_rw(this.block_size, this.file, this.file_size);
    }
    
    public void sr_rw(int block_size, File file, int file_size){
        try
        {
            b = new byte[file_size];
        }catch(NegativeArraySizeException e)
{            System.out.println("caught and thrown the array size is"+ file_size);}
        //intializing the buffer array with values to write
        for (int i = 0; i < file_size; i++) {
            b[i] = (byte) i;
        }

        ///////////////////////////////////////////////////
        ////////////Sequential wite///////////////////////
        /////////////////////////////////////////////////
        try {
            

            int i = 0;
            
            starttime = System.currentTimeMillis();
            while (i <= b.length) {
                w.seek(file.length());

                if (i + block_size < b.length) {
                    w.write(b, i, block_size);
                }

                i = i + block_size;
            }
            totalTime = (float)(System.currentTimeMillis() - starttime);
            throughput = (float)((file_size) / (totalTime));
            throughput_sw = (float)(((throughput)*1000 / (1024 * 1024)));
            w.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        ///////////////////////////////////////////////////
        ////////////Sequential Read///////////////////////
        /////////////////////////////////////////////////
        try {
            this.r = new RandomAccessFile(file, "rw");
        

            int i = 0;
            starttime = System.currentTimeMillis();
            while (i <= b.length) {
                r.seek(i);
                endLatency = System.currentTimeMillis();
                if (i + block_size < b.length) {
                    r.read(b, i, block_size);
                }

                i = i + block_size;
            }            
            latency_sr = (float)(endLatency - starttime);
            totalTime = (float) (System.currentTimeMillis() - starttime);
            throughput = (float)((file_size) / (totalTime));
            throughput_sr = (float)(((throughput)*1000 / (1048576)));
            r.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        ///////////////////////////////////////////////////
        ////////////Random Read///////////////////////
        /////////////////////////////////////////////////
        try {
           this.r2 = new RandomAccessFile(file, "rw");
            int i = 0;
            starttime = System.currentTimeMillis();
            while (i <= b.length) {
                r2.seek(random.nextInt(block_size));
                 endLatency = System.currentTimeMillis();
                if (i + block_size < b.length) {
                    r2.read(b, i, block_size);
                }

                i = i + block_size;
            }
            latency_rr = (float)(endLatency - starttime);
            totalTime = (float) (System.currentTimeMillis() - starttime);
            throughput = (float)((file_size) / (totalTime));
            throughput_rr = (float)(((throughput)*1000/ (1048576)));
            r2.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public float get_throughput_sw() {
        return throughput_sw;
    }

    public float get_throughput_sr() {
        return throughput_sr;
    }

    public float get_throughput_rr() {
        return throughput_rr;
    }
    public float get_latency_sr() {
        return latency_sr;
    }
    public float get_latency_rr() {
        return latency_rr;
    }
}
   