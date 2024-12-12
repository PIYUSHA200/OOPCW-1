import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private int maxTicketCapacity;
    private Queue<Integer> tickets; // A queue to represent the tickets in the pool
    private int ticketCounter; // A counter to generate unique ticket IDs

    public TicketPool(int maxTicketCapacity, int totalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
        this.ticketCounter = 1;

        // Initialize the tickets in the pool
        for (int i = 0; i < totalTickets; i++) {
            tickets.add(ticketCounter++);
        }
    }

    public synchronized void addTickets(int ticketsToAdd) {
        int added = 0;
        while (added < ticketsToAdd && tickets.size() < maxTicketCapacity) {
            tickets.add(ticketCounter++); // Add new ticket with unique ID to the pool
            added++;
        }

        if (added > 0) {
            System.out.println("Vendor added " + added + " tickets. Total tickets: " + tickets.size());
        } else {
            System.out.println(Thread.currentThread().getName() + " tried adding tickets, but capacity is full!");
        }

        notifyAll(); // Notify waiting threads
    }

    public synchronized void purchaseTickets(int ticketsToPurchase) {
        while (tickets.size() < ticketsToPurchase) {
            try {
                if (tickets.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " tried purchasing tickets, but none were available.");
                }
                wait(); // Wait for tickets to be added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Keep track of the tickets the customer buys
        StringBuilder purchasedTickets = new StringBuilder();
        for (int i = 0; i < ticketsToPurchase; i++) {
            int ticket = tickets.poll(); // Remove a ticket from the pool
            purchasedTickets.append(ticket).append(" ");
        }

        System.out.println(Thread.currentThread().getName() + " purchased " + ticketsToPurchase + " tickets. Purchased tickets id: " + purchasedTickets.toString().trim() + ". Remaining tickets: " + tickets.size());
    }
}
