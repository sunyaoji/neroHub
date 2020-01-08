package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.Model.Youke;

public class JiagouwuListener implements ActionListener{
	private DefaultTableModel model;
	JTable jt;
	public JiagouwuListener(DefaultTableModel model,JTable jt) {
		this.model=model;
		this.jt=jt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("加入购物车")) {
			if(jt.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "请选择选择商品！");
			}else {
				Youke y=new Youke();
				int id=(Integer) model.getValueAt(jt.getSelectedRow(), 0);
				String name=(String) model.getValueAt(jt.getSelectedRow(), 1);
				int number=(Integer) model.getValueAt(jt.getSelectedRow(), 2);
				double price= (Double) model.getValueAt(jt.getSelectedRow(), 3);
				y.jiagouwu(id, name, number, price);
			}
		}
	}

}
