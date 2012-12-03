/*****************************************************************************
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/

public class GuitarHero
{
    public static void main(String[] args) 
    {
        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        
        GuitarString[] arr = new GuitarString[37];
        //for(int i = 0; i <= arr.length; i++)
           // arr[i] = keyboard.substring(i);
        // the main input loop
        while (true) 
        {
            // check if the user has typed a key, and, if so, process it

            if (StdDraw.hasNextKeyTyped()) 
            {

                // the user types this character
                char key = StdDraw.nextKeyTyped();
                
                //keyboard.indexOf(key);
                // pluck the corresponding string
                try{
                    if (key == 'a')
                        stringA.pluck();
                    if (key == 'c') 
                        stringC.pluck();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

                // compute the superposition of the samples
                double sample = 0;
                sample = stringA.sample() + stringC.sample();
                // send the result to standard audio
                StdAudio.play(sample);

                // advance the simulation of each guitar string by one step
                try{
                    stringA.tic();
                    stringC.tic();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }

}

