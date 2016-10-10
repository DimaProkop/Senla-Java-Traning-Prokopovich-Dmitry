package task2;

import task2.classes.Analyzer;
import task2.classes.NumberGenerator;

/**
 * Created by prokop on 9.10.16.
 */
public class Main {

    public static void main(String[] args) {

        NumberGenerator numberGenerator = new NumberGenerator(3, 3);
        Analyzer analyzer = new Analyzer();
        int [] array = numberGenerator.generateArray();
        int sum = analyzer.getSumFirstDigits(array);
        System.out.print(sum);
    }
}
