package by.bsuir.lab3.service.impl;

import by.bsuir.lab3.dao.TicketsOrderDao;
import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.TicketsOrder;
import by.bsuir.lab3.entity.User;
import by.bsuir.lab3.service.TicketsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsOrderServiceImpl implements TicketsOrderService {

	@Autowired
    TicketsOrderDao ticketsOrderDao;

	public TicketsOrderServiceImpl() {
	}

	public TicketsOrderServiceImpl(TicketsOrderDao ticketsOrderDao) {
		this.ticketsOrderDao = ticketsOrderDao;
	}

	public TicketsOrderDao getTicketsOrderDao() {
		return ticketsOrderDao;
	}

	public void setTicketsOrderDao(TicketsOrderDao ticketsOrderDao) {
		this.ticketsOrderDao = ticketsOrderDao;
	}

	@Override
	public List<TicketsOrder> getTicketsOrderList() {
		return ticketsOrderDao.readAll("id");
	}

	@Override
	public void createTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.create(ticketsOrder);
	}

	@Override
	public TicketsOrder readTicketsOrder(int id) {
		return ticketsOrderDao.read(id);
	}

	@Override
	public void updateTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.update(ticketsOrder);
	}

	@Override
	public void deleteTicketsOrder(TicketsOrder ticketsOrder) {
		ticketsOrderDao.delete(ticketsOrder);
	}

	@Override
	public TicketsOrder readUserNonPaidOrder(User user) {
		for (TicketsOrder order : ticketsOrderDao.readAll("user", user))
			if (!order.getIsPaid())
				return order;
		return null;
	}

	@Override
	public TicketsOrder readOrderWhereSeatPresent(Seat seat, FilmSession filmSession) {
		return ticketsOrderDao.read(seat, filmSession);
	}

	@Override
	public TicketsOrder readCurrentUserNonPaidOrder(User user) {
		return ticketsOrderDao.read(user);
	}
}
