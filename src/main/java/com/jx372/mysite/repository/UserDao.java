package com.jx372.mysite.repository;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	/*@Autowired
	private SqlSession sqlSession;*/
	
	@Autowired
	private SqlSession sqlSession;

	// 수정폼
	public UserVo get(Long no) {
		System.out.println("[DAO] get.no :" + no);
		UserVo vo=sqlSession.selectOne("user.getByNo",no);
		System.out.println("[DAO] mybatis vo : " + vo);
		/*Map를 ResultType으로 사용하는 예제*/
		/*Map map= sqlSession.selectOne("user.getByNo",no);*/
		return vo;
	}

	// 로그인 처리
	public UserVo get(UserVo uservo) {
		System.out.println("[DAO] email :" + uservo.getEmail()+" password :" + uservo.getPassword());
		return (UserVo) sqlSession.selectOne("getByEp",uservo);
	}

	public boolean insert(UserVo vo) {
		System.out.println("[DAO] insert.UserVo :" + vo);
		int count=sqlSession.insert("user.insert",vo);
		return count==1;
	}

	public boolean update(UserVo vo) {
		System.out.println("[DAO] update.UserVo :" + vo);
		// TODO Auto-generated method stub
		int count=sqlSession.update("user.update",vo);
		return count==1;	
	}
}
