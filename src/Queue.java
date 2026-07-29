public class Queue {

    private int[] queue;
    private int front, rear;
    private int capacity;

    public Queue(int size) {
        queue = new int[size];
        front = -1;
        rear = -1;
        capacity = 0;
    }

    public boolean isFull() {
        return capacity == queue.length;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public void add(int num) {
        if(isFull()) {
            System.out.println("!full!");
            return;
        }
        rear = (rear+1) % queue.length;
        capacity++;
        queue[rear] = num;
    }

    public int delete() {
        if(isEmpty()) {
            System.out.println("!empty!");
            return -1;
        }
        front = (front+1) % queue.length;
        capacity--;
        return queue[front];
    }

    public void show() {
        if(isEmpty()) System.out.println("empty");
        else {
            for(int i : queue) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Queue q = new Queue(5);

        q.add(3);
        q.add(5);
        q.add(4);
        q.add(1);
        q.add(9);

        q.add(66);
        q.show();

        System.out.println(q.delete());
        q.add(66);
        q.show();

        System.out.println(q.delete());
        System.out.println(q.delete());
        System.out.println(q.delete());
        System.out.println(q.delete());
        q.add(33);
        q.show();
    }
}
