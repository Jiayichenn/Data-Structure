public class LinkedListDeque<T> {

    private IntNode sentinel;
    private int size;

    //Create an empty link list
    public LinkedListDeque() {
        sentinel = new IntNode(null, sentinel, sentinel); //NOT SURE???????????????
        size = 0;
    }

    //Constructor
/*    public LinkedListDeque(T x){
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }*/

    //Define the inner class
    private class IntNode {
        //Variables for the inner class
        private T item;
        private IntNode next;
        private IntNode prev;
        //Constructor for the inner class
        IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    //Add the item to the front of the DLList
    public void addFirst(T item) {
        IntNode head = new IntNode(item, sentinel.next, sentinel);
        if (isEmpty()) {
            sentinel.prev = head;
            head.next = sentinel;
        } else {
            sentinel.next.prev = head;
        }
        sentinel.next = head;
        size += 1;
    }

    //Add the item to the back of the DLList
    public void addLast(T item) {
        IntNode tail = new IntNode(item, sentinel, sentinel);
        if (isEmpty()) {
            sentinel.next = tail;
        } else {
            sentinel.prev.next = tail;
            tail.prev = sentinel.prev;
        }
        sentinel.prev = tail;
        size += 1;
    }

    //Checking if the deque only has the sentinel
    public boolean isEmpty() {
        return size == 0;
    }

    //Return the number of items in deque
    public int size() {
        return size;
    }

    //Print the item from the front to the last in the deque
    public void printDeque() {
        IntNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " "); //NOT SURE?????????????????????
        }
    }

    //Pop out the first item in the deque
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        IntNode target = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return target.item;
    }

    //pop out the last item in the deque
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode target = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return target.item;
    }

    //get the item for the right index
    public T get(int index) {
        IntNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            if (index == 0) {
                return ptr.item;
            }
            index--;
        }
        return null;
    }

    //Get method by using recusion
    public T getRecursive(int index) {
        IntNode result = helper(index, sentinel.next);
        if (result != null) {
            return result.item;
        } else {
            return null;
        }
    }
    //helper method for the GetRecursive method
    private IntNode helper(int j, IntNode p) {
        if (j == 0) {
            return p;
        } else if (p == sentinel) {
            return null;
        } else {
            return helper(j - 1, p.next);
        }
    }
}
