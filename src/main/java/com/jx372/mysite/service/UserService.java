package com.jx372.mysite.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.UserDao;
import com.jx372.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public boolean join(UserVo uservo)
	{
		System.out.println("[Service] join.Uservo :" + uservo);
		return userdao.insert(uservo);
	}

	public UserVo getUser(UserVo uservo) {
		System.out.println("[Service] getUser.Uservo :" + uservo);
		return userdao.get(uservo);
		// TODO Auto-generated method stub
	}

	public UserVo getUser(Long no) {
		// TODO Auto-generated method stub
		System.out.println("[Service] modify.no :" + no);
		UserVo uservo;
		uservo=userdao.get(no);
		System.out.println("[Service] mybatis vo : " + uservo);
		return uservo;
	}

	public boolean modify(UserVo uservo) {
		// TODO Auto-generated method stub
		System.out.println("[Service] modify.uservo :" + uservo);
		return userdao.update(uservo);
		
	}

	public boolean logout(HttpSession authUser) {
		// TODO Auto-generated method stub
		authUser.removeAttribute("authUser");
		authUser.invalidate();
		
		return true;
	}
	

}
