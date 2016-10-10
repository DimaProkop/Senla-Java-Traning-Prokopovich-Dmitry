package task1;

import task1.classes.Sorter;
import task1.classes.WordGenerator;

/**
 * Created by prokop on 7.10.16.
 */
public class Main {

    public static void main(String[] args) {

        WordGenerator wordGenerator = new WordGenerator(10);
        String[] array = wordGenerator.getArray();

        Sorter sorter = new Sorter();
        String[] sortedArray = sorter.getSortedArray(array);
        System.out.print(sortedArray[0]);

    }
}
