package multi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        App a = new App();
        a.learnPrimes();


    }

    private void learnPrimes() throws FileNotFoundException {
        List<List<Double>> numbers = readFile();
        List<List<Double>> outputs = loadOutputs();

        List<Integer> layers = new ArrayList<>();
        layers.add(70);
        layers.add(4);

        layers.add(1);

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers);


        for(int i = 0; i < 100000; i++) {

            for(int j = 0; j<numbers.size();j++){
                double error = net.backPropagate(numbers.get(j),outputs.get(j));

                System.out.println("Error at step "+i+" is "+error);
            }

            //System.out.println(inputs.get(0)+" xor "+inputs.get(1)+" = "+output.get(0));

        }

        System.out.println("Learning completed!");

        /* Test */
        int a = 0;
        for(List<Double> n : numbers){
            List<Double> output = net.feedForward(n);
            System.out.println(a+"= "+Math.round(output.get(0))+" ("+output.get(0)+")");
            a++;

        }




    }

    private List<List<Double>> loadOutputs(){
        List<List<Double>> outputs = new ArrayList<>();
        List<Double> output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(1.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(1.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(1.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(1.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);
        output = new ArrayList<>();
        output.add(0.0);
        outputs.add(output);

        return outputs;
    }

    private List<List<Double>> readFile() throws FileNotFoundException {
        File inputFile = new File("dataset3");

        Scanner inputReader = new Scanner(inputFile);

        List<List<Double>> l = new ArrayList<>();
        List<Double> a;

        while(inputReader.hasNext()){
            a = new ArrayList<>();
            for(int i = 0;i<70;i++){
                a.add(inputReader.nextDouble());
            }
            l.add(a);
        }

        return l;
    }



    private void learnXOR(){
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
        List<Double> output = net.feedForward(inputs);

        System.out.println(inputs.get(0)+" xor "+inputs.get(1)+" = "+Math.round(output.get(0))+" ("+output.get(0)+")");
    }

}
