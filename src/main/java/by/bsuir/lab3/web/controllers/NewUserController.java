package by.bsuir.lab3.web.controllers;

import by.bsuir.lab3.entity.*;
import by.bsuir.lab3.service.*;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import by.bsuir.lab3.web.util.TimerDaemon;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/newapp/user")
public class NewUserController extends HttpServlet {

	@Autowired
	@Qualifier("filmService")
	FilmService filmService;

	@Autowired
	@Qualifier("genreService")
	GenreService genreService;

	@Autowired
	@Qualifier("filmSessionService")
	FilmSessionService filmSessionService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Autowired
	@Qualifier("roleService")
	RoleService roleService;

	@Autowired
	@Qualifier("seatService")
	SeatService seatService;

	@Autowired
	@Qualifier("ticketService")
	TicketService ticketService;

	@Autowired
	@Qualifier("ticketOrderService")
	TicketsOrderService ticketsOrderService;

	private static final Logger logger = LogManager.getLogger();
	private TimerDaemon timer;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView main(HttpSession session, Principal principal) {
		System.out.println("principal " + principal);

		List<Film> films = filmService.getFilmList();
		return new ModelAndView("springMvcPages/user/main", ConstantDeclaration.REQUEST_PARAM_FILM_LIST, films);
	}

	@RequestMapping(value = "/chosenGenreFilms", method = RequestMethod.GET)
	public ModelAndView viewChosenGenreFilms(@RequestParam(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_GENRE_ID) int genre_id) {
		return new ModelAndView("springMvcPages/user/chosenGenreFilms", ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_GENRE,
				genreService.readGenre(genre_id));
	}

	@RequestMapping(value = "/filmPage", method = RequestMethod.GET)
	public ModelAndView viewFilmPage(@RequestParam int film_id, HttpServletRequest req) {
		Film chosenFilm = filmService.readFilm(film_id);
		return new ModelAndView("springMvcPages/user/filmPage", ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_FILM, chosenFilm);
	}

	@SuppressWarnings("serial")
	@RequestMapping(value = "/chooseSeat", method = RequestMethod.GET)
	public ModelAndView chooseSeat(@RequestParam(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_FILM_SESSION_ID) int filmSession_Id) {
		return new ModelAndView("springMvcPages/user/seatChoice").addAllObjects(new HashMap<String, Object>() {
			{
				put(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_SEAT, new Seat());
				put(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_FILM_SESSION, filmSessionService.readFilmSession(filmSession_Id));
			}
		});
	}

