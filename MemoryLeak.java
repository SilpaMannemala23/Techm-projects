import java.io.*;
import java.util.*;

public class MemoryLeak {

    private static List<BufferedReader> resourceList = new ArrayList<>();
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            resourceList.add(reader);  
        }
        for (int i = 0; i < 10; i++) {
            monitorMemoryUsage();
            Thread.sleep(1000);
        }
    }
    
    private static void monitorMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        System.out.println("Total Memory: " + totalMemory / 1024 + " KB");
        System.out.println("Free Memory: " + freeMemory / 1024 + " KB");
    }
}
