import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimpleStep {
    public static void main(String args[]) throws FileNotFoundException {

        SimpleStep p = new SimpleStep();

        List<List<Integer>> input = p.readFiles();
        List<Double> weights = new ArrayList<>();
        Random r = new Random();

        for(int i = 0; i<3;i++){
            weights.add(r.nextDouble()); // CAMBIA MUCHO SI ESTO ES RANDOM O SE INICIALIZA EN 0
//            weights.add(0.0); // CAMBIA MUCHO SI ESTO ES RANDOM O SE INICIALIZA EN 0
        }

        for(int j = 0;j<100;j++) {

            for (int i = 0; i < input.size(); i++) {
                if(i!=3)
                weights = p.updateWeights(0.1, input.get(i), weights, p.activation(p.excitation(input.get(i), weights)));
            }
        }

        for(Double d: weights){
            System.out.println(d);
        }
//        weights = p.updateWeights(0.1,input.get(0),weights,p.activation(p.excitation(input.get(0),weights)));
//        weights = p.updateWeights(0.1,input.get(1),weights,p.activation(p.excitation(input.get(1),weights)));
//        weights = p.updateWeights(0.1,input.get(2),weights,p.activation(p.excitation(input.get(2),weights)));
//        weights = p.updateWeights(0.1,input.get(3),weights,p.activation(p.excitation(input.get(3),weights)));
        for(List<Integer> in : input){
            for (Integer i :in){
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        System.out.println(p.activation(p.excitation(input.get(3),weights)));

    }


    private double excitation(List<Integer> input, List<Double> weights){
        double sum = weights.get(0);
        for(int i = 1; i< input.size()-1;i++){
            sum += (input.get(i)*weights.get(i));
        }

        return sum;
    }

    private int activation(double excitation){
        return excitation>=0?1:-1;
    }

    private List<Double> updateWeights(double learnRate, List<Integer> input,List<Double> weights, int prediction){
        List<Double> updatedWeights = new ArrayList<>();
        int expected = input.get(input.size()-1);

        for(int i =0;i<input.size()-1;i++){
            double delta = learnRate*(expected-prediction)*input.get(i);
            updatedWeights.add(weights.get(i)+delta);
        }


        return updatedWeights;
    }



    private List<List<Integer>> readFiles() throws FileNotFoundException {

        File inputFile = new File("dataset1");

        Scanner inputReader = new Scanner(inputFile);

        List<List<Integer>> l = new ArrayList<>();
        List<Integer> a;

        while(inputReader.hasNext()){
            a = new ArrayList<>();
            a.add(1);
            for(int i = 0;i<3;i++){
                a.add(inputReader.nextInt());
            }
            l.add(a);
        }

        return l;

    }
}
