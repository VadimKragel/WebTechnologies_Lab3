package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.SeatDao;
import by.bsuir.lab3.entity.Seat;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatDaoHibernateImpl implements SeatDao {

	@Override
	public void create(Seat entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Seat read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Seat.class);
		criteria.add(Restrictions.eq("id", id));
		Seat seat = (Seat) criteria.uniqueResult();
		session.close();
		return seat;
	}

	@Override
	public Seat read(int row, int number) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Seat.class);
		criteria.add(Restrictions.eq("row", row));
		criteria.add(Restrictions.eq("number", number));
		Seat seat = (Seat) criteria.uniqueResult();
		session.close();
		return seat;
	}

	@Override
	public void update(Seat entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Seat entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Seat> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Seat.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Seat> seats = criteria.list();
		session.close();
		return seats;
	}

	@Override
	public List<Seat> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Seat.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<Seat> seats = criteria.list();
		session.close();
		return seats;
	}

}
