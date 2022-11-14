import java.util.Random;

public class Main {

    public static void main(String[] args) {

        long seed = 123456789;
        Random random = new Random();
        random.setSeed(seed);
        int sampleAmount = 10000;

        int number = 9999;

        /* Custom LCG Method */
        LCG lcg = new LCG(101427,321,65536,seed);
        double[] lcgArray = lcg.randomLCGArray(sampleAmount);
        System.out.println("Number " + number + " (custom no randu): " + lcgArray[number]);

        /* Custom LCG with Randu */
        LCG lcgRandu = new LCG(65539,0,2147483648.0,seed);
        double[] lcgRanduArray = lcgRandu.randomLCGArray(sampleAmount);
        System.out.println("Number " + number + " (custom with randu: " + lcgRanduArray[number]);

        /* Native Java Random function */
        double[] javaRandomArray = new double[sampleAmount];
        for (int i = 0; i < javaRandomArray.length; i++) {
            javaRandomArray[i] = random.nextDouble();
        }

        System.out.println("Number " + number + " (native): " + javaRandomArray[number]);

        // TESTS
        nativeTests(javaRandomArray);
        lcgTests(lcgArray);
        lcgRanduTests(lcgRanduArray);
    }

    private static void nativeTests(double[] javaRandomArray) {
        System.out.println("\nNATIVE TESTS");
        // Insert tests here...
        KolmogorovSmirnovTest.RunTest(javaRandomArray);
        ChiSquaredTest.RunTest(javaRandomArray);
        RunsTest.RunTest(javaRandomArray, 6);
    }

    private static void lcgTests(double[] lcgArray) {
        System.out.println("\nLCG TESTS");
        // Insert tests here...
        KolmogorovSmirnovTest.RunTest(lcgArray);
        ChiSquaredTest.RunTest(lcgArray);
        RunsTest.RunTest(lcgArray,6);
    }

    private static void lcgRanduTests(double[] lcgRanduArray) {
        System.out.println("\nLCG RANDU TESTS");
        // Insert tests here...
        KolmogorovSmirnovTest.RunTest(lcgRanduArray);
        ChiSquaredTest.RunTest(lcgRanduArray);
        RunsTest.RunTest(lcgRanduArray,7);
    }
}