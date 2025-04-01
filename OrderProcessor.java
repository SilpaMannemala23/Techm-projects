import java.util.concurrent.BlockingQueue;

public class OrderProcessor implements Runnable {
    private final BlockingQueue<Order> orderQueue;
    private volatile boolean running = true;  

    public OrderProcessor(BlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Order order = orderQueue.take();
                System.out.println("Processing " + order);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Order Processor has stopped.");
    }
    public void stopProcessing() {
        running = false;
    }
}
