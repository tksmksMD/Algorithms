import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ArrayListVsLinkedList {
    public static void main(String[] args) {
        int numberOfElements = 100000; // количество элементов в списке
        int numberOfElementsToInsert = 1000; // количество элементов для вставки

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // заполняем список случайными числами
        fill(arrayList, numberOfElements, "ArrayList");
        fill(linkedList, numberOfElements, "LinkedList");

        // обращение к случайным элементам списка
        randomAccess(arrayList, numberOfElements, "ArrayList");
        randomAccess(linkedList, numberOfElements, "LinkedList");

        // последовательный доступ к элементам списка
        sequentialAccess(arrayList, "ArrayList");
        sequentialAccess(linkedList, "LinkedList");

        // вставка элементов в начало списка
        insertAtBeginning(arrayList, numberOfElementsToInsert, "ArrayList");
        insertAtBeginning(linkedList, numberOfElementsToInsert, "LinkedList");

        // вставка элементов в конец списка
        insertAtEnd(arrayList, numberOfElementsToInsert, "ArrayList");
        insertAtEnd(linkedList, numberOfElementsToInsert, "LinkedList");

        // вставка элементов в середину списка
        insertInMiddle(arrayList, numberOfElementsToInsert, "ArrayList");
        insertInMiddle(linkedList, numberOfElementsToInsert, "LinkedList");
    }

    // метод для заполнения списка случайными числами
    private static void fill(List<Integer> list, int numberOfElements, String listType) {
        long start = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < numberOfElements; i++) {
            list.add(random.nextInt(numberOfElements));
        }
        long end = System.currentTimeMillis();
        System.out.println("Fill " + listType + ": " + (end - start) + " ms");
    }

    // метод для обращения к случайным элементам списка
    private static void randomAccess(List<Integer> list, int numberOfElements, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfElements; i++) {
            int index = (int) (Math.random() * numberOfElements);
            list.get(index);
        }
        long end = System.currentTimeMillis();
        System.out.println("Random access in " + listType + ": " + (end - start) + " ms");
    }

    // метод для последовательного доступа к элементам списка
    private static void sequentialAccess(List<Integer> list, String listType) {
        long start = System.currentTimeMillis();
        for (Integer integer : list) {
            integer++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Sequential access in " + listType + ": " + (end - start) + " ms");
    }

    // метод для вставки элементов в начало списка
    private static void insertAtBeginning(List<Integer> list, int numberOfElementsToInsert, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfElementsToInsert; i++) {
            list.add(0, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert at beginning of " + listType + ": " + (end - start) + " ms");
    }
    // метод для вставки элементов в конец списка
    private static void insertAtEnd(List<Integer> list, int numberOfElementsToInsert, String listType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfElementsToInsert; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert at end of " + listType + ": " + (end - start) + " ms");
    }

    // метод для вставки элементов в середину списка
    private static void insertInMiddle(List<Integer> list, int numberOfElementsToInsert, String listType) {
        long start = System.currentTimeMillis();
        int midIndex = list.size() / 2;
        for (int i = 0; i < numberOfElementsToInsert; i++) {
            list.add(midIndex + i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Insert in middle of " + listType + ": " + (end - start) + " ms");
    }
}