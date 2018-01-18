/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pranavdeenumsetti
 */
public class Integer_Operations implements Runnable{
    int NumOfInstructions, number = 382, number2 = 304;
    public Integer_Operations(int NumOfInstructions)
    {
        this.NumOfInstructions = NumOfInstructions;
    }
    public void run(){  
       
        for(int i=0;i<NumOfInstructions; i++)
        {
            number = (number*number2);
            number = (number/number2);
            number = number+number2;
            number = number - number2;
        }
}  
    
}
