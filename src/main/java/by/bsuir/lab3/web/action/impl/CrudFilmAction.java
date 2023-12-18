package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.entity.Film;
import by.bsuir.lab3.entity.Genre;
import by.bsuir.lab3.service.FilmService;
import by.bsuir.lab3.service.GenreService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static by.bsuir.lab3.web.util.ConstantDeclaration.*;
import static by.bsuir.lab3.web.util.HttpRequestParamFormatter.fixGoogleDriveUrl;
import static by.bsuir.lab3.web.util.HttpRequestParamFormatter.getInt;

public class CrudFilmAction implements BaseAction {

	public FilmService filmService;
	GenreService genreService;

	public CrudFilmAction() {
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	public GenreService getGenreService() {
		return genreService;
	}

	public void setGenreService(GenreService genreService) {
		this.genreService = genreService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<Film> films = filmService.getFilmList();
		List<Genre> genres = genreService.getGenreList();
		req.setAttribute(REQUEST_PARAM_FILM_LIST, films);
		req.setAttribute(REQUEST_PARAM_GENRE_LIST, genres);
		if (HttpRequestParamValidator.isPost(req)) {
			String crudCommand = req.getParameter(REQUEST_PARAM_CRUD_COMMAND);
			HttpRequestParamValidator.validateRequestParamNotNull(crudCommand);
			Film film;

			switch (crudCommand) {
			case CRUD_OPERATION_NAME_CREATE:
				film = buildFilm(req);
				filmService.createFilm(film);
				break;
			case CRUD_OPERATION_NAME_READ:
				String filmId = req.getParameter(REQUEST_PARAM_FILM_ID);
				HttpRequestParamValidator.validateRequestParamNotNull(filmId);
				film = filmService.readFilm(getInt(filmId));
				req.setAttribute(REQUEST_PARAM_FOUND_FILM, film);
				break;
			case CRUD_OPERATION_NAME_UPDATE:
				film = buildFilm(req);
				filmService.updateFilm(film);
				break;
			case CRUD_OPERATION_NAME_DELETE:
				film = buildFilm(req);
				filmService.deleteFilm(film);
				break;
			default:
				return PAGE_ERROR;
			}
		}
		return PAGE_ADMIN_CRUD_FILM;
	}

	private Film buildFilm(HttpServletRequest req) {
		String id = req.getParameter(REQUEST_PARAM_FILM_ID);
		String filmName = req.getParameter(REQUEST_PARAM_FILM_NAME);
		String description = req.getParameter(REQUEST_PARAM_FILM_DESCRIPTION);
		String posterUrl = req.getParameter(REQUEST_PARAM_FILM_POSTER_URL);
		String[] genresId = req.getParameterValues(REQUEST_PARAM_FILM_GENRES);
		HttpRequestParamValidator.validateRequestParamNotNull(id, filmName, description, posterUrl);
		HttpRequestParamValidator.validateRequestParamNotNull(genresId);

		System.out.println(Arrays.toString(genresId));
		Film film = new Film();
		film.setId(getInt(id));
		film.setFilmName(filmName);
		film.setDescription(description);
		film.setPosterUrl(fixGoogleDriveUrl(posterUrl));

		Set<Genre> genres = new HashSet<>();
		for (String genreId : genresId) {
			genres.add(genreService.readGenre(getInt(genreId)));
		}
		film.setGenres(genres);
		return film;
	}
}
