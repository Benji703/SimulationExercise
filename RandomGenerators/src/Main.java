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
        double lcgArray[] = lcg.randomLCGArray(sampleAmount);
        System.out.println("Number " + number + " (custom no randu): " + lcgArray[number]);

        /* Custom LCG with Randu */
        LCG lcgRandu = new LCG(65539,0,2147483648.0,seed);
        double lcgRanduArray[] = lcgRandu.randomLCGArray(sampleAmount);
        System.out.println("Number " + number + " (custom with randu: " + lcgRanduArray[number]);

        /* Native Java Random function */
        double javaRandomArray[] = new double[sampleAmount];
        for (int i = 0; i < javaRandomArray.length; i++) {
            javaRandomArray[i] = random.nextDouble();
        }

        System.out.println("Number " + number + " (native): " + javaRandomArray[number]);
    }
}