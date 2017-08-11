package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.base.dao.QueryResult;
import com.guang.web.model.User;
@Service
public interface UserService {

	void add(User user);
	void delete(long id);
	void update(User user);
	User find(long id);
	User find(String name);
	QueryResult<User> findAll();
}
