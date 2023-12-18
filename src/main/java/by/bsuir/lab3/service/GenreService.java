package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.Genre;

import java.util.List;

public interface GenreService extends Service {

	List<Genre> getGenreList();

	void createGenre(Genre genre);

	Genre readGenre(int id);

	void updateGenre(Genre genre);

	void deleteGenre(Genre genre);

	public boolean isAnyFilmContainGenre(int id);
}
