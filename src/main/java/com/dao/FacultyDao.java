package com.dao;

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
	
	public int deleteFaculty(int facultyId)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("delete from faculty_master where faculty_id=?");
			pstate.setInt(1, facultyId);
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public int update_faculty(String faculty_name,String faculty_abbr,String faculty_email,String faculty_designation,String faculty_type, int faculty_id)
	{
		con=DbConnection.getConnection();
		
		try {
			pstate=con.prepareStatement("update faculty_master set faculty_name=?,faculty_abbreviation=?,faculty_email=?,faculty_designation=?,faculty_type=? where faculty_id=?");
			pstate.setString(1, faculty_name);
			pstate.setString(2, faculty_abbr);
			pstate.setString(3, faculty_email);
			pstate.setString(4, faculty_designation);
			pstate.setString(5, faculty_type);
			pstate.setInt(6, faculty_id);
			i=pstate.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public ResultSet getFacultyDetailsbyid(int facultyId)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from faculty_master where faculty_id=?");
			pstate.setInt(1,facultyId);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getFacultyDesigByName(String facultyName)
	{
		con=DbConnection.getConnection();
		
		try {
			pstate=con.prepareStatement("select faculty_designation from faculty_master where faculty_name=?");
			pstate.setString(1, facultyName);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getFacultyIdByName(String facultyName)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select faculty_id from faculty_master where faculty_name=?");
			pstate.setString(1, facultyName);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
}
