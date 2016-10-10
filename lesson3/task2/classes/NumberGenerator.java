package task2.classes;

import java.util.Random;

/**
 * Created by prokop on 9.10.16.
 */
public class NumberGenerator {
    private int count;
    private int length;

    public NumberGenerator(int count, int length) {
        this.count = count;
        this.length = length;
    }

    public int generateNumber() {
        int max = (int) Math.pow(10, length);
        int min = (int) Math.pow(10, length - 1);
        return (new Random().nextInt(max - min) + min) * getSign();
    }

    public int[] generateArray() {
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateNumber();
        }
        return array;
    }

    public int getSign() {
        return new Random().nextInt(2) == 0 ? 1 : -1;
    }
}
