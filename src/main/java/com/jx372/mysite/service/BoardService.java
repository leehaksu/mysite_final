package com.jx372.mysite.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.BoardDao;
import com.jx372.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList(int page) {
		// TODO Auto-generated method stub
		System.out.println("[Service] getList : firstNumber : " + page);
		List <BoardVo> list = new ArrayList<BoardVo>();
		list = boardDao.getList(page);
		return list;
		
	}
	public boolean write(BoardVo vo) {
		// TODO Auto-generated method stub
		System.out.println("[Service] BoardVo :" +vo );
		boolean check= boardDao.insert(vo);
		return check;
		
		
	}
	public BoardVo view(Long no) {
		// TODO Auto-generated method stub
		System.out.println("[Service] no : " + no);
		BoardVo vo = (BoardVo) boardDao.select(no);
		return vo;
		
	}
	public  boolean modify(BoardVo vo) {
		// TODO Auto-generated method stub
		System.out.println("[Service] BoardVo : " +vo);
		boolean check=boardDao.update(vo.getTitle(),vo.getContent(),vo.getNo());
		return check;
	}
	public boolean updateOrder(Long no)
	{
		System.out.println("[Service] updateOrder.No :" + no);
		BoardVo vo = boardDao.select(no);
		boolean check=boardDao.update(vo.getGroup_no(),vo.getOrder_no());
		return check;
	}
	public boolean updateHit(Long no) {
		// TODO Auto-generated method stub
		boolean check= boardDao.update(no);
		return check;
	}
	public BoardVo get(Long no)
	{
		BoardVo vo=boardDao.get(no);
		return vo;
	}


	
	
}
