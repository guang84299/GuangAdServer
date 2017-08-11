package com.guang.web.serviceImpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.base.dao.DaoTools;
import com.guang.web.model.QPermission;
import com.guang.web.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
	@Resource
	private DaoTools daoTools;
	
	public void add(QPermission permission) {
		daoTools.add(permission);
	}

	public void delete(long id) {
		daoTools.delete(QPermission.class, id);
	}

	public void update(QPermission permission) {
		daoTools.update(permission);
	}

	public QPermission find(long id) {
		return daoTools.find(QPermission.class, id);
	}

}
