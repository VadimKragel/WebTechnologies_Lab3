package by.bsuir.lab3.entity;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

	@Serial
	private static final long serialVersionUID = -7308318330045764024L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private FilmSession filmSession;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id")
	private Seat seat;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private TicketsOrder order;

	public Ticket() {
		super();
	}

	public Ticket(int id, FilmSession filmSession, Seat seat, TicketsOrder order) {
		super();
		this.id = id;
		this.filmSession = filmSession;
		this.seat = seat;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FilmSession getFilmSession() {
		return filmSession;
	}

	public void setFilmSession(FilmSession filmSession) {
		this.filmSession = filmSession;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public TicketsOrder getOrder() {
		return order;
	}

	public void setOrder(TicketsOrder order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filmSession == null) ? 0 : filmSession.hashCode());
		result = prime * result + id;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((seat == null) ? 0 : seat.hashCode());
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
		Ticket other = (Ticket) obj;
		if (filmSession == null) {
			if (other.filmSession != null)
				return false;
		} else if (!filmSession.equals(other.filmSession))
			return false;
		if (id != other.id)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;

		if (seat == null) {
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", filmSession=date:" + filmSession.getDate() + "|time:" + filmSession.getTime()
				+ ", seat=row:" + seat.getRow() + "|number:" + seat.getNumber()  + "]";
	}

}
