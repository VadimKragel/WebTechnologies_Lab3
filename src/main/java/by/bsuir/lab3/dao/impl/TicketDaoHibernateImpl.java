package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.TicketDao;
import by.bsuir.lab3.entity.Ticket;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketDaoHibernateImpl implements TicketDao {

	@Override
	public void create(Ticket entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Ticket read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("id", id));
		Ticket ticket = (Ticket) criteria.uniqueResult();
		session.close();
		return ticket;
	}

	@Override
	public void update(Ticket entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Ticket entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Ticket> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Ticket.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Ticket> tickets = criteria.list();
		session.close();
		return tickets;
	}

	@Override
	public List<Ticket> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Ticket.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<Ticket> tickets = criteria.list();
		session.close();
		return tickets;
	}

	@Override
	public List<Ticket> readAll(String property, Object value) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Ticket.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq(property, value));
		List<Ticket> tickets = criteria.list();
		session.close();
		return tickets;
	}

}
