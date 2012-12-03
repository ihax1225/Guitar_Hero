import java.util.*;
/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    RingBuffer buff;
    int ticCount = 0;
    public GuitarString(double frequency) 
    {
        int size = (int) Math.ceil( 44100 / frequency);
        buff = new RingBuffer(size);
    }

    public GuitarString(double[] array) throws Exception
    {
        buff = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++)
            if(buff.isFull())
                throw new Exception();
            else
                buff.add(array[i]);
    }

    public void pluck() throws Exception
    {
        Random rand = new Random(2);
        for(int i =0; i < buff.arr.length; i++){
            if(rand.nextInt() == 1)
                buff.add(Math.random() * .5);
            else
                buff.add(-Math.random() * .5);
        }
    }

    // advance the simulation one time step
    public void tic() throws Exception
    {
        //if(buff.isEmpty())
          //  throw new Exception();
       // else{
            double average = (buff.remove()  + buff.peek()) / 2;
            if(!buff.isFull()){
                buff.add(average * 0.994);
                ticCount++;
            }
        
    }

    // return the current sample
    public double sample()
    {
        return buff.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticCount;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  

        try{
            GuitarString testString = new GuitarString(samples);
            for (int i = 0; i < 25; i++) 
            {
                int t = testString.time();
                double sample = testString.sample();
                System.out.printf("%6d %8.4f\n", t, sample);
                testString.tic();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
