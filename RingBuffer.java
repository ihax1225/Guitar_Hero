import java.util.*;
/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    double[] arr;
    int front = 0, back;
    public RingBuffer(int capacity)
    {
        arr = new double[capacity+1];
        front = 0;
        back = 0;
    }

    public int size()
    {
        int size = back - front;
        return size < 0 ? arr.length + size : size;
    }

    public boolean isEmpty()
    {
        return back == front;
    }

    public boolean isFull()
    {
        return arr.length-1 == size();
    }

    public void add(double value) throws Exception
    {
        if(isFull()){ throw new ArrayIndexOutOfBoundsException(); }
        arr[back++] = value; 
        if(back >= arr.length)
            back =0;
    }

    public double peek()
    {
        return arr[front];
    }

    public double remove() throws Exception
    {   
        if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
        double item = arr[front++];
        if(front >= arr.length) front = 0;
        return item;
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity); 
        for (int i = 1; i <= capacity; i++) 
            try{
                buffer.add(i);
            }catch(Exception no){
                no.printStackTrace();
        }

        try{
            double t = buffer.remove();
            buffer.add(t);
        }
        catch(Exception ex){
            new Exception();
        }
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            try{
                double x = buffer.remove();
                double y = buffer.remove();

                buffer.add(x + y);
            }
            catch(Exception ex){
                new Exception();
            }
        }
        System.out.println(buffer.peek());
    }
}

