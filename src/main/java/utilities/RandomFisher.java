package utilities;

import java.util.concurrent.ThreadLocalRandom;

public class RandomFisher {
    private int[] values;
    private int count = 1;

    public RandomFisher(int size) {
        values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = i;
        }
    }

    public Integer next() {
        if (count > values.length) {
            return null;
        } else if (count == values.length) {
            count++;
            return values[0];
        } else {
            int rnd = ThreadLocalRandom.current().nextInt(0, values.length - count);
            int next = values[rnd];
            swapValues(rnd, values.length - count);
            count++;
            return next;
        }
    }

    private void swapValues(int index1, int index2) {
        int temp = values[index1];
        values[index1] = values[index2];
        values[index2] = temp;
    }
}
