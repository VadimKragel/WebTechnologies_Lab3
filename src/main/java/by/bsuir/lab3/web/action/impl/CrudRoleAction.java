package by.bsuir.lab3.web.action.impl;

import by.bsuir.lab3.entity.Role;
import by.bsuir.lab3.service.RoleService;
import by.bsuir.lab3.web.action.BaseAction;
import by.bsuir.lab3.web.util.ConstantDeclaration;
import by.bsuir.lab3.web.util.HttpRequestParamFormatter;
import by.bsuir.lab3.web.util.HttpRequestParamValidator;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CrudRoleAction implements BaseAction {

	private RoleService roleService;

	public CrudRoleAction() {
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public String executeAction(HttpServletRequest req) {
		List<Role> roles = roleService.getRoleList();
		req.setAttribute(ConstantDeclaration.REQUEST_PARAM_ROLE_LIST, roles);
		if (HttpRequestParamValidator.isPost(req)) {
			String crudCommand = req.getParameter(ConstantDeclaration.REQUEST_PARAM_CRUD_COMMAND);
			HttpRequestParamValidator.validateRequestParamNotNull(crudCommand);
			Role role;

			switch (crudCommand) {
			case ConstantDeclaration.CRUD_OPERATION_NAME_CREATE:
				role = buildRole(req);
				roleService.createRole(role);
				break;
			case ConstantDeclaration.CRUD_OPERATION_NAME_READ:
				String roleId = req.getParameter(ConstantDeclaration.REQUEST_PARAM_ROLE_ID);
				HttpRequestParamValidator.validateRequestParamNotNull(roleId);
				role = roleService.readRole(HttpRequestParamFormatter.getInt(roleId));
				req.setAttribute(ConstantDeclaration.REQUEST_PARAM_FOUND_ROLE, role);
				break;
			case ConstantDeclaration.CRUD_OPERATION_NAME_UPDATE:
				role = buildRole(req);
				roleService.updateRole(role);
				break;
			case ConstantDeclaration.CRUD_OPERATION_NAME_DELETE:
				role = buildRole(req);
				roleService.deleteRole(role);
				break;
			default:
				return ConstantDeclaration.PAGE_ERROR;
			}
		}
		return ConstantDeclaration.PAGE_ADMIN_CRUD_ROLE;
	}

	private Role buildRole(HttpServletRequest req) {
		String id = req.getParameter(ConstantDeclaration.REQUEST_PARAM_ROLE_ID);
		String roleName = req.getParameter(ConstantDeclaration.REQUEST_PARAM_ROLE_NAME);
		HttpRequestParamValidator.validateRequestParamNotNull(id, roleName);
		Role role = new Role();
		role.setId(HttpRequestParamFormatter.getInt(id));
		role.setRoleName(roleName);
		return role;
	}
}
