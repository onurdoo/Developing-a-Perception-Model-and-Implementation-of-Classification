import java.util.Random;
public class Neuron {
    public static final Double LEARNING_CONSTANT = 0.05; // the leaning costant.
    Double[][] data; // 2d list that keeps the input data.
    Double[][] testData;
    Double []weights; // 1d list that keeps the weight values.
    int[] outputsTest;
    int currentEpochCount = 0;
    public Neuron(Double[][] data,Double[][] testData, Double[] weights){ // the constructor with the arguments.
        this.weights = createRandomData(weights); // fill the weight list with random values at the start. 
        this.data = data; 
        this.testData = testData;
        this.outputsTest = new int[data[0].length];
        
    }
    public Double[] createRandomData(Double[] weights ){ // the function that creates random values beetween -1 and 1.
        for(int i = 0; i<weights.length; i++){
            Random rnd = new Random();
            weights[i] = (rnd.nextDouble() * 2 - 1);
        }
        return weights;
    }
    public void checkNetwork(Double[][] data, Double[] weights, int[] outputs){ // the function that checks the neuron network system respectively.
        for(int i = 0; i<data[0].length;i++){
            if(outputs[i] != data[2][i]){ // check the current output value.
                outputs[i] = checkOneNetwork(data[0][i], data[1][i], data[2][i], weights[0], weights[1], i, weights);
                // update the output value of one network according to the training process.
            }        
        }
    }
    // check one network and apply the traning processes according to assumed rules.
    // w[i] = w[i] + (learning costant * (target value - output value)* x[i]))
    public int checkOneNetwork(Double x1, Double x2, Double target,Double rnd_weightx1, Double rnd_weightx2, int index, Double[] weights){  
        int output; // the integer variable that keeps output value.
        Double sum; // the double variable that keeps the current sum value after the mulptiplication processes.
        sum = (x1*rnd_weightx1)  +  (x2*rnd_weightx2);
        // determine the output value according to the sum value.
        if(sum > 0) output = 1; 
        else output = -1; 
        if(output!=target){ // if current output not equal the target value. apply the training process.
            weights[0] += LEARNING_CONSTANT*(target - sum)*x1;
            weights[1] += LEARNING_CONSTANT*(target - sum)*x2;
        }
        return output; 
    }
    public String checkAccuracy(Double[][] data, int [] outputs){ // prints the accuracy values basicly in string format.
        int accurateCount = 0;
        for(int i = 0; i < data[0].length ; i++){
            if(data[2][i] == outputs[i]) accurateCount++;
        }
        String percentage = "%"+ (100*accurateCount/(data[0].length));
        return percentage;
    }
    public String checkAccuracy2(Double[][] data,Double weights[], int [] outputs){ // prints the accuracy values for testing  in string format.
        int accurateCount = 0;
        Double sum; // the double variable that keeps the current sum value after the mulptiplication processes.
        for(int j = 0; j< data[0].length; j++){
            int output; // the integer variable that keeps output value.
            sum = (data[0][j]*weights[0])  +  (data[1][j]*weights[1]);
            // determine the output value according to the sum value.
            if(sum > 0) output = 1; 
            else output = -1; 
            outputs[j] = output; 
            if(output == data[2][j]) accurateCount++;
        }
        String percentage = "%"+ (100*accurateCount/(data[0].length));
        return percentage;
    }
    public void train(Double[][] data, Double[] weights, int addedEpochCount){ // train the network according to the epoch counts. respectively checks the network and updates the output values.
        int [] outputs = new int[data[0].length];
        currentEpochCount += addedEpochCount;
        for(int i = 0; i <currentEpochCount; i++){ // turns the loop for epochs.
            checkNetwork(data, weights, outputs);
        }
        // show the current values after epochs.
        System.out.println("********************************AFTER "+  currentEpochCount + " EPOCH*********************************");
        printEpoch(data, weights, outputs, checkAccuracy(data, outputs));
    }
    public void startTraining(int epochCount){ // starts the training process.
        train(data, weights, epochCount);
    }
    public void test(Double[] weights, int [] outputsTest, Double[][] testData){ // the function that tests the network according to current values.
        System.out.println("************************************TEST***************************************");
        printEpoch(testData, weights, outputsTest, checkAccuracy2(testData,weights, outputsTest));
    }    
    public void startTesting(){ // the function that starts the testing process.
        test(weights, outputsTest, testData);
    }
    // the function that formats the one epoch's values basicly.
    public void printEpoch(Double[][] data, Double[] weights, int[] outputs, String accuracy){
        System.out.println("  x1|        x2|       weightx1|       weightx2|    target|  output|  accuracy|");
        for(int i = 0; i<data[0].length; i++){
            String space = "|      ";
            System.out.printf("%4.1f%s%4.1f%s%9.5f%s%9.5f%s%4d%s%2d%s%4s%s", data[0][i],space,data[1][i],space,weights[0],space
            ,weights[1],space,data[2][i].intValue(),space,outputs[i],space,accuracy,space);
            System.out.println("");
        }
    }
}
