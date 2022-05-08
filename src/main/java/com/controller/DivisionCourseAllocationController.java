package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDao;
import com.dao.DivisionCourseAllocationDao;
import com.dao.OfferedCourseDao;
import com.model.DivisionCourseAllocation;

/**
 * Servlet implementation class DivisionCourseAllocationController
 */
@WebServlet("/DivisionCourseAllocationController")
public class DivisionCourseAllocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionCourseAllocationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String t = request.getParameter("term");
		String year = request.getParameter("year");

		String term = t + year;
		 String division=request.getParameter("division");

		String[] allotedCourseNames=request.getParameterValues("course");
		ArrayList<String> allotedCourseCodes=new ArrayList<String>();
		OfferedCourseDao ocdao=new OfferedCourseDao();
		System.out.println(allotedCourseNames.length);
		try { 
		for(int i=0;i<allotedCourseNames.length;i++) 
		{ 
			System.out.println(allotedCourseNames[i]);
			ResultSet rs=ocdao.getCourseCode(allotedCourseNames[i]);
			if(rs.next())
			{ 
				allotedCourseCodes.add(rs.getString(1)); 
			}

		} 
		}catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
		
		List<String> allotedCourseNameList=Arrays.asList(allotedCourseNames);
		DivisionCourseAllocation dca=new DivisionCourseAllocation(term, division, allotedCourseNameList, allotedCourseCodes);
		
		

		DivisionCourseAllocationDao dcad=new DivisionCourseAllocationDao();
		int i=dcad.insertAllotedCourses(dca);
		if(i>0)
		{
			session.setAttribute("allotedCourse-Success", "true");
			response.sendRedirect("DivisionCourseAllocation.jsp");
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
