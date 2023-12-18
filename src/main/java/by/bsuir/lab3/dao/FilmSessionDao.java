package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;

import java.util.List;
import java.util.Map;

public interface FilmSessionDao extends BaseDao<FilmSession> {

	List<FilmSession> readAll(Map<String,Object> map);

	List<FilmSession> readAll(String property, Object value);

	List<FilmSession> readAllWhereSeatNotFree(Seat seat);
}
