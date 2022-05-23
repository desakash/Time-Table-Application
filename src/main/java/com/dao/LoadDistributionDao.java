package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.LoadDistribution;

public class LoadDistributionDao {
	
	Connection con=null;
	PreparedStatement pstate=null;
	int i=0;
	ResultSet rs=null;
	Statement st=null;
	
	
	public int DistributeLoad(LoadDistribution ld)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into load_distribution values (?,?,?,?,?,?,?,?,?,?,?,?)");
			pstate.setInt(1, ld.getFacultyId());
			pstate.setString(2, ld.getFacultyName());
			pstate.setString(3, ld.getDesignation());
			pstate.setString(4, ld.getDivision());
			pstate.setString(5, ld.getCourseCode());
			pstate.setString(6, ld.getCourseAbbr());
			pstate.setInt(7, ld.getTheory());
			pstate.setInt(8, ld.getPractical());
			pstate.setString(9, ld.getPracticalBatch());
			pstate.setInt(10, ld.getTutorial());
			pstate.setString(11, ld.getTutorialBatch());
			pstate.setInt(12, ld.getLoad());
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public ResultSet checkFacultyInTotalLoad(int facId)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("select *from total_load_distribution where fac_id=?");
			pstate.setInt(1, facId);
			rs=pstate.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	

	public int getTotalLoad(int facId)
	{
		con=DbConnection.getConnection();
		int totalLoad=0;
		try {
			pstate=con.prepareStatement("select * from load_distribution where faculty_id=?");
			pstate.setInt(1, facId);
			rs=pstate.executeQuery();
			while(rs.next())
			{
				totalLoad=totalLoad+rs.getInt(12);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalLoad;
	}
	
	public int insertTotalLoad(int facid,int totalLoad)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("insert into total_load_distribution values(?,?)");
			pstate.setInt(1, facid);
			pstate.setInt(2, totalLoad);
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	public int updateTotalLoad(int facId,int totalLoad)
	{
		con=DbConnection.getConnection();
		try {
			pstate=con.prepareStatement("update total_load_distribution set total_load=? where fac_id=?");
			pstate.setInt(1, totalLoad);
			pstate.setInt(2, facId);
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
}
