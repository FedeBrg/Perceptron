package multi;

import java.util.ArrayList;
import java.util.List;

public class MultiLayerPerceptron {

    public List<Layer> layers;

    public MultiLayerPerceptron(List<Integer> layerDisp){

        layers = new ArrayList<>();
        layers.add(new Layer(layerDisp.get(0),0));

        for(int i = 1; i < layerDisp.size(); i++) {
            layers.add(new Layer(layerDisp.get(i),layerDisp.get(i-1)));
        }
    }


    public List<Double> execute(List<Double> input)
    {

        double new_value;

        List<Double> output = new ArrayList<>();

        // Put input
        for(int i = 0; i<layers.get(0).size;i++){
            layers.get(0).perceptrons.get(i).value = input.get(i);
        }


        // Execute - hiddens + output
        for(int i = 1; i < layers.size(); i++){
            for(int j = 0; j < layers.get(i).size; j++){
                new_value = 0.0;
                for(int k = 0; k < layers.get(i - 1).size; k++){
                    new_value += layers.get(i).perceptrons.get(j).weights.get(k) * layers.get(i - 1).perceptrons.get(k).value;
                }

                new_value += layers.get(i).perceptrons.get(j).bias;

                layers.get(i).perceptrons.get(j).value = activationExp(new_value);
            }
        }


        // Get output
        for(int i = 0; i <  layers.get(layers.size()-1).size; i++){
            output.add(layers.get(layers.size()-1).perceptrons.get(i).value);
        }

        return output;
    }

    private double activationExp(double excitation){
        return 1 / (1 + Math.pow(Math.E, -excitation));

    }

    private double dExp(double x){
        return (x - Math.pow(x, 2));

    }


    public double backPropagate(List<Double> input, List<Double> output){
        List<Double> new_output = execute(input);
        double error;


        for(int i = 0; i < layers.get(layers.size()-1).size; i++){
            error = output.get(i) - new_output.get(i);
            layers.get(layers.size()-1).perceptrons.get(i).delta = error + dExp(new_output.get(i));
        }


        for(int i = layers.size() - 2; i >= 0; i--){
            for(int j = 0; j < layers.get(i).size; j++){
                error = 0.0;
                for(int k = 0; k < layers.get(i + 1).size; k++){
                    error += layers.get(i+1).perceptrons.get(k).delta * layers.get(i+1).perceptrons.get(k).weights.get(j);
                }
                layers.get(i).perceptrons.get(j).delta = error * dExp(layers.get(i).perceptrons.get(j).value);
            }

            for(int j = 0; j < layers.get(i+1).size; j++){
                for(int k = 0; k < layers.get(i).size; k++){
                    double aux = layers.get(i+1).perceptrons.get(j).weights.get(k);
                    layers.get(i+1).perceptrons.get(j).weights.set(k,aux + 0.1 * layers.get(i+1).perceptrons.get(j).delta*layers.get(i).perceptrons.get(k).value);
                }
                layers.get(i+1).perceptrons.get(j).bias += 0.1 * layers.get(i+1).perceptrons.get(j).delta;
            }
        }

        error = 0.0;

        for(int i = 0; i < output.size(); i++){
            error += Math.abs(new_output.get(i) - output.get(i));

        }

        error = error / output.size();
        return error;
    }



}
