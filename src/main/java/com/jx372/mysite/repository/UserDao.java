package com.jx372.mysite.repository;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	/*@Autowired
	private SqlSession sqlSession;*/
	
	@Autowired
	private SqlSession spqlSession;

	// 수정폼
	public UserVo get(Long no) {

		UserVo vo=spqlSession.selectOne("user.getByNo",no);
		/*Map를 ResultType으로 사용하는 예제*/
		/*Map map= sqlSession.selectOne("user.getByNo",no);*/
		return vo;
	}

	// 로그인 처리
	public UserVo get(String email, String password) {
		UserVo vo=null;
		
		return vo;

	}

	public boolean insert(UserVo vo) {
		int count=spqlSession.insert("user.insert");
		return count==1;
	}

	public boolean update(UserVo vo) {
		// TODO Auto-generated method stub
		int count=spqlSession.update("user.update",vo);
		return count==1;	
	}
}
