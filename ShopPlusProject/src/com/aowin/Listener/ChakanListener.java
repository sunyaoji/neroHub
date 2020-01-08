package com.aowin.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.aowin.Model.Gouwuche;

public class ChakanListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Gouwuche gwc=new Gouwuche();
		try {
			gwc.build();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
