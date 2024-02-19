import java.util.Random;

public class TimeAlgorithms {

    public static void main(String[] args) {
        int lengths[] = {5, 10, 50, 100, 500, 1000, 10000};
        long[][] unsortedResults = unsortedTests(lengths);
        long[][] sortedAscendingResults = sortedAscendTests(lengths);
        long[][] sortedDescendingResults = sortedDescendTests(lengths);
        return;
    }

    private static long[][] unsortedTests(int[] lengths) {
        long[][] unsortedResults = new long[lengths.length][4];
        int count = 0;
        for (int n : lengths) {
            // Select Forward
            Integer[] arr = initialiseRandomArray(n);
            unsortedResults[count][0] = timeSelectForward(arr);
            // Insert Forward
            arr = initialiseRandomArray(n);
            unsortedResults[count][1] = timeInsertForward(arr);
            // Merge Forward
            arr = initialiseRandomArray(n);
            unsortedResults[count][2] = timeMergeForward(arr);
            // Quick Forward
            arr = initialiseRandomArray(n);
            unsortedResults[count][3] = timeQuickForward(arr);
            count++;
        }
        return unsortedResults;
    }

    private static long[][] sortedAscendTests(int[] lengths) {
        long[][] sortedAscendingResults = new long[lengths.length][4];
        int count = 0;
        for (int n : lengths) {
            // Select Forward
            Integer[] arr = initialiseAscendingArray(n);
            sortedAscendingResults[count][0] = timeSelectForward(arr);
            // Insert Forward
            arr = initialiseAscendingArray(n);
            sortedAscendingResults[count][1] = timeInsertForward(arr);
            // Merge Forward
            arr = initialiseAscendingArray(n);
            sortedAscendingResults[count][2] = timeMergeForward(arr);
            // Quick Forward
            arr = initialiseAscendingArray(n);
            sortedAscendingResults[count][3] = timeQuickForward(arr);
            count++;
        }
        return sortedAscendingResults;
    }

    private static long[][] sortedDescendTests(int[] lengths) {
        long[][] sortedDescendingResults = new long[lengths.length][4];
        int count = 0;
        for (int n : lengths) {
            // Select Forward
            Integer[] arr = initialiseDescendingArray(n);
            sortedDescendingResults[count][0] = timeSelectForward(arr);
            // Insert Forward
            arr = initialiseDescendingArray(n);
            sortedDescendingResults[count][1] = timeInsertForward(arr);
            // Merge Forward
            arr = initialiseDescendingArray(n);
            sortedDescendingResults[count][2] = timeMergeForward(arr);
            // Quick Forward
            arr = initialiseDescendingArray(n);
            sortedDescendingResults[count][3] = timeQuickForward(arr);
            count++;
        }
        return sortedDescendingResults;
    }

    private static long timeSelectForward(Integer arr[]) {
        long start = System.nanoTime();
        SortingAlgorithms.selectionSort(arr, false);
        long end = System.nanoTime();
        return (long) ((end - start) * 0.001);
    }

    private static long timeInsertForward(Integer arr[]) {
        long start = System.nanoTime();
        SortingAlgorithms.insertionSort(arr, false);
        long end = System.nanoTime();
        return (long) ((end - start) * 0.001);
    }

    private static long timeMergeForward(Integer arr[]) {
        long start = System.nanoTime();
        SortingAlgorithms.mergeSort(arr, false);
        long end = System.nanoTime();
        return (long) ((end - start) * 0.001);
    }

    private static long timeQuickForward(Integer arr[]) {
        long start = System.nanoTime();
        SortingAlgorithms.quickSort(arr, false);
        long end = System.nanoTime();
        return (long) ((end - start) * 0.001);
    }

    private static Integer[] initialiseRandomArray(int length) {
        Integer[] arr = new Integer[length];
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rd.nextInt(length);
        }
        return arr;
    }

    private static Integer[] initialiseAscendingArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static Integer[] initialiseDescendingArray(int length) {
        Integer[] arr = new Integer[length];
        int j = 0;
        for (int i = length - 1; i >= 0; i--) {
            arr[j] = i;
            j++;
        }
        return arr;
    }
}
