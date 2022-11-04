import java.util.ArrayList;

public class RunsTest {

    public static void RunTest(double[] randomNumbersArray) {

        int sampleSize = randomNumbersArray.length;
        int runAmount = 1;
        ArrayList<Integer> runLengths = new ArrayList<Integer>();
        boolean isLarger = randomNumbersArray[0] < randomNumbersArray[1];
        System.out.println(isLarger);
        int currentRunLength = 0;

        //Counts number of runs and length of each run
        for (int i = 1; i < randomNumbersArray.length; i++) {
            if (randomNumbersArray[i] > randomNumbersArray[i-1]) {
                if (isLarger == true) {
                    currentRunLength++;
                } else {
                    isLarger = true;
                    runLengths.add(currentRunLength);
                    runAmount++;
                    currentRunLength = 1;
                }
            } else if (randomNumbersArray[i] < randomNumbersArray[i-1]) {
                if (isLarger == false) {
                    currentRunLength++;
                } else {
                    isLarger = false;
                    runLengths.add(currentRunLength);
                    runAmount++;
                    currentRunLength = 1;
                }
            } else {
                currentRunLength++;
            }
        }
        runLengths.add(currentRunLength);

        //Calculate the expected amount of runs for sample size in a truly random sequence
        double expectedRuns = 2*sampleSize-1/3;

        System.out.println("Amount of runs: " + runAmount);

    }
}
