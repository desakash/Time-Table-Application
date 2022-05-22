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
			pstate=con.prepareStatement("insert into load_distribution values (?,?,?,?,?,?,?,?,?,?,?)");
			pstate.setString(1, ld.getFacultyName());
			pstate.setString(2, ld.getDesignation());
			pstate.setString(3, ld.getDivision());
			pstate.setString(4, ld.getCourseCode());
			pstate.setString(5, ld.getCourseAbbr());
			pstate.setInt(6, ld.getTheory());
			pstate.setInt(7, ld.getPractical());
			pstate.setString(8, ld.getPracticalBatch());
			pstate.setInt(9, ld.getTutorial());
			pstate.setString(10, ld.getTutorialBatch());
			pstate.setInt(11, ld.getLoad());
			
			i=pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

}
