package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.entity.Film;
import by.bsuir.lab3.service.FilmService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class FilmListViewAction implements BaseAction {
	FilmService filmService;

	public FilmListViewAction() {
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<Film> films = filmService.getFilmList();
		req.setAttribute(ConstantDeclaration.REQUEST_PARAM_FILM_LIST, films);

		return ConstantDeclaration.PAGE_USER_MAIN;
	}
}
