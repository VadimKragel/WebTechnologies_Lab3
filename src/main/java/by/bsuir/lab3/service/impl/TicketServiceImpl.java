package by.bsuir.lab3.service.impl;

import by.bsuir.lab3.dao.TicketDao;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.Ticket;
import by.bsuir.lab3.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
    TicketDao ticketDao;

	public TicketServiceImpl() {
	}

	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public TicketDao getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Override
	public List<Ticket> getTicketList() {
		return ticketDao.readAll("id");
	}

	@Override
	public void createTicket(Ticket ticket) {
		ticketDao.create(ticket);
	}

	@Override
	public Ticket readTicket(int id) {
		return ticketDao.read(id);
	}

	@Override
	public void updateTicket(Ticket ticket) {
		ticketDao.update(ticket);
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		ticketDao.delete(ticket);
	}

	@Override
	public boolean isAnyTicketContainsSeat(Seat seat) {
		return !ticketDao.readAll("seat", seat).isEmpty();
	}

}
