package com.aowin.Model;

import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.Listener.Gouwuchejiaru;
import com.aowin.Listener.JiesuanListener;
import com.aowin.Uitls.Goods;
import com.aowin.Uitls.GoodsDao;

public class Gouwuche {
	public static DefaultTableModel model;
	public static JTable jt;
	public void build() throws SQLException {
		JFrame jf=new JFrame("欢迎来到购物车界面！");
		jf.setSize(500, 600);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=jf.getContentPane();
		con.setLayout(new FlowLayout());
		JLabel jl1=new JLabel("购物车");
		con.add(jl1);
		Object []o= {"编号","商品名","数量","价格"};
		model=new DefaultTableModel(o,0);
		jt=new JTable(model);
		JScrollPane js=new JScrollPane(jt);
		con.add(js);
		JButton jb1=new JButton("删除");
		con.add(jb1);
		JButton jb2=new JButton("结算");
		con.add(jb2);
		jf.setVisible(true);
		JiesuanListener jsl=new JiesuanListener(model,jt);
		jb1.addActionListener(jsl);
		jb2.addActionListener(jsl);
		GoodsDao gd=new GoodsDao();
		GoodsDao.l1.clear();
		ArrayList<Goods> al=Gouwuchejiaru.al1;
		for(int i=0;i<al.size();i++) {
			Goods g=al.get(i);
			int id=g.getId();
			String name=g.getName();
			int number=g.getNum();
			double price=g.getPrice();
			Object row[] ={id,name,number,price};
			model.addRow(row);
		}
}
}
