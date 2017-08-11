package com.guang.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "q_permission")
public class QPermission {
	private long id;
	private boolean addUser = false;//添加用户
	private boolean deleteUser = false;
	private boolean updateUser = false;
	private boolean model_user = false;//用户模块
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isAddUser() {
		return addUser;
	}
	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}
	public boolean isDeleteUser() {
		return deleteUser;
	}
	public void setDeleteUser(boolean deleteUser) {
		this.deleteUser = deleteUser;
	}
	public boolean isUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(boolean updateUser) {
		this.updateUser = updateUser;
	}
	public boolean isModel_user() {
		return model_user;
	}
	public void setModel_user(boolean model_user) {
		this.model_user = model_user;
	}
	
	
}
