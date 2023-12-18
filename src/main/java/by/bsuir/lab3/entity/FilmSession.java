package by.bsuir.lab3.entity;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class FilmSession implements Serializable {

	@Serial
	private static final long serialVersionUID = -6832669965931418330L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "ticketPrice")
	private BigDecimal ticketPrice;

	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@Fetch(FetchMode.JOIN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filmSession")
	private Set<Ticket> tickets;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tickets", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<TicketsOrder> ticketsOrders;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tickets", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
	private Set<Seat> seats;

	public FilmSession() {
		super();
	}

	public FilmSession(int id, String date, String time, BigDecimal ticketPrice, Film film, Set<Ticket> tickets,
			Set<TicketsOrder> ticketsOrders, Set<Seat> seats) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.ticketPrice = ticketPrice;
		this.film = film;
		this.tickets = tickets;
		this.ticketsOrders = ticketsOrders;
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Set<TicketsOrder> getTicketsOrders() {
		return ticketsOrders;
	}

	public void setTicketsOrders(Set<TicketsOrder> ticketsOrders) {
		this.ticketsOrders = ticketsOrders;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + id;
		result = prime * result + ((ticketPrice == null) ? 0 : ticketPrice.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmSession other = (FilmSession) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (id != other.id)
			return false;
		if (ticketPrice == null) {
			if (other.ticketPrice != null)
				return false;
		} else if (!ticketPrice.equals(other.ticketPrice))
			return false;

		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FilmSession [id=" + id + ", date=" + date + ", time=" + time + ", ticketPrice=" + ticketPrice + "]";
	}
}
