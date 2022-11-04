import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChiSquaredTest {

    public static void RunTest(double[] randomNumbersArray) {
        System.out.println("Running Chi squared test:");
        int N = randomNumbersArray.length;

        double[] sorted = Arrays.stream(randomNumbersArray).sorted().toArray();

        // Divide into classes
        double increment = 0.1;
        List<List<Double>> O = new ArrayList<>();   // Actual in each class
        for (int i = 0; i < 1/increment; i++) {
            O.add(new ArrayList<>());
        }

        for (double value : sorted) {
            int k_n = 0;

            float min = 0;
            while (min < 1) {

                double max = min + 0.1;
                if (min <= value && value < max) {
                    // If in range add to the k_n class
                    O.get(k_n).add(value);
                    break;
                }

                k_n++;
                min += increment;
            }
        }

        for (int i = 0, kSize = O.size(); i < kSize; i++) {
            List<Double> doubles = O.get(i);
            System.out.println("O: " + i + ",\tCount " + doubles.size());
        }

        // Expected in each class
        List<Integer> E = new ArrayList<>();    // Expected in each class
        int expected = N / O.size();
        for (int i = 0, oSize = O.size(); i < oSize; i++) {
            E.add(expected); // For uniform distribution we expect an equal amount
        }
        System.out.println("E: for all,\tCount " + expected);

        double X_squared = 0;
        for (int i = 0; i < O.size(); i++) {
            X_squared += Math.pow(O.get(i).size() - E.get(i), 2) / E.get(i);
        }
        System.out.println("X_squared: " + X_squared);

        // Check
        double alpha = 0.05;            // Level of significance
        double df = O.size()-1;         // Degrees of freedom
        System.out.println("Level of significance (alpha): " + alpha);
        System.out.println("Degrees of freedom (df): " + df);

        double cv = 16.92;  // Area to the right of critical value (for df=9 and alpha=0.05)
        System.out.println("Area to the right of critical value: " + cv);
        System.out.println("X_squared: " + X_squared);

        if (X_squared < cv) {
            System.out.println("H_0 has NOT been rejected");
        } else {
            System.out.println("H_0 has been rejected");
        }

        System.out.println();
    }
}
