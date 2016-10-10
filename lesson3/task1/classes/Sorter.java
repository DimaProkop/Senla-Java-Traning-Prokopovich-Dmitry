package task1.classes;

/**
 * Created by prokop on 9.10.16.
 */
public class Sorter {

    public String[] getSortedArray(String[] array)
    {
        int step = array.length / 2;
        while (step > 0)
        {
            for (int i = 0; i < (array.length - step); i++)
            {
                int j = i;
                while (j >= 0 && compare(array[j].length(), array[j + step].length()))
                {
                    String temp = array[j];
                    array[j] = array[j + step];
                    array[j + step] = temp;
                    j--;
                }
            }
            step = step / 2;
        }

        return array;
    }

    private boolean compare(int a, int b){
        return a < b;
    }
}
