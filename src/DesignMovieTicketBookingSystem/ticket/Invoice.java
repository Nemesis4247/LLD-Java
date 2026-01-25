package DesignMovieTicketBookingSystem.ticket;

import java.time.Instant;
import java.time.LocalDateTime;

public class Invoice {
    Ticket ticket;
    double amount;
    String userId;
    String paymentMethod;
    int invoiceId;
    Instant timestamp;
}
