package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.aowin.Uitls.Daochu;
import com.aowin.Uitls.GoodsDao;

public class JiesuanListener implements ActionListener{
	static int id;
	static int num1;
	static int num2;
	DefaultTableModel model1;
	static DefaultTableModel model2;
	static JTable jt1;
	static JTable jt2;
	public JiesuanListener(DefaultTableModel model1,JTable jt1) {
		this.model1=model1;
		this.jt1=jt1;
	}
	public JiesuanListener(DefaultTableModel model2) {
		this.model2=model2;
	}
	public JiesuanListener(JTable jt2) {
		this.jt2=jt2;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("删除")) {
			if(jt1.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "请选择你想删除的行！");
				return;
			}
			id=(Integer) model1.getValueAt(jt1.getSelectedRow(), 0);
			num1=(Integer) model2.getValueAt(id-1,2);
			num2=(Integer) model1.getValueAt(jt1.getSelectedRow(),2);
			jt2.setValueAt(num1+num2,id-1,2);
			model1.removeRow(jt1.getSelectedRow());
			for(int i=0;i<Gouwuchejiaru.al1.size();i++) {
			if(Gouwuchejiaru.al1.get(i).getId()==id) {
			 Gouwuchejiaru.al1.remove(i);
			 }
		}
		}
		if(e.getActionCommand().equals("结算")) {
			double end=0;
			for(int i=0;i<model1.getRowCount();i++) {
				int num=(Integer) model1.getValueAt(i,2);
				double price=(Double) model1.getValueAt(i,3);
				end+=num*price;
			}
			if(JOptionPane.showConfirmDialog(null, "你的购买总金额为"+end+"元，确定结算吗？")==0) {
				if(model1.getRowCount()==0) {
					JOptionPane.showMessageDialog(null, "购物车是空的！");
					return;
				}
				GoodsDao gd=new GoodsDao();
				try {
					gd.deleteall();
				} catch (SQLException e1) {
				}
				for(int i=0;i<model2.getRowCount();i++) {
					int id=(Integer) model2.getValueAt(i, 0);
					String name=(String) model2.getValueAt(i, 1);
					int num=(Integer) model2.getValueAt(i,2);
					double price=(Double) model2.getValueAt(i,3);
					try {
						gd.add(id, name, num, price);
					} catch (SQLException e1) {
					}
				}
				JOptionPane.showMessageDialog(null, "结算成功！");
				Daochu dc=new Daochu(end);
				try {
					dc.xieru();
				} catch (IOException e1) {
				}
				model1.setRowCount(0);
				Gouwuchejiaru.al1.clear();
			}
		}

	}
}
