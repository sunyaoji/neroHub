package com.aowin.Model;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.aowin.Listener.Inlistener;


public class Denglu {
	public static void main(String[] args) {
		JFrame jf=new JFrame("欢迎来到用户登录界面！");
		jf.setSize(400, 300);
		//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con=jf.getContentPane();
		con.setLayout(new FlowLayout());
		JLabel jl1=new JLabel("用户名:");
		JTextField jtf1=new JTextField(12);
		con.add(jl1);
		con.add(jtf1);
		JLabel jl2=new JLabel(" 密码:");
		JPasswordField jp1=new JPasswordField(12);
		jp1.setEchoChar('*');
		con.add(jl2);
		con.add(jp1);
		JButton jb1=new JButton("登录");
		con.add(jb1);
		JButton jb2=new JButton("游客登录");
		con.add(jb2);
		JLabel jl3=new JLabel("账号密码输入有误，请重新输入账号密码！");
		con.add(jl3);
		jl3.setVisible(false);
		Inlistener in=new Inlistener(jf,jtf1,jp1,jl3);
		jb1.addActionListener(in);
		jb2.addActionListener(in);
		jf.setVisible(true);
	}
}
