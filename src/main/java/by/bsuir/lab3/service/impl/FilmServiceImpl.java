package by.bsuir.lab3.service.impl;

import by.bsuir.lab3.dao.FilmDao;
import by.bsuir.lab3.entity.Film;
import by.bsuir.lab3.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
    FilmDao filmDao;

	public FilmServiceImpl() {
	}

	public FilmServiceImpl(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	public FilmDao getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	@Override
	public List<Film> getFilmList() {
		return filmDao.readAll("id");
	}

	@Override
	public void createFilm(Film film) {
		filmDao.create(film);
	}

	@Override
	public Film readFilm(int id) {
		return filmDao.read(id);
	}

	@Override
	public void updateFilm(Film film) {
		filmDao.update(film);
	}

	@Override
	public void deleteFilm(Film film) {
		filmDao.delete(film);
	}
}
