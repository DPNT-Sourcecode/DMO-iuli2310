package io.accelerate.solutions.DMO;


public class DemoRound4n5Solution {
    
    public String waves(Integer numberOfWaves) {
        if (numberOfWaves < 1 || numberOfWaves > 4) {
            throw new IllegalArgumentException("Wave crest count must be between 1 and 4.");
        }

        Main.StringOutputSink outputSink = new Main.StringOutputSink();
        Main.InputProviderScalar inputProvider = () -> (float) numberOfWaves;

        Main.run(outputSink, inputProvider);
        return outputSink.content();
    }

}

