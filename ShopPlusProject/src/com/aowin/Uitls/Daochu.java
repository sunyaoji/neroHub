package com.aowin.Uitls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.aowin.Listener.Gouwuchejiaru;
import com.aowin.Model.Gouwuche;

public class Daochu {
	static double end;
	public Daochu(double end) {
		this.end=end;
	}
public static void main(String[] args){
}
public void xieru()  throws IOException{
	boolean flag = true;
	File f=null;
	BufferedWriter bw = null;
	try {
		f = new File("src/user.txt");
		bw = new BufferedWriter(new FileWriter(f));
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:");
		d.getTime();
		String time=df.format(d);
		bw.write(time+"\n");
		for (int i=0;i<Gouwuchejiaru.al1.size();i++) {
			int id=Gouwuchejiaru.al1.get(i).getId();
			String name=Gouwuchejiaru.al1.get(i).getName();
			int num=Gouwuchejiaru.al1.get(i).getNum();
			double price=Gouwuchejiaru.al1.get(i).getPrice();
			String wr="编号:"+id+"商品名:"+name+"数量:"+num+"价格:"+price;
			bw.write(wr+"\n");
		}
		bw.write("消费金额为:"+end+"\n");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "写入文件失败！");
	} finally {
		if (bw != null) {
			bw.flush();
			bw.close();
			JOptionPane.showMessageDialog(null, "写入文件成功！");
		}
}
}
}
