import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimpleSigmoid {


    public static void main(String args[]) throws FileNotFoundException {
        SimpleSigmoid perceptron = new SimpleSigmoid();

        List<List<Double>> input = perceptron.readFiles();
        List<Double> weights = new ArrayList<>();
        Random r = new Random();
        SimpleSigmoid p = new SimpleSigmoid();

        for(int i = 0; i<input.get(0).size()-1;i++){
            weights.add(0.0);
//            weights.add(r.nextDouble());
        }

    for(int j = 0;j<100000;j++) {

        for (int i = 0; i < input.size(); i++) {
            weights = p.updateWeights(0.1   , input.get(i), weights, p.activationExp(p.excitation(input.get(i), weights)));
        }
    }

    double error = 0;
    for(int i = 0;i<input.size();i++){
        double a = p.activationExp(input.get(i).get(input.get(i).size()-1));
        double b = p.activationExp(p.excitation(input.get(i),weights));
        System.out.printf("EXPECTED: %f PREDICTED %f = %f\n",a,b,a-b);
        error += Math.abs(a-b);
    }

        System.out.println(error);
    }



    private double excitation(List<Double> input, List<Double> weights){
        double sum = weights.get(0);
        for(int i = 1; i< input.size()-1;i++){
            sum += (input.get(i)*weights.get(i));
        }

        return sum;
    }

    private double activationTan(double excitation){
        return Math.tanh(excitation);
    }

    private double activationExp(double excitation){
        double exp = Math.exp(-2*excitation);
        return 1/(exp+1);
    }


    private double dTan(double x){
        double tan = activationTan(x);
        return 1 - Math.pow(tan,2);
    }

    private double dExp(double x){
        double exp = activationExp(x);
        return 2*exp*(1-exp);
    }

    private List<Double> updateWeights(double learnRate, List<Double> input,List<Double> weights, double prediction){
        List<Double> updatedWeights = new ArrayList<>();
        double expected = input.get(input.size()-1);

        //System.out.printf("expected %f predicted %f\n",activationExp(expected),prediction);

        for(int i =0;i<input.size()-1;i++){
            double delta = learnRate*(activationExp(expected)-prediction)*input.get(i)*dExp(excitation(input,weights));
//            double delta = learnRate*(activationTan(expected)-prediction)*input.get(i)*dTan(excitation(input,weights));
            updatedWeights.add(weights.get(i)+delta);
        }


        return updatedWeights;
    }


    private List<List<Double>> readFiles() throws FileNotFoundException {


        File inputFile = new File("dataset2");

        Scanner inputReader = new Scanner(inputFile);

        List<List<Double>> l = new ArrayList<>();
        List<Double> a;

        while(inputReader.hasNext()){
            a = new ArrayList<>();
            a.add(1.0);
            for(int i = 0;i<4;i++){
                a.add(inputReader.nextDouble());
            }
            l.add(a);
        }

        return l;

    }





}
