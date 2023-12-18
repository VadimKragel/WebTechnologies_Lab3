package by.bsuir.lab3.dao.impl;

import by.bsuir.lab3.dao.UserDao;
import by.bsuir.lab3.entity.User;
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
public class UserDaoHibernateImpl implements UserDao {

	@Override
	public void create(User entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public User read(int id) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}
	@Override
	public User read(String login) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public void update(User entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(User entity) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		session.getTransaction().begin();
		session.delete(entity);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<User> readAll() {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("id"));
		List<User> users = criteria.list();
		session.close();
		return users;
	}

	@Override
	public List<User> readAll(String sortingColumn) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(sortingColumn));
		List<User> users = criteria.list();
		session.close();
		return users;
	}

	@Override
	public User readAllWhereEq(Map<String, Object> map) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		for (Entry<String, Object> m : map.entrySet())
			criteria.add(Restrictions.eq(m.getKey(), m.getValue()));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public List<User> readAllUsersWhereRoleIdPresent(int roleId) {
		SessionFactory factory = SessionFactoryManager.getSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("select u from User u inner join u.role r where r.id=:idRole")
				.setParameter("idRole", roleId);
		List<User> users = query.list();
		session.close();
		return users;
	}
}
