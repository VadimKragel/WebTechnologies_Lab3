package by.bsuir.lab3.entity;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "orders")
public class TicketsOrder implements Serializable {

	@Serial
	private static final long serialVersionUID = -1852653197354547323L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "orderNumber")
	private int orderNumber; // SQL trigger-generated column

	@Column(name = "isPaid")
	private boolean isPaid;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Fetch(FetchMode.JOIN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<Ticket> tickets;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ticketsOrders")
	private Set<FilmSession> filmSessions;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ticketsOrders")
	private Set<Seat> seats;

	public TicketsOrder() {
		super();
	}

	public TicketsOrder(int id, int orderNumber, User user, boolean isPaid, Set<Ticket> tickets,
			Set<FilmSession> filmSessions, Set<Seat> seats) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.isPaid = isPaid;
		this.tickets = tickets;
		this.user = user;
		this.filmSessions = filmSessions;
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<FilmSession> getFilmSessions() {
		return filmSessions;
	}

	public void setFilmSessions(Set<FilmSession> filmSessions) {
		this.filmSessions = filmSessions;
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
		result = prime * result + id;
		result = prime * result + (isPaid ? 1231 : 1237);
		result = prime * result + orderNumber;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		TicketsOrder other = (TicketsOrder) obj;
		if (id != other.id)
			return false;
		if (isPaid != other.isPaid)
			return false;
		if (orderNumber != other.orderNumber)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", isPaid=" + isPaid + ", user=" + user.getLogin()
				+ "]";
	}

}
