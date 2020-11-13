package service;

import dao.UserDAO_Mariadb;
import vo.UserVO;

public class UserServiceImpl implements UserService{
	
	private UserDAO_Mariadb dao = null;

	
	public UserServiceImpl() {
		super();
	}


	public UserServiceImpl(UserDAO_Mariadb dao) {
		super();
		this.dao = dao;
	}


	public UserDAO_Mariadb getDao() {
		return dao;
	}


	public void setDao(UserDAO_Mariadb dao) {
		this.dao = dao;
	}


	@Override
	public UserVO login(UserVO user) {
		return dao.login(user);
	}
	
	

}
