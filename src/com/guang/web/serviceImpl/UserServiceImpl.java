package com.guang.web.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.base.dao.DaoTools;
import com.guang.base.dao.QueryResult;
import com.guang.web.model.User;
import com.guang.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private DaoTools daoTools;

	public void add(User user) {
		daoTools.add(user);
	}

	public void delete(long id) {
		daoTools.delete(User.class, id);
	}

	public void update(User user) {
		daoTools.update(user);
	}

	public User find(long id) {
		return daoTools.find(User.class, id);
	}

	public User find(String name) {
		List<User> list = daoTools.find(User.class, "name", name, 0, 1, null).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<User> findAll() {
		return daoTools.find(User.class, null, null, 0, 1000000, null);
	}

}
