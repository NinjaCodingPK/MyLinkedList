package myUtil;

import java.util.*;

/**
 * Created by wookie on 6/3/16.
 */
public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int modCount;
    private int size;

    private class Node<T> {
        private T data;
        private Node next;
        private Node prev;

        public Node(){}

        public Node(T data) {
            this.data = data;
        }
    }

    public T getFirst() {
        return (T) first.data;
    }

    public T getLast() {
        return (T) last.data;
    }


    public void addFirst(T element) {
        modCount++;
        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            return;
        }
        Node node = new Node(element);
        first.prev = node;
        node.next = first;
        first = node;
        size++;
    }

    public boolean add(T element) {
        modCount++;
        if (first == null) {
            first = new Node(element);
            last = first;
            size++;
            return false;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(element);
        last = temp.next;
        last.prev = temp;
        size++;
        return false;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size()) {
            add(element);
            return;
        }
        modCount++;
        Node temp = getNode(index);

        Node node = new Node(element);
        temp.next.prev = node;
        node.next = temp.next;
        node.prev = temp;
        temp.next = node;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        Node temp = getNode(index);
        return (T) temp.data;
    }

    private Node getNode(int index) {
        int i;
        Node temp;
        if (index < size()/2) {
            i = 0;
            temp = first;
            while (i != index) {
                temp = temp.next;
                i++;
            }
            //* return (T) temp.data;
        } else {    // index >= size() / 2
            i = size() - 1;
            temp = last;
            while (i != index) {
                temp = temp.prev;
                i--;
            }

        }
        return temp;
    }

    public void set(int index, T value) {
        checkIndex(index);
        modCount++;
        Node temp = getNode(index);
        temp.data = value;
    }

    public T removeFirst() {
        if (first == null) {
            throw new UnsupportedOperationException("The list is empty");
        } else if (last == first) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.data;
        }
        modCount++;
        Node temp = first;
        first = first.next;
        first.prev = null;
        size--;
        return (T) temp.data;
    }

    public T removeLast() {
        if (first == null && last == null) {
            throw new UnsupportedOperationException("The list is empty");
        } else if (last == first) {
            size--;
            Node temp = first;
            first = last = null;
            return (T) temp.data;
        }
        modCount++;
        Node temp = last;
        last = last.prev;
        last.next = null;
        size--;
        return (T) temp.data;
    }

    public boolean contains(T element) {
        Node temp = first;
        while (temp != null) {
            if (temp.data.equals(element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void print() {
        Node temp = first;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public ListIterator<T> iterator() {
        return new ListIterator<T>() {
            int modCount = 0;
            Node<T> current = first;
            int currentIndex = 0;
            Node<T> justReturned = null;

            @Override
            public boolean hasNext() {
                return  (current != null && current.next != null);
            }

            @Override
            public T next() {
                justReturned = current;
                current = current.next;
                currentIndex++;

                return (T) justReturned.data;
            }

            @Override
            public boolean hasPrevious() {
                return (current != null && current.prev != null);
            }

            @Override
            public T previous() {
                justReturned = current;
                current = current.prev;
                currentIndex--;
                return (T) justReturned.data;
            }

            @Override
            public int nextIndex() {
                return currentIndex++;
            }

            @Override
            public int previousIndex() {
                return currentIndex--;
            }

            @Override
            public void remove() {
                checkMods();
                modCount++;
                if (justReturned == null) {
                    throw new IndexOutOfBoundsException("Just Returned is null");
                }
                if (justReturned == first) {
                    MyLinkedList.this.removeFirst();
                } else if (justReturned == last) {
                    MyLinkedList.this.removeLast();
                } else {
                    justReturned.next.prev = justReturned.prev;
                    justReturned.prev.next = justReturned.next;
                    justReturned = null;
                }
            }

            @Override
            public void set(T t) {
                checkMods();
                modCount++;
                justReturned.data = t;
            }

            @Override
            public void add(T t) {
                checkMods();
                modCount++;
                if (justReturned == first) {
                    MyLinkedList.this.addFirst(t);
                } else if (justReturned == last) {
                    MyLinkedList.this.add(t);
                } else {
                    Node node = new Node(t);
                    justReturned.next.prev = node;
                    node.next = justReturned.next;
                    justReturned.next = node;
                    node.prev = justReturned;
                }
            }

            private void checkMods() {
                if (modCount != MyLinkedList.this.modCount) throw new ConcurrentModificationException();
            }
        };
    }

}