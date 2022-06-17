package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.TimeTableGenerationn;

public class TimeTableGenerationDao {
	
	PreparedStatement pstat=null;
	Connection con=null;
	ResultSet rs=null;
	int i=0;
	public int insert(TimeTableGenerationn ttg)
	{
		con=DbConnection.getConnection();
		try {
			pstat=con.prepareStatement("insert into timetable_generation values(?,?,?,?,?,?,?,?,?,?)");
			pstat.setString(1,ttg.getDivision());
			pstat.setString(2,ttg.getOffered_courses());
			pstat.setString(3,ttg.getHead());
			pstat.setString(4,ttg.getDay());
			pstat.setString(5,ttg.getFromtime());
			pstat.setString(6,ttg.getTotime());
			pstat.setString(7,ttg.getFaculty());
			pstat.setString(8,ttg.getPractical_batch());
			pstat.setString(9,ttg.getTutorial_batch());
			pstat.setString(10,ttg.getClassroom());
			
			i=pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public boolean check(TimeTableGenerationn ttg)
	{	
		try {
			con=DbConnection.getConnection();
		pstat=con.prepareStatement("select * from timetable_generation where DAY=? and FROMTIME=? and TOTIME=? and division=?");
	
			pstat.setString(1,ttg.getDay());
			pstat.setString(2,ttg.getFromtime());
			pstat.setString(3,ttg.getTotime());
			pstat.setString(4,ttg.getDivision());
			
			rs=pstat.executeQuery();
			if(rs.next())
			{
				String day=rs.getString(4);
				String faculty=rs.getString(7);
				String classroom=rs.getString(8);
				String fromtime=rs.getString(5);
				String totime=rs.getString(6);
				String division=rs.getString(1);
				
				if(faculty.equals(ttg.getFaculty()) || classroom.equals(ttg.getClassroom()) || division.equals(ttg.getDivision())&&(fromtime.equals(ttg.getFromtime()) || totime.equals(ttg.getTotime())))
						
				
				{
					
					return false;
				}
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public ResultSet displayFaculty(String division,String course,String head)
	{
		con=DbConnection.getConnection();
		
		try {
			System.out.println("select * from load_distribution where division=? and course_code=? and"+head+">0");
			pstat=con.prepareStatement("select * from load_distribution where division=? and course_code=? and "+head+">0");
			pstat.setString(1,division);
			pstat.setString(2,course);
			
			rs=pstat.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return rs;
	}
	
	public ResultSet displayBatches(String division,String course,String faculty)
	{
	
		con=DbConnection.getConnection();
		System.out.println("Inside displaybatch dao");
		System.out.println(division+"\t"+course+"\t"+faculty);

		try {
			pstat=con.prepareStatement("select * from load_distribution where division=? and course_code=? and faculty_name=?");
			pstat.setString(1,division);
			pstat.setString(2,course);
			pstat.setString(3,faculty);
			
			rs=pstat.executeQuery();
//			if(rs.next())
//			{
//				System.out.println(rs.getString(9));
//	
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		return rs;
		
	}
	
	
	public ResultSet displayTutorialBatches(String division,String course,String faculty)
	{
	
		con=DbConnection.getConnection();
		System.out.println("Inside displaybatch dao");
		System.out.println(division+"\t"+course+"\t"+faculty);

		try {
			pstat=con.prepareStatement("select * from load_distribution where division=? and course_code=? and faculty_name=?");
			pstat.setString(1,division);
			pstat.setString(2,course);
			pstat.setString(3,faculty);
			
			rs=pstat.executeQuery();
//			if(rs.next())
//			{
//				System.out.println(rs.getString(9));
//	
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		return rs;
		
	}
	/*
	 * public int update(String day,String fromtime,String totime) {
	 * System.out.println("inside update method"); con=DbConnection.getConnection();
	 * try { pstat=con.
	 * prepareStatement("update slot_master set FROMTIME_STATUS=1,TOTIME_STATUS=1 where day=? and FROMTIME=? and TOTIME=? "
	 * ); pstat.setString(1,day); pstat.setString(2,fromtime); pstat.setString(3,
	 * totime);
	 * 
	 * i=pstat.executeUpdate(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return i;
	 * 
	 * }
	 */

}
