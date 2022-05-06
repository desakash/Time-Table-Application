package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Course;

public class CourseDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;

	public int createCourse(Course cou)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into course_master values(?,?,?,?,?,?,?)");
			pstate.setString(1, cou.getCourse_code());
			pstate.setString(2, cou.getCourse_name());
			pstate.setString(3, cou.getCourse_abbr());
			pstate.setInt(4, cou.getYear());
			pstate.setInt(5, cou.getNo_of_theory());
			pstate.setInt(6, cou.getNo_of_pract());
			pstate.setInt(7, cou.getNo_of_tuts());
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	public ResultSet getCourseName()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select course_name from course_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getCourseCode(String courseName)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select course_code from course_master where course_name=?");
			pstate.setString(1, courseName);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getCourseDetails()
	{
		con=DbConnection.getConnection();
		try {
			st=con.createStatement();
			rs=st.executeQuery("select *from course_master");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
