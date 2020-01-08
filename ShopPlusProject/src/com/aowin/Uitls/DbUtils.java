package com.aowin.Uitls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	public static Connection getConnction() {
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/aowin";
		String user="root";
		String password="123456";
		String bao="com.mysql.jdbc.Driver";
		try {
			Class.forName(bao);
		} catch (ClassNotFoundException e) {
			System.out.println("获取包未成功！");
		}
		try {
			con=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("加载未成功！");
		}
		return con;
}
	public static void close(Connection con,Statement st,ResultSet rs) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
}