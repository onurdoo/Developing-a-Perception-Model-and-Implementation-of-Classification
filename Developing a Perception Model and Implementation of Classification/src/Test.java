
public class Test {
    public static void main(String[] args) {  
    // First index of matris represents x1, second represents x2, third represent the target values.
    Double [][] defaultData = {{0.6,0.2,-0.3,-0.1,0.1, -0.2, -0.4, -0.6},{0.5, 0.4, -0.5, -0.1, 0.1, 0.7, -0.2, 0.3},
        {1.0,1.0,-1.0,-1.0,1.0,1.0,-1.0,-1.0}}; // default data. includes the values that given in the project portfolio.
    Double [][] testData = {{0.4,-0.2,-0.1,0.9,-0.2},{-0.36, -0.35, 0.82,- 0.25, -0.61},
        {1.0,-1.0,1.0,1.0,-1.0}}; // the test data that created from me.
    
    Double[] weights = new Double[2]; // 1d list that keeps the weight values.
    Neuron neuron1 = new Neuron(defaultData, testData, weights); // create the object from 'Neuron' class and send the necessary data information for training process.
    neuron1.startTraining(10); // starts the training process for 10 epoch.
    neuron1.startTesting(); // tests the current network.
    neuron1.startTraining(90); // starts the training process for 100 epoch.
    neuron1.startTesting(); // tests the current network.
    }
}

