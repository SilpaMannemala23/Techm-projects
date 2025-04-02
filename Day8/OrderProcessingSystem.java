import java.util.concurrent.*;

public class OrderProcessingSystem {
    private static final int NUM_CUSTOMERS = 5;
    private static final int NUM_ORDERS = 20;

    public static void main(String[] args) {
        
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(10);

        OrderProcessor orderProcessor = new OrderProcessor(orderQueue);
        Thread processorThread = new Thread(orderProcessor);
        processorThread.start();

        
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            new Thread(new Customer(orderQueue, "Customer_" + (i + 1))).start();
        }

        
        try {
            Thread.sleep(10000);  
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        System.out.println("Shutting down the system...");
        orderProcessor.stopProcessing();
        try {
            processorThread.join(); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("System shut down.");
    }
}
