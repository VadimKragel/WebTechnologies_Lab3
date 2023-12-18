package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.service.FilmService;
import by.bsuir.lab3.service.FilmSessionService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import jakarta.servlet.http.HttpServletRequest;

public class FilmViewAction implements BaseAction {

	FilmService filmService;
	FilmSessionService filmSessionService;

	public FilmViewAction() {
	}

	public FilmService getFilmService() {
		return filmService;
	}

	public void setFilmService(FilmService filmService) {
		this.filmService = filmService;
	}

	public FilmSessionService getFilmSessionService() {
		return filmSessionService;
	}

	public void setFilmSessionService(FilmSessionService filmSessionService) {
		this.filmSessionService = filmSessionService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		return ConstantDeclaration.PAGE_USER_FILM_PAGE;
	}
}
