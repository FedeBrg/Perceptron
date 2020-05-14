package multi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args)
    {
        List<Integer> layers = new ArrayList<>();
        layers.add(2);
        layers.add(4);

        layers.add(1);

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers);
        Random r = new Random();
        /* Learning */
        for(int i = 0; i < 1000000; i++)
        {
            List<Double> inputs = new ArrayList<>();

            inputs.add(Math.floor(r.nextDouble()*2)/1.0);
            inputs.add(Math.floor(r.nextDouble()*2)/1.0);
//            inputs.add(Math.round(Math.random())/1.0);
//            inputs.add(Math.round(Math.random())/1.0);
            List<Double> output = new ArrayList<>();
            double error;

            if(inputs.get(0).equals(inputs.get(1)))
                output.add(0.0);
            else
                output.add(1.0);


            //System.out.println(inputs.get(0)+" xor "+inputs.get(1)+" = "+output.get(0));

            error = net.backPropagate(inputs, output);
            //System.out.println("Error at step "+i+" is "+error);
        }

        System.out.println("Learning completed!");

        /* Test */
        List<Double> inputs = new ArrayList<>();
        inputs.add(1.0);
        inputs.add(1.0);
        List<Double> output = net.execute(inputs);

        System.out.println(inputs.get(0)+" xor "+inputs.get(1)+" = "+Math.round(output.get(0))+" ("+output.get(0)+")");


    }

}
