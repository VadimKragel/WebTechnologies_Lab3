package by.bsuir.lab3.web.controllers;

import by.bsuir.lab3.web.action.ActionManagerContext;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.Serial;

@Controller
@RequestMapping(value = "/oldapp")
public class OldController extends HttpServlet {
	@Serial
	private static final long serialVersionUID = -6886728390526627968L;

	private static final Logger logger = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext servletContext = req.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		String action = req.getParameter(ConstantDeclaration.REQUEST_PARAM_ACTION);
		BaseAction baseAction = ActionManagerContext.getAction(action, webApplicationContext);
		String page;
		if (action != null) {
			page = baseAction.executeAction(req);

			String nextStep = (String) req.getAttribute(ConstantDeclaration.REQUEST_PARAM_ACTION);
			System.out.println("action= " + action);
			System.out.println("nextStep= " + nextStep);

			if (nextStep == null || action.equals(nextStep)) {
				req.getRequestDispatcher(page).forward(req, resp);
			} else {
				resp.sendRedirect("do?action=" + nextStep);
			}

		} else {
			logger.error("Incorrect action");
			req.setAttribute(ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "Incorrect action");
			req.getRequestDispatcher(ConstantDeclaration.PAGE_ERROR).forward(req, resp);
		}

	}

}
