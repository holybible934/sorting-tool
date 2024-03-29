import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        final int arrSize = scanner.nextInt();
//        int[] array = IntStream.range(0, arrSize).map(i -> scanner.nextInt()).toArray();
//        System.out.println(mergeSort(array, 0, arrSize));
        System.out.println(arrSize == 0 ? 0 : arrSize - 1);
    }

//    public static int mergeSort(int[] array, int leftIncl, int rightExcl) {
//        // the base case: if subarray contains <= 1 items, stop dividing because it's sorted
//        if (rightExcl <= leftIncl + 1) {
//            return 0;
//        }
//
//        /* divide: calculate the index of the middle element */
//        int middle = leftIncl + (rightExcl - leftIncl) / 2;
//
//        int result = mergeSort(array, leftIncl, middle);  // conquer: sort the left subarray
//        result += mergeSort(array, middle, rightExcl); // conquer: sort the right subarray
//
//        /* combine: merge both sorted subarrays into sorted one */
//        merge(array, leftIncl, middle, rightExcl);
//        return ++result;
//    }
//
//    private static void merge(int[] array, int left, int middle, int right) {
//        int i = left;   // index for the left subarray
//        int j = middle; // index for the right subarray
//        int k = 0;      // index for the temp subarray
//
//        int[] temp = new int[right - left]; // temporary array for merging
//
//    /* get the next lesser element from one of two subarrays
//       and then insert it in the array until one of the subarrays is empty */
//        while (i < middle && j < right) {
//            if (array[i] <= array[j]) {
//                temp[k] = array[i];
//                i++;
//            } else {
//                temp[k] = array[j];
//                j++;
//            }
//            k++;
//        }
//
//        /* insert all the remaining elements of the left subarray in the array */
//        for (; i < middle; i++, k++) {
//            temp[k] = array[i];
//        }
//
//        /* insert all the remaining elements of the right subarray in the array */
//        for (; j < right; j++, k++) {
//            temp[k] = array[j];
//        }
//
//        /* effective copying elements from temp to array */
//        System.arraycopy(temp, 0, array, left, temp.length);
//    }
}