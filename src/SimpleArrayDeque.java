import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Circular Deque Array
 * Memory Complexity: O(size) + O(1) = O(size)
 * @param <T> The type of array elements.
 */
public class SimpleArrayDeque<T> implements SimpleDeque<T> {
    private int wrapper;
    private int maxIndex;
    private int capacity;
    private T[] arr;
    private int left;
    private int right;
    private int size;

    /**
     * Constructs a new array based deque with limited capacity.
     * Time complexity: O(capacity)
     * Memory complexity: O(capacity)
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleArrayDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxIndex = capacity - 1;
        // Handle division by 0.
        if (maxIndex == 0) {
            wrapper = 1;
        } else {
            wrapper = maxIndex;
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.left = 0;
        this.right = -1;
        this.size = 0;
    }

    /**
     * Constructs a new array based deque with limited capacity, and initially populates the deque
     * with the elements of another SimpleDeque.
     * Time complexity: O(capacity + otherDeque.size)
     * Memory complexity: O(capacity)
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleArrayDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
        if ((capacity <= 0) || otherDeque.size() > capacity) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        maxIndex = capacity - 1;
        if (maxIndex == 0) {
            wrapper = 1;
        } else {
            wrapper = maxIndex;
        }
        this.arr = (T[]) new Object[capacity];
        this.left = 0;
        int counter = 0;
        // Iterate through otherDeque and retrieve elements
        Iterator it = otherDeque.iterator();
        while (counter < capacity) {
            arr[counter] = (T) it.next();
            counter++;
        }
        this.right = counter;
        this.size = counter;
    }

    /**
     * Returns if the array is empty.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return True if array is empty false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns if the array is full.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return True if array is full false otherwise.
     */
    @Override
    public boolean isFull() {
        return (size == capacity);
    }

    /**
     * Return the size of the array.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return the size of the array
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds an element to the left of the array.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @param e Element to push
     * @throws RuntimeException
     */
    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        // Update pointer an add element wrapping around the array
        left = (left - 1) % wrapper;
        // Handle negative indices
        if (left < 0) {
            left = maxIndex + (left) % wrapper;
        }
        arr[left] = e;
        size++;
    }

    /**
     * Adds an element to the right of the array.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @param e Element to push
     * @throws RuntimeException
     */
    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        right = (right + 1) % wrapper;
        arr[right] = e;
        size++;
    }

    /**
     * Returns the leftmost element.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[left];
    }

    /**
     * Returns the rightmost element.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[right];
    }

    /**
     * Removes and retrieves the leftmost element.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public T popLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = arr[left];
        arr[left] = null;
        left = (left + 1) % wrapper;
        size--;
        return element;
    }

    /**
     * Removes and retrieves the rightmost element.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public T popRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // Retrieve element and update pointer
        if (right < 0) {
            right = maxIndex + right % wrapper;
        }
        T element = arr[right];
        arr[right] = null;
        right = (right - 1) % wrapper;
        if (right < 0) {
            right = maxIndex + right % wrapper;
        }
        size--;
        return element;
    }

    /**
     * Constructs an iterator of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return an ascending iterator of the deque.
     */
    @Override
    public Iterator<T> iterator() {
        Iterator iterator = new Iterator() {
            // Keep track of the current element being outputted
            int index = left - 1;
            int counter = 0;

            @Override
            public boolean hasNext() {
                return (counter < size() + 1) && (!isEmpty());
            }

            @Override
            public Object next() {
                counter++;
                // Wrap around the array
                index = (index + 1) % wrapper;
                if (index < 0) {
                    index = maxIndex + index % wrapper;
                }
                if (hasNext()) {
                    return arr[index];
                }
                return null;
            }
        };
        return iterator;
    }

    /**
     * Constructs an reversed iterator of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return a descending iterator of the deque.
     */
    @Override
    public Iterator<T> reverseIterator() {
        Iterator iterator = new Iterator() {
            int index = right + 1;
            int counter = 0;

            @Override
            public boolean hasNext() {
                return (counter < size() + 1) && (!isEmpty());
            }

            @Override
            public Object next() {
                counter++;
                index = (index - 1) % wrapper;
                if (index < 0) {
                    index = maxIndex + index % wrapper;
                }
                if (hasNext()) {
                    return arr[index];
                }
                return null;
            }
        };
        return iterator;
    }
}
