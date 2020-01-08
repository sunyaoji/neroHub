package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aowin.Model.Guanli;
import com.aowin.Model.Youke;
import com.aowin.Uitls.Admin;
import com.aowin.Uitls.GoodsDao;


public class Inlistener implements ActionListener{
   private JFrame jf=new JFrame();
   private JTextField jtf1=new JTextField();
   private JPasswordField jp2=new JPasswordField();
   private String reg1="";
   private String reg2="";
   JLabel jl3;
   public Inlistener(JFrame jf,JTextField jtf1,JPasswordField jp2,JLabel jl) {
	   this.jf=jf;
	   this.jtf1=jtf1;
	   this.jp2=jp2;
	   this.jl3=jl;
   }
	public void actionPerformed(ActionEvent e) {
		int y=0;
		if(e.getActionCommand().equals("登录")) {
		GoodsDao gds=new GoodsDao();
		try {
			ArrayList<Admin> al=gds.selectadmin();
			for(int i=0;i<al.size();i++) {
				reg1=al.get(i).getUser();
				reg2=al.get(i).getPassword();
				if(jtf1.getText().matches(reg1)) {
					if(jp2.getText().matches(reg2)) {
				if(JOptionPane.showConfirmDialog(null, "确定登入吗？")==0) {
					Guanli g=new Guanli();
					g.build();
					jf.setVisible(false);
					y=1;
					break;
					}}}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(y==0) {
			jl3.setVisible(true);
			jtf1.setText("");
			jp2.setText("");
		}
}
		if(e.getActionCommand().equals("游客登录")){
			Youke yk=new Youke();
			try {
				yk.build();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jf.setVisible(false);
		}
}
}