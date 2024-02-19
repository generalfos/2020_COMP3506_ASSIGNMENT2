import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversibleDeque<T> implements SimpleDeque<T> {
    SimpleDeque<T> deque;

    /**
     * Constructs a new reversible deque, using the given data deque to store
     * elements.
     * The data deque must not be used externally once this ReversibleDeque
     * is created.
     * Time complexity: O(1)
     * Memory complexity: O(1) (stored reference to data deque)
     * @param data a deque to store elements in.
     */
    public ReversibleDeque(SimpleDeque<T> data) {
        this.deque = data;
    }

    /**
     * Reverses the order of the elements in the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     */
    public void reverse() {
        T[] arr = (T[]) new Object[deque.size()];
        int counter = 0;
        // Remove and store elements from the array
        while (deque.size() > 0) {
            arr[counter] = deque.popRight();
            counter++;
        }
        // Add elements into the array through the right
        for (T element : arr) {
            deque.pushRight(element);
        }
    }

    /**
     * Returns the number of elements currently stored in the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return Number of elements.
     */
    @Override
    public int size() {
        return deque.size();
    }

    /**
     * Returns whether the deque is empty.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return true if the deque is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    /**
     * Returns the number of elements currently stored in the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return Number of elements.
     */
    @Override
    public boolean isFull() {
        return deque.isFull();
    }

    /**
     * Pushes an element to the left of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @param e Element to push
     * @throws RuntimeException if the deque is already full
     */
    @Override
    public void pushLeft(T e) throws RuntimeException {
        deque.pushLeft(e);
    }

    /**
     * Pushes an element to the right of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @param e Element to push
     * @throws RuntimeException if the deque is already full
     */
    @Override
    public void pushRight(T e) throws RuntimeException {
        deque.pushRight(e);
    }

    /**
     * Returns the element at the left of the deque, but does not remove it.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @returns the leftmost element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T peekLeft() throws NoSuchElementException {
        return deque.peekLeft();
    }

    /**
     * Returns the element at the right of the deque, but does not remove it.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @returns the rightmost element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T peekRight() throws NoSuchElementException {
        return deque.peekRight();
    }

    /**
     * Removes and returns the element at the left of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @returns the leftmost element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T popLeft() throws NoSuchElementException {
        return deque.popLeft();
    }

    /**
     * Removes and returns the element at the right of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @returns the rightmost element
     * @throws NoSuchElementException if the deque is empty
     */
    @Override
    public T popRight() throws NoSuchElementException {
        return deque.popRight();
    }

    /**
     * Returns an iterator for the deque in left to right sequence.
     * Time complexity: Dependent on implementation (at most O(n^2))
     * Memory complexity: O(1)
     * @returns an iterator over the elements in in order from leftmost to rightmost.
     */
    @Override
    public Iterator<T> iterator() {
        return deque.iterator();
    }

    /**
     * Returns an iterator for the deque in right to left sequence.
     * Time complexity: Dependent on implementation (at most O(n^2)).
     * Memory complexity: O(1)
     * @returns an iterator over the elements in in order from rightmost to leftmost.
     */
    @Override
    public Iterator<T> reverseIterator() {
        return deque.reverseIterator();
    }
}
