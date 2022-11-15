public class NewRunsTest {

    private static String h0 = "The occurrence of increasing or descreasing values is random";
    private static String ha = "The occurrence of increasing or descreasing values is NOT random";
    private static int increasing = 0; // the amount of increasing numbers
    private static int decreasing = 0; // the amount of decreasing numbers
    private static int runs = 0; // the amount of change between an increasing and decreasing number
    private static double alpha = 0.05;
    private static boolean wasLastDescending;


    public static void newRunsTest(double[] numberArray, int freedom){
        determineValues(numberArray);
        double zts = (runs - expectedNumberOfRuns(increasing, decreasing)) / variance(increasing,decreasing);
        System.out.println("\n\n\n");
        System.out.println("min test");
        System.out.println(zts);


    }

    //find the amount of increasing & decreasing value and amount of runs
    private static void determineValues(double[] numberArray){
        int i = 0;
        for (double element : numberArray){
            System.out.print(numberArray[i] + "  " );
            if (i == 0){
                runs++;
                increasing++;
                System.out.println("i: " + i + "  netural");
                wasLastDescending = false;
            } else if (numberArray[i-1] < numberArray[i]) {
                increasing++;
                if(wasLastDescending){
                    runs++;
                }
                System.out.println("i: " + i + "   increasing: " + increasing );
                wasLastDescending = false;
            } else if (numberArray[i-1] > numberArray[i]){

                decreasing++;
                if(!wasLastDescending){
                    runs++;
                }
                System.out.println("i: " + i + "  decreasing: " + decreasing);
                wasLastDescending = true;
            }

            i++;
        }


    }
    private static double variance(int number1, int number2){
        System.out.println("number1: " + number1 + "  number2: " + number2  );
        System.out.println("variance");
        double variance = (Math.sqrt(2 *number1 *number2 * (2 * number1 * number2 - number1- number2) / Math.pow(number1+number2,2) + (number1 + number2 -1)));
        System.out.println(variance);
        return variance;
    }

    private static double expectedNumberOfRuns(int number1, int number2){
   //     System.out.println("expected Number of Runs");
        double expectedRuns = ((2 * number1 * number2) / (number1 + number2)) + 1;
        System.out.println(expectedRuns);
        return expectedRuns;
    }

}
