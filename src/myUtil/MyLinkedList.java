package myUtil;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by wookie on 6/3/16.
 */
public class MyLinkedList<T> implements List<T>, Queue<T> {
    private int size = 0;
    private class Node<T> {
        private Node next;
        private Node prev;
        private T value;

        private Node() {

        }

        private Node(T value) {
            this.value = value;
        }
    }
    private Node<T> start = new Node<>();
    private Node<T> end = new Node<>();
    private int modCount;

    public MyLinkedList() {
        start.next = end;
        end.prev = start;
    }

    public void push_front(T o) {
        Node<T> temp = start.next;
        start.next = new Node<>(o);
        start.next.prev = start;
        start.next.next = temp;
        temp.prev = start.next;
        size++;
        modCount++;
    }

    public void push_back(T o) {
        Node<T> temp = end.prev;
        end.prev = new Node<>(o);
        end.prev.next = end;
        end.prev.prev = temp;
        temp.next = end.prev;
        size++;
        modCount++;
    }

    public T pop_front() {
        if(size > 0) {
            T value = (T) start.next.value;
            start.next = start.next.next;
            start.next.prev = start;
            size--;
            modCount++;
            return value;
        }
        else
            return null;
    }

    public T pop_back() {
        if(size > 0) {
            T value = (T) end.prev.value;
            end.prev = end.prev.prev;
            end.prev.next = end;
            size--;
            modCount++;
            return value;
        }
        else
            return null;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return pop_front();
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {

    }

    @Override
    public void sort(Comparator<? super T> c) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        this.push_back(t);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private boolean checkIndex(int index) {
        return ((index >= 0) && (index < size));
    }

    private Node<T> getNode(int index) {
        if(!checkIndex(index)) {
            throw new IllegalArgumentException();
        }

        Node<T> node;
        if(index < (double)size/2) {
            node = start.next;
            for(int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        else {
            node = end.prev;
            for(int i = size-1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            Node<T> current = start.next;
            @Override
            public boolean hasNext() {
                return current != end;
            }

            @Override
            public T next() {
                current = current.next;
                return (T)current.prev.value;
            }

            @Override
            public boolean hasPrevious() {
                return current != start;
            }

            @Override
            public T previous() {
                current = current.prev;
                return (T)current.prev.value;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }

            @Override
            public void set(T t) {
                current.value = t;
            }

            @Override
            public void add(T t) {

            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }
}
