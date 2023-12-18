package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.entity.Genre;
import by.bsuir.lab3.service.GenreService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.bsuir.lab3.web.util.ConstantDeclaration.*;
import static by.bsuir.lab3.web.util.HttpRequestParamFormatter.getInt;

public class CrudGenreAction implements BaseAction {
	GenreService genreService;

	public CrudGenreAction() {
	}

	public GenreService getGenreService() {
		return genreService;
	}

	public void setGenreService(GenreService genreService) {
		this.genreService = genreService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<Genre> genres = genreService.getGenreList();
		req.setAttribute(REQUEST_PARAM_GENRE_LIST, genres);
		if (HttpRequestParamValidator.isPost(req)) {
			String crudCommand = req.getParameter(REQUEST_PARAM_CRUD_COMMAND);
			HttpRequestParamValidator.validateRequestParamNotNull(crudCommand);
			Genre genre;

			switch (crudCommand) {
			case CRUD_OPERATION_NAME_CREATE:
				genre = buildGenre(req);
				genreService.createGenre(genre);
				break;
			case CRUD_OPERATION_NAME_READ:
				String genreId = req.getParameter(REQUEST_PARAM_GENRE_ID);
				HttpRequestParamValidator.validateRequestParamNotNull(genreId);
				genre = genreService.readGenre(getInt(genreId));
				req.setAttribute(REQUEST_PARAM_FOUND_GENRE, genre);
				break;
			case CRUD_OPERATION_NAME_UPDATE:
				genre = buildGenre(req);
				genreService.updateGenre(genre);
				break;
			case CRUD_OPERATION_NAME_DELETE:
				genre = buildGenre(req);
				genreService.deleteGenre(genre);
				break;
			default:
				return PAGE_ERROR;
			}
		}
		return PAGE_ADMIN_CRUD_GENRE;
	}

	private Genre buildGenre(HttpServletRequest req) {
		String id = req.getParameter(REQUEST_PARAM_GENRE_ID);
		String genreName = req.getParameter(REQUEST_PARAM_GENRE_NAME);
		HttpRequestParamValidator.validateRequestParamNotNull(id, genreName);

		Genre genre = new Genre();
		genre.setId(getInt(id));
		genre.setGenreName(genreName);
		return genre;
	}
}