	@RequestMapping(value = "/toBasket", method = RequestMethod.POST)
	public ModelAndView order(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_SEAT) Seat seat,
							  @RequestParam(ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_FILM_SESSION_ID) int filmSession_Id, HttpSession session,
							  Principal principal) {
		if (!seatService.isSeatFree(seat, filmSessionService.readFilmSession(filmSession_Id)))
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "This seat isn't free");

		User user = userService.readUser(principal.getName());
		/* User user = (User) session.getAttribute(SESSION_PARAM_CURRENT_USER); */
		if (user != null) {
			FilmSession filmSession = filmSessionService.readFilmSession(filmSession_Id);
			TicketsOrder ticketsOrder;
			if ((ticketsOrder = ticketsOrderService.readUserNonPaidOrder(user)) == null) {
				ticketsOrder = new TicketsOrder();
				ticketsOrder.setUser(user);
				ticketsOrderService.createTicketsOrder(ticketsOrder);

				timer = new TimerDaemon();
				timer.setDaemon(true);
				timer.start();
				System.out.println("timer.start();");
				session.setAttribute("isTimerNeed", true);
			}
			ticketService.createTicket(new Ticket(0, filmSession, seat, ticketsOrder));
			return new ModelAndView("redirect:/newapp/user/chooseSeat", ConstantDeclaration.REQUEST_PARAM_USER_CHOSEN_FILM_SESSION_ID,
					filmSession_Id);
		} else
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "You have to be logged in");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("springMvcPages/user/login");
		/*
		 * return new ModelAndView("springMvcPages/user/login",
		 * SESSION_PARAM_CURRENT_USER, new User());
		 */
	}

	/*
	 * @RequestMapping(value = "/check_User", method = RequestMethod.POST) public
	 * ModelAndView checkUser(@ModelAttribute("user") User user, HttpServletRequest
	 * req) { HttpSession session = req.getSession(); if
	 * (session.getAttribute(SESSION_PARAM_CURRENT_USER) != null) { return new
	 * ModelAndView("springMvcPages/error", REQUEST_PARAM_ERROR_MESSAGE,
	 * "You are already logged in"); } User foundUser = userService.readUser(new
	 * HashMap<String, Object>() { { put("login", user.getLogin()); put("password",
	 * user.getPassword()); } }); if (foundUser != null) {
	 * session.setAttribute(SESSION_PARAM_CURRENT_USER, foundUser);
	 * session.setMaxInactiveInterval(600); return new
	 * ModelAndView("redirect:/newapp/user/"); } else { return new
	 * ModelAndView("springMvcPages/error", REQUEST_PARAM_ERROR_MESSAGE,
	 * "Incorrect username or password"); } }
	 * 
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET) public
	 * ModelAndView logout(HttpServletRequest req) { req.getSession().invalidate();
	 * req.setAttribute(SESSION_PARAM_CURRENT_USER, null); return new
	 * ModelAndView("redirect:/newapp/user/"); }
	 */

	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public ModelAndView newUser(ModelMap model) {
		return new ModelAndView("springMvcPages/user/signUp", "command", new User());
	}

	@RequestMapping(value = "/checkLog", method = RequestMethod.GET)
	public @ResponseBody String checkLog(@RequestParam String jsonLogin, HttpServletRequest req) {
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(jsonLogin);
			String login = (String) obj.get("login");
			if (userService.readUser("login", login) == null) {
				return String.format(ConstantDeclaration.SIGN_UP_CHECK_LOGIN_RESULT_STRING, ConstantDeclaration.SIGN_UP_CHECK_LOGIN_SUCCESS_COLOR,
						"This login is free");
			} else {
				return String.format(ConstantDeclaration.SIGN_UP_CHECK_LOGIN_RESULT_STRING, ConstantDeclaration.SIGN_UP_CHECK_LOGIN_FAIL_COLOR,
						"This login is already taken");
			}
		} catch (ParseException e) {
			logger.error("jsonLogin parsing error");
		}
		return "springMvcPages/error";
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public @ResponseBody String checkEmail(@RequestParam String email, HttpServletRequest req) {
		if (email.length() == 0)
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_EMAIL_RESULT_STRING, "Email address is required");
		else if (!HttpRequestParamValidator.checkEmailInput(email))
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_EMAIL_RESULT_STRING, "Email must be in the format: xxxxxx@yyyy.zz");
		else if (userService.readUser("email", email) != null)
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_EMAIL_RESULT_STRING, "This email is already taken");
		else
			return "";
	}

	@RequestMapping(value = "/checkPass", method = RequestMethod.GET)
	public @ResponseBody String checkPassword(@RequestParam String password) {
		if (password.length() >= ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_WEAK_STRENGTH
				&& password.length() < ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_MIDDLE_STRENGTH)
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_RESULT_STRING, ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_WEAK_STRENGTH_COLOR,
					"Easy password");
		else if (password.length() >= ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_MIDDLE_STRENGTH
				&& password.length() < ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_STRONG_STRENGTH)
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_RESULT_STRING, ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_MIDDLE_STRENGTH_COLOR,
					"Middle password");
		else if (password.length() >= ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_STRONG_STRENGTH)
			return String.format(ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_RESULT_STRING, ConstantDeclaration.SIGN_UP_CHECK_PASSWORD_STRONG_STRENGTH_COLOR,
					"Strong password");
		else
			return "";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("user") User user, HttpServletRequest req) {
		String userLogin = user.getLogin();
		String userEmail = user.getEmail();
		String userPassword = user.getPassword();
		HttpRequestParamValidator.validateRequestParamNotNull(userLogin, userEmail, userPassword);

		if (userService.readUser("login", userLogin) != null) {
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "This login is already taken");
		} else if (userService.readUser("email", userEmail) != null) {
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "This email is already taken");
		} else if (userPassword.length() < 5) {
			return new ModelAndView("springMvcPages/error", ConstantDeclaration.REQUEST_PARAM_ERROR_MESSAGE, "This password is too short");
		}
		user.setRole(roleService.readRole(ConstantDeclaration.DEFAULT_ROLE_ID));
		userService.createUser(user);
		return new ModelAndView("springMvcPages/success", ConstantDeclaration.REQUEST_PARAM_SUCCESS_MESSAGE,
				"You are successfully signed up.<br>Now, you can log in.");
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView enterToProgile(Principal principal, HttpSession session) {
		User currentUser = userService.readUser(principal.getName());
		session.setAttribute(ConstantDeclaration.SESSION_PARAM_CURRENT_USER, currentUser);
		return new ModelAndView("springMvcPages/user/profile", ConstantDeclaration.REQUEST_PARAM_CURRENT_USER_CURRENT_ORDER,
				ticketsOrderService.readCurrentUserNonPaidOrder(currentUser));
	}

	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public ModelAndView buyTickets(@RequestParam(ConstantDeclaration.REQUEST_PARAM_CURRENT_USER_CURRENT_ORDER_ID) int ticketsOrderid,
			HttpSession session) {
		TicketsOrder ticketsOrder = ticketsOrderService.readTicketsOrder(ticketsOrderid);
		ticketsOrder.setIsPaid(true);
		ticketsOrderService.updateTicketsOrder(ticketsOrder);
		timer.setStop(true);
		session.setAttribute("isTimerNeed", false);
		return new ModelAndView("springMvcPages/success", ConstantDeclaration.REQUEST_PARAM_SUCCESS_MESSAGE,
				"You have successfully bought tickets.");
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "springMvcPages/error";
	}

	@RequestMapping(value = "/timer", method = RequestMethod.GET)
	public @ResponseBody String getTimer(HttpSession session, Principal principal) {
		if (timer != null && !timer.isAlive()) {
			session.setAttribute("isTimerNeed", false);
			return "Time is over";
		}
		return String.format("%02d : %02d", timer.getMinutesDisplay(), timer.getSecondsDisplay());
	}

	@RequestMapping(value = "/deleteNonPaidOrder", method = RequestMethod.GET)
	public @ResponseBody void deleteNonPaidOrder(HttpSession session, Principal principal) {
		User currentUser = userService.readUser(principal.getName());
		TicketsOrder ticketsOrder = ticketsOrderService.readUserNonPaidOrder(currentUser);
		ticketsOrderService.deleteTicketsOrder(ticketsOrder);
	}
}
