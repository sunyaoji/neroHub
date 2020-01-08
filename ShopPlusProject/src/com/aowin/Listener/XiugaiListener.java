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

public class XiugaiListener implements ActionListener{
	private static DefaultTableModel model;
	static JTable jt;
	JTextField jtf;
	JFrame jf;
	public XiugaiListener(JFrame jf,JTextField jtf) {
		this.jtf=jtf;
		this.jf=jf;
	}
	public XiugaiListener(DefaultTableModel model,JTable jt) {
		this.model=model;
		this.jt=jt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("修改商品名")) {
			if(jt.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "请选择选择商品！");
			}else {
				Guanli g=new Guanli();
				int id=(Integer) model.getValueAt(jt.getSelectedRow(), 0);
				String name=(String) model.getValueAt(jt.getSelectedRow(), 1);
				int number=(Integer) model.getValueAt(jt.getSelectedRow(), 2);
				double price= (Double) model.getValueAt(jt.getSelectedRow(), 3);
				g.xiugai(id, name, number, price);
			}
		}
		if(e.getActionCommand().equals("确认修改")) {
			if(jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "名字不能为空！");
				return;
			}else {
				String name=jtf.getText();
				model.setValueAt(name, jt.getSelectedRow(), 1);
				JOptionPane.showMessageDialog(null, "修改成功！");
				jf.setVisible(false);
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
