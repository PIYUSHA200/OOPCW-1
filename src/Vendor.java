import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Vendor implements Runnable {
    private TicketPool ticketPool;
    private int releaseRate;
    private int releaseCount;
    private int vendorId;
    private AtomicBoolean running;

    public Vendor(TicketPool ticketPool, int releaseRate, int releaseCount, int vendorId, AtomicBoolean running) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.releaseCount = releaseCount;
        this.vendorId = vendorId;
        this.running = running;
    }

    @Override
    public void run() {
        while (running.get()) { // Check the running flag
            ticketPool.addTickets(releaseCount);
            logTicketRelease(releaseCount); // Log ticket release
            try {
                Thread.sleep(releaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Handle thread interruption gracefully
                break; // Exit the loop if interrupted
            }
        }
    }

    private void logTicketRelease(int releaseCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("system_logs.txt", true))) {
            writer.write(String.format("Vendor-%d released %d tickets.%n", vendorId, releaseCount));
        } catch (IOException e) {
            System.err.println("Error logging vendor ticket release: " + e.getMessage());
        }
    }
}

