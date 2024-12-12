import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int retrievalRate;
    private int retrievalCount;
    private int customerId;
    private AtomicBoolean running;

    public Customer(TicketPool ticketPool, int retrievalRate, int retrievalCount, int customerId, AtomicBoolean running) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.retrievalCount = retrievalCount;
        this.customerId = customerId;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) {
            ticketPool.purchaseTickets(retrievalCount);
            logTicketPurchase(retrievalCount); // Log ticket purchase
            try {
                Thread.sleep(retrievalRate); // Wait for the specified rate before purchasing again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle thread interruption gracefully
                break; // Exit the loop if interrupted
            }
        }
    }

    private void logTicketPurchase(int retrievalCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("system_logs.txt", true))) {
            writer.write(String.format("Customer-%d purchased %d tickets.%n", customerId, retrievalCount));
        } catch (IOException e) {
            System.err.println("Error logging customer ticket purchase: " + e.getMessage());
        }
    }
}


