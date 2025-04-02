import java.util.concurrent.BlockingQueue;
public class Customer implements Runnable {
    private static int orderCounter = 0;
    private final BlockingQueue<Order> orderQueue;
    private final String customerName;

    public Customer(BlockingQueue<Order> orderQueue, String customerName) {
        this.orderQueue = orderQueue;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            
            Thread.sleep((long) (Math.random() * 1000)); 
            Order newOrder = new Order(++orderCounter, customerName);
            System.out.println(customerName + " placed " + newOrder);
            orderQueue.put(newOrder); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
