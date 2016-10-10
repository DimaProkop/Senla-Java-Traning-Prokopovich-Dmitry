package task2.classes;

/**
 * Created by prokop on 9.10.16.
 */
public class Analyzer {

    private boolean isNegative(int n) {
        return n < 0;
    }

    public int getSumFirstDigits(int[] array) {
        int sum = 0;
        for(int digit: array) {
            int count = 1;
            if(isNegative(digit)) {
                count = 2;
            }
            String str = String.valueOf(digit).substring(0, count);
            sum += Integer.parseInt(String.valueOf(str));
        }
        return sum;
    }
}
