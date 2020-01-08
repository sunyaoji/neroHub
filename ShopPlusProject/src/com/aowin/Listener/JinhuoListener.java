package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.aowin.Model.Guanli;
import com.aowin.Uitls.GoodsDao;

public class JinhuoListener implements ActionListener{
	private static DefaultTableModel model;
	static JTable jt;
	JTextField jtf;
	JFrame jf;
	public JinhuoListener(JFrame jf,JTextField jtf) {
		this.jtf=jtf;
		this.jf=jf;
	}
	public JinhuoListener(DefaultTableModel model,JTable jt) {
		this.model=model;
		this.jt=jt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("进货")) {
			if(jt.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "请选择选择商品！");
			}else {
				Guanli g=new Guanli();
				int id=(Integer) model.getValueAt(jt.getSelectedRow(), 0);
				String name=(String) model.getValueAt(jt.getSelectedRow(), 1);
				int number=(Integer) model.getValueAt(jt.getSelectedRow(), 2);
				double price= (Double) model.getValueAt(jt.getSelectedRow(), 3);
				g.jinhuo(id, name, number, price);
			}
		}
		if(e.getActionCommand().equals("确认进货")) {
			try{
			if(jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "数量不能为空！");
				return;
			}
			int number1=(Integer) model.getValueAt(jt.getSelectedRow(), 2);
			int number2=Integer.parseInt(jtf.getText());
			if(number2<0) {
				JOptionPane.showMessageDialog(null, "请输入正确的数量！");
				jtf.setText("");
				return;
			}
			int number3=number1+number2;
			JOptionPane.showMessageDialog(null, "进货成功！");
			model.setValueAt(number3, jt.getSelectedRow(), 2);
			jf.setVisible(false);
			}catch(NumberFormatException e0) {
				JOptionPane.showMessageDialog(null, "请输入正确的格式！");
				jtf.setText("");
			}
			GoodsDao gd=new GoodsDao();
			try {
				gd.deleteall();
			} catch (SQLException e1) {
			}
			for(int i=0;i<model.getRowCount();i++) {
				int id=(Integer) model.getValueAt(i, 0);
				String name=(String) model.getValueAt(i, 1);
				int num=(Integer) model.getValueAt(i,2);
				double price=(Double) model.getValueAt(i,3);
				try {
					gd.add(id, name, num, price);
				} catch (SQLException e1) {
				}
		}
	}
}
}
