package com.aowin.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.aowin.model.Book;
import com.aowin.model.Bookrecord;
import com.aowin.model.LendBook;
import com.aowin.model.Moneyrecord;
import com.aowin.model.User;

public interface BookMapper {
	public List<Book> ShowBook(Map map);
	public List<Book> SelectAll();
	public User Login(@Param("account") String account,@Param("password") String password);
	public int UpdateBook(int bookid);
	public int addbookrecord(Bookrecord bookrecord);
	public List<LendBook> Showlend(Map map);
	public List<LendBook> Alllend(String userid);
	public int returnrecord(@Param("bookid") int bookid,@Param("userid") int userid,@Param("returntime") String returntime);
	public int returnbook(int bookid);
	public int returnuser(Map map);
	public double Showowing(String userid);
	public int pay(String userid);
	public int payinsert(Moneyrecord moneyrecord);
}
