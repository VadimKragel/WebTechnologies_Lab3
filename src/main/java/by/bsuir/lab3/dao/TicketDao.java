package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.Ticket;

import java.util.List;

public interface TicketDao extends BaseDao<Ticket> {

	List<Ticket> readAll(String property, Object value);
}
