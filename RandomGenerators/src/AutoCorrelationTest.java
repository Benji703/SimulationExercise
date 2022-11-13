public class AutoCorrelationTest {

    public static void RunTest(double[] randomNumbersArray) {
        System.out.println("Running autocorrelation test:");
        int i = 3;
        int m = 128;
        int n = randomNumbersArray.length;
        double bigM = ((n-i)/ m)-1;

        //Calculating rho or Pim hat
        double sumR = 0;
        for (int j = i; j < n-m; j += m) {
            double r = randomNumbersArray[j] * randomNumbersArray[j+m];
            sumR += r;
        }

        double rho = (1/(bigM+1)) * sumR - 0.25;
        System.out.println("Value of rho or pim hat: " + rho);

        //Calculating sigma
        double sigma = (Math.sqrt(((13*bigM)+7))/(12*(bigM+1)));
        System.out.println("Value of sigma: " + sigma);

        //Calculating test statistic
        double z = rho/sigma;

        System.out.println("Zo equals: " + z);

        double minusZ = -1.96;
        double plusZ = 1.96;

        System.out.println("Level of significance (alpha): 0.05");
        if (minusZ <= z && z <= plusZ) {
            System.out.println("H_0 has not been rejected");
        } else {
            System.out.println("H_0 rejected");
        }

    }

}
