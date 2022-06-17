import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        double[] chart = new double[total];
        double mean = 0;
        // String num = sc.nextLine();
        // String[] temp = num.split(", ");
        // System.out.println(Arrays.toString(temp));
        for(int i = 0; i < total; i++)
        {
            double num = sc.nextDouble();
            mean += num;
            chart[i] = num;
        }
        sc.close();
        Arrays.sort(chart);
        
        System.out.println(Arrays.toString(chart));
        System.out.println("Mean: " + mean / total);

        // median
        double median = 0;
        if(total % 2 == 0)
        {
            median = (double)(chart[total / 2] + chart[total / 2 - 1]) / 2;
        }
        else
        {
            median = chart[total / 2];
        }

        //q1 / q3
        double q1 = 0;
        double q3 = 0;
        if((total / 2) % 2 == 0)
        {
            q1 = (double)(chart[total / 4] + chart[total / 4 - 1]) / 2;
            q3 = (double)(chart[(int)(total * 0.75)] + chart[(total / 4 + total / 2) - 1]) / 2;
        }
        else
        {
            q1 = chart[total / 4];
            q3 = chart[(int)(total * 0.75)]; 
        }

        double range = (chart[total - 1] - chart[0]);
        double iqr = q3 - q1;
        double k = Math.ceil((1 + 3.322 * Math.log10(total)));

        System.out.println("Q1: " + q1);
        System.out.println("Q2 / Median: " + median);
        System.out.println("Q3: " + q3);
        System.out.println("Range: " + range);
        System.out.println("IQR (Q3 - Q1): " + iqr);
        System.out.println("# of intervals (K = 1 + 3.322(log10 n)): " + k);
        System.out.println("Interval width (range / k): " + range / k);
        
        double temp = 0;
        for(int i = 0; i < total; i++)
        {
            temp += Math.pow(chart[i] - mean, 2);
        }
        double sd = Math.sqrt(temp / total);
        System.out.println("Standard deviation (sigma): " + sd);
        System.out.println("Variance: " + Math.pow(sd, 2));
        //outliers
        for(int i = 0; i < total; i++)
        {
            if(chart[i] > q3 + 1.5 * iqr || chart[i] < q1 - 1.5 * iqr)
            {
                System.out.println(chart[i] + " is a suspected outlier");
            }

            if(chart[i] > q3 + 3 * iqr || chart[i] < q1 - 3 * iqr)
            {
                System.out.println(chart[i] + " is a extreme outlier");
            }
        }
    }
}
