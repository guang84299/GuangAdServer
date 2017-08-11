package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.web.model.QPermission;

@Service
public interface PermissionService {

	void add(QPermission permission);
	void delete(long id);
	void update(QPermission permission);
	QPermission find(long id);
}
