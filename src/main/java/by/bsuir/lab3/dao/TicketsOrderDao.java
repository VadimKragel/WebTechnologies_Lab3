package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.TicketsOrder;
import by.bsuir.lab3.entity.User;

import java.util.List;

public interface TicketsOrderDao extends BaseDao<TicketsOrder> {

	List<TicketsOrder> readAll(String string, Object object);

	TicketsOrder read(Seat seat, FilmSession filmSession);

	TicketsOrder read(User user);

}
