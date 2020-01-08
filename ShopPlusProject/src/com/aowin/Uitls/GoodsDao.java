package com.aowin.Uitls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






public class GoodsDao {
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;
	public static ArrayList<Goods> l1=new ArrayList<Goods>();
	public static ArrayList<Admin> l2=new ArrayList<Admin>();
	public ArrayList selectAll() throws SQLException{
		con=DbUtils.getConnction();
		st=con.createStatement();
		String sql="select * from shop";
		rs=st.executeQuery(sql);
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			int num=rs.getInt("number");
			double price=rs.getDouble("price");
			Goods s=new Goods(id,name,num,price);
			l1.add(s);
		}
		return l1;
	}
	public ArrayList selectadmin() throws SQLException{
		con=DbUtils.getConnction();
		st=con.createStatement();
		String sql="select * from admin";
		rs=st.executeQuery(sql);
		while(rs.next()) {
			String user=rs.getString("user");
			String password=rs.getString("password");
			Admin a=new Admin(user,password);
			l2.add(a);
		}
		return l2;
	}
	public void add(int ID,String NAME,int num,double price) throws SQLException {
		con=DbUtils.getConnction();
		st=con.createStatement();
		String sql="insert into shop values(?,?,?,?)";
		ps=con.prepareStatement(sql);
			ps.setInt(1,ID);
			ps.setString(2,NAME);
			ps.setInt(3,num);
			ps.setDouble(4,price);
			ps.executeUpdate();
	}
	
	public void deleteall()throws SQLException {
		con=DbUtils.getConnction();
		String sql="delete from shop";
		ps=con.prepareStatement(sql);
		int rows=ps.executeUpdate();
	}
	public void delete(int ID) throws SQLException {
		con=DbUtils.getConnction();
		String sql="delete from shop where id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, ID);
		int rows=ps.executeUpdate();
	}
}