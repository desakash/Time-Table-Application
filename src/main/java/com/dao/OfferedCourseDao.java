package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.OfferedCourses;

public class OfferedCourseDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	public int insertOfferedCourses(OfferedCourses ocs)
	{
		con=DbConnection.getConnection();
		for(int j=0;j<ocs.getCourseCodes().size();j++)
		{
			try {
				pstate=con.prepareStatement("insert into offered_course values(?,?)");
				pstate.setString(1, ocs.getTerm());
				pstate.setString(2, ocs.getCourseCodes().get(j));
				i=pstate.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return i;
	}

}
