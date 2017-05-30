package com.jx372.mysite.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GuestBookVo;



@Repository
public class GuestBookDao{

	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insert(GuestBookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		
		return count==1;
	}

	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		list = sqlSession.selectList("guestbook.getList");
		return list;
	}

	public boolean delete(GuestBookVo vo) {
		int count =sqlSession.delete("guestbook.delete", vo);
		
		return count==1;
	}
}
