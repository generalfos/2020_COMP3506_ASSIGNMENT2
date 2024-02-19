public class SortingAlgorithms {
    /**
     * Sorts the given array using the selection sort algorithm.
     * This should modify the array sin-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void selectionSort(T[] input, boolean reversed) {
        int n = input.length;
        // Iterate over the array
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Find the minimum element in the subarray i+1...n
            for (int j = i + 1; j < n; j++) {
                if (reversed ? (input[minIndex].compareTo(input[j]) < 0) :
                        input[minIndex].compareTo(input[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                // Move the min to its position
                swap(input, minIndex, i);
            }
        }
    }

    /**
     * Sorts the given array using the insertion sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void insertionSort(T[] input, boolean reversed) {
        int n = input.length;
        T valueToInsert;
        int holePosition;
        for (int i = 0; i < n; i++) {
            valueToInsert = input[i];
            holePosition = i;
            // Find position for value to be inserted
            while ((holePosition > 0)
                   && (reversed ?
                    (input[holePosition - 1].compareTo(valueToInsert) < 0) :
                    (input[holePosition - 1].compareTo(valueToInsert) > 0))) {
                input[holePosition] = input[holePosition - 1];
                holePosition = holePosition - 1;
            }
            // Insert the value at its correct position
            input[holePosition] = valueToInsert;
        }
    }

    /**
     * Sorts the given array using the merge sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void mergeSort(T[] input, boolean reversed) {
        int n = input.length;
        if (n <= 1) {
            return;
        }
        mergeSortRecursive(input, 0, n - 1, reversed);
    }

    private static <T extends Comparable> void mergeSortRecursive(T[] input, int start,
                                                                  int end, boolean reversed) {
        if (input.length <= 1) {
            return;
        }
        if (start < end) {
            int mid = (start + end) / 2;
            // Recursive calls covering both halves of the array
            mergeSortRecursive(input, start, mid, reversed);
            mergeSortRecursive(input, mid + 1, end, reversed);
            merge(input, start, mid, end, reversed);
        }
    }

    private static <T extends Comparable> void merge(T[] input, int start1,
                                                     int mid, int end, boolean reversed) {
        int start2 = mid + 1;
        while ((start1 <= mid) && (start2 <= end)) {
            if (reversed ? (input[start1].compareTo(input[start2]) >= 0) :
                    (input[start1].compareTo(input[start2]) <= 0)) {
                start1++;
            } else {
                int index = start2;
                T val = input[index];
                while (index != start1) {
                    input[index] = input[index - 1];
                    index--;
                }
                input[start1] = val;
                start1++;
                start2++;
                mid++;
            }
        }
    }

    /**
     * Sorts the given array using the quick sort algorithm.
     * This should modify the array in-place.
     *
     * You should use the value at the middle of the input  array(i.e. floor(n/2))
     * as the pivot at each step.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void quickSort(T[] input, boolean reversed) {
        int l = 0;
        int r = input.length - 1;
        inPlaceQuickSort(input, reversed, l, r);
    }

    private static <T extends Comparable> void inPlaceQuickSort(T[] input,
                                                                boolean reversed,
                                                                int l, int  r) {
        // Handle the base case
        if (l >= r) {
            return;
        }
        // Get new partition index.
        int partitionIndex = partition(input, l, r, reversed);
        // Call quick sort on the sub arrays
        inPlaceQuickSort(input, reversed, l, partitionIndex - 1);
        inPlaceQuickSort(input, reversed, partitionIndex + 1, r);
    }

    private static <T extends Comparable> int partition(T[] input, int l,
                                                               int r,
                                                               boolean reversed) {
        int pivot = l + (r - l)/2;
        T val = input[pivot];
        // Shift the pivot value to the rightmost index
        swap(input, pivot, r);
        int i = l - 1;
        for (int j = l; j < r; j++) {
            /* If the current element is greater than or equal to pivot move it
            to the lowest available index */
            if (reversed ? input[j].compareTo(val) >= 0 :
                    input[j].compareTo(val) <= 0) {
                i++;
                swap(input, i, j);
            }
        }
        i++;
        // Move the pivot element to its correct position
        swap(input, i, r);
        return i;
    }

    private static <T> void swap(T[] input, int index1, int index2) {
        T temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }
}
