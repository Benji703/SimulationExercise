public class LCG {
    private long seed;
    private int c;
    private int a;
    private double m;

    public LCG(int a, int c, double m, long seed) {
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public double[] randomLCGArray(int sequenceSize) {
        double lcgArray[] = new double[sequenceSize];
        double x = (double)this.seed;

        for (int i = 0; i < lcgArray.length; i++) {
            x = (this.a*x+this.c) % this.m;
            lcgArray[i] = x/this.m;
        }
        return lcgArray;
    }
}
