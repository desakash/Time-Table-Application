package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Class;

public class ClassDao {
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	public int create_Class(Class c)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into class_master values(?,?,?,?,?)");
			pstate.setInt(1, c.getClassId());
			pstate.setString(2, c.getClassName());
			pstate.setString(3, c.getTyeOfClass());
			pstate.setInt(4, c.getBuildingId());
			pstate.setInt(5, c.getFloor());
			System.out.println(c.getFloor());
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet getClassDetails()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select *from class_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}


}
