import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        final int arrSize = scanner.nextInt();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(insertionSort(arr));
    }

    public static int insertionSort(int[] array) {
        /* iterating over elements in the unsorted part */
        int counter = 0;
        for (int i = 1; i < array.length; i++) {
            boolean shifted = false;
            int elem = array[i]; // take the next element
            int j = i - 1;

            /* find a suitable position to insert and shift elements to the right */
            while (j >= 0 && array[j] < elem) {
                array[j + 1] = array[j]; // shifting
                j--;
                shifted = true;
            }
            if (shifted) {
                counter++;
            }
            array[j + 1] = elem; // insert the element in the found position in the sorted part
//            Arrays.stream(array).forEachOrdered(e -> System.out.print(" " + e));
//            System.out.println();
        }
        return counter;
    }
}