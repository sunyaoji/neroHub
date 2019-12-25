package com.aowin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aowin.dao.BookMapper;
import com.aowin.model.Book;
import com.aowin.model.Bookrecord;
import com.aowin.model.LendBook;
import com.aowin.model.Moneyrecord;
import com.aowin.model.User;
@Service
public class BookService {
	@Autowired
	private BookMapper bookmapper;

	@Transactional(propagation=Propagation.SUPPORTS)
public List<Book> ShowBook(Map map) {
	List<Book> book=bookmapper.ShowBook(map);
	return book;
}

	@Transactional(propagation=Propagation.SUPPORTS)
public List<Book> SelectAll() {
	List<Book> book=bookmapper.SelectAll();
	return book;
}
	
	@Transactional(propagation=Propagation.SUPPORTS)
public User Login(String account,String password) {
	User user=bookmapper.Login(account, password);
	return user;
}

	@Transactional(propagation=Propagation.REQUIRED)
public int Borrow(Bookrecord bookrecord) {
	bookmapper.UpdateBook(bookrecord.getBookid());
	int n=bookmapper.addbookrecord(bookrecord);
	return n;
}
	@Transactional(propagation=Propagation.SUPPORTS)
public List<LendBook> Showlend(Map map) {
	List<LendBook> lendbook=bookmapper.Showlend(map);
	return lendbook;
}
	@Transactional(propagation=Propagation.SUPPORTS)
public List<LendBook> Alllend(String userid) {
	List<LendBook> lendbook=bookmapper.Alllend(userid);
	return lendbook;
}
@Transactional(propagation=Propagation.REQUIRED)
public int returnrecord(int bookid,int userid,String returntime) {
	int n=bookmapper.returnrecord(bookid, userid, returntime);
	return n;	
}
@Transactional(propagation=Propagation.REQUIRED)
public int returnbook(int bookid) {
	int n=bookmapper.returnbook(bookid);
	return n;	
}
@Transactional(propagation=Propagation.REQUIRED)
public int returnuser(Map map) {
	int n=bookmapper.returnuser(map);
	return n;	
}
@Transactional(propagation=Propagation.SUPPORTS)
public double Showowing(String userid) {
	double owing=bookmapper.Showowing(userid);
	return owing;	
}
@Transactional(propagation=Propagation.REQUIRED)
public int Pay(String userid) {
	int n=bookmapper.pay(userid);
	return n;	
}
@Transactional(propagation=Propagation.REQUIRED)
public int payinsert(Moneyrecord moneyrecord) {
	int n=bookmapper.payinsert(moneyrecord);
	return n;
}
}
