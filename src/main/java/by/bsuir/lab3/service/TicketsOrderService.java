package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.TicketsOrder;
import by.bsuir.lab3.entity.User;

import java.util.List;

public interface TicketsOrderService extends Service {

	List<TicketsOrder> getTicketsOrderList();

	void createTicketsOrder(TicketsOrder ticketsOrder);

	TicketsOrder readTicketsOrder(int id);

	void updateTicketsOrder(TicketsOrder ticketsOrder);

	void deleteTicketsOrder(TicketsOrder ticketsOrder);

	TicketsOrder readUserNonPaidOrder(User user);

	TicketsOrder readOrderWhereSeatPresent(Seat seat, FilmSession filmSession);

	TicketsOrder readCurrentUserNonPaidOrder(User user);
}
