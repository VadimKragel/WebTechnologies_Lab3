package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.Film;

import java.util.List;
import java.util.Map;

public interface FilmDao extends BaseDao<Film> {

	List<Film> readAll(Map<String, Object> map);

	List<Film> readAllFilmsWhereGenreIdPresent(int genreId);
}
