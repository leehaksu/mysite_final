
package com.jx372.mysite.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.GuestBookDao;
import com.jx372.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestbookDao;

	public List<GuestBookVo> getList() {
		// TODO Auto-generated method stub
		System.out.println("[Service] guestbookService");
		return guestbookDao.getList();
	}

	public boolean delete(Long no, String passwd) {
		// TODO Auto-generated method stub
		System.out.println("[Service] guestbookService : no=" +no +", passwd= " + passwd);
		GuestBookVo guestbookvo = new GuestBookVo();
		guestbookvo.setNo(no);
		guestbookvo.setPasswd(passwd);
		return guestbookDao.delete(guestbookvo);
	}
	public boolean insert(GuestBookVo vo)
	{
		System.out.println("[Service] guestbookService : uservo : " + vo);
		guestbookDao.insert(vo);
		
		return false;
	}

}
