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
        a.learnNumbers();


    }

    private void learnNumbers() throws FileNotFoundException {
        List<List<Double>> numbers = readFile();
        List<List<Double>> outputs = loadNumbers();

        List<Integer> layers = new ArrayList<>();
        layers.add(70);
        layers.add(10);
        layers.add(10);

        MultiLayerPerceptron net = new MultiLayerPerceptron(layers);


        for(int i = 0; i < 100000; i++) {

            for(int j = 0; j<numbers.size();j++){
                double error = net.backPropagate(numbers.get(j),outputs.get(j));

                //System.out.println("Error at step "+i+" is "+error);
            }

            //System.out.println(inputs.get(0)+" xor "+inputs.get(1)+" = "+output.get(0));

        }

        System.out.println("Learning completed!");

        /* Test */
        int a = 0;
        for(List<Double> n : numbers){
            List<Double> output = net.feedForward(n);
            System.out.printf("input: %d \t",a);
            for (Double out : output){
                System.out.printf("%d\t",Math.round(out));
            }
            a++;
            System.out.println();

        }

        List<Double> test = readTest();
        System.out.println("Deformed zero:");
        for(int i = 0; i<70;i++){
            if(test.get(i) == 0){
                System.out.print(" ");
            }
            else if(test.get(i) == 1){
                System.out.print("1");
            }
            if(((i+1)%10)==0){
                System.out.println();
            }
        }
        System.out.println("Network output:");
        System.out.println("0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
        List<Double> output = net.feedForward(test);
        for (Double out : output){
            System.out.printf("%d\t",Math.round(out));
        }

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

    private List<List<Double>> loadNumbers(){
        List<List<Double>> outputs = new ArrayList<>();
        List<Double> output = new ArrayList<>();

        for(int i = 0; i< 10;i++){
            for(int j = 0; j< 10;j++) {
                if(i == j ){
                    output.add(1.0);
                }
                else{
                    output.add(0.0);
                }
            }
            outputs.add(output);
            output = new ArrayList<>();
        }

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

    private List<Double> readTest() throws FileNotFoundException {
        File inputFile = new File("testNumber");

        Scanner inputReader = new Scanner(inputFile);

        List<Double> a = new ArrayList<>();

        while(inputReader.hasNext()){
            a.add(inputReader.nextDouble());
        }

        return a;
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
