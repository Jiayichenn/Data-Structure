public class ArrayDeque<T> {

    //stuff = (T[]) new Object[size];
    private T[] item;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int CAP = 8;
    public ArrayDeque() {
        item = (T[]) new Object[CAP];
        size = 0;
        nextLast = 4;
        nextFirst = 3;
    }

    public void printDeque() {
        int count = 0;
        int s = size;
        if (s != 0) {
            while (count < size) {
                System.out.print(get(count) + " ");
                count++;
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeMore(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (nextLast == 0 && nextFirst == size - 1) { //the case of not circular yet
            // no need to limit nextFirst, but i did it anyway
            nextFirst = capacity / 2 - size / 2 - 1;
            nextLast = nextFirst + size + 1;
            System.arraycopy(item, 0, temp, nextFirst + 1, size);
        } else {
            System.arraycopy(item, 0, temp, 0, nextLast);
            nextFirst = capacity - (size - nextFirst);
            System.arraycopy(item, nextLast, temp, nextFirst + 1, size - nextLast);
        }
        item = temp;
        /*int remember_theFirst = 0;
        if (nextFirst != item.length - 1) {
            remember_theFirst = nextFirst + 1;
        }
        nextFirst = capacity/2-1-size/2;
        nextLast = nextFirst+size+1;
        System.arraycopy(item, 0, temp, nextFirst+1, size);
        item = temp;*/
    }

    private void resizeLess(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(item, nextFirst + 1, temp, 0, size);
            nextFirst = capacity - 1;
            nextLast = size;
        } else {
            System.arraycopy(item, 0, temp, 0, nextLast);
            int futureNextFirst = capacity - 1 - (size - nextLast);
            if (nextFirst != item.length - 1) {
                System.arraycopy(item, nextFirst + 1, temp, futureNextFirst + 1, size - nextLast);
                nextFirst = futureNextFirst;
            } else {
                nextFirst = capacity - 1;
            }
        }
        item = temp;
       /* System.arraycopy(item, start, temp, 0, size);
        nextFirst = capacity - 1;
        nextLast = size;
        item = temp;*/
    }

    private void resizeZero(int capacity) {
        if (capacity >= 2) {
            T[] temp = (T[]) new Object[capacity];
            item = temp;
            nextLast = capacity / 2;
            nextFirst = capacity / 2 - 1;
        }
    }

    public void addLast(T x) {
        if (size == item.length) {
            resizeMore(size * 2);
        }
        item[nextLast] = x;
        size++;
        if (nextLast == (item.length - 1)) {
            nextLast = 0;
        } else {
            nextLast++;
        }
    }

    public void addFirst(T x) {
        if (size == item.length) {
            resizeMore(size * 2);
        }
        item[nextFirst] = x;
        size++;
        if (nextFirst == 0) {
            nextFirst = item.length - 1;
        } else {
            nextFirst--;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (nextFirst == (item.length - 1)) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        T result = item[nextFirst];
        item[nextFirst] = null;
        size--;
        //checking if i need to reduce size
        double ratio = (double) size / (item.length);
        if (ratio < 0.25 && ratio > 0) {
            resizeLess((item.length) / 2);
        } else if (ratio == 0) {
            resizeZero((item.length) / 2);
        }
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = item.length - 1;
        } else {
            nextLast--;
        }
        T result = item[nextLast];
        item[nextLast] = null;
        size--;
        //checking if i need to reduce size
        double ratio = (double) size / (item.length);
        if (ratio < 0.25 && ratio > 0) {
            resizeLess((item.length) / 2);
        } else if (ratio == 0) {
            resizeZero((item.length) / 2);
        }
        return result;
    }

    public T get(int i) {
        if (i < 0 || i > (size - 1)) {
            return null;
        }
        int rightIndex = (i + 1 + nextFirst) % (item.length);
        return item[rightIndex];
    }

    public int size() {
        return size;
    }
}
