package by.bsuir.lab3.web.tags;

import by.bsuir.lab3.dao.impl.SeatDaoHibernateImpl;
import by.bsuir.lab3.dao.impl.TicketsOrderDaoHibernateImpl;
import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.TicketsOrder;
import jakarta.servlet.jsp.tagext.TagSupport;

public class InitSeatTag extends TagSupport {
	private int row;
	private int column;
	private FilmSession filmSession;
	private boolean isStateRequired;

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setFilmSession(FilmSession filmSession) {
		this.filmSession = filmSession;
	}

	public void setIsStateRequired(boolean isStateRequired) {
		this.isStateRequired = isStateRequired;
	}

	@Override
	public int doStartTag() {
		Seat seat = new SeatDaoHibernateImpl().read(row, column);
		if (seat != null && isStateRequired) {
			TicketsOrder ticketsOrder = new TicketsOrderDaoHibernateImpl().read(seat, filmSession);
			if (ticketsOrder == null)
				seat.setState(Seat.State.FREE);
			else if (ticketsOrder.getIsPaid()) {
				seat.setState(Seat.State.OCCUPIED);
			} else {
				seat.setState(Seat.State.BOOKED);
			}
		}
		pageContext.setAttribute("seat", seat);
		return SKIP_BODY;
	}
}
