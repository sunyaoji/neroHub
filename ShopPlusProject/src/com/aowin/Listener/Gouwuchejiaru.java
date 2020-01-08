package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.aowin.Uitls.Goods;

public class Gouwuchejiaru implements ActionListener{
	public static ArrayList<Goods> al1=new ArrayList<Goods>();
	DefaultTableModel model;
	static JTable jt;
	JTable jt1;
	JTextField jtf3;
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf4;
	int number1;
	int number2;
	JFrame jf;
	public Gouwuchejiaru(DefaultTableModel model,JTable jt) {
		this.model=model;
		this.jt=jt;
	}
	public Gouwuchejiaru(String number,JTextField jtf3,JFrame jf,JTextField jtf1,JTextField jtf2,JTextField jtf4) {
		this.number1=Integer.parseInt(number);
		this.jtf1=jtf1;
		this.jtf2=jtf2;
		this.jtf4=jtf4;
		this.jtf3=jtf3;
		this.jf=jf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("加购物车")) {
			try {
			number2=Integer.parseInt(jtf3.getText());
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "请输入正确的格式！");
				jtf3.setText("");
				return;
			}catch(NullPointerException e2) {
				return;
			}
			if(number2>number1||number2<=0) {
				JOptionPane.showMessageDialog(null, "输入商品数量有误,请重新输入！");
				jtf3.setText("");
			}else {
				int id=Integer.parseInt(jtf1.getText());
				String name=jtf2.getText();
				int num=number2;
				double price=Double.parseDouble(jtf4.getText());
				int y=0;
				int w=0;
				for(int i=0;i<al1.size();i++) {
				if(al1.get(i).getId()==id) {
					y=1;
					w=i;
				}
				}
				if(y==1) {
					al1.get(w).setNum(al1.get(w).getNum()+num);
					jt.setValueAt(number1-number2, jt.getSelectedRow(), 2);
				}
				else {
					Goods g=new Goods(id,name,num,price);
					al1.add(g);
					jt.setValueAt(number1-number2, jt.getSelectedRow(), 2);
				}
				JOptionPane.showMessageDialog(null, "购物车添加成功！");
				jf.setVisible(false);
			}
		}
	}

}
