import java.util.Arrays;

public class RunsTest {
    public static void RunTest(double[] numberArray) {
        //Find median number in array
        double[] sortedArray = Arrays.stream(numberArray).sorted().toArray();

        double median;
        if (sortedArray.length % 2 == 0)
            median = ((double)sortedArray[sortedArray.length/2] + (double)sortedArray[sortedArray.length/2 - 1])/2;
        else
            median = (double) sortedArray[sortedArray.length/2];

        //Create new array with symbols instead of numbers
        String[] symbolArray = new String[numberArray.length];
        for (int i = 0; i < numberArray.length; i++){
            if (numberArray[i] < median) {
                symbolArray[i] = "a";
            }
            if (numberArray[i] > median) {
                symbolArray[i] = "b";
            }
        }

        //Counting number of types and runs
        int n_1 = 0; //number of the first type
        int n_2 = 0; //number of the second type
        int g = 0; //number of runs

        String previousLetter =  null;

        for(String value : symbolArray) {
            if (previousLetter == null) {
                previousLetter = value;
                g++;
            }

            if (value == "a"){
                n_1++;
                if (previousLetter == "b") {
                    previousLetter = value;
                    g++;
                }
            }
            else if (value == "b") {
                n_2++;
                if (previousLetter == "a") {
                    previousLetter = value;
                    g++;
                }
            }
        }

        //number of runs should be normal distributed given mean(expectedRuns) & standard deviation(standardDeviation)
        //Calculate mean
        double n = n_1+n_2;
        double expectedRuns = ((2*n_1*n_2)/n)+1;

        //Calculate standard deviation
        //double standardDeviation = Math.sqrt(((2*n_1*n_2)*(2*n_1*n_2-n_1-n_2))/Math.pow(n, 2)*(n-1));
        double sdTop = ((2*n_1*n_2)*(2*n_1*n_2-n_1-n_2));
        double sdBottom = Math.pow(n, 2)*(n-1);
        double standardDeviation = Math.sqrt(sdTop/sdBottom);

        //Absolute value for the Test Statistic
        double z = Math.abs((g - expectedRuns)/standardDeviation);

        double alpha = 0.05;
        System.out.println("Running Runs test: ");
        System.out.println("Number of a's = " + n_1);
        System.out.println("Number of b's = " + n_2);
        System.out.println("Expected number of runs = " + expectedRuns);
        System.out.println("Actual number of runs = " + g);
        System.out.println("Standard deviation = " + standardDeviation);
        System.out.println("Level of significance : " + alpha);

        double confidenceVale = 1.96; //For the standard normal distribution,  P(-1.96 < Z < 1.96) = 0.95, i.e.
        // ,there is a 95% probability that a standard normal variable, Z, will fall between -1.96 and 1.96.

        System.out.println("Critical value for normal distribution (at 95%): " + 1.96);
        System.out.println("Test statistic is: " + z);

        if (z < confidenceVale) {
            System.out.println("H_0 has NOT been rejected");
        } else {
            System.out.println("H_0 has been rejected");
        }
        System.out.println(" ");

    }
}
