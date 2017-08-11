package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.web.model.QPermission;
import com.guang.web.model.User;
import com.guang.web.service.PermissionService;
import com.guang.web.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	private static final long serialVersionUID = 1L;
	
	@Resource private UserService userService;
	@Resource private PermissionService permissionService;
	
	public String list() {

		List<User> list = userService.findAll().getList();
		for(User u : list)
		{
			u.setPermission(permissionService.find(u.getPermissionsId()));
		}
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pages", "user");
		return "index";
	}
	
	public String login()
	{
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			User user = userService.find(name);
			if(user != null && user.getPassword().equals(password))
			{
				QPermission permission = permissionService.find(user.getPermissionsId());
				user.setPermission(permission);
				ActionContext.getContext().getSession().put("user", user);
				ActionContext.getContext().put("login","登录成功！");
				logger.info(name + " 登录成功！");
				return "index";
			}
		}
		ActionContext.getContext().put("login","用户名或密码不正确！");
		return "index";
	}
	
	public String loginout()
	{
		User u = (User) ActionContext.getContext().getSession().get("user");
		logger.info(u.getName() + " 退出成功！");
		ActionContext.getContext().getSession().put("user", null);
		return "index";
	}
	
	public String addUser()
	{
		User u = (User) ActionContext.getContext().getSession().get("user");
		if(!u.getPermission().isAddUser())
		{
			ActionContext.getContext().put("addUser","没有权限！");
			list();
			return "index";
		}
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		String addUser = ServletActionContext.getRequest().getParameter("addUser");
		String deleteUser = ServletActionContext.getRequest().getParameter("deleteUser");
		String updateUser = ServletActionContext.getRequest().getParameter("updateUser");
		String model_user = ServletActionContext.getRequest().getParameter("model_user");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			QPermission permission = new QPermission();		
			permission.setAddUser(addUser!=null);
			permission.setDeleteUser(deleteUser!=null);
			permission.setUpdateUser(updateUser!=null);
			permission.setModel_user(model_user!=null);
			permissionService.add(permission);
			
			userService.add(new User(permission.getId(),name, password));
			ActionContext.getContext().put("addUser","用户添加成功！");
			ActionContext.getContext().put("pages", "user");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("addUser","用户添加失败！");
		ActionContext.getContext().put("pages", "user");
		return "index";
	}
	
	public String deleteUser()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(!user.getPermission().isDeleteUser())
		{
			ActionContext.getContext().put("deleteUser","没有权限！");
			list();
			return "index";
		}
		
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			User u = userService.find(Long.parseLong(data));
			userService.delete(u.getId());
			permissionService.delete(u.getPermissionsId());
			ActionContext.getContext().put("deleteUser","用户删除成功！");
		}
		list();
		return "index";
	}
	
	public void findUser()
	{
		String data = ServletActionContext.getRequest().getParameter("data");
		if(data != null && !"".equals(data))
		{
			User u = userService.find(Long.parseLong(data));
			QPermission permission = permissionService.find(u.getPermissionsId());
			u.setPermission(permission);
			print(JSONObject.fromObject(u).toString());
		}		
	}
	
	public String updateUser()
	{
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(!user.getPermission().isDeleteUser())
		{
			ActionContext.getContext().put("updateUser","没有权限！");
			list();
			return "index";
		}
		
		String name = ServletActionContext.getRequest().getParameter("name");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		String addUser = ServletActionContext.getRequest().getParameter("addUser");
		String deleteUser = ServletActionContext.getRequest().getParameter("deleteUser");
		String updateUser = ServletActionContext.getRequest().getParameter("updateUser");
		String model_user = ServletActionContext.getRequest().getParameter("model_user");
		
		if(name != null && !"".equals(name) && password != null && !"".equals(password))
		{
			User u = userService.find(name);
			u.setPassword(password);
			
			QPermission permission = permissionService.find(u.getPermissionsId());		
			permission.setAddUser(addUser!=null);
			permission.setDeleteUser(deleteUser!=null);
			permission.setUpdateUser(updateUser!=null);
			permission.setModel_user(model_user!=null);
			permissionService.update(permission);
			
			userService.update(u);
			
			ActionContext.getContext().put("updateUser","用户更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateUser","用户更改失败！");
		return "index";
	}
	
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		QPermission permission = new QPermission();
		permission.setAddUser(true);
		permission.setDeleteUser(true);
		permission.setUpdateUser(true);
		permission.setModel_user(true);
		permissionService.add(permission);
		
		userService.add(new User(permission.getId(),"guang", "920616"));
	}

}
