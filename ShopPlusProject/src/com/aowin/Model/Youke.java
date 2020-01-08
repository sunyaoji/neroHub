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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.aowin.Listener.ChakanListener;
import com.aowin.Listener.Gouwuchejiaru;
import com.aowin.Listener.JiagouwuListener;
import com.aowin.Listener.JiesuanListener;
import com.aowin.Uitls.Goods;
import com.aowin.Uitls.GoodsDao;

public class Youke {
	public static DefaultTableModel model;
	public static JTable jt;
	public void jiagouwu(int id,String name,int number,double price) {
		String id1=String.valueOf(id);
		String name1=name;
		String number1=String.valueOf(number);
		String price1=String.valueOf(price);
		JFrame jf=new JFrame("��Ʒ����");
		jf.setSize(180, 300);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=jf.getContentPane();
		con.setLayout(new FlowLayout());
		JLabel jl1=new JLabel("���");
		con.add(jl1);
		JTextField jtf1=new JTextField(12);
		jtf1.setText(id1);
		jtf1.setEditable(false);
		con.add(jtf1);
		JLabel jl2=new JLabel("��Ʒ��");
		con.add(jl2);
		JTextField jtf2=new JTextField(12);
		jtf2.setText(name1);
		jtf2.setEditable(false);
		con.add(jtf2);
		JLabel jl3=new JLabel("����");
		con.add(jl3);
		JTextField jtf3=new JTextField(12);
		jtf3.setText(number1);
		con.add(jtf3);
		JLabel jl4=new JLabel("�۸�");
		con.add(jl4);
		JTextField jtf4=new JTextField(12);
		jtf4.setText(price1);
		jtf4.setEditable(false);
		con.add(jtf4);
		JButton jb1=new JButton("�ӹ��ﳵ");
		con.add(jb1);
		jf.setVisible(true);
		Gouwuchejiaru gwj=new Gouwuchejiaru(number1,jtf3,jf,jtf1,jtf2,jtf4);
		jb1.addActionListener(gwj);
	}
	public void build() throws SQLException {
		JFrame jf=new JFrame("��ӭ�����ο͹�����棡");
		jf.setSize(500, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=jf.getContentPane();
		con.setLayout(new FlowLayout());
		JLabel jl1=new JLabel("�ο͹���ϵͳ");
		con.add(jl1);
		Object []o= {"���","��Ʒ��","����","�۸�"};
		model=new DefaultTableModel(o,0);
		jt=new JTable(model);
		JScrollPane js=new JScrollPane(jt);
		con.add(js);
		JButton jb1=new JButton("���빺�ﳵ");
		con.add(jb1);
		JButton jb2=new JButton("�鿴���ﳵ");
		con.add(jb2);
		jf.setVisible(true);
		ChakanListener ck=new ChakanListener();
		jb2.addActionListener(ck);
		GoodsDao gd=new GoodsDao();
		ArrayList<Goods> al=gd.selectAll();
		for(int i=0;i<al.size();i++) {
			Goods g=al.get(i);
			int id=g.getId();
			String name=g.getName();
			int number=g.getNum();
			double price=g.getPrice();
			Object row[] ={id,name,number,price};
			model.addRow(row);
		}
		JiesuanListener jsl=new JiesuanListener(jt);
		JiesuanListener jsl2=new JiesuanListener(model);
		Gouwuchejiaru gwcjr=new Gouwuchejiaru(model, jt);
		JiagouwuListener jgwl=new JiagouwuListener(model, jt);
		jb1.addActionListener(jgwl);
}
}
