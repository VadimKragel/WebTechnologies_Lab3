package by.bsuir.lab3.entity;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "seats")
public class Seat implements Serializable {

	@Serial
	private static final long serialVersionUID = -8046225975482720446L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "row")
	int row;

	@Column(name = "number")
	int number;

	@Fetch(FetchMode.JOIN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
	private Set<Ticket> tickets;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tickets", joinColumns = @JoinColumn(name = "seat_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<TicketsOrder> ticketsOrders;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tickets", joinColumns = @JoinColumn(name = "seat_id"), inverseJoinColumns = @JoinColumn(name = "session_id"))
	private Set<FilmSession> filmSessions;

	@Transient
	private State state;

	public enum State {
		FREE("green"), BOOKED("yellow"), OCCUPIED("red");

		String buttonColor;

		State(String buttonColor) {
			this.buttonColor = buttonColor;
		}

		public String getButtonColor() {
			return buttonColor;
		}
	}

	public Seat() {
		super();
	}

	public Seat(int id, int row, int number, Set<Ticket> tickets, Set<TicketsOrder> ticketsOrders,
			Set<FilmSession> filmSessions, State state) {
		super();
		this.id = id;
		this.row = row;
		this.number = number;
		this.tickets = tickets;
		this.ticketsOrders = ticketsOrders;
		this.filmSessions = filmSessions;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public Set<FilmSession> getFilmSessions() {
		return filmSessions;
	}

	public void setFilmSessions(Set<FilmSession> filmSessions) {
		this.filmSessions = filmSessions;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + row;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Seat other = (Seat) obj;
		if (id != other.id)
			return false;
		if (number != other.number)
			return false;
		if (row != other.row)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", row=" + row + ", number=" + number + "]";
	}
}
