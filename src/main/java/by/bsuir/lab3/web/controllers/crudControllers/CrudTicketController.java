package by.bsuir.lab3.web.controllers.crudControllers;

import by.bsuir.lab3.entity.Ticket;
import by.bsuir.lab3.service.FilmSessionService;
import by.bsuir.lab3.service.SeatService;
import by.bsuir.lab3.service.TicketService;
import by.bsuir.lab3.service.TicketsOrderService;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import by.bsuir.lab3.web.util.HttpRequestParamFormatter;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping(value = "/newapp/admin/crud/ticket")
public class CrudTicketController {

	@Autowired
    TicketService ticketService;
	@Autowired
    FilmSessionService filmSessionService;
	@Autowired
    SeatService seatService;
	@Autowired
	@Qualifier("ticketOrderService")
    TicketsOrderService ticketsOrderService;

	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_TICKET_LIST, ticketService.getTicketList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_SESSION_LIST, filmSessionService.getFilmSessionList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_SEAT_LIST, seatService.getSeatList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_FILM_TICKETS_ORDER_LIST, ticketsOrderService.getTicketsOrderList());
		mav.addObject(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET, new Ticket());
		mav.setViewName("springMvcPages/admin/crud_ticket");
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticket.getFilmSession(), ticket.getSeat(), ticket.getOrder());
		ticketService.createTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8" })
	public @ResponseBody String read(@RequestParam String id) throws UnsupportedEncodingException {
		HttpRequestParamValidator.validateRequestParamIdNotNull(HttpRequestParamFormatter.getInt(id));
		Ticket foundTicket = ticketService.readTicket(HttpRequestParamFormatter.getInt(id));
		return "{\"foundTicket\" : \"" + foundTicket + "\"}";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamNotNull(ticket.getId(), ticket.getFilmSession(), ticket.getSeat(), ticket.getOrder());
		ticketService.updateTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(ConstantDeclaration.REQUEST_PARAM_COMMAND_NAME_CRUD_TICKET) Ticket ticket) {
		HttpRequestParamValidator.validateRequestParamIdNotNull(ticket.getId());
		ticketService.deleteTicket(ticket);
		return "redirect:/newapp/admin/crud/ticket/";
	}

}
