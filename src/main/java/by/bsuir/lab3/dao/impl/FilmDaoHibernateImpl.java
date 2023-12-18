package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.FilmDao;
import by.bsuir.lab3.entity.Film;
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
public class FilmDaoHibernateImpl implements FilmDao {

	@Override
	public void create(Film entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public Film read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Film.class);
		criteria.add(Restrictions.eq("id", id));
		Film film = (Film) criteria.uniqueResult();
		session.close();
		return film;
	}

	@Override
	public void update(Film entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Film entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Film> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Film.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Film> films = criteria.list();
		session.close();
		return films;
	}

	@Override
	public List<Film> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Film.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<Film> films = criteria.list();
		session.close();
		return films;
	}

	@Override
	public List<Film> readAll(Map<String, Object> map) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Film.class);
		for (Entry<String, Object> m : map.entrySet())
			criteria.add(Restrictions.eq(m.getKey(), m.getValue()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Film> films = criteria.list();
		session.close();
		return films;
	}

	@Override
	public List<Film> readAllFilmsWhereGenreIdPresent(int genreId) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("select f from Film f inner join f.genres g where g.id=:idGenre")
				.setParameter("idGenre", genreId);
		List<Film> films = query.list();
		session.close();
		return films;
	}
}
