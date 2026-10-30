public class ArrayQueue<T> implements QueueInterface<T> {
    
    private T[] queue;
    private int size;

    private int front;
    private int back;

    public ArrayQueue() {
        queue = (T[]) new Object[4];
        size = 0;
        front = -1;
        back = -1;
    }

    private int next(int index) {
        if (index == queue.length - 1) {
            return 0;
        }
        return index + 1;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public T add(T element) {

        if (size == queue.length) {
            T[] hold = (T[]) new Object[queue.length * 2];

            int index = 0;
            for (int i = front; i < queue.length; i++) {
                hold[index] = queue[i];
                index++;
            }
            for (int i = 0; i < front; i++) {
                hold[index] = queue[i];
                index++;
            }

            front = 0;
            back = queue.length - 1;

            queue = hold;
        }

        queue[next(back)] = element;

        back = next(back);
        if (size == 0) {
            front = back;
        }
        size++;

        return element;
    }

    public T peek() throws QueueUnderflowException {
        if (size == 0) {
            throw new QueueUnderflowException("No elements in queue.");
        }
        return queue[front];
    }

    public T remove() throws QueueUnderflowException {
        if (size == 0) {
            throw new QueueUnderflowException("No elements in queue.");
        }

        if (size <= queue.length / 4 && queue.length >= 4) {
            T[] hold = (T[]) new Object[queue.length / 2];

            int index = 0;
            if (front < back) {
                for (int i = front; i <= back; i++) {
                    hold[index] = queue[i];
                    index++;
                }
            }
            else if (front > back) {
                for (int i = front; i < queue.length; i++) {
                    hold[index] = queue[i];
                    index++;
                }
                for (int i = 0; i <= back; i++) {
                    hold[index] = queue[i];
                    index++;
                }
            }
            else if (front == back) {
                hold[0] = queue[front];
            }

            front = 0;
            back = size-1;
            queue = hold;
        }

        T element = queue[front];
        queue[front] = null;
        front = next(front);
        size--;

        if (size == 0) {
            front = -1;
            back = -1;
        }

        return element;
    }

    public void clear() {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }
        size = 0;
        front = -1;
        back = -1;
    }
}
