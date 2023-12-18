package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.FilmSessionDao;
import by.bsuir.lab3.entity.FilmSession;
import by.bsuir.lab3.entity.Seat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class FilmSessionDaoHibernateImpl implements FilmSessionDao {

	@Override
	public void create(FilmSession entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public FilmSession read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FilmSession.class);
		criteria.add(Restrictions.eq("id", id));
		FilmSession filmSession = (FilmSession) criteria.uniqueResult();
		session.close();
		return filmSession;
	}

	@Override
	public void update(FilmSession entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(FilmSession entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<FilmSession> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FilmSession.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FilmSession> filmSessions = criteria.list();
		session.close();
		return filmSessions;
	}

	@Override
	public List<FilmSession> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FilmSession.class);
		// delete duplicates in "left outer join" query
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<FilmSession> filmSessions = criteria.list();
		session.close();
		return filmSessions;
	}

	@Override
	public List<FilmSession> readAll(String property, Object value) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FilmSession.class);
		criteria.add(Restrictions.eq(property, value));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FilmSession> filmSessions = criteria.list();
		session.close();
		return filmSessions;
	}

	@Override
	public List<FilmSession> readAll(Map<String, Object> map) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(FilmSession.class);
		for (Entry<String, Object> m : map.entrySet())
			criteria.add(Restrictions.eq(m.getKey(), m.getValue()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FilmSession> filmSessions = criteria.list();
		session.close();
		return filmSessions;
	}

	@Override
	public List<FilmSession> readAllWhereSeatNotFree(Seat seat) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Query query = session
				.createQuery("select fs from FilmSession as fs inner join fs.tickets as t where t.seat.id = :idSeat")
				.setParameter("idSeat", seat.getId());
		List<FilmSession> FilmSessions = query.list();
		session.close();
		return FilmSessions;
	}

}
