package io.accelerate.solutions.DMO;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class Main {
    @FunctionalInterface
    interface InputProviderScalar {
        float fetch();
    }

    private static final DecimalFormat df;
    static {
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    private static final String TITLE = "WAVES";
    private static final String PROMPT = "TYPE IN HOW MANY WAVES TO DRAW AS A NUMBER BETWEEN 1 AND 4?";
    private static final String WAVE_PATTERN = "____....~~~~''''~~~~....____";
    private static final float WAVE_SEGMENT_COUNT = 7f;
    private static final float WAVE_STEP = 4f;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputProviderScalar inputForScalarF = () -> Float.parseFloat(scanner.nextLine());
            run(new ConsoleOutputSink(), inputForScalarF);
        }
    }

    interface OutputSink {
        void print(String text);

        void println();
    }

    static final class ConsoleOutputSink implements OutputSink {
        @Override
        public void print(String text) {
            System.out.print(text);
        }

        @Override
        public void println() {
            System.out.println();
        }
    }

    static final class StringOutputSink implements OutputSink {
        private final StringBuilder builder = new StringBuilder();

        @Override
        public void print(String text) {
            builder.append(text);
        }

        @Override
        public void println() {
            builder.append(System.lineSeparator());
        }

        public String content() {
            return builder.toString();
        }

        public StringBuilder builder() {
            return builder;
        }
    }

    private static final class Output {
        private final OutputSink sink;
        private int currentLineCharCount = 0;

        private Output(OutputSink sink) {
            this.sink = sink;
        }

        private void print(String variable) {
            sink.print(variable);
            currentLineCharCount += variable.length();
        }

        private void print(float variable) {
            String text = df.format(variable);
            sink.print(text);
            currentLineCharCount += text.length();
        }

        private void println() {
            sink.println();
            currentLineCharCount = 0;
        }

        private String tab(float numSpaces) {
            return " ".repeat(Math.round(numSpaces - currentLineCharCount));
        }
    }

    public static int asInt(float variable) {
        return Math.round(variable);
    }

    public static float roundDownToInt(float variable) {
        return (float) Math.floor(variable);
    }

    public static float random(int positiveInt) {
        return 0.5f;
    }

    public static String mid(String text, float startingIndex, float numChars) {
        return text.substring(asInt(startingIndex - 1), asInt(startingIndex + numChars - 1));
    }

    public static float len(String text) {
        return (float) text.length();
    }

    public static void run(OutputSink outputSink, InputProviderScalar inputForScalarF) {
        Output output = new Output(outputSink);
        float waveCount = showIntroAndReadWaveCount(output, inputForScalarF);
        WaveParameters parameters = new WaveParameters(waveCount);

        renderWaves(output, parameters);
        output.println();
    }

    private static float showIntroAndReadWaveCount(Output output, InputProviderScalar inputProvider) {
        output.print(TITLE);
        output.println();

        output.print(PROMPT);
        output.println();

        float waveCount = inputProvider.fetch();
        output.println();
        return waveCount;
    }

    private static void renderWaves(Output output, WaveParameters parameters) {
        for (float waveIndex = 1f; !isBeyondLoopEnd(waveIndex, parameters.waveCount(), 1f); waveIndex += 1f) {
            renderWaveCycle(output, parameters);
        }
    }

    private static void renderWaveCycle(Output output, WaveParameters parameters) {
        for (float position = 1f; !isBeyondLoopEnd(position, parameters.patternLength(), WAVE_STEP); position += WAVE_STEP) {
            output.print(mid(WAVE_PATTERN, position, parameters.sampleWidth()));
        }
    }

    private static boolean isBeyondLoopEnd(float current, float limit, float step) {
        return (current - limit) * step > 0;
    }

    private static final class WaveParameters {
        private final float waveCount;
        private final float patternLength;
        private final float sampleWidth;

        private WaveParameters(float waveCount) {
            this.waveCount = waveCount;
            this.patternLength = len(WAVE_PATTERN);
            float crestWidth = patternLength / waveCount;
            this.sampleWidth = crestWidth / WAVE_SEGMENT_COUNT;
        }

        private float waveCount() {
            return waveCount;
        }

        private float patternLength() {
            return patternLength;
        }

        private float sampleWidth() {
            return sampleWidth;
        }
    }
}

