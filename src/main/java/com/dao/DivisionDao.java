package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Division;

public class DivisionDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	public int create_division(Division d)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into division_master values(?,?,?,?,?)");
			pstate.setInt(1, d.getDivision_id());
			pstate.setString(2, d.getDivision_name());
			pstate.setInt(3,d.getYear() );
			pstate.setInt(4, d.getNo_of_practicals());
			pstate.setInt(5, d.getNo_of_tutorials());
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public ResultSet getDivisionDetails()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select *from division_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getDivisionNames()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select division_name from division_master");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
