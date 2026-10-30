public class LinkedQueue<T> implements QueueInterface<T> {
    
    LLNode<T> front;
    LLNode<T> back;
    int size;

    public LinkedQueue() {
        front = new LLNode<T>(null);
        back = new LLNode<T>(null);
        back.setLink(front);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public T add(T element) {

        if (size == 0) {
            front.setInfo(element);
        }
        else if (size == 1) {
            back.setInfo(element);
        }
        else {
            LLNode<T> next = new LLNode(element);
            next.setLink(back);
            back = next;
        }

        size++;

        return element;
    }

    public T peek() throws QueueUnderflowException {
        if (size == 0) {
            throw new QueueUnderflowException("No elements in queue.");
        }
        return front.getInfo();
    }

    public T remove() throws QueueUnderflowException {
        if (size == 0) {
            throw new QueueUnderflowException("No elements in queue.");
        }

        T element = front.getInfo();

        if (size == 1) {
            front.setInfo(null);
            size--;
            return element;
        }
        else if (size == 2) {
            front.setInfo(back.getInfo());
            back.setInfo(null);
            size--;
            return element;
        }
        
        LLNode<T> current = back;
        while (current.getLink() != front) {
            current = current.getLink();
        }

        current.setLink(null);
        front = current;

        size--;

        return element;
    }

    public void clear() {
        front = new LLNode<T>(null);
        back = new LLNode<T>(null);
        back.setLink(front);
        size = 0;
    }
}
