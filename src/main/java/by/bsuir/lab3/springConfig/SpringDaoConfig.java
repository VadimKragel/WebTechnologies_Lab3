package by.bsuir.lab3.springConfig;

import by.bsuir.lab3.dao.*;
import by.bsuir.lab3.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDaoConfig {
	
	@Bean
	FilmDao filmDao() {
		return new FilmDaoHibernateImpl();
	}

	@Bean
	FilmSessionDao filmSessionDao() {
		return new FilmSessionDaoHibernateImpl();
	}

	@Bean
	GenreDao genreDao() {
		return new GenreDaoHibernateImpl();
	}

	@Bean
	RoleDao roleDao() {
		return new RoleDaoHibernateImpl();
	}

	@Bean
	SeatDao seatDao() {
		return new SeatDaoHibernateImpl();
	}

	@Bean
	TicketDao ticketDao() {
		return new TicketDaoHibernateImpl();
	}

	@Bean
	TicketsOrderDao ticketsOrderDao() {
		return new TicketsOrderDaoHibernateImpl();
	}

	@Bean
	UserDao userDao() {
		return new UserDaoHibernateImpl();
	}

}
