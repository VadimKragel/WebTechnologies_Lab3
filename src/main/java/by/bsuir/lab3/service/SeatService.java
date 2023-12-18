package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;

import java.util.List;

public interface SeatService extends Service {

	List<Seat> getSeatList();

	void createSeat(Seat seat);

	Seat readSeat(int id);

	Seat readSeat(int row, int number);

	void updateSeat(Seat seat);

	void deleteSeat(Seat seat);

	Seat setSeatState(Seat seat, FilmSession filmSession);

	boolean isSeatExist(Seat seat);

	boolean isSeatExist(int id);

	boolean isSeatFree(Seat seat, FilmSession filmSession);
}
