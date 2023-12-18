package by.bsuir.lab3.web.tags;

import by.bsuir.lab3.dao.impl.GenreDaoHibernateImpl;
import by.bsuir.lab3.entity.Genre;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class DisplayGenresBlockTag extends TagSupport {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public int doStartTag() throws JspException {
		List<Genre> genres = new GenreDaoHibernateImpl().readAll("genreName");
		StringBuilder genresBlock = new StringBuilder();
		genresBlock.append("<h5>Choose genre:</h5>");
		for (Genre g : genres) {
			genresBlock
			.append("<hr>")
			.append("<a href=\"/cinema/newapp/user/chosenGenreFilms?user_chosen_genre_id=")
			.append(g.getId())
			.append("\">")
			.append(g.getGenreName())
			.append("</a>");
		}
		JspWriter out = pageContext.getOut();
		try {
			out.write(genresBlock.toString());
		} catch (IOException e) {
			logger.error(e.getStackTrace());
		}
		return SKIP_BODY;
	}
}