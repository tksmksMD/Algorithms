import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] sizes = {10,1000,10000,100000,1000000}; // розміри масивів для сортування
        for (int size : sizes) {
            ArrayList<Integer> arr = generateArray(size);
            for (SortingType type : SortingType.values()) {
                Sorter sorter = SortFactory.createSorter(type);
                ArrayList<Integer> sortedArr = measureSortingTime(arr, sorter);
                if (sortedArr.size() > 100) {
                    System.out.println("Sorted by " + type.toString() + " algorithm: " + sortedArr.subList(0, 50).toString() + "...");
                } else {
                    System.out.println("Sorted by " + type.toString() + " algorithm: " + sortedArr.toString());
                }
            }
            System.out.println("---------------------------------------------------");
        }
    }
    private static ArrayList<Integer> generateArray(int size) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < size) {
            uniqueNumbers.add(rand.nextInt(size));
        }
        arr.addAll(uniqueNumbers);
        if (size >= 100) {
            System.out.println("First 50 elements of generated array: " + arr.subList(0, 50).toString() + "...");
        } else {
            System.out.println("Generated array: " + arr.toString());
        }
        return arr;
    }


    private static ArrayList<Integer> measureSortingTime(ArrayList<Integer> arr, Sorter sorter) {
        long startTime = System.nanoTime();
        ArrayList<Integer> sortedArr = sorter.sort(arr);
        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime);
        System.out.println(sorter.getClass().getSimpleName() + " algorithm execution time: " + executionTime + " ns");
        return sortedArr;
    }

    interface Sorter {
        ArrayList<Integer> sort(ArrayList<Integer> input);
    }

    class BubbleSorter implements Sorter {
        private static final int THRESHOLD = 10000;
        public ArrayList<Integer> sort(ArrayList<Integer> input) {
            ArrayList<Integer> arr = new ArrayList<Integer>(input);
            if (arr.size() > THRESHOLD) {
                ArrayList<Integer> left = new ArrayList<Integer>(arr.subList(0, arr.size() / 2));
                ArrayList<Integer> right = new ArrayList<Integer>(arr.subList(arr.size() / 2, arr.size()));
                left = sort(left);
                right = sort(right);
                arr = merge(left, right);
            }
            else {
                for (int i = 0; i < arr.size() - 1; i++) {
                    for (int j = 0; j < arr.size() - 1; j++) {
                        if (arr.get(j) > arr.get(j + 1)) {
                            int temp = arr.get(j);
                            arr.set(j, arr.get(j + 1));
                            arr.set(j + 1, temp);
                        }
                    }
                }
            }
            return arr;
        }

        private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int i = 0, j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    result.add(left.get(i));
                    i++;
                } else {
                    result.add(right.get(j));
                    j++;
                }
            }
            while (i < left.size()) {
                result.add(left.get(i));
                i++;
            }
            while (j < right.size()) {
                result.add(right.get(j));
                j++;
            }
            return result;
        }
    }

    static class ShellSorter implements Sorter {
        public ArrayList<Integer> sort(ArrayList<Integer> input) {
            int n = input.size();
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = input.get(i);
                    int j;
                    for (j = i; j >= gap && input.get(j - gap) > temp; j -= gap) {
                        input.set(j, input.get(j - gap));
                    }
                    input.set(j, temp);
                }
            }
            return input;
        }
    }

    static class MergeSorter implements Sorter {
        private static final int THRESHOLD = 1000;

        public ArrayList<Integer> sort(ArrayList<Integer> input) {
            if (input.size() < 2) {
                return input;
            }
            if (input.size() <= THRESHOLD) {
                return insertionSort(input);
            }

            int mid = input.size() / 2;
            ArrayList<Integer> left = new ArrayList<>(input.subList(0, mid));
            ArrayList<Integer> right = new ArrayList<>(input.subList(mid, input.size()));
            left = sort(left);
            right = sort(right);
            return merge(left, right);
        }

        private ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
            ArrayList<Integer> result = new ArrayList<>();
            int leftIndex = 0;
            int rightIndex = 0;
            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (left.get(leftIndex) < right.get(rightIndex)) {
                    result.add(left.get(leftIndex));
                    leftIndex++;
                } else {
                    result.add(right.get(rightIndex));
                    rightIndex++;
                }
            }
            while (leftIndex < left.size()) {
                result.add(left.get(leftIndex));
                leftIndex++;
            }
            while (rightIndex < right.size()) {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
            return result;
        }

        private ArrayList<Integer> insertionSort(ArrayList<Integer> input) {
            for (int i = 1; i < input.size(); i++) {
                int key = input.get(i);
                int j = i - 1;
                while (j >= 0 && input.get(j) > key) {
                    input.set(j + 1, input.get(j));
                    j--;
                }
                input.set(j + 1, key);
            }
            return input;
        }
    }

    static class QuickSorter implements Sorter {
        public ArrayList<Integer> sort(ArrayList<Integer> input) {
            if (input.size() < 2) {
                return input;
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            stack.push(input.size());
            while (!stack.isEmpty()) {
                int end = stack.pop();
                int start = stack.pop();
                if (end - start < 2) {
                    continue;
                }
                int pivot = input.get(start + ((end - start) / 2));
                int left = start;
                int right = end - 1;
                while (left <= right) {
                    while (left <= right && input.get(left) < pivot) {
                        left++;
                    }
                    while (left <= right && input.get(right) > pivot) {
                        right--;
                    }
                    if (left <= right) {
                        Collections.swap(input, left, right);
                        left++;
                        right--;
                    }
                }
                stack.push(start);
                stack.push(right + 1);
                stack.push(left);
                stack.push(end);
            }
            return input;
        }
    }

    enum SortingType {
        BUBBLE_SORT,
        SHELL_SORT,
        MERGE_SORT,
        QUICK_SORT
    }

    static class SortFactory {
        public static Sorter createSorter(SortingType type) {
            switch (type) {
                case BUBBLE_SORT:
                    return new Main().new BubbleSorter();
                case SHELL_SORT:
                    return new ShellSorter();
                case MERGE_SORT:
                    return new MergeSorter();
                case QUICK_SORT:
                    return new QuickSorter();
                default:
                    throw new IllegalArgumentException("Invalid sorting type");
            }
        }
    }
}