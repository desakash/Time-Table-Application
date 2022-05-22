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

}
