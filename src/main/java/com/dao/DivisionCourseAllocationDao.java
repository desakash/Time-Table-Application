package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.DivisionCourseAllocation;

public class DivisionCourseAllocationDao {
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	public int insertAllotedCourses(DivisionCourseAllocation dca)
	{
		con=DbConnection.getConnection();
		
		System.out.println("size"+dca.getAllotedCourseCodes().size());
		for(int j=0;j<dca.getAllotedCourseCodes().size();j++)
		{
			try {
				pstate=con.prepareStatement("insert into division_course_allocation values(?,?,?,?)");
				pstate.setString(1, dca.getTerm());
				pstate.setString(2, dca.getDivision());
				pstate.setString(3, dca.getAllotedCourseCodes().get(j));
				pstate.setString(4, dca.getAllotedCourseNames().get(j));
				
				
				i=pstate.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}

}
