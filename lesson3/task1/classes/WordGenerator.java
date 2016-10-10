package task1.classes;

import java.util.Random;

/**
 * Created by prokop on 9.10.16.
 */
public class WordGenerator {
    private String[] wordArray;
    private int length;

    private final int LEFT_LIMIT = 97; // letter 'a'
    private final int RIGHT_LIMIT = 122; // letter 'z'
    private int limit = 15;

    public WordGenerator(int length) {
        this.length = length;
        wordArray = new String[length];
    }

    public String[] getArray() {
        for (int i = 0; i < this.length; i++) {
            wordArray[i] = generateString(new Random().nextInt(limit +1));
        }
        return wordArray;
    }

    private String generateString(int length) {

        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = LEFT_LIMIT + (int)
                    (new Random().nextFloat() * (RIGHT_LIMIT - LEFT_LIMIT+1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
