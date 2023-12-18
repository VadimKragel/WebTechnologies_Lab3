package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends Service {

	List<User> getUserList();

	void createUser(User user);

	User readUser(int id);

	User readUser(String login);

	User readUser(String parametre, Object value);

	User readUser(Map<String, Object> map);

	void updateUser(User user);

	void deleteUser(User user);

}
