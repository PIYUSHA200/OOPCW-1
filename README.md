# Real-Time Ticketing System

## Overview
The Real-Time Ticketing System is a Java-based application designed to simulate a ticketing system where multiple vendors release tickets and multiple customers purchase them concurrently.
The system uses multithreading to handle the concurrent operations of vendors and customers.


## Key Features
- Multithreading: Simultaneous operations of multiple vendors and customers.
- Configuration: Ability to load previous configurations or enter new configurations.
- Logging: Logs ticket releases and purchases to a file.
- Graceful Shutdown: Allows the system to be stopped gracefully by user input.


## Configuration
The system configuration can be loaded from a `config.txt` file or entered manually by the user. The configuration includes:
- Total Tickets: The total number of tickets available initially.
- Tickets Released Per Cycle: The number of tickets each vendor releases per cycle.
- Tickets Retrieved Per Cycle: The number of tickets each customer retrieves per cycle.
- Maximum Ticket Pool Capacity: The maximum number of tickets that can be held in the ticket pool.


## Classes
*`Configuration`*
Handles the configuration of the system, including loading from a file or user input.

*`Customer`*
Represents a customer that purchases tickets from the ticket pool at a specified rate.

*`TicketPool`*
Manages the pool of tickets, including adding and removing tickets.

*`Vendor`*
Represents a vendor that releases tickets into the ticket pool at a specified rate.

*`Main`*
The entry point of the application, responsible for initializing the system, starting threads, and handling user input to stop the system.
