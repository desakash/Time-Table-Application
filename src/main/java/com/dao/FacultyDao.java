package com.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Faculty;

public class FacultyDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;

	public int createFaculty(Faculty fc)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into faculty_master values(?,?,?,?,?,?)");
			pstate.setInt(1, fc.getFacultyId());
			pstate.setString(2, fc.getFacultyName());
			pstate.setString(3, fc.getFacultyAbbr());
			pstate.setString(4, fc.getFacultyEmail());
			pstate.setString(5, fc.getFacultyDesignation());
			pstate.setString(6, fc.getFacultyType());

			i=pstate.executeUpdate();
			
//			for(int j=0;j<fc.getFacultyCourseCodes().size();j++)
//			{
//				pstate=con.prepareStatement("insert into faculty_courses values(?,?)");
//				pstate.setInt(1, fc.getFacultyId());
//				pstate.setString(2, fc.getFacultyCourseCodes().get(j));
//				i=pstate.executeUpdate();
//			}
//			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public ResultSet getFacultyDetails()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select *from faculty_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
