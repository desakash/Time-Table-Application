package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDao;
import com.dao.FacultyDao;
import com.dao.LoadDistributionDao;
import com.model.LoadDistribution;

/**
 * Servlet implementation class LoadDistributionController
 */
@WebServlet("/LoadDistributionController")
public class LoadDistributionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDistributionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		
		String facultyName=request.getParameter("faculty_name");
		
		int faculty_id=0;
		String facultyDesignation=null;
		
		FacultyDao fdao=new FacultyDao();
		rs=fdao.getFacultyDesigByName(facultyName);
		try {
			if(rs.next())
			{
				facultyDesignation=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String divisionName=request.getParameter("division_name");
		String courseCode=request.getParameter("offered_course").substring(0, 5);
		String courseAbbr=null;
		
		CourseDao cdao=new CourseDao();
		rs=cdao.getCourseAbbrByCourseCode(courseCode);
		try {
			if(rs.next())
			{
				courseAbbr=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String theory=request.getParameter("theory");
		int practical=Integer.parseInt(request.getParameter("practical"));
		int tutorial=Integer.parseInt(request.getParameter("tutorial"));
		int th=0;
		rs=cdao.getCourseDetailsById(courseCode);
		try {
			if(rs.next())
			{
				if(theory.equals("yes"))
				{
					th=rs.getInt(5);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String A_Batch=request.getParameter("a");
		String B_Batch=request.getParameter("b");
		String C_Batch=request.getParameter("c");
		
		int practicalCount=0;
		if(A_Batch.equals("on"))
		{
			sb.append("A Batch");
			practicalCount++;
		}
		if(B_Batch.equals("on"))
		{
			sb.append(", B Batch");
			practicalCount++;
		}
		if(C_Batch.equals("on"))
		{
			sb.append(", C Batch");
			practicalCount++;
		}
		System.out.println(practicalCount);
		System.out.println("batches : "+sb);
		
		String t1=request.getParameter("t1");
		String t2=request.getParameter("t2");
		String t3=request.getParameter("t3");
		
		int tutorialCount=0;
		if(t1.equals("on"))
		{
			sb2.append("T1 Batch");
			tutorialCount++;
		}
		if(t2.equals("on"))
		{
			sb2.append(", T2 Batch");
			tutorialCount++;
		}
		if(t3.equals("on"))
		{
			sb.append(", T3 Batch");
			tutorialCount++;
		}
		System.out.println(tutorialCount);
		System.out.println("batches : "+sb2);
		
		String practicalBatches=sb.toString();
		String tutorialBatches=sb2.toString();
		
		
		int load=(practical*practicalCount)+th+(tutorial*tutorialCount);
		
		LoadDistribution ld=new LoadDistribution(facultyName, facultyDesignation, divisionName, courseCode, courseAbbr, th, practical, practicalBatches, tutorial,tutorialBatches, load);
		
		LoadDistributionDao lddao=new LoadDistributionDao();
		int i=lddao.DistributeLoad(ld);
		if(i>0)
		{
			
		}
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
