import java.util.Arrays;

public class KolmogorovSmirnovTest {

    public static void RunTest(double[] randomNumbersArray) {
        System.out.println("Running Kolmogorov Smirnov test:");
        int N = randomNumbersArray.length;

        double[] sorted = Arrays.stream(randomNumbersArray).sorted().toArray();

        // Map D values
        double[] dPlus = new double[N];
        double[] dMinus = new double[N];

        for (int i = 0; i < sorted.length; i++) {
            double Ri = sorted[i];

            dPlus[i] = (i / (float) N) - Ri;
            dMinus[i] = Ri - ((i-1) / (float) N);
        }

        double dPlusMax = Arrays.stream(dPlus).max().orElseThrow();
        double dMinusMax = Arrays.stream(dMinus).max().orElseThrow();

        double d = Math.max(dPlusMax, dMinusMax);

        System.out.println("D: " + d + ", D-: " + dMinusMax + ", D+: " + dPlusMax);

        // Calculate significance level
        double dAlpha = 1.36/Math.sqrt(N);    // Significance level
        System.out.println("Significance level (D_alpha): " + dAlpha);

        // Compare
        if (d <= dAlpha) {
            System.out.println("H_0 has been ACCEPTED");
        } else {
            System.out.println("H_0 has been REJECTED");
        }
    }
}
