package by.bsuir.lab3.dao;

import by.bsuir.lab3.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao extends BaseDao<User> {

	/*
	 * User read(String login);
	 * 
	 * User read(String email);
	 */

	User readAllWhereEq(Map<String,Object> map);

	List<User> readAllUsersWhereRoleIdPresent(int genreId);

	User read(String login);
}
