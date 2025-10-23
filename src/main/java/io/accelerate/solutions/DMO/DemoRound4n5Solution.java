package io.accelerate.solutions.DMO;


public class DemoRound4n5Solution {
    
    public String waves(Integer numberOfWaves) {
        if (numberOfWaves < 1 || numberOfWaves > 4) {
            throw new IllegalArgumentException("Wave crest count must be between 1 and 4.");
        }

        Main.StringOutputSink outputSink = new Main.StringOutputSink();
        Main.InputProviderScalar inputProvider = () -> (float) numberOfWaves;

        Main.run(outputSink, inputProvider);

        String rawOutput = outputSink.content();
        String[] lines = rawOutput.split("\\R");

        for (int index = lines.length - 1; index >= 0; index--) {
            String line = lines[index];
            if (!line.isEmpty()) {
                return line;
            }
        }

        return "";
    }

}


