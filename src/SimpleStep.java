import java.util.ArrayList;
import java.util.List;

public class SimpleStep {
    public static void main(String args[]){

        List<List<Integer>> input = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        l.add(-1);
        l.add(1);
        l.add(0);
        input.add(l);
        l = new ArrayList<>();
        l.add(1);
        l.add(-1);
        l.add(0);
        input.add(l);
        l = new ArrayList<>();
        l.add(-1);
        l.add(-1);
        l.add(0);
        input.add(l);
        l = new ArrayList<>();
        l.add(1);
        l.add(1);
        l.add(1);
        input.add(l);

        SimpleStep a = new SimpleStep();
        for(double w : a.trainWeights(input,0.1,5)){
            System.out.printf("%f\n",w);
        }

                l = new ArrayList<>();
        l.add(-1);
        l.add(1);
        l.add(0);
        System.out.println(a.predict(l,a.trainWeights(input,0.1,5)));

    }

    private int predict(List<Integer> rows, List<Double> weights){
        double activation = -weights.get(0);
        for(int i=0;i<rows.size()-1;i++){
            activation+=(weights.get(i+1)*rows.get(i));
        }

        return activation >= 0.0?1:0;
    }



    private List<Double> trainWeights(List<List<Integer>> train, double learnRate, int epochs){
        List<Double> weights = new ArrayList<>();
        for (int i = 0; i<train.get(0).size();i++){
            weights.add(0.0);
        }

        double sum_error;
        int prediction;
        double error;
        for(int e = 0; e<epochs;e++){
            sum_error = 0;
            for(List<Integer> input : train){
                prediction = predict(input,weights);
                error = input.get(input.size()-1) - prediction;
                sum_error+=(error*error);
                weights.set(0,weights.get(0)+(learnRate*error));
                for (int i = 0; i<input.size()-1;i++){
                    weights.set(i+1,weights.get(i+1)+(learnRate*error*input.get(i)));
                }
            }
            System.out.printf("epoch:%d,lrate:%f,error:%f\n",e,learnRate,sum_error);
        }

        return weights;
    }




}
