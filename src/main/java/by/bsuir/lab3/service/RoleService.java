package by.bsuir.lab3.service;

import by.bsuir.lab3.entity.Role;

import java.util.List;

public interface RoleService extends Service {

	List<Role> getRoleList();

	void createRole(Role role);

	Role readRole(int id);

	void updateRole(Role role);

	void deleteRole(Role role);

	public boolean isAnyFilmContainGenre(int id);
}
