import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
public void add(T data) {
    if (data == null) {
        throw new IllegalArgumentException("Data cannot be null!");
    }

    if (size + 1 >= backingArray.length) {
        int newLength = backingArray.length * 2;
        T[] newBackingArray = (T[]) new Comparable[newLength];

        for (int i = 0; i <= size; i++) {
            newBackingArray[i] = backingArray[i];
        }

        backingArray = newBackingArray;
    }

    int currentIndex = size + 1;
    backingArray[currentIndex] = data;

    while (currentIndex > 1) {
        T parentValue = backingArray[currentIndex / 2];
        if (data.compareTo(parentValue) < 0) {
            backingArray[currentIndex] = parentValue;
            backingArray[currentIndex / 2] = data;
            currentIndex /= 2;
        } else {
            break;
        }
    }

    size++;
}

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty!");
        }

        T removeValue = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        int currentIndex = 1;
        boolean shouldBubbleDown = true;
        while (currentIndex <= size && shouldBubbleDown) {
            int leftChildIndex = currentIndex * 2;
            int rightChildIndex = leftChildIndex + 1;
            int minIndex = currentIndex;

            if (leftChildIndex <= size && backingArray[leftChildIndex].compareTo(backingArray[minIndex]) < 0) {
                minIndex = leftChildIndex;
            }
            if (rightChildIndex <= size && backingArray[rightChildIndex].compareTo(backingArray[minIndex]) < 0) {
                minIndex = rightChildIndex;
            }
            if (minIndex != currentIndex) {
                T temp = backingArray[currentIndex];
                backingArray[currentIndex] = backingArray[minIndex];
                backingArray[minIndex] = temp;
                currentIndex = minIndex;
            } else {
                shouldBubbleDown = false;
            }
        }
        return removeValue;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}