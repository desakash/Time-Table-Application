package com.controller.EditControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CourseDao;

/**
 * Servlet implementation class EditCourseController
 */
@WebServlet("/EditCourseController")
public class EditCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		String courseId=request.getParameter("courseId");
		String courseCode=request.getParameter("course_code");
		String courseName=request.getParameter("course_name");
		String courseAbbr=request.getParameter("course_abbr");
		String year=request.getParameter("year");
		
		int yearInt=0;
		if(year.equals("First Year"))
		{
			yearInt=1;
		}
		if(year.equals("Second Year"))
		{
			yearInt=2;
		}
		if(year.equals("Third Year"))
		{
			yearInt=3;
		}
		
		int noOfTheory=Integer.parseInt(request.getParameter("no_of_theory"));
		int noOfPractical=Integer.parseInt(request.getParameter("no_of_pract"));
		int noOfTutorials=Integer.parseInt(request.getParameter("no_of_tuts"));

		CourseDao cd=new CourseDao();
		int i=cd.updateCourse(courseCode,courseName,courseAbbr,yearInt,noOfTheory,noOfPractical,noOfTutorials,courseId);
		
		if(i>0)
		{
			
			HttpSession session=request.getSession();
			
			session.setAttribute("update-msg", "true");
			
			response.sendRedirect("CourseDetails.jsp");
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
