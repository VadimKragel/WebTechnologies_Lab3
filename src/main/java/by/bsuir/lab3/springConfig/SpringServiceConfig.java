package by.bsuir.lab3.springConfig;

import by.bsuir.lab3.dao.*;
import by.bsuir.lab3.service.*;
import by.bsuir.lab3.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SpringServiceConfig {
	{
		System.out.println("SpringServiceConfig");
	}

	@Bean
	protected UserDetailsService userDetailsService(UserDao userDao) {
		return new CustomUserDetailsServiceImpl(userDao);
	}

	@Bean
	FilmService filmService(FilmDao filmDao) {
		return new FilmServiceImpl(filmDao);
	}

	@Bean
	FilmSessionService filmSessionService(FilmSessionDao filmSessionDao) {
		return new FilmSessionServiceImpl(filmSessionDao);
	}

	@Bean
	GenreService genreService(GenreDao genreDao, FilmDao filmDao) {
		return new GenreServiceImpl(genreDao, filmDao);
	}

	@Bean
	RoleService roleService(RoleDao roleDao, UserDao userDao) {
		return new RoleServiceImpl(roleDao, userDao);
	}

	@Bean
	SeatService seatService(SeatDao seatDao, TicketsOrderDao ticketsOrderDao) {
		return new SeatServiceImpl(seatDao, ticketsOrderDao);
	}

	@Bean
	TicketService ticketService(TicketDao ticketDao) {
		return new TicketServiceImpl(ticketDao);
	}

	@Bean
	TicketsOrderService ticketOrderService(TicketsOrderDao ticketsOrderDao) {
		return new TicketsOrderServiceImpl(ticketsOrderDao);
	}

	@Bean
	UserService userService(UserDao userDao) {
		return new UserServiceImpl(userDao);
	}
}
