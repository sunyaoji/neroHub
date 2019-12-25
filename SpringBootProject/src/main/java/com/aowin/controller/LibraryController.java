package com.aowin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aowin.model.Book;
import com.aowin.model.Bookrecord;
import com.aowin.model.LendBook;
import com.aowin.model.Moneyrecord;
import com.aowin.model.User;
import com.aowin.service.impl.BookService;
@Controller
public class LibraryController {
	@Autowired
	private BookService bookservice;
	
	@RequestMapping("/Tuichu.do")
	public String tuichu(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/temp.htm";
	}
	
	@RequestMapping("/ShowBook.do")
	public String showBook(String page,Model model) {
		Map map=new HashMap();
		int nowpage=1;
		int pagesize=5;
		if(page!=null) {
			nowpage=Integer.parseInt(page);
		}
		List<Book> booklist=bookservice.SelectAll();
		int totalpage=booklist.size()%pagesize==0?booklist.size()/pagesize:(booklist.size()/pagesize)+1;
		map.put("begin", (nowpage-1)*pagesize);
		map.put("pagesize", pagesize);
		List<Book> pagelist=bookservice.ShowBook(map);
		model.addAttribute("booklist", booklist);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pagelist", pagelist);
		model.addAttribute("page", nowpage);
		model.addAttribute("chapage", 0);
		return "book/bookmain.jsp";
	}
	
	@RequestMapping("/Login.do")
	public String login(HttpServletRequest request) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User u = bookservice.Login(account, password);
		if(u==null) {
			request.setAttribute("error", "用户名或密码错误");
			return "/login.jsp";
		}else {
			request.getSession().setAttribute("user", u);
			return "redirect:/index.jsp";
		}
	}
	
	@RequestMapping("/Borrow.do")
	public String Borrow(Bookrecord bookrecord,Model model) {
		List<LendBook> booklist=bookservice.Alllend(String.valueOf(bookrecord.getUserid()));
		int z=0;
		for(LendBook l:booklist) {
			if(l.getFreeday()<0) {
				z=1;
				break;
			}
		}
		if(booklist.size()>=5) {
			model.addAttribute("msg","失败五本");
		}else if(z==1) {
			model.addAttribute("msg","失败超期");
		}
		else {
			int n=bookservice.Borrow(bookrecord);
			if(n!=0) {
			model.addAttribute("msg","成功");}else {
				model.addAttribute("msg","失败");
			}
		}
		return "/ShowBook.do";
	}
	
	@RequestMapping("/ShowBorrow.do")
	public String ShowBorrow(String page,Model model,String userid) {
		Map map=new HashMap();
		int nowpage=1;
		int pagesize=5;
		if(page!=null) {
			nowpage=Integer.parseInt(page);
		}
		List<LendBook> booklist=bookservice.Alllend(userid);
		int totalpage=booklist.size()%pagesize==0?booklist.size()/pagesize:(booklist.size()/pagesize)+1;
		map.put("begin", (nowpage-1)*pagesize);
		map.put("pagesize", pagesize);
		map.put("userid", userid);
		List<LendBook> pagelist=bookservice.Showlend(map);
		System.out.println(booklist.size());
		System.out.println(booklist.size());
		model.addAttribute("booklist", booklist);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("pagelist", pagelist);
		model.addAttribute("page", nowpage);
		return "book/bookback.jsp";
	}
	
	@RequestMapping("/Return.do")
	public String Return(String[] bookid,String userid,String returntime,Model model,Moneyrecord moneyrecord) {
		List<LendBook> booklist=bookservice.Alllend(userid);
		Map map=new HashMap();
		double owing=bookservice.Showowing(userid);
		double change=0;
		for(LendBook l:booklist) {
			if(l.getFreeday()<0) {
				change=change+l.getFreeday()*0.2*(-1);
				owing=owing+l.getFreeday()*0.2*(-1);
			}
		}
		if(change!=0) {
			moneyrecord.setMoney(change);
			bookservice.payinsert(moneyrecord);
		}
		map.put("owing", owing);
		map.put("userid", userid);
		for(String bid:bookid) {
			int bookid0=Integer.parseInt(bid);
			int userid0=Integer.parseInt(userid);
		bookservice.returnrecord(bookid0, userid0, returntime);
		bookservice.returnbook(bookid0);
		}
		bookservice.returnuser(map);
		model.addAttribute("msg", "成功");
		return "/ShowBorrow.do";
	}
	
	@RequestMapping("/ShowOwing.do")
	public String ShowOwing(String userid,Model model) {
		double owing=bookservice.Showowing(userid);
		model.addAttribute("owing",owing);
		return "book/pay.jsp";
	}
	
	@RequestMapping("/Pay.do")
	public String Pay(String userid,Model model,Moneyrecord moneyrecord) {
		bookservice.payinsert(moneyrecord);
		int n=bookservice.Pay(userid);
		if(n!=0) {
			model.addAttribute("msg","成功");
		}else {
			model.addAttribute("msg","失败");
		}
		return "ShowOwing.do";
	}
}
