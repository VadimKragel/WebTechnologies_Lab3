package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.entity.Role;
import by.bsuir.lab3.entity.User;
import by.bsuir.lab3.service.RoleService;
import by.bsuir.lab3.service.UserService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.bsuir.lab3.web.util.ConstantDeclaration.*;
import static by.bsuir.lab3.web.util.HttpRequestParamFormatter.getInt;

public class CrudUserAction implements BaseAction {

	private RoleService roleService;
	private UserService userService;

	public CrudUserAction() {
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<User> users = userService.getUserList();
		List<Role> roles = roleService.getRoleList();
		req.setAttribute(REQUEST_PARAM_USER_LIST, users);
		req.setAttribute(REQUEST_PARAM_ROLE_LIST, roles);
		if (HttpRequestParamValidator.isPost(req)) {
			String crudCommand = req.getParameter(REQUEST_PARAM_CRUD_COMMAND);
			HttpRequestParamValidator.validateRequestParamNotNull(crudCommand);
			User user;

			System.out.println(req.getParameter(REQUEST_PARAM_USER_ROLE));

			switch (crudCommand) {
			case CRUD_OPERATION_NAME_CREATE:
				user = buildUser(req);
				userService.createUser(user);
				break;
			case CRUD_OPERATION_NAME_READ:
				String userId = req.getParameter(REQUEST_PARAM_USER_ID);
				HttpRequestParamValidator.validateRequestParamNotNull(userId);
				user = userService.readUser(getInt(userId));
				req.setAttribute(REQUEST_PARAM_FOUND_USER, user);
				break;
			case CRUD_OPERATION_NAME_UPDATE:
				user = buildUser(req);
				userService.updateUser(user);
				break;
			case CRUD_OPERATION_NAME_DELETE:
				user = buildUser(req);
				userService.deleteUser(user);
				break;
			default:
				return PAGE_ERROR;
			}
		}
		return PAGE_ADMIN_CRUD_USER;
	}

	private User buildUser(HttpServletRequest req) {
		String id = req.getParameter(REQUEST_PARAM_USER_ID);
		String login = req.getParameter(REQUEST_PARAM_USER_LOGIN);
		String email = req.getParameter(REQUEST_PARAM_USER_EMAIL);
		String password = req.getParameter(REQUEST_PARAM_USER_PASSWORD);
		String roleId = req.getParameter(REQUEST_PARAM_ROLE_ID);
		System.out.println(id + " " + login + " " + email + " " + password + " " + roleId);
		HttpRequestParamValidator.validateRequestParamNotNull(id, login, email, password, roleId);

		User user = new User();
		Role role = roleService.readRole(getInt(roleId));
		user.setId(getInt(id));
		user.setLogin(login);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		return user;
	}
}
