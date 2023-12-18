package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.Ticket;

import java.util.List;

public interface TicketService extends Service {

	List<Ticket> getTicketList();

	void createTicket(Ticket ticket);

	Ticket readTicket(int id);

	void updateTicket(Ticket ticket);

	void deleteTicket(Ticket ticket);

	boolean isAnyTicketContainsSeat(Seat seat);
}
