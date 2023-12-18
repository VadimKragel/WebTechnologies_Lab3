package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.Film;

import java.util.List;

public interface FilmService extends Service {

	List<Film> getFilmList();

	void createFilm(Film film);

	Film readFilm(int id);

	void updateFilm(Film film);

	void deleteFilm(Film film);
}
