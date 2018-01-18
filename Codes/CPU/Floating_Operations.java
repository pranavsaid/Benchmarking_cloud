/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pranavdeenumsetti
 */
public class Floating_Operations implements Runnable{
    int NumOfInstructions;
    float number = 38456.36f;
    float number2 = 305876.23f;
    
    public Floating_Operations(int NumOfInstructions)
    {
        this.NumOfInstructions = NumOfInstructions;
    }
    public void run(){  
        
        long start = System.nanoTime();
        for(int i=0;i<NumOfInstructions; i++)
        {
            number = (number*number2)+number2;
            number = (number-number2)/number2;
        }
        
     }
}
