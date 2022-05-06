package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDao;
import com.dao.OfferedCourseDao;
import com.model.OfferedCourses;

/**
 * Servlet implementation class OfferedCourseController
 */
@WebServlet("/OfferedCourseController")
public class OfferedCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OfferedCourseController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String t = request.getParameter("term");
		String year = request.getParameter("year");

		String term = t + year;


		String[] courseName=request.getParameterValues("course");
		ArrayList<String> facultyCourseCodes=new ArrayList<String>();

		ResultSet rs=null; 
		CourseDao cdao=new CourseDao();
		try { 
		for(int i=0;i<courseName.length;i++) 
		{ 
			rs=cdao.getCourseCode(courseName[i]);
			if(rs.next())
			{ 
				facultyCourseCodes.add(rs.getString(1)); 
			}

		} 
		}catch(Exception e) 
		{ 
			e.printStackTrace(); 
		}
		
		OfferedCourses ocs=new OfferedCourses(term, facultyCourseCodes);
		OfferedCourseDao ocdao=new OfferedCourseDao();
		int j=ocdao.insertOfferedCourses(ocs);
		if(j>0)
		{
			session.setAttribute("offeredCourseSuccess", "true");
			response.sendRedirect("OfferedCourses.jsp");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
