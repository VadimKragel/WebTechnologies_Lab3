package by.bsuir.lab3.service.impl;

import by.bsuir.lab3.dao.FilmDao;
import by.bsuir.lab3.dao.GenreDao;
import by.bsuir.lab3.entity.Film;
import by.bsuir.lab3.entity.Genre;
import by.bsuir.lab3.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
    GenreDao genreDao;
	@Autowired
    FilmDao filmDao;

	public GenreServiceImpl() {
	}

	public GenreServiceImpl(GenreDao genreDao, FilmDao filmDao) {
		this.genreDao = genreDao;
		this.filmDao = filmDao;
	}

	public GenreDao getGenreDao() {
		return genreDao;
	}

	public void setGenreDao(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	public FilmDao getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	@Override
	public List<Genre> getGenreList() {
		return genreDao.readAll("id");
	}

	@Override
	public void createGenre(Genre genre) {
		genreDao.create(genre);
	}

	@Override
	public Genre readGenre(int id) {
		return genreDao.read(id);

	}

	@Override
	public void updateGenre(Genre genre) {
		genreDao.update(genre);

	}

	@Override
	public void deleteGenre(Genre genre) {
		genreDao.delete(genre);
	}

	@Override
	public boolean isAnyFilmContainGenre(int id) {
		List<Film> films = filmDao.readAllFilmsWhereGenreIdPresent(id);
		return films.size() != 0;
	}
}
