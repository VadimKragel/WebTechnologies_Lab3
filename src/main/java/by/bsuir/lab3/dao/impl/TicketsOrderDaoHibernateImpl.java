package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.TicketsOrderDao;
import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import by.bsuir.lab3.entity.TicketsOrder;
import by.bsuir.lab3.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketsOrderDaoHibernateImpl implements TicketsOrderDao {

	@Override
	public void create(TicketsOrder entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public TicketsOrder read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		criteria.add(Restrictions.eq("id", id));
		TicketsOrder ticketOrder = (TicketsOrder) criteria.uniqueResult();
		session.close();
		return ticketOrder;
	}

	@Override
	public TicketsOrder read(Seat seat, FilmSession filmSession) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		criteria.createAlias("tickets", "t");
		criteria.add(Restrictions.eq("t.seat", seat));
		criteria.add(Restrictions.eq("t.filmSession", filmSession));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		TicketsOrder ticketOrder = (TicketsOrder) criteria.uniqueResult();
		session.close();
		return ticketOrder;
	}

	@Override
	public TicketsOrder read(User user) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.add(Restrictions.eq("isPaid", false));
		TicketsOrder ticketOrder = (TicketsOrder) criteria.uniqueResult();
		session.close();
		return ticketOrder;
	}

	@Override
	public void update(TicketsOrder entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(TicketsOrder entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<TicketsOrder> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TicketsOrder> ticketOrders = criteria.list();
		session.close();
		return ticketOrders;
	}

	@Override
	public List<TicketsOrder> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<TicketsOrder> ticketOrders = criteria.list();
		session.close();
		return ticketOrders;
	}

	@Override
	public List<TicketsOrder> readAll(String property, Object value) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(TicketsOrder.class);
		criteria.add(Restrictions.eq(property, value));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<TicketsOrder> ticketOrders = criteria.list();
		session.close();
		return ticketOrders;
	}

}
