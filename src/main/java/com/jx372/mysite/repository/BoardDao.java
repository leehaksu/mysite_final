package com.jx372.mysite.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public int getSize() {
		int count=0;
		System.out.println(" 검색 결과 : " + count);
		count = sqlSession.selectOne("board.getsize");
		return count;
	}

	public List<BoardVo> getList(int page) {
		System.out.println("[DAO] getList  page : " );
		List<BoardVo> list = new ArrayList<BoardVo>();
		list = sqlSession.selectList("board.getList", page);
		System.out.println("[DAO] getList size="+list.size());
		return list;

	}

	public BoardVo get(Long no) {
		BoardVo vo = sqlSession.selectOne("board.get", no);

		return vo;
	}

	public boolean insert(BoardVo vo) {
		System.out.println("[DAO] boardVo :" + vo);
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	public boolean update(Long no) {
		int count = sqlSession.update("board.updateHit", no);
		return count == 1;
	}

	public boolean update(int group_no,int boardNo) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("group_no", group_no);
		map.put("no", boardNo);
		int count = sqlSession.update("board.updateOrder", map);
		return count == 1;
	}

	public boolean update(String title, String content, Long boardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("[DAO] update 부분 진입");
		map.put("title", title);
		map.put("content", content);
		map.put("no", boardNo);

		int count = sqlSession.update("board.update", map);
		return count == 1;
	}

	public BoardVo select(Long no) {
		BoardVo vo = sqlSession.selectOne("board.select", no);
		return vo;
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("board.delete", no);
		return count == 1;
	}

}
