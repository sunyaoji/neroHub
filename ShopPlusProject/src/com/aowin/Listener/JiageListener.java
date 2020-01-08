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

public class JiageListener implements ActionListener{
	private static DefaultTableModel model;
	static JTable jt;
	JTextField jtf;
	JFrame jf;
	public JiageListener(JFrame jf,JTextField jtf) {
		this.jtf=jtf;
		this.jf=jf;
	}
	public JiageListener(DefaultTableModel model,JTable jt) {
		this.model=model;
		this.jt=jt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("�۸����")) {
			if(jt.getSelectedRow()<0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ѡ����Ʒ��");
			}else {
				Guanli g=new Guanli();
				int id=(Integer) model.getValueAt(jt.getSelectedRow(), 0);
				String name=(String) model.getValueAt(jt.getSelectedRow(), 1);
				int number=(Integer) model.getValueAt(jt.getSelectedRow(), 2);
				double price= (Double) model.getValueAt(jt.getSelectedRow(), 3);
				g.jiage(id, name, number, price);
			}
		}
		if(e.getActionCommand().equals("ȷ�ϵ����۸�")) {
			if(jtf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�۸���Ϊ�գ�");
				return;}
			else {
			try{
				double price=Double.parseDouble(jtf.getText());
				if(price<=0) {
					JOptionPane.showMessageDialog(null, "��������ȷ�ļ۸�");
					jtf.setText("");
					return;
				}
				JOptionPane.showMessageDialog(null, "�۸�����ɹ���");
				model.setValueAt(price, jt.getSelectedRow(), 3);
				jf.setVisible(false);
				}catch(NumberFormatException e0) {
					JOptionPane.showMessageDialog(null, "��������ȷ�ĸ�ʽ��");
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
}}
}
