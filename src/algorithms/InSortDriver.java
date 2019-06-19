package algorithms;

import java.util.Random;

/**
 * driver class that takes a generated random array
 * and testes different input cases
 * @author jhakon pappoe
 * @version 0.1
 */
public class InSortDriver {

    public static final int NUMBER_OF_ELEMENTS = 25;

    /**
     * driver has different inputs to test the sorting method
     * @param args driver argument
     */
    public static void main(String[] args) {
        InSort inSort = new InSort();

        inSort.sort(getRandomArray(), 5);
        System.out.println(
            "*************************************************************************************"
                + "************************");
        inSort.sort(getRandomArray(), 3);
        System.out.println(
            "*************************************************************************************"
                + "************************");
        inSort.sort(getRandomArray(), 2);
        System.out.println(
            "*************************************************************************************"
                + "************************");
        inSort.sort(getRandomArray(), 7);
    }

    private static int[] getRandomArray() {
        Random rand = new Random();
        int[] arr = new int[rand.nextInt(NUMBER_OF_ELEMENTS) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }
        System.out.println("Generating an array of size " + arr.length +
            " with elements in the range [" + 1 + ", " + 100 + "]");
        return arr;
    }
}