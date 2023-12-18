package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.Film;
import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;

import java.util.List;

public interface FilmSessionService extends Service {

	List<FilmSession> getFilmSessionList();

	List<FilmSession> getFilmSessionListWhereSeatNotFree(Seat seat);

	List<FilmSession> getChosenFilmFilmSessionList(Film film);

	void createFilmSession(FilmSession filmSession);

	FilmSession readFilmSession(int id);

	void updateFilmSession(FilmSession filmSession);

	void deleteFilmSession(FilmSession filmSession);

	boolean isAnyTicketRelatedToFilmSession(int filmSessionId);

}
