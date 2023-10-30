import java.util.*;
import java.lang.*;

/**
 * Class for QuickSort Investigation, exploring how changing
 * the switch to insertion sort affects runtime. Note that
 * there are many private methods here for how quicksort works.
 * (You won't need to consider all of them, especially fillAndShuffle().)
 * You'll modify main, and you're welcome to add any additional
 * methods you'd like. You will likely need to modify the way in which
 * the insertionSort threshold (currently set with MIN_SIZE) is
 * specified.
 *
 * @author Anna Rafferty
 * @author Layla Oesper
 * @author Eric Alexander
 * @author Titus Klinge
 * @author Sneha Narayan
 * @author [YOUR NAMES HERE]
 */
public class SortTest {

    /**
     * Helper function you may decide to use to print out a given array to the console.
     *
     * @param arr the array to print
     */
    private static void printArr(int[] arr) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < arr.length; i++) {
            sj.add(Integer.toString(arr[i]));
        }
        System.out.println(sj.toString());
    }

    /**
     * Generates a pseudo-random permutation of the integers from 0 to a.length - 1.
     * See p. 139 of "Seminumerical Algorithms, 2nd edition," by Donald Knuth.
     */
    public static void fillAndShuffle(int[] a) {
        // Fill the array with the integers from 0 to a.length - 1.
        int k;
        for (k = 0; k < a.length; k++) {
            a[k] = k;
        }

        // Shuffle.
        for (k = a.length - 1; k > 0; k--) {
            int swapIndex = (int)Math.floor(Math.random() * (k + 1));
            int temp = a[k];
            a[k] = a[swapIndex];
            a[swapIndex] = temp;
        }
    }

    /**
     * You'll put your experiments for the investigation here.
     * The current contents are just to give you an example.
     */
    public static void main(String[] args) {
        System.err.println("Investigation not yet designed and carried out!");

        //This is just an example of how you might do timing - you can erase
        // it and write your own investigation.
        int[] standardSortArray = new int[1000000];
        fillAndShuffle(standardSortArray);
        long startTime = System.currentTimeMillis();
        Arrays.sort(standardSortArray);
        long endTime = System.currentTimeMillis();
        System.out.println("Array length: " + standardSortArray.length + "; time to sort (ms): " + (endTime-startTime));

    }
}
