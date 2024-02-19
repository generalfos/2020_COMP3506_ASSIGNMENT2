import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque using a linked list.
 * Memory Complexity: O(3*size) + O(1) = O(size)
 * @param <T> The type of array elements.
 */
public class SimpleLinkedDeque<T> implements SimpleDeque<T> {
    private Link first;
    private Link last;
    private int size;
    private int capacity;

    /**
     * Constructs a new linked list based deque with unlimited capacity.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     */
    public SimpleLinkedDeque() {
        first = last = null;
        size = 0;
        capacity = -1;
    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     * Time complexity: O(1)
     * Memory complexity: O(capacity)
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        first = last = null;
        size = 0;
        this.capacity = capacity;
    }

    /**
     * Constructs a new linked list based deque with unlimited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     * Time complexity: O(size * time of otherDeque.iterator().next())
     * Memory complexity: O(size)
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @requires otherDeque != null
     */
    public SimpleLinkedDeque(SimpleDeque<? extends T> otherDeque) {
        capacity = -1;
        this.size = otherDeque.size();
        Iterator it = otherDeque.iterator();
        // Loop over the elements in otherDeque
        for (int i = 0; i < size; i++) {
            T element = (T) it.next();
            Link temp = new Link(element);
            // Handle adding the first, second and kth element
            if (i == 0) {
                first = temp;
                first.updatePrev(null);
            } else if (i == 1) {
                last = temp;
                first.updateNext(last);
                last.updatePrev(first);
                last.updateNext(null);
            } else {
                // Add the link to the chain
                Link temp2 = last;
                // Update pointers of the link and its neighbours
                last.updateNext(temp);
                last = temp;
                last.updateNext(null);
                last.updatePrev(temp2);
            }
        }
    }
    
    /**
     * Constructs a new linked list based deque with limited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     * Time complexity: O(size * time of otherDeque.iterator().next())
     * Memory complexity: O(size)
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleLinkedDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
        if (capacity <= 0 || otherDeque.size() > capacity) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.size = otherDeque.size();
        Iterator it = otherDeque.iterator();
        for (int i = 0; i < size; i++) {
            T element = (T) it.next();
            Link temp = new Link(element);
            if (i == 0) {
                first = temp;
                first.updatePrev(null);
            } else if (i == 1) {
                last = temp;
                first.updateNext(last);
                last.updatePrev(first);
                last.updateNext(null);
            } else {
                Link temp2 = last;
                last.updateNext(temp);
                last = temp;
                last.updateNext(null);
                last.updatePrev(temp2);
            }
        }
    }

    /**
     * Returns whether the deque is empty.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return true if the deque is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements currently stored in the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return Number of elements.
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Returns the number of elements currently stored in the deque.
     * Time complexity: O(1)
     * Memory complexity: O(1)
     * @return Number of elements.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Pushes an element to the left of the deque.
     * Time complexity: O(1)
     * Memory complexity: O(6) = O(1)
     * @param e Element to push
     * @throws RuntimeException if the deque is already full
     */
    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        // Handle adding the first, second and kth element
        if (first == null) {
            first = new Link(e);
            first.updatePrev(null);
            if (last != null) {
                first.updateNext(last);
                last.updatePrev(first);
            }
        } else if (last == null) {
            last = first;
            last.updateNext(null);
            first = new Link(e);
            first.updatePrev(null);
            first.updateNext(last);
            last.updatePrev(first);
        } else {
            Link temp = first;
            first = new Link(e);
            // Update relevant pointers
            first.updatePrev(null);
            first.updateNext(temp);
            temp.updatePrev(first);
        }
        size++;
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
        if (isFull()) {
            throw new RuntimeException();
        }
        if (last == null) {
            last = new Link(e);
            last.updateNext(null);
            if (first != null) {
                last.updatePrev(first);
            }
        } else if (first == null) {
            first = last;
            first.updatePrev(null);
            last = new Link(e);
            last.updateNext(null);
            last.updatePrev(first);
            first.updateNext(last);
        } else {
            Link temp = last;
            last = new Link(e);
            last.updatePrev(temp);
            last.updateNext(null);
            temp.updateNext(last);
        }
        size++;
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
        if (size() <= 0) {
            throw new NoSuchElementException();
        } else if (!(this.getFirst() == null)) {
            return  this.getFirst().element;
        } else {
            return this.getLast().element;
        }
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
        if (size() <= 0) {
            throw new NoSuchElementException();
        } else if (!(this.getLast() == null)) {
            return this.getLast().element;
        } else {
            return this.getFirst().element;
        }
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
        if (size() <= 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            T element = this.getLast().element;
            first = null;
            last = null;
            size--;
            return element;
        }
        T element = this.getFirst().element;
        first = this.getFirst().next;
        size--;
        // Handle the case where all elements have been removed
        if (size() > 0) {
            first.updatePrev(null);
        }
        return element;
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
        if (size() <= 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            T element = this.getFirst().element;
            first = null;
            last = null;
            size--;
            return element;
        }
        T element = this.getLast().element;
        last = this.getLast().prev;
        last.updateNext(null);
        size--;
        if (size() > 0) {
            first.updatePrev(null);
        }
        return element;
    }

    /**
     * Returns an iterator for the deque in left to right sequence.
     * Time complexity: O(size^2) (for each call to next())
     * Memory complexity: O(1)
     * @returns an iterator over the elements in in order from leftmost to rightmost.
     */
    @Override
    public Iterator<T> iterator() {
        Iterator iterator = new Iterator() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return (counter < size()) && (!isEmpty());
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    Link temp = getFirst();
                    if (temp == null) {
                        counter++;
                        return getLast().element;
                    }
                    for (int i = 0; i < counter; i++) {
                        if (temp.next == null) {
                            return temp.element;
                        }
                        temp = temp.next;
                    }
                    // Update pointer
                    counter++;
                    return temp.element;
                }
                throw new NoSuchElementException();
            }
        };
        return iterator;
    }


    /**
     * Returns an iterator for the deque in right to left sequence.
     * Time complexity: O(size^2) (for each call to next())
     * Memory complexity: O(1)
     * @returns an iterator over the elements in in order from rightmost to leftmost.
     */
    @Override
    public Iterator<T> reverseIterator() {
        Iterator iterator = new Iterator() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return (counter < size()) && (!isEmpty());
            }

            @Override
            public Object next() {
                if (hasNext()) {
                    Link temp = getLast();
                    if (temp == null) {
                        counter++;
                        return getFirst().element;
                    }
                    for (int i = 0; i < counter; i++) {
                        temp = temp.prev;
                    }
                    counter++;
                    return temp.element;
                }
                throw new NoSuchElementException();
            }
        };

        return iterator;
    }

    private Link getFirst() {
        return first;
    }

    private Link getLast() {
        return last;
    }

    private class Link {
        T element;
        Link next;
        Link prev;

        private Link(T element) {
            this.element = element;
            next = null;
            prev = null;
        }

        private void updateNext(Link next) {
            this.next = next;
        }

        private void updatePrev(Link prev) {
            this.prev = prev;
        }

        private void updateElement(T element) {
            this.element = element;

        }
    }
}
