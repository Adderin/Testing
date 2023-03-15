package edu.uopeople.temp.ai;

/**
 * This program uses the Deeplearning4j library to build and train a neural network
 * that can classify handwritten digits from the MNIST dataset.
 * <p>
 * The network has two layers: a fully-connected hidden layer with 128 nodes,
 * and an output layer with 10 nodes, one for each digit class (0-9).
 * The network is trained using the Nesterov's accelerated gradient descent algorithm,
 * with a learning rate of 0.01 and momentum of 0.9.
 * <p>
 * After training the model for 3 epochs,
 * the program evaluates the model on the test data and prints the evaluation statistics.
 *
 * <p>
 *     Not working due to Exception in thread "main" java.net.UnknownHostException: dl4jdata.blob.core.windows.net
 * </p>
 */

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import java.io.IOException;

public class HandwrittenDigitClassifier {

    public static void main(String[] args) throws IOException {
        // Load the data
        int batchSize = 64;
        DataSetIterator trainData = new MnistDataSetIterator(batchSize, true, 12345);
        DataSetIterator testData = new MnistDataSetIterator(batchSize, false, 12345);

// Define the model
        int numInputs = 28 * 28;
        int numHiddenNodes = 128;
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nesterovs(0.01, 0.9))
                .list()
                .layer(new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
                        .activation(Activation.RELU).build())
                .layer(new OutputLayer.Builder().lossFunction(LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX).build())
                .build();
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

// Train the model
        int numEpochs = 3;
        for (int i = 0; i < numEpochs; i++) {
            model.fit(trainData);
        }

        // Evaluate the model
        Evaluation eval = model.evaluate(testData);
        System.out.println(eval.stats());
    }

}
