import java.util.Arrays;

public class RunsTest {
    public static void RunTest(double[] numberArray, int k) {
        System.out.println("Running Runs test:");
        int doF = k -1; //Degrees of Freedom(DoF)
        double alpha = 0.05; //Level of Significances(LoS)

        //Observe number of runs as well as count length of each run
        int runLength = 0;
        int[] observedRuns = new int[k];

        boolean previousWasSmaller = false;
        if(numberArray[1] > numberArray[0]){
            previousWasSmaller = true;
        } else {
            previousWasSmaller =false;
        }
        for(int i = 1; i < numberArray.length; i++) {
            if(numberArray[i] > numberArray[i-1]){
                if(previousWasSmaller) {
                    runLength++;
                } else {
                    if(runLength <= k){
                        observedRuns[runLength-1]++;
                    }
                    runLength = 1;
                    previousWasSmaller = true;
                }
            }
            if(numberArray[i] < numberArray[i-1]) {
                if(!previousWasSmaller) {
                    runLength++;
                } else {
                    if(runLength <= k){
                        observedRuns[runLength-1]++;
                    }
                    runLength = 1;
                    previousWasSmaller = false;
                }
            }
            if(numberArray[i] == numberArray[i-1]) {
                runLength++;
            }
        }
        if(runLength <= k -1){
            observedRuns[runLength-1]++;
        }

        System.out.println("Observed Runs: ");
        for(int i = 0; i < observedRuns.length; i++) {
            System.out.print(observedRuns[i] + " : ");
        }

        //Calculate expected number of runs
        System.out.println("");
        double n = numberArray.length; // Sequence length
        double[] expectedRunsList = new double[k];
        double expectedRun = 0;

        System.out.println("Expected runs: ");
        for (int i = 1; i < k+1; i++) {
            double numberFactorial = i+3;
            double factorial = 1;
            for(int j=1; j<=numberFactorial;j++){
                factorial=factorial*j;
            }
            /*
            double part1 = (2/factorial);
            double part2 = (n*(Math.pow(i,2)+(3*i)+1));
            double part3 = ((((Math.pow(i,3)+(3*Math.pow(i,2)))-i)-4));
            */
            expectedRun = (2/factorial)*((n*(Math.pow(i,2)+(3*i)+1))-((((Math.pow(i,3)+(3*Math.pow(i,2)))-i)-4)));
            double intExpectedRun = expectedRun;
            System.out.printf("%,.2f", intExpectedRun);
            System.out.print(" : ");
            expectedRunsList[i-1] = intExpectedRun;
        }

        //Calculate chi_squared
        double chi_squared = 0;
        for(int i = 0; i < k-1; i++) {
            chi_squared = chi_squared + Math.pow(expectedRunsList[i]-observedRuns[i],2)/expectedRunsList[i];
        }

        double chi_df = 0;
        switch (doF) {
            case (6):
                chi_df = 11.07; //chi_df given DoF 6 and 0.05 alpha (LoS)
                break;
            case (7):
                chi_df = 12.59; // chi_df given 7 DoF and 0.05 alpha (LoS)
                break;
        }

        System.out.println("\nLevel of significance (alpha): " + alpha);
        System.out.println("Degrees of freedom (df): " + doF);
        System.out.println("X_df: " + chi_df);
        System.out.println("X_squared: " + chi_squared);
        if (chi_squared < chi_df) {
            System.out.println("H_0 has NOT been rejected");
        } else {
            System.out.println("H_0 has been rejected");
        }
        System.out.println();
    }
}
