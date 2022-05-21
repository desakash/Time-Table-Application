package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Slot;

public class SlotDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	public ResultSet checkSlot(String day,String fromtime,String totime)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from slot_master where day=? and fromtime=? and totime=?");
			pstate.setString(1, day);
			pstate.setString(2, fromtime);
			pstate.setString(3, totime);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public int createSlot(Slot s)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into slot_master values(?,?,?)");
			pstate.setString(1,s.getDay());
			pstate.setString(2, s.getFromtime());
			pstate.setString(3, s.getTotime());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet getAllSlots()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select *from slot_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
