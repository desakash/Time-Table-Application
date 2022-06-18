package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

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
		con=DbConnection.getConnection();
		try {
			pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
			pstat.setString(1, ttg.getDay());
			pstat.setString(2, ttg.getFromtime());
			pstat.setString(3, ttg.getTotime());
			rs=pstat.executeQuery();
			while(rs.next())
			{
				if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
				{
					return false;	
				}
				if(ttg.getHead().equals("Theory"))
				{
					if(ttg.getDivision().equals(rs.getString(1)))
					{
						return false;
					}
				}
				if(ttg.getHead().equals("Practical"))
				{
					if(ttg.getDivision().equals(rs.getString(1)))
					{
						if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
						{
							return false;
						}
					}
				}
				if(ttg.getHead().equals("Tutorial"))
				{
					if(ttg.getDivision().equals(rs.getString(1)))
					{
						if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
						{
							return false;
						}
					}
					
				}
				if(ttg.getHead().equals("Break"))
				{
					if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
					{
						return false;
					}
				}
			}
			
			if((Integer.parseInt(ttg.getTotime().substring(0, 2)))-(Integer.parseInt(ttg.getFromtime().substring(0,2)))>1)
			{
				//-1 time check
				
				int newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2))-1;
				int newToTime=Integer.parseInt(ttg.getTotime().substring(0,2))-1;
				
				System.out.println("new from time with minus 1 :"+newFromTime);
				System.out.println("new to time with minus 1 :"+newToTime);

				
				pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
				pstat.setString(1, ttg.getDay());
				pstat.setString(2,Integer.toString(newFromTime)+":00" );
				pstat.setString(3, Integer.toString(newToTime)+":00");
				rs=pstat.executeQuery();
				while(rs.next())
				{
					if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
					{
						return false;
					}
					if(ttg.getHead().equals("Theory"))
					{
						if(ttg.getDivision().equals(rs.getString(1)))
						{
							return false;
						}
					}
					if(ttg.getHead().equals("Practical"))
					{
						if(ttg.getDivision().equals(rs.getString(1)))
						{
							if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
							{
								return false;
							}
						}
						
					}
					if(ttg.getHead().equals("Tutorial"))
					{
						if(ttg.getDivision().equals(rs.getString(1)) )
						{
							if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
							{
								return false;
							}
						}
						
					}
					if(ttg.getHead().equals("Break"))
					{
						if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
						{
							return false;
						}
					}
				}
				
				
				//+1 time check
				
						newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2))+1;
						newToTime=Integer.parseInt(ttg.getTotime().substring(0,2))+1;
						
						System.out.println("new from time with PLUS 1 :"+newFromTime);
						System.out.println("new to time with PLUS 1 :"+newToTime);
						
						pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
						pstat.setString(1, ttg.getDay());
						pstat.setString(2,Integer.toString(newFromTime)+":00" );
						pstat.setString(3, Integer.toString(newToTime)+":00");
						rs=pstat.executeQuery();
						while(rs.next())
						{
							if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
							{
								return false;
							}
							if(ttg.getHead().equals("Theory"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									return false;
								}
							}
							if(ttg.getHead().equals("Practical"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Tutorial"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Break"))
							{
								if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
								{
									return false;
								}
							}
						}
						
						//from time same to time +1
						newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2));
						newToTime=Integer.parseInt(ttg.getTotime().substring(0,2))+1;
						
						System.out.println("new from time with PLUS 1 :"+newFromTime);
						System.out.println("new to time with PLUS 1 :"+newToTime);
						
						pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
						pstat.setString(1, ttg.getDay());
						pstat.setString(2,Integer.toString(newFromTime)+":00" );
						pstat.setString(3, Integer.toString(newToTime)+":00");
						rs=pstat.executeQuery();
						while(rs.next())
						{
							if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
							{
								return false;
							}
							if(ttg.getHead().equals("Theory"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									return false;
								}
							}
							if(ttg.getHead().equals("Practical"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Tutorial"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Break"))
							{
								if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
								{
									return false;
								}
							}
						}
						
						//to time same from time +1
						newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2))+1;
						newToTime=Integer.parseInt(ttg.getTotime().substring(0,2));
						
						System.out.println("new from time with PLUS 1 :"+newFromTime);
						System.out.println("new to time with PLUS 1 :"+newToTime);
						
						pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
						pstat.setString(1, ttg.getDay());
						pstat.setString(2,Integer.toString(newFromTime)+":00" );
						pstat.setString(3, Integer.toString(newToTime)+":00");
						rs=pstat.executeQuery();
						while(rs.next())
						{
							if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
							{
								return false;
							}
							if(ttg.getHead().equals("Theory"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									return false;
								}
							}
							if(ttg.getHead().equals("Practical"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Tutorial"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Break"))
							{
								if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
								{
									return false;
								}
							}
						}
						
						//from time same to time -1
						newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2));
						newToTime=Integer.parseInt(ttg.getTotime().substring(0,2))-1;
						
						System.out.println("new from time with PLUS 1 :"+newFromTime);
						System.out.println("new to time with PLUS 1 :"+newToTime);
						
						pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
						pstat.setString(1, ttg.getDay());
						pstat.setString(2,Integer.toString(newFromTime)+":00" );
						pstat.setString(3, Integer.toString(newToTime)+":00");
						rs=pstat.executeQuery();
						while(rs.next())
						{
							if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
							{
								return false;
							}
							if(ttg.getHead().equals("Theory"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									return false;
								}
							}
							if(ttg.getHead().equals("Practical"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Tutorial"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Break"))
							{
								if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
								{
									return false;
								}
							}
						}	
						
						
						//to time same from time -1
						newFromTime=Integer.parseInt(ttg.getFromtime().substring(0,2))-1;
						newToTime=Integer.parseInt(ttg.getTotime().substring(0,2));	
						
						System.out.println("new from time with PLUS 1 :"+newFromTime);
						System.out.println("new to time with PLUS 1 :"+newToTime);
						
						pstat=con.prepareStatement("select *from timetable_generation where day=? and fromtime=? and totime=?");
						pstat.setString(1, ttg.getDay());
						pstat.setString(2,Integer.toString(newFromTime)+":00" );
						pstat.setString(3, Integer.toString(newToTime)+":00");
						rs=pstat.executeQuery();
						while(rs.next())
						{
							if(ttg.getClassroom().equals(rs.getString(10))||ttg.getFaculty().equals(rs.getString(7)))
							{
								return false;
							}
							if(ttg.getHead().equals("Theory"))
							{
								if(ttg.getDivision().equals(rs.getString(1)))
								{
									return false;
								}
							}
							if(ttg.getHead().equals("Practical"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getPractical_batch().equals(rs.getString(8))||ttg.getTutorial_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Tutorial"))
							{
								if(ttg.getDivision().equals(rs.getString(1)) )
								{
									if(ttg.getTutorial_batch().equals(rs.getString(9))||ttg.getPractical_batch()!=null)
									{
										return false;
									}
								}
								
							}
							if(ttg.getHead().equals("Break"))
							{
								if(ttg.getDivision().equals(rs.getString(1))||ttg.getPractical_batch()!=null||ttg.getTutorial_batch()!=null)
								{
									return false;
								}
							}
						}
			
			}
						
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return true;
		
		/*
		 * try { con=DbConnection.getConnection(); pstat=con.
		 * prepareStatement("select * from timetable_generation where DAY=? and FROMTIME=? and TOTIME=? and division=?"
		 * );
		 * 
		 * pstat.setString(1,ttg.getDay()); pstat.setString(2,ttg.getFromtime());
		 * pstat.setString(3,ttg.getTotime()); pstat.setString(4,ttg.getDivision());
		 * 
		 * rs=pstat.executeQuery(); if(rs.next()) { String day=rs.getString(4); String
		 * faculty=rs.getString(7); String classroom=rs.getString(10); String
		 * fromtime=rs.getString(5); String totime=rs.getString(6); String
		 * division=rs.getString(1);
		 * 
		 * if(faculty.equals(ttg.getFaculty()) || classroom.equals(ttg.getClassroom())
		 * || division.equals(ttg.getDivision())&&(fromtime.equals(ttg.getFromtime()) ||
		 * totime.equals(ttg.getTotime())))
		 * 
		 * 
		 * {
		 * 
		 * return false; } }
		 * 
		 * 
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * return true;
		 */
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
	
	public boolean checkLoadTimetable(TimeTableGenerationn ttg)
	{
		con=DbConnection.getConnection();
		int head=0;
		try {
			pstat=con.prepareStatement("select *from load_distribution where division=? and course_code=? and "+ttg.getHead()+">0");
			pstat.setString(1, ttg.getDivision());
			pstat.setString(2, ttg.getOffered_courses().substring(0, 6));
			System.out.println("select *from load_distribution where division="+ttg.getDivision()+" and course_code="+ttg.getOffered_courses().substring(0, 6)+" and "+ttg.getHead()+">0");
			rs=pstat.executeQuery();
			System.out.println(ttg.getHead());
			if(rs==null)
			{
				System.out.println("rs is null");
			}
			if(rs.next())
			{
				System.out.println("inside firs rs");
				if(ttg.getHead().equals("Theory"))
				{
					head=rs.getInt(7);
					
				}
				else if(ttg.getHead().equals("Practical"))
				{
					head=rs.getInt(8);
					

				}
				else if(ttg.getHead().equals("Tutorial"))
				{
					head=rs.getInt(9);
					
				}
			}
			if(ttg.getHead().equals("Theory"))
			{
				System.out.println("head in theory"+head);
				pstat=con.prepareStatement("select *from timetable_generation where division=? and course=? and head=?");
				pstat.setString(1, ttg.getDivision());
				pstat.setString(2, ttg.getOffered_courses());
				pstat.setString(3, ttg.getHead());
				
				rs=pstat.executeQuery();
				
				if(rs.next())
				{
					
					System.out.println((Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2))));
					int res=(Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2)));
					if(res==head)
					{
						return false;
						
					}
					else if((head-res)<(Integer.parseInt(ttg.getTotime().substring(0,2))-Integer.parseInt(ttg.getFromtime().substring(0, 2))))
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					return true;
				}
			}
			else if(ttg.getHead().equals("Practical"))
			{
				System.out.println("head in practical"+head);
				pstat=con.prepareStatement("select *from timetable_generation where division=? and course=? and head=? and practical_batch=?");
				pstat.setString(1, ttg.getDivision());
				pstat.setString(2, ttg.getOffered_courses());
				pstat.setString(3, ttg.getHead());
				pstat.setString(4, ttg.getPractical_batch());
				
				rs=pstat.executeQuery();
				
				if(rs.next())
				{
					System.out.println("inside rs of pratical");
					System.out.println((Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2))));
					int res=(Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2)));
					if(res==head)
					{
						return false;
						
					}
					else if((head-res)<(Integer.parseInt(ttg.getTotime().substring(0,2))-Integer.parseInt(ttg.getFromtime().substring(0, 2))))
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					System.out.println("inside else of practical");
					return true;
				}
			}
			else if(ttg.getHead().equals("Tutorial"))
			{
				System.out.println("head in tutorial"+head);
				pstat=con.prepareStatement("select *from timetable_generation where division=? and course=? and head=? and tutorial_batch=?");
				pstat.setString(1, ttg.getDivision());
				pstat.setString(2, ttg.getOffered_courses());
				pstat.setString(3, ttg.getHead());
				pstat.setString(4, ttg.getTutorial_batch());
				
				rs=pstat.executeQuery();
				
				if(rs.next())
				{
					
					System.out.println((Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2))));
					int res=(Integer.parseInt(rs.getString(6).substring(0, 2)))-(Integer.parseInt(rs.getString(5).substring(0,2)));
					if(res==head)
					{
						return false;
						
					}
					else if((head-res)<(Integer.parseInt(ttg.getTotime().substring(0,2))-Integer.parseInt(ttg.getFromtime().substring(0, 2))))
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				else
				{
					return true;
				}
			}
			
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
	
	public ResultSet getTimeTable(String division)
	{
		con=DbConnection.getConnection();
		try {
			pstat=con.prepareStatement("select *from timetable_generation where division=?");
			pstat.setString(1, division);
			
			rs=pstat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getTimeTableByDay(String div, String day)
	{
		System.out.println("hello in getTimetable by day");
		con=DbConnection.getConnection();
		try {
			pstat=con.prepareStatement("select *from timetable_generation where division=? and day=? order by(fromtime)");
			pstat.setString(1, div);
			pstat.setString(2, day);
			rs=pstat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public ResultSet getDuplicateRow(String ft,String tt,String day)
	{
		System.out.println("hello hello");
		con=DbConnection.getConnection();
		try {
			pstat=con.prepareStatement("select *from timetable_generation where fromtime=? and totime=? and day=?");
			pstat.setString(1, ft);
			pstat.setString(2, tt);
			pstat.setString(3, day);
			rs=pstat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
				
	}

}
